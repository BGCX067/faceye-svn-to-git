/*
 * Ext JS Library 2.0.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

MainPanel = function(){
    this.preview = new Ext.Panel({
        id: 'preview',
        region: 'south',
        cls:'preview',
        autoScroll: true,
        listeners: FeedViewer.LinkInterceptor,
        tbar: [{
            id:'tab',
            text: '在新标签中查看',
            iconCls: 'new-tab',
            disabled:true,
            handler : this.openTab,
            scope: this
        },
        '-',
        {
            id:'win',
            text: '查看原文',
            iconCls: 'new-win',
            disabled:true,
            scope: this,
            handler : function(){
                window.open(this.gsm.getSelected().data.link);
            }
        }],

        clear: function(){
            this.body.update('');
            var items = this.topToolbar.items;
            items.get('tab').disable();
            items.get('win').disable();
        }
    });

    this.grid = new FeedGrid(this, {
        tbar:[{
            text:'全部展开',
            tooltip: {title:'全部展开',text:'在新标签中展开所有文章'},
            iconCls: 'tabs',
            handler: this.openAll,
            scope:this
        },
        '-',
        {
            split:true,
            text:'阅读面板',
            tooltip: {title:'阅读面板',text:'展开, 移动或隐藏阅读面板'},
            iconCls: 'preview-bottom',
            handler: this.movePreview.createDelegate(this, []),
            menu:{
                id:'reading-menu',
                cls:'reading-menu',
                width:100,
                items: [{
                    id:'bottom',
                    text:'在底部显示',
                    checked:false,
                    group:'rp-group',
                    checkHandler:this.movePreview,
                    scope:this,
                    iconCls:'preview-bottom'
                },{  
                    id:'right',
                    text:'在右侧显示',
                    checked:false,
                    group:'rp-group',
                    checkHandler:this.movePreview,
                    scope:this,
                    iconCls:'preview-right'
                },{
                    id:'hide',
                    text:'隐藏',
                    checked:true,
                    group:'rp-group',
                    checkHandler:this.movePreview,
                    scope:this,
                    iconCls:'preview-hide'
                }]
            }
        },
         '-',
        {
            pressed: true,
            id:'article-button-title-only',
            enableToggle:true,
            text:'只看标题',
            tooltip: {title:'只看标题',text:'在列表中查看每篇的文章的标题'},
            iconCls: 'summary',
            scope:this,
            toggleHandler: function(btn, pressed){
                this.grid.togglePreview(0);
                onArticleButtonClick(0);
            }
        },
        '-',
        {
        	id:'article-button-summary',
            pressed: false,
            enableToggle:true,
            text:'摘要',
            tooltip: {title:'文章摘要',text:'在列表中查看每篇的文章的短小摘要'},
            iconCls: 'summary',
            scope:this,
            toggleHandler: function(btn, pressed){
                this.grid.togglePreview(1);
                onArticleButtonClick(1);
            }
        },
        '-',
        {
        	id:'button-article-full',
            pressed: false,
            enableToggle:true,
            text:'全文',
            tooltip: {title:'文章全文',text:'在列表中查看每篇的文章的全文'},
            iconCls: 'summary',
            scope:this,
            toggleHandler: function(btn, pressed){
                this.grid.togglePreview(2);
                onArticleButtonClick(2);
            }
        }]
    });
    /**
     * 当点击时触发本事件，改变其它button的状态。
     */
