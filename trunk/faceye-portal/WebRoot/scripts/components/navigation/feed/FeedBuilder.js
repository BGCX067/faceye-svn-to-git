/**
*作者：宋海鹏
* 说明：构建feed阅读器
*/
Ext.namespace('com.faceye.ui.feed');
Ext.namespace('com.faceye.ui.util');
/**
 * TabColseMenu
 */
com.faceye.ui.feed.TabCloseMenu=function(){
    var tabs, menu, ctxItem;
    this.init = function(tp){
        tabs = tp;
        tabs.on('contextmenu', onContextMenu);
    };

    function onContextMenu(ts, item, e){
        if(!menu){ // create context menu on first right click
            menu = new Ext.menu.Menu([{
                id: tabs.id + '-close',
                text: '关闭标签',
                handler : function(){
                    tabs.remove(ctxItem);
                }
            },{
                id: tabs.id + '-close-others',
                text: '关闭其它标签',
                handler : function(){
                    tabs.items.each(function(item){
                        if(item.closable && item != ctxItem){
                            tabs.remove(item);
                        }
                    });
                }
            }]);
        }
        ctxItem = item;
        var items = menu.items;
        items.get(tabs.id + '-close').setDisabled(!item.closable);
        var disableOthers = true;
        tabs.items.each(function(){
            if(this != item && this.closable){
                disableOthers = false;
                return false;
            }
        });
        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
        menu.showAt(e.getPoint());
    }
};

/**
 * session provider
 */
 com.faceye.ui.util.SessionProvider = Ext.extend(Ext.state.CookieProvider, {
    readCookies : function(){
        if(this.state){
            for(var k in this.state){
                if(typeof this.state[k] == 'string'){
                    this.state[k] = this.decodeValue(this.state[k]);
                }
            }
        }
        return Ext.apply(this.state || {},com.faceye.ui.util.SessionProvider.superclass.readCookies.call(this));
    }
});

/**
*FeedViewer
*
*/
com.faceye.ui.feed.FeedViewer={
	init:function(){
		 Ext.QuickTips.init();
    Ext.state.Manager.setProvider(new com.faceye.ui.util.SessionProvider({state: Ext.appState}));

    var tpl = Ext.Template.from('preview-tpl', {
        compiled:true,
        getBody : function(v, all){
            return Ext.util.Format.stripScripts(v || all.description);
        }
    });
    com.faceye.ui.feed.FeedViewer.getTemplate = function(){
        return tpl;
    };

    var feeds = new com.faceye.ui.feed.FeedPanel();
    var mainPanel = new  com.faceye.ui.feed.MainPanel();

    feeds.on('feedselect', function(feed){
        mainPanel.loadFeed(feed);
    });
    
    var viewport = new Ext.Viewport({
        layout:'border',
        items:[
            new Ext.BoxComponent({ // raw element
                region:'north',
                el: 'header',
                height:32
            }),
            feeds,
            mainPanel
         ]
    });

    // add some default feeds
    feeds.addFeed({
        url:'http://ecsun.javaeye.com/rss',
        text: 'ECSUN javaeye'
    }, false, true);

    feeds.addFeed({
        url:'http://extjs.com/forum/external.php?type=RSS2',
        text: 'ExtJS.com Forums'
    }, true);

    feeds.addFeed({
        url:'http://feeds.feedburner.com/ajaxian',
        text: 'Ajaxian'
    }, true);
	}
};

com.faceye.ui.feed.FeedViewer.LinkInterceptor={
	 render: function(p){
        p.body.on({
            'mousedown': function(e, t){ // try to intercept the easy way
                t.target = '_blank';
            },
            'click': function(e, t){ // if they tab + enter a link, need to do it old fashioned way
                if(String(t.target).toLowerCase() != '_blank'){
                    e.stopEvent();
                    window.open(t.href);
                }
            },
            delegate:'a'
        });
    }
};

/**
*Feed Window
*/

