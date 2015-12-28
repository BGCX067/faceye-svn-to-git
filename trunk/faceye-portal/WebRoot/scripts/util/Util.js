/**
 * ------------------------------------------------------------
*创建一个通用的工具类，用于帮助其它javascripts的生成
* -------------------------------------------------------------
*/
Ext.ns('com.faceye.ui');
Ext.ns('com.faceye.ui.util');
Ext.ns('com.faceye.components');
Ext.ns('com.faceye.components.rss');
Ext.ns('com.faceye.compoents.core.security');
Ext.ns('com.faceye.portal');
Ext.ns('com.faceye.portal.portlet');
//定义全局路径
var BP='/faceye-ext-portal/';

/**
 * ************************************************************
 *  tree 工具类
 * 
 * *************************************************************
 */
 com.faceye.ui.util.Tree=function(el,rootName,rootId,url){
 	
 };
 /**
  * 生成分页工具条
  */
 com.faceye.ui.util.PaggingToolBar=function(pageSize,store){
 	return new com.faceye.ui.util.PagingToolBar({
 		    pageSize: pageSize,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示的是 {0} - {1} of {2}',
            emptyMsg: '没有可以显示的结果集'
 	 });
 };
 
 com.faceye.ui.util.PagingToolBar=Ext.extend(Ext.PagingToolbar,{
 	   initComponent : function(){
        com.faceye.ui.util.PagingToolBar.superclass.initComponent.call(this);
        this.cursor = 0;
        this.bind(this.store);
    },
    onRender : function(ct, position){
        Ext.PagingToolbar.superclass.onRender.call(this, ct, position);
        this.first = this.addButton({
            tooltip: this.firstText,
            iconCls: "x-tbar-page-first",
            disabled: true,
            handler: this.onClick.createDelegate(this, ["first"])
        });
        this.prev = this.addButton({
            tooltip: this.prevText,
            iconCls: "x-tbar-page-prev",
            disabled: true,
            handler: this.onClick.createDelegate(this, ["prev"])
        });
//        var count = this.store.getCount();
//        alert(count);
//        var pageData=getPageData();
//        alert(pageData.total);
        this.addSeparator();
        this.add(this.beforePageText);
        this.field = Ext.get(this.addDom({
           tag: "input",
           type: "text",
           size: "3",
           value: "1",
           cls: "x-tbar-page-number"
        }).el);
        this.field.on("keydown", this.onPagingKeydown, this);
        this.field.on("focus", function(){this.dom.select();});
        this.afterTextEl = this.addText(String.format(this.afterPageText, 1));
        this.field.setHeight(18);
        this.addSeparator();
        this.next = this.addButton({
            tooltip: this.nextText,
            iconCls: "x-tbar-page-next",
            disabled: true,
            handler: this.onClick.createDelegate(this, ["next"])
        });
        this.last = this.addButton({
            tooltip: this.lastText,
            iconCls: "x-tbar-page-last",
            disabled: true,
            handler: this.onClick.createDelegate(this, ["last"])
        });
        this.addSeparator();
        this.loading = this.addButton({
            tooltip: this.refreshText,
            iconCls: "x-tbar-loading",
            handler: this.onClick.createDelegate(this, ["refresh"])
        });

        if(this.displayInfo){
            this.displayEl = Ext.fly(this.el.dom).createChild({cls:'x-paging-info'});
        }
        if(this.dsLoaded){
            this.onLoad.apply(this, this.dsLoaded);
        }
    },
    updateInfo : function(){
        if(this.displayEl){
            var count = this.store.getCount();
            var msg = count == 0 ?
                this.emptyMsg :
                String.format(
                    this.displayMsg,
                    this.cursor+1, this.cursor+count, this.store.getTotalCount()
                );
            this.displayEl.update(msg);
        }
    }
 });