function onArticleButtonClick(arg0){
	var titleButton=Ext.getCmp('article-button-title-only');
	var summaryButton=Ext.getCmp('article-button-summary');
	var fullButton=Ext.getCmp('button-article-full');
	if(arg0==0){
		titleButton.disable();
		 if(summaryButton.disabled){
		 	summaryButton.enable();
		 }
		 if(fullButton.disabled){
		 	fullButton.enable();
		 }
	}
	if(arg0==1){
		summaryButton.disable();
		if(titleButton.disabled){
			titleButton.enable();
		}
		if(fullButton.disabled){
			fullButton.enable();
		}
	}
	if(arg0==2){
		fullButton.disable();
		if(titleButton.disabled){
			titleButton.enable();
		}
		if(summaryButton.disabled){
			summaryButton.enable();
		}
	}
};
    MainPanel.superclass.constructor.call(this, {
        id:'main-tabs',
        activeTab:0,
        region:'center',
        margins:'0 5 5 0',
        resizeTabs:true,
        border:false,
        tabWidth:150,
        minTabWidth: 120,
        enableTabScroll: true,
        plugins: new Ext.ux.TabCloseMenu(),
        items: {
            id:'main-view',
            layout:'border',
            title:'正在加载...',
            hideMode:'offsets',
            items:[
                this.grid, {
                id:'bottom-preview',
                layout:'fit',
                hidden:true,
                height: 250,
                split: true,
                border:false,
                region:'south'
            }, {
                id:'right-preview',
                layout:'fit',
                border:false,
                region:'east',
                width:550,
                items:this.preview,
                split: true,
                hidden:true
            }]
        }
    });
    this.gsm = this.grid.getSelectionModel();
    this.gsm.on('rowselect', function(sm, index, record){
//    	alert(record.data['title']);
//    	alert(sm);
//    	alert(this.gsm);
        FeedViewer.getTemplate().overwrite(this.preview.body, record.data);
//        alert(this.preview);
        var items = this.preview.topToolbar.items;
//        alert(1);
        items.get('tab').enable();
        items.get('win').enable();
    }, this, {buffer:250});

    this.grid.store.on('beforeload', this.preview.clear, this.preview);
    this.grid.store.on('load', this.gsm.selectFirstRow, this.gsm);

    this.grid.on('rowdblclick', this.openTab, this);
};

Ext.extend(MainPanel, Ext.TabPanel, {

    loadFeed : function(feed){
        this.grid.loadFeed(feed.link);
        Ext.getCmp('main-view').setTitle('<font color="red">'+feed.text+'</font>');
        //当加载时激活列表面板
        this.setActiveTab(Ext.getCmp('main-view'));
    },

    movePreview : function(m, pressed){
        if(!m){ // cycle if not a menu item click
            var readMenu = Ext.menu.MenuMgr.get('reading-menu');
            readMenu.render();
            var items = readMenu.items.items;
            var b = items[0], r = items[1], h = items[2];
            if(b.checked){
                r.setChecked(true);
            }else if(r.checked){
                h.setChecked(true);
            }else if(h.checked){
                b.setChecked(true);
            }
            return;
        }
        
        if(pressed){
            var preview = this.preview;
            var right = Ext.getCmp('right-preview');
            var bot = Ext.getCmp('bottom-preview');
            var btn = this.grid.getTopToolbar().items.get(2);
            switch(m.id){
                case 'bottom':
                    right.hide();
                    bot.add(preview);
                    bot.show();
                    bot.ownerCt.doLayout();
                    btn.setIconClass('preview-bottom');
                    break;
                case 'right':
                    bot.hide();
                    right.add(preview);
                    right.show();
                    right.ownerCt.doLayout();
                    btn.setIconClass('preview-right');
                    break;
                case 'hide':
                    preview.ownerCt.hide();
                    preview.ownerCt.ownerCt.doLayout();
                    btn.setIconClass('preview-hide');
                    break;
            }
        }
    },

    openTab : function(record){
    	alert(1);
        record = (record && record.data) ? record : this.gsm.getSelected();
        var d = record.data;
        var id = !d.link ? Ext.id() : d.link.replace(/[^A-Z0-9-_]/gi, '');
        var tab;
        if(!(tab = this.getItem(id))){
            tab = new Ext.Panel({
                id: id,
                cls:'preview single-preview',
                title: d.title,
                tabTip: d.title,
                html: FeedViewer.getTemplate().apply(d),
                closable:true,
                listeners: FeedViewer.LinkInterceptor,
                autoScroll:true,
                border:true,
                tbar: [{
                    text: '查看原文',
                    iconCls: 'new-win',
                    handler : function(){
                        window.open(d.link);
                    }
                }]
            });
            this.add(tab);
        }
        this.setActiveTab(tab);
    },

    openAll : function(){
        this.beginUpdate();
        this.grid.store.data.each(this.openTab, this);
        this.endUpdate();
    }
});