com.faceye.ui.feed.FeedWindow=function(){
    this.feedUrl = new Ext.form.ComboBox({
        id: 'feed-url',
        fieldLabel: 'Enter the URL of the feed to add',
        emptyText: 'http://example.com/blog/feed',
        width: 450,
        validationEvent: false,
        validateOnBlur: false,
        msgTarget: 'under',
        triggerAction: 'all',
        displayField: 'url',
        mode: 'local',

        listeners:{
            valid: this.syncShadow,
            invalid: this.syncShadow,
            scope: this
        },
        tpl: new Ext.XTemplate(
                '<tpl for="."><div class="x-combo-list-item">',
                '<em>{url}</em><strong>{text}</strong>',
                '<div class="x-clear"></div>',
                '</div></tpl>'),
        store: new Ext.data.SimpleStore({
            fields: ['url', 'text'],
            data : this.defaultFeeds
        })
    });

    this.form = new Ext.FormPanel({
        labelAlign:'top',
        items:this.feedUrl,
        border: false,
        bodyStyle:'background:transparent;padding:10px;'
    });

    com.faceye.ui.feed.FeedWindow.superclass.constructor.call(this, {
        title: 'Add Feed',
        iconCls: 'feed-icon',
        id: 'add-feed-win',
        autoHeight: true,
        width: 500,
        resizable: false,
        plain:true,
        modal: true,
        y: 100,
        autoScroll: true,
        closeAction: 'hide',

        buttons:[{
            text: 'Add Feed!',
            handler: this.onAdd,
            scope: this
        },{
            text: 'Cancel',
            handler: this.hide.createDelegate(this, [])
        }],

        items: this.form
    });

    this.addEvents({add:true});
};

Ext.extend(com.faceye.ui.feed.FeedWindow, Ext.Window, {
    defaultFeeds : [
        ['http://www.divergingpath.com/rss.cfm?mode=full', 'Aaron Conran\'s Blog'],
        ['http://feeds.yuiblog.com/YahooUserInterfaceBlog',  'Yahoo! UI Blog'],
        ['http://feeds.feedburner.com/jquery/', 'jQuery Blog'],
        ['http://sports.yahoo.com/nba/rss.xml', 'NBA News'],
        ['http://feeds.dzone.com/dzone/frontpage', 'DZone.com']
    ],

    show : function(){
        if(this.rendered){
            this.feedUrl.setValue('');
        }
        com.faceye.ui.feed.FeedWindow.superclass.show.apply(this, arguments);
    },

    onAdd: function() {
        this.el.mask('Validating Feed...', 'x-mask-loading');
        var url = this.feedUrl.getValue();
        Ext.Ajax.request({
            url: BP+'feedAction.do?method=parseFeed',
            params: {feed: url},
            success: this.validateFeed,
            failure: this.markInvalid,
            scope: this,
            feedUrl: url
        });
    },

    markInvalid : function(){
        this.feedUrl.markInvalid('The URL specified is not a valid RSS2 feed.');
        this.el.unmask();
    },

    validateFeed : function(response, options){
        var dq = Ext.DomQuery;
        var url = options.feedUrl;

        try{
            var xml = response.responseXML;
            var channel = xml.getElementsByTagName('channel')[0];
            if(channel){
                var text = dq.selectValue('title', channel, url);
                var description = dq.selectValue('description', channel, 'No description available.');
                this.el.unmask();
                this.hide();

                return this.fireEvent('validfeed', {
                    url: url,
                    text: text,
                    description: description
                });
            }
        }catch(e){
        }
        this.markInvalid();
    }
});

