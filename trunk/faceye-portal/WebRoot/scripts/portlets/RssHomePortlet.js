/**
 * www.faceye.com网络支持系统 作者:宋海鹏
 * ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478 说明: 新的RSS显示模板
 * 用于在网站首页显示RSS订阅信息 供用户订阅
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools
		});
		var portletTabs = new Ext.TabPanel({
			id : 'portlet-feed-container-tab',
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
				bodyStyle : 'padding:5px 0px 0 0'
			}
		});

		var panel = new Ext.Panel({
			id : id + '_panel',
			layout : 'column',
			header : false,
			border : true,
			// plain:true,
			// margins:'0 5 0 0',
			// defaults : {
			// autoScroll : true,
			// // autoHeight : true,
			// bodyStyle : 'padding:0 0 0 0'
			// },
			items : [{
				region : 'west',
				id : 'rss-home-portlet-tree-panel',
				header : false,
				columnWidth : .15,
				autoScroll : true,
				autoHeight : true,
				style : 'padding:0px 5px 0px 0px'
			// html:'<div id="feed-category-tree-viewer"
					// style="overflow:auto;border:1px solid #c3daf9;"></div>'
					}, {
						id : 'rss-home-portlet-info-container',
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
			var feedStore = new Ext.data.Store({
				baseParams : {
					categoryId : categoryId
				},
				proxy : new Ext.data.HttpProxy({
					url : BP + 'feedAction.do?method=getFeeds'
				}),
				reader : new Ext.data.JsonReader({
					root : 'root',
					totalProperty : 'total',
					id : 'id',
					fields : ['id', 'name', 'url', 'description', 'columnId',
							'columnName', 'createDate', 'categoryId',
							'categoryName', 'isSubscribed']
				})
			});
			feedStore.load({
				params : {
					start : 0,
					limit : 30
				}
			});
			var feedTemplate = new Ext.XTemplate(
					'<div class="view-template-div"><table class="view-template-table"><tr>'
							+ '<tpl for=".">'
							+ '<td><tpl if="isSubscribed==\'true\'">'
							+ '<img src="'
							+ BP
							+ 'components/navigation/feed/feed-viewer/images/rss_added.gif" id="{id}_img_ed"></img>'
							+ '</tpl><tpl if="isSubscribed==\'false\'">'
							+ '<img onclick="com.faceye.components.rss.Util.rssSubscribe(\'{id}\')" src="'
							+ BP
							+ 'components/navigation/feed/feed-viewer/images/rss_add.gif" id="{id}_img"></img>'
							+ '</tpl><a href="{url}" target="_blank">{name}</a></td>'
							+ '<tpl if="this.isEnter(xindex)==true">' + '</tr>'
							+ '<tr>' + '</tpl>' + '</tpl></table></div>', {
						isEnter : function(index) {
							if (index % 3 === 0) {
								return true;
							} else {
								return false;
							}
						}
					});

			var feedDataView = new Ext.DataView({
				id : 'feed-view-' + categoryId,
				store : feedStore,
				tpl : feedTemplate,
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
				bbar : com.faceye.ui.util.PaggingToolBar(30, feedStore),
				listeners : {
					activate : function(tab) {
						if (!tab.items) {
							var view = feedDataView;
							tab.add(view);
							tab.doLayout();
						}
					}
				}
			});
			return portletPanel;
		}
		// alert(Ext.getCmp('rss-home-portlet-tree-panel').id);
		// panel.doLayout();
		// portlet.doLayout();
		var treeDivId='feed-category-tree-viewer_'+Ext.id();
			var treeDiv = Ext.getBody().createChild({
				tag : 'div',
				id:treeDivId,
				style : 'overflow:auto;border:1px solid #c3daf9;'
			});
		// var treeDiv=new
		// Ext.Element({tag:'div',id:'feed-category-tree-viewer',style:'overflow:auto;border:1px
		// solid #c3daf9;'});
		// panel.on('afterlayout', function(p) {
		// Ext.get('content-iframe').setHeight(document.body.scrollHeight);
		// if (!panel.findById('rss-home-portlet-tree')) {
		var tree = new Ext.tree.TreePanel({
			id : 'rss-home-portlet-tree_' + Ext.id(),
			el : treeDiv,
			// renderTo:Ext.getCmp('rss-home-portlet-tree-panel'),
			// renderTo:Ext.getBody(),
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
				dataUrl : BP + 'categoryAction.do?method=getFeedCategoryTree'
			})
		});
		root = new Ext.tree.AsyncTreeNode({
			text : 'Common Platform',
			draggable : false,
			id : 'source'
		});
		tree.setRootNode(root);
		tree.on('click', function(node) {
			if (node.isLeaf()) {
				var portletTabs = Ext.getCmp('portlet-feed-container-tab');
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
				/**
				 * open node by single click,not double click.
				 */
				node.toggle();
			}
		});
		root.on('load', function(r) {
			var firstNode = r.firstChild;
			var categoryId = firstNode.id;
			var categoryName = firstNode.text;
			var feedPanel = load(categoryId, categoryName);
			var portletTabs = Ext.getCmp('portlet-feed-container-tab');
			portletTabs.add(feedPanel);
			portletTabs.doLayout();
			portletTabs.activate(feedPanel);
		});
		tree.render();
		root.expand();
		panel.items.itemAt(0).add(tree);
		portlet.add(panel);
		// portlet.doLayout();
		return portlet;
	}

}
