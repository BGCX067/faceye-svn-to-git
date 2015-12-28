/**
 * www.faceye.com网络支持系统 
 * 作者:宋海鹏 ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478
 * 说明:Tradition Portlet
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
//		var tools = [{
//			id : 'gear',
//			handler : function() {
//				Ext.Msg.alert('Message', 'The Settings tool was clicked.');
//			}
//		}, {
//			id : 'close',
//			handler : function(e, target, panel) {
//				Ext.Msg.confirm('删除自定义工具', '您确认要删除"' + panel.title + '"吗?',
//						function(btn, text) {
//							if (btn == 'yes') {
//								var portletId = panel.id.split('_')[0];
//								var portalId = panel.ownerCt.ownerCt.id;
//								// 发送删除数据的请求
//								Ext.Ajax.request({
//									url : BP
//											+ 'portalContainerAction.do?method=removeUserPortletSubscribe',
//									failure : function() {
//										Ext.Msg.alert('删除自定义工具条', panel.title
//												+ '删除失败！');
//									},
//									success : function() {
//										panel.ownerCt.remove(panel, true);
//										// Ext.Msg.alert('传统导航删除', '传统导航删除成功！');
//									},
//									params : {
//										portletId : portletId,
//										portalId : portalId
//									}
//								});
//							} else {
//								return;
//							}
//						});
//			}
//		}];
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools 
		});
		var traditionCategoriesStore = new Ext.data.JsonStore({
			url : BP + 'categoryAction.do?method=getTraditionCategorise',
			root : 'root',
			fields : ['id', 'text']
		});
		var portletTabs = new Ext.TabPanel({
			id : 'portlet-tradition-container',
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

		function load(categoryId) {
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
			return traditionDataView;
		}
		traditionCategoriesStore.load({
			callback : function(r, options, success) {
				for (var i = 0; i < r.length; i++) {
					var categoryId = r[i].data['id'];
					var categoryName = r[i].data['text'];
					var portletPanel = new Ext.Panel({
						id : categoryId,
						title : categoryName,
						// iconCls : 'tabs',
						closable : false,
						listeners : {
							activate : function(tab) {
								if (!tab.items) {
									var view = load(tab.id);
									tab.add(view);
									tab.doLayout();
								}
							}
						}
					});
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