/**
*feed grid
*/
com.faceye.ui.feed.FeedGrid=function(viewer, config) {
    this.viewer = viewer;
    Ext.apply(this, config);

    this.store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: BP+'feedAction.do?method=parseFeed'
        }),

        reader: new Ext.data.XmlReader(
            {record: 'item'},
            ['title', 'author', {name:'pubDate', type:'date'}, 'link', 'description', 'content']
        )
    });
    this.store.setDefaultSort('pubDate', "DESC");

    this.columns = [{
        id: 'title',
        header: "Title",
        dataIndex: 'title',
        sortable:true,
        width: 420,
        renderer: this.formatTitle
      },{
        header: "Author",
        dataIndex: 'author',
        width: 100,
        hidden: true,
        sortable:true
      },{
        id: 'last',
        header: "Date",
        dataIndex: 'pubDate',
        width: 150,
        renderer:  this.formatDate,
        sortable:true
    }];

    com.faceye.ui.feed.FeedGrid.superclass.constructor.call(this, {
        region: 'center',
        id: 'topic-grid',
        loadMask: {msg:'Loading Feed...'},

        sm: new Ext.grid.RowSelectionModel({
            singleSelect:true
        }),

        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true,
            getRowClass : this.applyRowClass
        }
    });

    this.on('rowcontextmenu', this.onContextClick, this);
};

Ext.extend(com.faceye.ui.feed.FeedGrid, Ext.grid.GridPanel, {

    onContextClick : function(grid, index, e){
        if(!this.menu){ // create context menu on first right click
            this.menu = new Ext.menu.Menu({
                id:'grid-ctx',
                items: [{
                    text: 'View in new tab',
                    iconCls: 'new-tab',
                    scope:this,
                    handler: function(){
                        this.viewer.openTab(this.ctxRecord);
                    }
                },{
                    iconCls: 'new-win',
                    text: 'Go to Post',
                    scope:this,
                    handler: function(){
                        window.open(this.ctxRecord.data.link);
                    }
                },'-',{
                    iconCls: 'refresh-icon',
                    text:'Refresh',
                    scope:this,
                    handler: function(){
                        this.ctxRow = null;
                        this.store.reload();
                    }
                }]
            });
            this.menu.on('hide', this.onContextHide, this);
        }
        e.stopEvent();
        if(this.ctxRow){
            Ext.fly(this.ctxRow).removeClass('x-node-ctx');
            this.ctxRow = null;
        }
        this.ctxRow = this.view.getRow(index);
        this.ctxRecord = this.store.getAt(index);
        Ext.fly(this.ctxRow).addClass('x-node-ctx');
        this.menu.showAt(e.getXY());
    },

    onContextHide : function(){
        if(this.ctxRow){
            Ext.fly(this.ctxRow).removeClass('x-node-ctx');
            this.ctxRow = null;
        }
    },

    loadFeed : function(url) {
        this.store.baseParams = {
            feed: url
        };
        this.store.load();
    },

    togglePreview : function(show){
        this.view.showPreview = show;
        this.view.refresh();
    },

    // within this function "this" is actually the GridView
    applyRowClass: function(record, rowIndex, p, ds) {
        if (this.showPreview) {
            var xf = Ext.util.Format;
            p.body = '<p>' + xf.ellipsis(xf.stripTags(record.data.description), 200) + '</p>';
            return 'x-grid3-row-expanded';
        }
        return 'x-grid3-row-collapsed';
    },

    formatDate : function(date) {
        if (!date) {
            return '';
        }
        var now = new Date();
        var d = now.clearTime(true);
        var notime = date.clearTime(true).getTime();
        if (notime == d.getTime()) {
            return 'Today ' + date.dateFormat('g:i a');
        }
        d = d.add('d', -6);
        if (d.getTime() <= notime) {
            return date.dateFormat('D g:i a');
        }
        return date.dateFormat('n/j g:i a');
    },

    formatTitle: function(value, p, record) {
        return String.format(
                '<div class="topic"><b>{0}</b><span class="author">{1}</span></div>',
                value, record.data.author, record.id, record.data.forumid
                );
    }
});

