/**
 * www.faceye.com网络支持系统 作者:宋海鹏
 * ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478 说明:portal维护
 */
com.faceye.portal.Portal = {
	init : function() {
		Ext.QuickTips.init();
		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
		Ext.form.Field.prototype.msgTarget = 'side';
		/**
		 * portal结构生成
		 */
		var url = location.search;
		// alert(Ext.urlDecode(url).toString());
		var params = Ext.urlDecode(url.substring(1));
		var username = '';
		if (params.user) {
			username = params.user;
		}
		var tabs = this.buildPortalContainer();
		var portalContainerStore = new Ext.data.JsonStore({
			baseParams:{user:username},
			url : BP + 'portalContainerAction.do?method=getPortalContainer',
			root : 'rows',
			fields : ['id', 'name']
		});
		portalContainerStore.load({
			callback : function(r, options, success) {
				var portalContainerId = r[0].data['id'];
				var portalStore = new Ext.data.JsonStore({
					url : BP + 'portalAction.do?method=getPortals',
					root : 'root',
					fields : ['id', 'name']
				});
				portalStore.load({
					params : {
						portalContainerId : portalContainerId
					},
					callback : function(portalRecords, portalOptions,
							portalSuccess) {
						for (var i = 0; i < portalRecords.length; i++) {
							var portalId = portalRecords[i].data['id'];
							var portalName = portalRecords[i].data['name'];
							com.faceye.portal.BuildPortalPanel.init(portalId,
									portalName);
						}
					}
				})
			}
		});
		var viewport = this.buildViewport(tabs);
		viewport.render(Ext.getBody());
	},

	/**
	 * 创建面板
	 */
	buildViewport : function(tabs) {
		var viewport = Ext.getCmp('portal-container');
		// if (tabs) {
		// tabs.destroy();
		// }
		var bottom = com.faceye.ui.BottomLayout.init();
		var header = com.faceye.ui.HeaderPanel.init();
		var log = com.faceye.ui.LogPanel.init();
		viewport = new Ext.Panel({
			id : 'portal-container',
			border : false,
			items : [header, log, tabs, bottom]
		});
		return viewport
	},
	/**
	 * 创建portals容器 主要是各个portal面板
	 */
	buildPortalContainer : function() {
		var tabs = Ext.getCmp('portals-container');
		// 添加新标签页的按扭
		var addNewTablePanelButton = new Ext.Button({
			id : 'default-tabs-tool-bar-add',
			text : '添加新标签页',
			iconCls : 'default-tabs-tool-bar-add',
			handler : buildDefaultUserDefinitionPortal
		});
		// 选择当前portal格式的按扭
		var selectPortalStyleButton = new Ext.Button({
			id : 'default-tabs-tool-bar-style',
			text : '页面属性',
			iconCls : 'default-tabs-tool-bar-style',
			// handler : addNewTablePanel
			handler : function(e) {
				var win = Ext
						.getCmp('default-toolbar-column-style-select-window');
				if (win) {
					win.destroy();
				}
				var templateStore = new Ext.data.JsonStore({
					url : BP
							+ 'portalColumnTemplateAction.do?method=getPortalColummnTemplates',
					root : 'root',
					fields : ['id', 'name', 'imageSrc', 'columnScale']
				});
				templateStore.load();
				var tpl = new Ext.XTemplate(
						'<tpl for=".">',
						'<div class="thumb-wrap" id="{id}">',
						'<div class="thumb"><img src="{imageSrc}" title="{columnScale}" onclick="onCheckColumnStyle(\'{id}\')"></img></div>',
						'<span class="x-editable">{columnScale}</span></div>',
						'</tpl>', '<div class="x-clear"></div>');

				var dataView = new Ext.DataView({
					store : templateStore,
					tpl : tpl,
					border : false,
					autoHeight : true,
					title : '样式设置',
					autoWidth : true,
					multiSelect : true,
					overClass : 'x-view-over',
					itemSelector : 'div.thumb-wrap',
					emptyText : '没有可以显示的版式',
					prepareData : function(data) {
						data.imageSrc = BP+'' + data.imageSrc;
						return data;
					}
				});
				/**
				 * 编辑标签页名字的 form
				 */
				var editLabelNameForm = new Ext.FormPanel({
					layout : 'form',
					labelWidth : 45,
					url : '#',
					frame : false,
					hideBorders : false,
					border : false,
					defaultType : 'textfield',
					labelAlign : 'right',
					monitorValid : true,
					bodyStyle : '{padding:5px 0 5px 0}',
					items : [{
						fieldLabel : '<font color="red">*</font>名称',
						name : 'name',
						width : 200,
						allowBlank : false,
						maxLength : 20,
						maxLengthText : '名称长度不能超过20个有效字符!',
						msgTarget : 'under',
						blankText : '名称不能为空',
						validateOnBlur : true
					}],
					buttons : [{
						id : 'editLabelNameForm-btn',
						text : '提交',
						scope : this,
						formBind : true,
						type : 'submit',
						handler : function(btn) {
							var tabs = Ext.getCmp('portals-container');
							var newTitle = editLabelNameForm.items.itemAt(0)
									.getValue();
							var activeTab = tabs.getActiveTab();
							var portalId = activeTab.items.itemAt(0).id;
							activeTab.setTitle(newTitle);
							Ext.Ajax.request({
								url : BP
										+ 'portalAction.do?method=changePortalName',
								method : 'POST',
								params : {
									portalId : portalId,
									portalName : newTitle
								}
							});
						}
					}]
				});
				/**
				 * 将当前标签的名字自动填入form 的field.
				 */
				editLabelNameForm.on('afterlayout', function() {
					var tabs = Ext.getCmp('portals-container');
					var activeTab = tabs.getActiveTab();
					var currentTitle = activeTab.title;
					editLabelNameForm.items.itemAt(0).setValue(currentTitle);
				});
				/**
				 * 结束编辑标签标题 form 开始装载各功能块的windwo定义
				 */
				win = new Ext.Window({
					id : 'default-toolbar-column-style-select-window',
					layout : 'accordion',
					width : 300,
					buttonAlign : 'center',
					title : '设置页面属性',
					// height : 400,
					autoHeight : true,
					x : this.getEl().getX() + 5,
					y : this.getEl().getY() + 20,
					expandOnShow : true,
					// plain : true,
					modal : true,
					// border:false,
					autoScroll : false,
					shadow : false,
					items : [{
						title : '名称',
						border : false,
						layout : 'form',
						autoHeight : true,
						// autoScroll : true,
						items : [editLabelNameForm]
					}, {
						title : '版式',
						layout : 'fit',
						border : false,
						autoScroll : true,
						autoHeight : true,
						items : [dataView]
					}, {
						title : '图标',
						border : false,
						layout : 'fit'
					}],
					buttons : [{
						text : '关闭',
						type : 'submit',
						handler : function() {
							Ext
									.getCmp('default-toolbar-column-style-select-window')
									.hide();
						}
					}]
				});
				win.show();
			}
		});
		/**
		 * 到用户选择portlet的页面
		 */
		var userSubscribePortletButton = new Ext.Button({
			id : 'default-tabs-tool-bar-user-subscribe-portlet',
			text : '选择桌面工具',
			iconCls : 'default-tabs-tool-bar-add',
			handler : function(btn) {
				var activeTab = tabs.getActiveTab();
				var portalId = activeTab.items.itemAt(0).id;
				window.location.href = BP
						+ "portletAction.do?method=forward&forward=userSubscrbie&portalId="
						+ portalId;
			}
		});
		if (!tabs) {
			tabs = new Ext.TabPanel({
				id : 'portals-container',
				resizeTabs : true, // turn on tab resizing
				minTabWidth : 115,
				activeTab : 0,
				tbar : [addNewTablePanelButton, selectPortalStyleButton,
						userSubscribePortletButton],
				tabWidth : 135,
				// height : 2000,
				autoHeight : true,
				collapseFirst : true,
				style : '{padding:2px 2px 2px 2px}',
				plain : true,
				enableTabScroll : true,
				animCollapse : true,
				defaults : {
					autoScroll : true,
					autoHeight : true,
					bodyStyle : 'padding:1px 1px 1px 1px'
				},
				plugins : new Ext.ux.TabCloseMenu()
			});
		}
		return tabs;
	}
};

