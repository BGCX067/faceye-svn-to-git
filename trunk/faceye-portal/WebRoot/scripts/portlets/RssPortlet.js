/**
 * RSS portlet
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
		var tools = [{
			id : 'gear',
			handler : function() {
				Ext.Msg.alert('Message', 'The Settings tool was clicked.');
			}
		}, {
			id : 'close',
			handler : function(e, target, panel) {
				panel.ownerCt.remove(panel, true);
			}
		}];
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools
		});
		var feedCategoriesStore = new Ext.data.JsonStore({
			url : BP+'categoryAction.do?method=getFeedCategorise',
			root : 'root',
			fields : ['id', 'text']
		});
		var portletTabs = new Ext.TabPanel({
			id : 'portlet-feed-container',
			resizeTabs : true, // turn on tab resizing
			minTabWidth : 45,
			activeTab : 0,
			tabWidth : 45,
			// height : 2000,
			autoHeight : true,
			autoWidth : true,
			collapseFirst : true,
			border : false,
			animScroll : true,
			plain : true,
			enableTabScroll : true,
			animCollapse : true,
			defaults : {
				autoScroll : true,
				autoHeight : true,
				bodyStyle : 'padding:5px 5px 0 0'
			}
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
					url : BP+'feedAction.do?method=getFeeds'
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
							+ '<img src="'+BP+'components/navigation/feed/feed-viewer/images/rss_added.gif" id="{id}_img_ed"></img>'
							+ '</tpl><tpl if="isSubscribed==\'false\'">' +
									'<img onclick="com.faceye.components.rss.Util.rssSubscribe(\'{id}\')" src="'+BP+'components/navigation/feed/feed-viewer/images/rss_add.gif" id="{id}_img"></img>' +
									'</tpl><a href="{url}" target="_blank">{name}</a></td>'
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
		feedCategoriesStore.load({
			callback : function(r, options, success) {
				for (var i = 0; i < r.length; i++) {
					var categoryId = r[i].data['id'];
					var categoryName = r[i].data['text'];
					var portletPanel = load(categoryId, categoryName);
					portletTabs.add(portletPanel);
					if (portletTabs.items.itemAt(0).id == portletPanel.id) {
						portletTabs.activate(portletPanel);
					}
				}
			}
		});

		portlet.add(portletTabs);
		return portlet;
	}
};