/**
*MainPanel
*/
com.faceye.ui.feed.MainPanel=function(){
    this.preview = new Ext.Panel({
        id: 'preview',
        region: 'south',
        cls:'preview',
        autoScroll: true,
        listeners: com.faceye.ui.feed.FeedViewer.LinkInterceptor,

        tbar: [{
            id:'tab',
            text: 'View in New Tab',
            iconCls: 'new-tab',
            disabled:true,
            handler : this.openTab,
            scope: this
        },
        '-',
        {
            id:'win',
            text: 'Go to Post',
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

    this.grid = new com.faceye.ui.feed.FeedGrid(this, {
        tbar:[{
            text:'Open All',
            tooltip: {title:'Open All',text:'Opens all item in tabs'},
            iconCls: 'tabs',
            handler: this.openAll,
            scope:this
        },
        '-',
        {
            split:true,
            text:'Reading Pane',
            tooltip: {title:'Reading Pane',text:'Show, move or hide the Reading Pane'},
            iconCls: 'preview-bottom',
            handler: this.movePreview.createDelegate(this, []),
            menu:{
                id:'reading-menu',
                cls:'reading-menu',
                width:100,
                items: [{
                    text:'Bottom',
                    checked:true,
                    group:'rp-group',
                    checkHandler:this.movePreview,
                    scope:this,
                    iconCls:'preview-bottom'
                },{
                    text:'Right',
                    checked:false,
                    group:'rp-group',
                    checkHandler:this.movePreview,
                    scope:this,
                    iconCls:'preview-right'
                },{
                    text:'Hide',
                    checked:false,
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
            enableToggle:true,
            text:'Summary',
            tooltip: {title:'Post Summary',text:'View a short summary of each item in the list'},
            iconCls: 'summary',
            scope:this,
            toggleHandler: function(btn, pressed){
                this.grid.togglePreview(pressed);
            }
        }]
    });

    com.faceye.ui.feed.MainPanel.superclass.constructor.call(this, {
        id:'main-tabs',
        activeTab:0,
        region:'center',
        margins:'0 5 5 0',
        resizeTabs:true,
        tabWidth:150,
        minTabWidth: 120,
        enableTabScroll: true,
        plugins: new com.faceye.ui.feed.TabCloseMenu(),
        items: {
            id:'main-view',
            layout:'border',
            title:'Loading...',
            hideMode:'offsets',
            items:[
                this.grid, {
                id:'bottom-preview',
                layout:'fit',
                items:this.preview,
                height: 250,
                split: true,
                border:false,
                region:'south'
            }, {
                id:'right-preview',
                layout:'fit',
                border:false,
                region:'east',
                width:350,
                split: true,
                hidden:true
            }]
        }
    });

    this.gsm = this.grid.getSelectionModel();

    this.gsm.on('rowselect', function(sm, index, record){
        FeedViewer.getTemplate().overwrite(this.preview.body, record.data);
        var items = this.preview.topToolbar.items;
        items.get('tab').enable();
        items.get('win').enable();
    }, this, {buffer:250});

    this.grid.store.on('beforeload', this.preview.clear, this.preview);
    this.grid.store.on('load', this.gsm.selectFirstRow, this.gsm);

    this.grid.on('rowdblclick', this.openTab, this);
};

Ext.extend(com.faceye.ui.feed.MainPanel, Ext.TabPanel, {
    loadFeed : function(feed){
        this.grid.loadFeed(feed.url);
        Ext.getCmp('main-view').setTitle(feed.text);
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
            switch(m.text){
                case 'Bottom':
                    right.hide();
                    bot.add(preview);
                    bot.show();
                    bot.ownerCt.doLayout();
                    btn.setIconClass('preview-bottom');
                    break;
                case 'Right':
                    bot.hide();
                    right.add(preview);
                    right.show();
                    right.ownerCt.doLayout();
                    btn.setIconClass('preview-right');
                    break;
                case 'Hide':
                    preview.ownerCt.hide();
                    preview.ownerCt.ownerCt.doLayout();
                    btn.setIconClass('preview-hide');
                    break;
            }
        }
    },

    openTab : function(record){
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
                    text: 'Go to Post',
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


/**
*FeedPanel
*/

com.faceye.ui.feed.FeedPanel=function() {
    com.faceye.ui.feed.FeedPanel.superclass.constructor.call(this, {
        id:'feed-tree',
        region:'west',
        title:'Feeds',
        split:true,
        width: 225,
        minSize: 175,
        maxSize: 400,
        collapsible: true,
        margins:'5 0 5 5',
        cmargins:'5 5 5 5',
        rootVisible:false,
        lines:false,
        autoScroll:true,
        root: new Ext.tree.TreeNode('Feed Viewer'),
        collapseFirst:false,

        tbar: [{
            iconCls:'add-feed',
            text:'Add Feed',
            handler: this.showWindow,
            scope: this
        },{
            id:'delete',
            iconCls:'delete-icon',
            text:'Remove',
            handler: function(){
                var s = this.getSelectionModel().getSelectedNode();
                if(s){
                    this.removeFeed(s.attributes.url);
                }
            },
            scope: this
        }]
    });

    this.feeds = this.root.appendChild(
        new Ext.tree.TreeNode({
            text:'My Feeds',
            cls:'feeds-node',
            expanded:true
        })
    );

    this.getSelectionModel().on({
        'beforeselect' : function(sm, node){
             return node.isLeaf();
        },
        'selectionchange' : function(sm, node){
            if(node){
                this.fireEvent('feedselect', node.attributes);
            }
            this.getTopToolbar().items.get('delete').setDisabled(!node);
        },
        scope:this
    });

    this.addEvents({feedselect:true});

    this.on('contextmenu', this.onContextMenu, this);
};

Ext.extend(com.faceye.ui.feed.FeedPanel, Ext.tree.TreePanel, {

    onContextMenu : function(node, e){
        if(!this.menu){ // create context menu on first right click
            this.menu = new Ext.menu.Menu({
                id:'feeds-ctx',
                items: [{
                    id:'load',
                    iconCls:'load-icon',
                    text:'Load Feed',
                    scope: this,
                    handler:function(){
                        this.ctxNode.select();
                    }
                },{
                    text:'Remove',
                    iconCls:'delete-icon',
                    scope: this,
                    handler:function(){
                        this.ctxNode.ui.removeClass('x-node-ctx');
                        this.removeFeed(this.ctxNode.attributes.url);
                        this.ctxNode = null;
                    }
                },'-',{
                    iconCls:'add-feed',
                    text:'Add Feed',
                    handler: this.showWindow,
                    scope: this
                }]
            });
            this.menu.on('hide', this.onContextHide, this);
        }
        if(this.ctxNode){
            this.ctxNode.ui.removeClass('x-node-ctx');
            this.ctxNode = null;
        }
        if(node.isLeaf()){
            this.ctxNode = node;
            this.ctxNode.ui.addClass('x-node-ctx');
            this.menu.items.get('load').setDisabled(node.isSelected());
            this.menu.showAt(e.getXY());
        }
    },

    onContextHide : function(){
        if(this.ctxNode){
            this.ctxNode.ui.removeClass('x-node-ctx');
            this.ctxNode = null;
        }
    },

    showWindow : function(btn){
        if(!this.win){
            this.win = new com.faceye.ui.feed.FeedWindow();
            this.win.on('validfeed', this.addFeed, this);
        }
        this.win.show(btn);
    },

    selectFeed: function(url){
        this.getNodeById(url).select();
    },

    removeFeed: function(url){
        var node = this.getNodeById(url);
        if(node){
            node.unselect();
            Ext.fly(node.ui.elNode).ghost('l', {
                callback: node.remove, scope: node, duration: .4
            });
        }
    },

    addFeed : function(attrs, inactive, preventAnim){
        var exists = this.getNodeById(attrs.url);
        if(exists){
            if(!inactive){
                exists.select();
                exists.ui.highlight();
            }
            return;
        }
        Ext.apply(attrs, {
            iconCls: 'feed-icon',
            leaf:true,
            cls:'feed',
            id: attrs.url
        });
        var node = new Ext.tree.TreeNode(attrs);
        this.feeds.appendChild(node);
        if(!inactive){
            if(!preventAnim){
                Ext.fly(node.ui.elNode).slideIn('l', {
                    callback: node.select, scope: node, duration: .4
                });
            }else{
                node.select();
            }
        }
        return node;
    },

    // prevent the default context menu when you miss the node
    afterRender : function(){
        com.faceye.ui.feed.FeedPanel.superclass.afterRender.call(this);
        this.el.on('contextmenu', function(e){
            e.preventDefault();
        });
    }
});