/**
 * 选中版式后的处理. 传入参数为columnStyleName.
 */
function onCheckColumnStyle(portalColumnTemplateId) {
	var tabs = Ext.getCmp('portals-container');
	var activeTab = tabs.getActiveTab();
	var win = Ext.getCmp('default-toolbar-column-style-select-window');
	var portal = activeTab.items.itemAt(0);
	Ext.Ajax.request({
		url : BP + 'portalAction.do?method=changePortalColumnTempate',
		params : {
			portalId : portal.id,
			portalColumnTemplateId : portalColumnTemplateId
		},
		success : function() {
			activeTab.remove(portal);
			activeTab.fireEvent('activate', this);
			// win.hide();
		},
		failure : function() {
			Ext.Msg.alert('版式', '版式改变失败！');

		}
	});
};
/**
 * 当点击'添加新标签页'按扭时,调用本方法. 添加新的标签页
 */
function buildDefaultUserDefinitionPortal() {
	// var portainsContainer = Ext.getCmp('portals-container');
	Ext.Ajax.request({
		url : BP + 'portalContainerAction.do?method=saveUserDefinePortal',
		success : function() {
			var store = new Ext.data.JsonStore({
				url : BP + 'portalAction.do?method=getLastPortalByUserId',
				root : 'rows',
				fields : ['id', 'name']
			});
			store.load({
				callback : function(r, options, success) {
					var portalId = r[0].data['id'];
					var portalName = r[0].data['name'];
					com.faceye.portal.BuildPortalPanel.init(portalId,
							portalName);
					var panel = Ext.getCmp(portalId + '_p');
					var tabs = Ext.getCmp('portals-container');
					tabs.activate(panel);
				}
			})

		}
	});
};
/**
 * 创建装载 portal的panel
 */
