/**
 * www.faceye.com网络支持系统 作者:宋海鹏
 * ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478 说明:Tradition
 * Portlet
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools
		});
		var portletTabs = new Ext.TabPanel({
			id : 'portlet-tradition-container-tab',
			resizeTabs : true, // turn on tab resizing
			minTabWidth : 45,
			activeTab : 0,
			tabWidth : 45,

			// height : 2000,
			autoHeight : true,
			autoWidth : true,
			collapseFirst : true,
			border : true,
			animScroll : true,
			plain : true,
			enableTabScroll : true,
			animCollapse : true,
			defaults : {
				autoScroll : true,
				autoHeight : true,
				bodyStyle : 'padding:5px 5px 0 0',
				style : '{width:100%;}'
			}
		});
		var panel = new Ext.Panel({
			id : id + '_panel',
			layout : 'column',
			header : false,
			items : [{
				region : 'west',
				id : 'tradition-home-portlet-tree-panel',
				header : false,
				columnWidth : .15,
				autoScroll : true,
				autoHeight : true,
				style : 'padding:0px 5px 0px 0px'
			}, {
				id : 'tradition-home-portlet-info-container',
				region : 'center',
				columnWidth : .85,
				border : true,
				header : false,
				autoScroll : false,
				items : [portletTabs]
			}]
		});
		function load(categoryId, categoryName) {
			/**
			 * 为显示传统导航数据,创建使用的模板.
			 */
			var traditionTemplate = new Ext.XTemplate(
					'<div class="view-template-div"><table class="view-template-table"><tr>'
							+ '<tpl for=".">'
							+ '<td><a href="{url}" target="_blank">{name}</a></td>'
							+ '<tpl if="this.isEnter(xindex)==true">' + '</tr>'
							+ '<tr>' + '</tpl>' + '</tpl></table></div>', {
						isEnter : function(index) {
							if (index % 5 === 0) {
								return true;
							} else {
								return false;
							}
						}
					});

			var traditionStore = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : BP + 'traditionAction.do'
				}),
				reader : new Ext.data.JsonReader({
					root : 'root',
					totalProperty : 'total',
					id : 'id',
					fields : ['id', 'name', 'url', 'description', 'columnId',
							'columnName', 'createDate', 'categoryId',
							'categoryName']
				})
			});
			traditionStore.load({
				params : {
					method : 'getTraditions',
					categoryId : categoryId,
					start : '-1'
				}
			});
			var traditionDataView = new Ext.DataView({
				store : traditionStore,
				tpl : traditionTemplate,
				loadingText : '正在加载...',
				style : 'overflow:auto',
				itemSelector : 'div-column',
				multiSelect : true,
				autoHeight : true
			});
			var portletPanel = new Ext.Panel({
				id : categoryId,
				title : categoryName,
				// iconCls : 'tabs',
				closable : false,
				bbar : com.faceye.ui.util.PaggingToolBar(30, traditionStore),
				listeners : {
					activate : function(tab) {
						if (!tab.items) {
							var view = traditionDataView;
							tab.add(view);
							tab.doLayout();
						}
					}
				}
			});
			return portletPanel;
		}
		
		   var treeDivId='tradition-category-tree-viewer_'+Ext.id();
			var treeDiv = Ext.getBody().createChild({
				tag : 'div',
				id : treeDivId,
				style : 'overflow:auto;border:1px solid #c3daf9;'
			});
		// Ext.get('content-iframe').setHeight(document.body.scrollHeight);
//		if (!panel.findById('tradition-home-portlet-tree')) {
			var traditionTree = new Ext.tree.TreePanel({
				id : 'tradition-home-portlet-tree_' + Ext.id(),
				el : treeDiv,
				header : false,
				// title : 'RSS分类',
				autoScroll : true,
				autoHeight : true,
				animate : true,
				border : false,
				enableDD : false,
				containerScroll : true,
				rootVisible : false,
				loader : new Ext.tree.TreeLoader({
					dataUrl : BP
							+ 'categoryAction.do?method=getTraditionCategoryTree'
				})
			});
			root = new Ext.tree.AsyncTreeNode({
				text : 'Common Platform',
				draggable : false,
				id : 'source'
			});
			traditionTree.setRootNode(root);
			traditionTree.on('click', function(node) {
				if (node.isLeaf()) {
					var portletTabs = Ext
							.getCmp('portlet-tradition-container-tab');
					if (!Ext.getCmp(node.id)) {
						var categoryId = node.id;
						var categoryName = node.text;
						var feedPanel = load(categoryId, categoryName);
						portletTabs.add(feedPanel);
						portletTabs.doLayout();
						portletTabs.activate(feedPanel);
					} else {
						portletTabs.activate(Ext.getCmp(node.id));
					}
					return true;
				} else {
					node.toggle();
				}
			});
			traditionTree.render();
			root.expand();
			root.on('load', function(r) {
				var firstNode = r.firstChild;
				var categoryId = firstNode.id;
				var categoryName = firstNode.text;
				var feedPanel = load(categoryId, categoryName);
				var portletTabs = Ext.getCmp('portlet-tradition-container-tab');
				portletTabs.add(feedPanel);
				portletTabs.doLayout();
				portletTabs.activate(feedPanel);
			});
			traditionTree.show();
			panel.items.itemAt(0).add(traditionTree);
//		}
		portlet.add(panel);
		return portlet;
	}
};