com.faceye.portal.BuildPortalPanel = {
	init : function(portalId, portalName) {
		var tabs = Ext.getCmp('portals-container');
		var panel = new Ext.Panel({
			id : portalId + '_p',
			title : portalName,
			iconCls : 'tabs',
			closable : false,
			listeners : {
				activate : function(tab) {
					if (!tab.items) {
						com.faceye.portal.BuildPortal
								.init(portalId, portalName);
						com.faceye.portal.BuildPortalColumn.init(portalId,
								portalName);
					}
				},
				beforedestroy : function(tab) {
					var portalId = tab.id.split('_')[0];
					Ext.Ajax.request({
						url : BP + 'portalAction.do?method=remove',
						params : {
							portalId : portalId
						}
					});

				}
			}
		});
		if (tabs.items.length > 0) {
			panel.closable = true;
		}
		tabs.add(panel);
		if (tabs.items.itemAt(0).id == panel.id) {
			tabs.activate(panel);
		}
	}
};

/**
 * 创建portal
 */
com.faceye.portal.BuildPortal = {
	init : function(portalId, portalName) {
		var portal = new Ext.ux.Portal({
			id : portalId,
			border : false,
			region : 'center',
			style : 'padding:0 0 0 0'
		});
	}
};
/**
 * 创建portalColumn
 */
com.faceye.portal.BuildPortalColumn = {
	init : function(portalId, portalName) {
		var tabs = Ext.getCmp('portals-container');
		var portal = Ext.getCmp(portalId);
		var panel = Ext.getCmp(portalId + '_p');
		var portalColumnStore = new Ext.data.JsonStore({
			url : BP + 'portalColumnAction.do?method=getPortalColumns',
			root : 'root',
			fields : ['id', 'name', 'icon', 'iconCls', 'singleColumnScale',
					'createTime']
		});
		portalColumnStore.load({
			params : {
				portalId : portalId
			},
			callback : function(portalColumnRecords, portalColumnOptions,
					portalColumnSuccess) {
				for (var j = 0; j < portalColumnRecords.length; j++) {
					var portalColumnId = portalColumnRecords[j].data['id'];
					var singleColumnScale = portalColumnRecords[j].data['singleColumnScale']
							/ 100;
					var portalColumn = new Ext.ux.PortalColumn({
						id : portalColumnId,
						columnWidth : singleColumnScale,
						style : 'padding:2px 2px 2px 2px'
					});
					com.faceye.portal.BuildPortlet.init(portalColumnId);
					portal.add(portalColumn);
				}
				panel.add(portal);
				tabs.doLayout();
			}
		});
	}
};
/**
 * 创建portlet
 */
com.faceye.portal.BuildPortlet = {
	init : function(portalColumnId) {
		var portalColumn = Ext.getCmp(portalColumnId);
		// var classLoader = new ClassLoader();
		// classLoader
		// .loadFormFileSrc(BP+'scripts/portal/TraditionPortlet.js');
		// var portlet = com.faceye.portal.portlet.TraditionPortlet.init();
		// portalColumn.add(portlet);
		var portletStore = new Ext.data.JsonStore({
			url : BP + 'portletAction.do?method=getPortletsByPortalColumn',
			root : 'root',
			fields : ['id', 'name', 'source', 'createTime', 'url', 'imageSrc',
					'init']
		});
		portletStore.load({
			params : {
				portalColumnId : portalColumnId
			},
			callback : function(r, options, success) {
				for (var i = 0; i < r.length; i++) {
					// classLoader.loadFormFileSrc(r[i].data['url']);
					// var portlet =
					// com.faceye.portal.portlet.SinglePortlet.init();
					// portalColumn.add(portlet);
					var id = r[i].data['id'];
					var name = r[i].data['name'];
					var url = r[i].data['url'];
					Ext.Ajax.request({
						url : r[i].data['url'],
						params : {
							id : id,
							name : name
						},
						success : function(response, options) {
							var source = response.responseText;
							var headerDom = document
									.getElementsByTagName('head').item(0);
							var jsDom = document.createElement('script');
							jsDom.type = 'text/javascript';
							jsDom.language = 'javascript';
							jsDom.defer = true;
							jsDom.text = source;
							headerDom.appendChild(jsDom);
							var portlet = com.faceye.portal.portlet.SinglePortlet
									.init(options.params.id,
											options.params.name);
							// function change(){
							// if(portlet.draggable){
							// portlet.draggable=false;
							// }
							// }
							// com.faceye.ui.UserLogin.is().load({
							// callback : function(r, options, success) {
							// if (r[0].data.login === 'yes') {
							//
							// } else {
							// change();
							// }
							// }
							// });
							// portlet.on('beforerender', function(container) {
							// if (container.draggable) {
							// container.draggable = false;
							// }
							// });
							portalColumn.add(portlet);
							portalColumn.doLayout();
						}
					});
				}

			}
		});
	}
}