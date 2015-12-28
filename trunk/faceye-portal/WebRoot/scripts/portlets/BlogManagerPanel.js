/**
 * www.faceye.com网络支持系统 作者:宋海鹏
 * ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478 说明:Blog porlet
 */
com.faceye.portal.portlet.BlogManagerPanel = {
	init : function() {
		var articleStore = new Ext.data.Store({
			// baseParams : {
			// categoryId : categoryId
			// },
			proxy : new Ext.data.HttpProxy({
				url : BP + 'articleAction.do?method=getArticles'
			}),
			reader : new Ext.data.JsonReader({
				root : 'root',
				totalProperty : 'total',
				id : 'id',
				fields : ['id', 'name', 'content', 'createTime',
						'categoryName', 'categoryId', 'discusCount',
						'clickCount']
			})
		});
		function renderTopic(value, metadata, record, rowIndex, colIndex, store) {
			var html = '<div><p><a href="#" onclick="com.faceye.portal.portlet.ArticleEditForm.detail(\'{0}\')"><span id="user-blog-article-detail-title">{1}</span></a></p>'
					+ '<p><a href="#" onclick="com.faceye.portal.portlet.ArticleDiscusManager.init(\'{0}\')">评论管理({2})</a> | <a href="#" onclick="com.faceye.portal.portlet.ArticleClickManager.init(\'{0}\')">访问记录({3})</a> </p>'
					+ '</div>';
			return String.format(html, record.data.id, record.data.name,
					record.data.discusCount, record.data.clickCount);
		}
		var cm = new Ext.grid.ColumnModel([
				new Ext.grid.CheckboxSelectionModel(), {
					id : 'id', // id assigned so we can apply custom css (e.g.
					dataIndex : 'id',
					hidden : true
				}, {
					header : "标题",
					dataIndex : 'name',
					renderer : renderTopic
				}, {
					header : '发表日期',
					dataIndex : 'createTime'
				}, {
					header : '分类',
					dataIndex : 'categoryName'
				}]);
		// cm.defaultSortable = true;
		var grid = new Ext.grid.GridPanel({
			// el:'topic-grid',
			// renderTo:outGridPanel,
			// title : '我的博客',
			header : false,
			region : 'center',
			border : false,
			autoHeight : false,
			// autoScroll:true,
			// height:400,
			loadMask : true,
			stripeRows : true,
			trackMouseOver : true,
			layoutConfig : {
				autoWidth : true,
				layout : 'fit'
			},
			// autoExpandMax:1000,
			// width:900,
			store : articleStore,
			cm : cm,
			bodyStyle : 'width:100%;',
			trackMouseOver : false,
			// selectRow:Ext.emptyFn，控制选中的记录是否高亮度显示
			// sm: new Ext.grid.RowSelectionModel({selectRow:Ext.emptyFn}),
			sm : new Ext.grid.CheckboxSelectionModel(),
			loadMask : true,
			viewConfig : {
				forceFit : true,
				enableRowBody : true
			},
			tbar : [{
				id : 'add',
				text : '撰写新日志',
				tooltip : '添加新日志~~',
				iconCls : 'add',
				handler : function(btn) {
					com.faceye.portal.portlet.ArticleEditForm.to();
				}
			}, {
				id : 'edit',
				text : '编辑',
				tooltip : '编辑选中的日志,一次只可以选中一条日志进行编辑',
				iconCls : 'option',
				handler : function(btn) {
					var selectionModel = grid.getSelectionModel();
					// 取得共选择了多少条记录。
					var selectedCount = selectionModel.getCount();
					if (selectedCount === 0) {
						Ext.Msg.alert('编辑操作', '您没有选择要编辑的数据，请选择您准备编辑的数据');
						return;
					} else if (selectedCount > 1) {
						Ext.Msg.alert('编辑操作', '您每次只能选择一条待编辑的数据，请选择您准备编辑的数据');
						return;
					} else {
						var records = selectionModel.getSelections();
						com.faceye.portal.portlet.ArticleEditForm
								.edit(records[0].id);
					}
				}
			}, {
				id : 'remove',
				text : '删除日志',
				tooltip : '删除选中的日志,一次可以删除多条日志~~',
				iconCls : 'remove',
				handler : function(btn) {
					var selectionModel = grid.getSelectionModel();
					// 取得共选择了多少条记录。
					var selectedCount = selectionModel.getCount();
					if (selectedCount === 0) {
						Ext.Msg.alert('删除操作', '您没有选择要删除的数据，请选择您准备删除的数据');
						return;
					} else {
						var records = selectionModel.getSelections();
						var _ids = '';
						for (var i = 0; i < records.length; i++) {
							_ids += records[i].id;
							_ids += '_';
						}
						Ext.Msg.confirm('删除数据', '您确认要删除选中的数据吗?', function(btn,
								text) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : BP + 'articleAction.do?method=remove',
									method : 'POST',
									params : {
										ids : _ids,
										entityClass : 'com.faceye.components.blog.dao.model.Article'
									},
									success : function(response, options) {
										com.faceye.SingleCicerone.msg(null,
												'数据已成功删除~~');
										grid.store.reload();
										// Ext.get(options.params.id).remove();
									},
									failure : function(response, options) {
										com.faceye.SingleCicerone.msg(null,
												'数据删除失败,请再次尝试删除~~');
									}
								});
							}
						});
					}
				}
			}, '-', {
				id : 'lock',
				text : '锁定',
				tooltip : '锁定以后其它人不能看到被锁定的日志,更好的保护个人数据安全~',
				iconCls : 'lock',
				handler : function(btn) {

				}
			}, {
				id : 'unlock',
				text : '解锁',
				tooltip : '可以使用解锁功能将已锁定的数据解锁,让其它人重新访问被锁定的数据~~',
				iconCls : 'unlock',
				handler : function(btn) {

				}
			}],
			bbar : com.faceye.ui.util.PaggingToolBar(15, articleStore)
		});
		articleStore.load({
			params : {
				start : 0,
				limit : 15
			}
		});
		if (!Ext.get('user-blog-article-category-tree')) {
			Ext.getBody().createChild({
				tag : 'div',
				id : 'user-blog-article-category-tree',
				style : 'overflow:auto;height:100%;border:0px solid #c3daf9;'
			});
		}
		var tree;
		if (!Ext.getCmp('user-blog-article-category-tree')) {
			tree = new Ext.tree.TreePanel({
				id : 'user-blog-article-category-tree',
				el : 'user-blog-article-category-tree',
				region : 'east',
				autoScroll : true,
				animate : true,
				border : false,
				margins : '0 0 5 0',
				// autoHeight:true,
				enableDD : false,
				containerScroll : true,
				rootVisible : false,
				loader : new Ext.tree.TreeLoader({
					dataUrl : BP + 'articleCategoryAction.do?method=tree'
				})
			});
			root = new Ext.tree.AsyncTreeNode({
				text : 'Common Platform',
				draggable : false,
				id : 'source'
			});
			tree.setRootNode(root);
			tree.render();
			root.expand();
		} else {
			tree = Ext.getCmp('user-blog-article-category-tree');
		}
		tree.on('click', function(node) {
			if (node.isLeaf()) {
				var id = node.id;
				grid.store.reload({
					params : {
						start : 0,
						limit : 15,
						categoryId : id
					}
				});
			} else {
				node.toggle();
			}
		});
		var panel = new Ext.Panel({
			id : 'user-blog-manager-panel',
			// title : '管理我的博客<font color="red"><a href="#"
			// onclick="com.faceye.portal.portlet.ArticleEditForm.to()">添加新文章</a></font>',
			layout : 'column',
			header : false,
			border : false,
			items : [{
				id : 'west-user-blog-article-category-tree',
				region : 'west',
				// layout:'fit',
				title : '我的博文分类',
				columnWidth : .15,
				width : 150,
				maxSize : 400,
				autoHeight : true,
				autoScroll : true,
				minSize : 100,
				style : 'padding:2px 2px 2px 2px',
				split : true,
				items : [tree]
			}, {
				id : 'user-article-grid',
				region : 'east',
				autoScroll : true,
				title : '我的日志',
				style : 'padding:2px 2px 2px 0px',
				columnWidth : .85,
				// autoHeight:true,
				// height:500,
				layout : 'fit',
				items : [grid]
			}],
			region : 'south'
		});
		grid.setHeight(Ext.getBody().getHeight() - 58);
		// panel.add(grid);
		return panel;
	}
};

com.faceye.portal.portlet.ArticleEditForm = {
	to : function() {
		window.open(BP + 'articleAction.do?method=forward&forward=update');
	},
	edit : function(id) {
		window.open(BP + 'articleAction.do?method=forward&forward=update&id='
				+ id);
	},
	detail : function(id) {
		window.open(BP + 'articleAction.do?method=forward&forward=detail&id='
				+ id);
	},
	remove : function(id) {
		Ext.Msg.confirm('删除数据', '您确认要删除本条的数据吗?', function(btn, text) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : BP + 'articleAction.do?method=remove',
					method : 'POST',
					params : {
						id : id,
						entityClass : 'com.faceye.components.blog.dao.model.Article'
					},
					success : function(response, options) {
						com.faceye.SingleCicerone.msg(null, '数据已成功删除~~');
						Ext.get(options.params.id).remove();
					},
					failure : function(response, options) {
						com.faceye.SingleCicerone.msg(null, '数据删除失败,请再次尝试删除~~');
					}
				});
			}
		});
	},
	init : function() {
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'under';
		var container = com.faceye.ui.Container.init();
		var panel = Ext.getCmp('default-center-body-container');
		var parentIdText = new Ext.form.TextField({
			fieldLabel : '分类',
			inputType : 'trigger',
			readOnly : true,
			name : 'categoryId',
			hidden : true,
			hideLabel : true
		});
		// 构建父节点输入框
		var parentNameText = new Ext.form.TextField({
			fieldLabel : '分类<font color="red">*</font>',
			inputType : 'trigger',
			name : 'categoryName',
			readOnly : true,
			msgTarget : 'under',
			allowBlank : false,
			blankText : '分类不能为空',
			validateOnBlur : true,
			listeners : {
				render : function(ct) {
					var parentEl = this.getEl().up('div.x-form-element');
					if (parentEl) {
						var child = parentEl.createChild({
							tag : 'button',
							html : '分类'
						});
						child.on('click', function() {
							var winT, selectTree;
							if (!winT && !selectTree) {
								winT = new Ext.Window({
									layout : 'fit',
									modal : true,
									closable : false,
									title : '选择栏目',
									width : 200,
									height : 300,
									plain : true,
									buttons : [{
										text : '确定',
										handler : function() {
											winT.hide(this);
										}
									}, {
										text : '关闭',
										handler : function() {
											winT.hide(this);
										}
									}]
								});
								// 生成树形结构
								Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
								if (!Ext
										.get('blog-article-category-manager-tree')) {
									Ext.getBody().createChild({
										tag : 'div',
										id : 'blog-article-category-manager-tree'
									});
								}
								var Tree = Ext.tree;
								if (!Ext
										.getCmp('blog-article-category-manager-tree-panel')) {
									selectTree = new Tree.TreePanel({
										id : 'blog-article-category-manager-tree-panel',
										el : 'blog-article-category-manager-tree',
										autoScroll : true,
										animate : true,
										enableDD : true,
										containerScroll : true,
										rootVisible : false,
										loader : new Tree.TreeLoader({
											dataUrl : BP
													+ 'articleCategoryAction.do?method=tree'
										})
									});
									var root = new Tree.AsyncTreeNode({
										text : '根分类',
										draggable : false,
										id : 'source'
									});
									selectTree.setRootNode(root);
									selectTree.on('click', function(node) {
										/**
										 * 当点击节点的时候，将当前点击节点的值设为正在添加新节点的父节点
										 */
										parentIdText.setValue(node.id);
										parentNameText.setValue(node.text);
										if (node.isLeaf()) {
											return true;
										} else {
											node.toggle();
										}
									});
									// render the tree
									selectTree.render();
									root.expand();
								} else {
									selectTree = Ext
											.getCmp('blog-article-category-manager-tree-panel');
								}
							}
							winT.add(selectTree);
							winT.show();
						});
					}

				}
			}
		});

		var htmlEditor = new Ext.form.HtmlEditor({
			name : 'content',
			fieldLabel : '内容',
			height : 360,
			width : 500
		});
		var form = new Ext.FormPanel({
			labelWidth : 65, // label settings here cascade unless
			layout : 'form',
			bodyBorder : false,
			border : false,
			url : BP + 'articleAction.do?method=save',
			frame : false,
			bodyStyle : 'padding:5px 5px 0',
			width : 875,
			// height : 780,
			autoHeight : true,
			autoWidth : true,
			margins : '5 5 5 5',
			monitorValid : true,
			defaultType : 'textfield',
			reader : new Ext.data.JsonReader({
				root : 'rows',
				success : true,
				fields : ['id', 'name', 'content', 'categoryId', 'categoryName']
			}),
			items : [{
				fieldLabel : '名字<font color="red">*</font>',
				name : 'name',
				tabIndex : 0,
				maxLength : 50,
				width : 350,
				msgTarget : 'under',
				allowBlank : false,
				blankText : '名称不能为空',
				maxLengthText : '名称长度不能超过50个有效字符!'
			}, parentNameText, htmlEditor, {
				name : 'id',
				hidden : true,
				hideLabel : true,
				hidden : true
			}, parentIdText],
			buttons : [{
				id : 'user-blog-article-submit',
				text : '提交',
				scope : this,
				formBind : true,
				handler : function(btn) {
					form.getForm().submit({
						url : BP + 'articleAction.do?method=save',
						method : 'POST',
						params : {
							entityClass : 'com.faceye.components.blog.dao.model.Article'
						},
						success:function(f,action){
						f.reset();
						Ext.Msg.alert('提示','保存成功.');
						}
					});
				}
			}, {
				id : 'user-blog-argicle-reset',
				text : '重置',
				handler : function(btn) {
					form.reset();
				}
			}]
		});
		if (id) {
			form.getForm().load({
				url : BP + 'articleAction.do',
				method : 'POST',
				params : {
					method : 'update',
					entityClass : 'com.faceye.components.blog.dao.model.Article',
					id : id
				},
				waitMsg : '正在加载数据，请稍后...'
			});
		}
		panel.add(form);
		container.render(Ext.getBody());
	}
};
/**
 * 文章评论管理
 */
com.faceye.portal.portlet.ArticleDiscusManager = {
	init : function(id) {
		var store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : BP + 'discusAction.do?method=getDiscusesOfArticle'
			}),
			baseParams : {
				articleId : id
			},
			reader : new Ext.data.JsonReader({
				root : 'root',
				totalProperty : 'total',
				id : 'id',
				fields : ['id', 'name', 'content', 'createTime', 'articleId',
						'userId', 'username']
			})
		});
		function renderContent(value, metadata, record, rowIndex, colIndex,
				store) {
			var html = '<p><a href="#" onclick="com.faceye.portal.portlet.ArticleDiscusManager.detail(\''
					+ record.data.id + '\')">{0}</a></p>';
			var xf = Ext.util.Format;
			var content = xf.ellipsis(xf.stripTags(record.data.content), 100);
			return String.format(html, content);
		}
		var cm = new Ext.grid.ColumnModel([
				new Ext.grid.CheckboxSelectionModel(), {
					id : 'id', // id assigned so we can apply custom css (e.g.
					dataIndex : 'id',
					hidden : true
				}, {
					header : '内容',
					dataIndex : 'content',
					renderer : renderContent
				}, {
					header : '作者',
					dataIndex : 'username'
				}, {
					header : '发表日期',
					dataIndex : 'createTime'
				}]);
		store.load({
			params : {
				start : 0,
				limit : 15
			// articleId : id
			}
		});
		var grid = new Ext.grid.GridPanel({
			// el:'topic-grid',
			// renderTo:outGridPanel,
			// title : '我的博客',
			id : 'user-blog-grid-article-discus',
			header : false,
			region : 'center',
			border : false,
			autoHeight : false,
			loadMask : true,
			autoScroll : true,
			stripeRows : true,
			trackMouseOver : true,
			layoutConfig : {
				autoWidth : true,
				layout : 'fit'
			},
			// autoExpandMax:1000,
			// width:900,
			store : store,
			cm : cm,
			bodyStyle : 'width:100%;height:100%;',
			trackMouseOver : false,
			// selectRow:Ext.emptyFn
			// selectRow:Ext.emptyFn，控制选中的记录是否高亮度显示
			// sm : new Ext.grid.RowSelectionModel({
			// selectRow : Ext.emptyFn
			// }),
			sm : new Ext.grid.CheckboxSelectionModel(),
			viewConfig : {
				forceFit : true,
				enableRowBody : true,
				showPreview : false,
				getRowClass : function(record, rowIndex, p, store) {
					var xf = Ext.util.Format;
					if (this.showPreview) {
						p.body = '<div id="user-blog-article-content-summary">'
								+ xf.ellipsis(
										xf.stripTags(record.data.content), 500)
								+ '</div>';
						p.body += '<hr  id="user-blog-article-hr"/><div id="user-blog-article-list-small-tool"><p>发表于'
								+ record.data.createTime + '</p></div>';
						return 'x-grid3-row-expanded';
					}
					p.body += '<hr id="user-blog-article-hr"/><div id="user-blog-article-list-small-tool"><p>发表于'
							+ record.data.createTime + '</p></div>';
					return 'x-grid3-row-collapsed';
				}
			},
			tbar : [{
				id : 'title-only',
				text : '全文显示',
				iconCls : 'detailChange',
				tooltip : '改变标题的显示方式~',
				handler : function(btn) {
					toggleDetails(btn);
				}
			}, '-', {
				id : 'remove',
				text : '删除',
				iconCls : 'remove',
				tooltip : '删除选定的数据',
				handler : function(btn) {
					var selectModel = grid.getSelectionModel();
					var records = selectModel.getSelections();
					if (records.length === 0) {
						Ext.Msg.alert('删除操作', '您没有选中要删除的数据，请选择您准备删除的数据');
						return;
					} else {
						Ext.Msg.confirm('删除数据', '您确认要删除选中的数据吗?', function(btn,
								text) {
							if (btn == 'yes') {
								var _ids = '';
								for (var i = 0; i < records.length; i++) {
									_ids += records[i].id;
									_ids += '_';
								}
								// 发送删除数据的请求
								Ext.Ajax.request({
									url : BP + 'discusAction.do?method=remove',
									failure : function() {
										Ext.Msg.alert('栏目删除', '栏目删除失败！');
									},
									success : function() {
										Ext.Msg.alert('栏目删除', '栏目删除成功！');
										Ext
												.getCmp('user-blog-grid-article-discus').store
												.reload();
									},
									params : {
										entityClass : 'com.faceye.components.blog.dao.model.Discus',
										ids : _ids
									}
								});

							} else {
								return;
							}
						});
					}
				}
			}],
			bbar : com.faceye.ui.util.PaggingToolBar(15, store)
		});
		function toggleDetails(btn) {
			var view = grid.getView();
			if (view.showPreview) {
				btn.setText('全文显示');
				view.showPreview = false;
			} else {
				view.showPreview = true;
				btn.setText('只看标题');
			}
			view.refresh();
		}
		var win = new Ext.Window({
			id : 'user-blog-artilce-discus-manager-win',
			title : '文章评论管理',
			layout : 'fit',
			// 模式窗口
			modal : true,
			width : 650,
			height : 350,
			// closeAction : 'hide',
			plain : true,
			// X:200,
			// Y:50,
			buttonAlign : 'center'
		});
		win.add(grid);
		win.show(this);
	}
};

com.faceye.portal.portlet.ArticleClickManager = {
	init : function(id) {
		var win = new Ext.Window({
			id : 'user-blog-artilce-click-manager-win',
			title : '文章评论管理',
			layout : 'fit',
			// 模式窗口
			modal : true,
			width : 650,
			height : 350,
			// closeAction : 'hide',
			plain : true,
			// X:200,
			// Y:50,
			buttonAlign : 'center'
		});
		var store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : BP + 'articleClickAction.do?method=index'
			}),
			baseParams : {
				articleId : id
			},
			reader : new Ext.data.JsonReader({
				root : 'root',
				totalProperty : 'total',
				id : 'id',
				fields : ['id', 'name', 'articleId', 'createTime', 'ip',
						'userId', 'username']
			})
		});
		var cm = new Ext.grid.ColumnModel([
		// new Ext.grid.CheckboxSelectionModel()
				{
					id : 'id', // id assigned so we can apply custom css (e.g.
					dataIndex : 'id',
					hidden : true
				}, {
					header : '访问者',
					dataIndex : 'username'
				// renderer : renderContent
				}, {
					header : 'IP',
					dataIndex : 'ip'
				}, {
					header : '访问日期',
					dataIndex : 'createTime'
				}]);
		store.load({
			params : {
				start : 0,
				limit : 15
			// articleId : id
			}
		});
		var grid = new Ext.grid.GridPanel({
			// el:'topic-grid',
			// renderTo:outGridPanel,
			// title : '我的博客',
			id : 'user-blog-grid-article-discus',
			header : false,
			region : 'center',
			border : false,
			autoHeight : false,
			loadMask : true,
			autoScroll : true,
			stripeRows : true,
			trackMouseOver : true,
			layoutConfig : {
				autoWidth : true,
				layout : 'fit'
			},
			// autoExpandMax:1000,
			// width:900,
			store : store,
			cm : cm,
			bodyStyle : 'width:100%;height:100%;',
			trackMouseOver : false,
			// selectRow:Ext.emptyFn
			// selectRow:Ext.emptyFn，控制选中的记录是否高亮度显示
			sm : new Ext.grid.RowSelectionModel({
				selectRow : Ext.emptyFn
			}),
			// sm : new Ext.grid.CheckboxSelectionModel(),
			viewConfig : {
				forceFit : true,
				enableRowBody : true
			},
			bbar : com.faceye.ui.util.PaggingToolBar(15, store)
		});
		win.add(grid);
		win.show();
	}
};
/**
 * 显示明细信息
 */
com.faceye.portal.portlet.ArticleDetail = {
	init : function() {
		var container = com.faceye.ui.Container.init();
		var articleDetailPanel = new Ext.Panel({
			id : 'blog-artcile-detail-panel',
			header : false,
			border : false,
			layout : 'fit'
		});
		var articleDiscusMessagePanel = new Ext.Panel({
			id : 'blog-artcile-discus-message-panel',
			border : false,
			header : false,
			layout : 'fit'
		});
		var articleDiscusPanel = new Ext.Panel({
			id : 'blog-artcile-discus-panel',
			border : false,
			header : false,
			layout : 'fit'
		});
		var panel = Ext.getCmp('default-center-body-container');
		panel.add(articleDetailPanel);
		panel.add(articleDiscusMessagePanel);
		panel.add(articleDiscusPanel);
		var store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : BP + 'discusAction.do?method=getDiscusesOfArticle'
			}),
			baseParams : {
				articleId : id
			},
			reader : new Ext.data.JsonReader({
				root : 'root',
				totalProperty : 'total',
				id : 'id',
				fields : ['id', 'name', 'content', 'createTime', 'articleId',
						'userId', 'username']
			})
		});
		function renderContent(value, metadata, record, rowIndex, colIndex,
				store) {
			var html = '<div id="user-blog-article-discus-content">'
					+ '<p>{0}</p>' + '<p>发布者:{1} {2}<p>' + '</div>';
			return String.format(html, record.data.content,
					record.data.username, record.data.createTime);
		}
		var cm = new Ext.grid.ColumnModel([
		// new Ext.grid.CheckboxSelectionModel(),
				{
					id : 'id', // id assigned so we can apply custom css (e.g.
					dataIndex : 'id',
					hidden : true
				}, {
					dataIndex : 'content',
					renderer : renderContent
				}]);
		store.load({
			params : {
				start : 0,
				limit : 15
			// articleId : id
			}
		});
		var grid = new Ext.grid.GridPanel({
			id : 'user-blog-grid-article-discus',
			header : false,
			region : 'center',
			border : false,
			autoHeight : true,
			loadMask : true,
			stripeRows : true,
			trackMouseOver : true,
			layoutConfig : {
				autoWidth : true,
				layout : 'fit'
			},
			store : store,
			cm : cm,
			bodyStyle : 'width:100%;height:100%;',
			trackMouseOver : false,
			// selectRow:Ext.emptyFn，控制选中的记录是否高亮度显示
			sm : new Ext.grid.RowSelectionModel({
				selectRow : Ext.emptyFn
			}),
			// sm : new Ext.grid.CheckboxSelectionModel(),
			viewConfig : {
				forceFit : true,
				enableRowBody : true,
				showPreview : true
			},
			bbar : com.faceye.ui.util.PaggingToolBar(15, store)
		});
		articleDiscusMessagePanel.add(grid);
		var tpl = new Ext.XTemplate('<div class="user-blog-article-detail">'
				+ '<table>' + '<tr>' + '<td>' + '<a href="' + BP
				+ 'default.jsp">FaceYe首页</a>' + '>' + '</td>' + '</tr>'
				+ '<tr>' + '<td>' + '</td>' + '</tr>' + '<tr>' + '<td>'
				+ '<span id="article-time">[{createTime}]</span>'
				+ '<span id="article-title-split">|</span>'
				+ '<span id="user-blog-article-detail-title">{name}</span>'
				+ '</td>' + '<tr>' + '<td class="user-blog-article-content">'
				+ '{content}' + '</td>' + '</tr>' + '</table>' + '</div>' + '');
		var store = new Ext.data.JsonStore({
			url : BP + 'articleAction.do?method=detail',
			root : 'rows',
			success : true,
			fields : ['id', 'name', 'content', 'createTime', 'categoryName',
					'categoryId']
		});
		store.load({
			params : {
				id : id,
				entityClass : 'com.faceye.components.blog.dao.model.Article'
			},
			callback : function(r, options, success) {
				var record = r[0];
				tpl.overwrite(articleDetailPanel.body, record.data);
			}
		});
		var form = com.faceye.portal.portlet.ArticleDiscus.init(id);
		articleDiscusPanel.add(form);
		container.render(Ext.getBody());
	}
};
/**
 * 对博客文章发表评论。
 */
com.faceye.portal.portlet.ArticleDiscus = {
	init : function(id) {
		var discusEditor = new Ext.form.HtmlEditor({
			name : 'content',
			fieldLabel : '评论',
			height : 120,
			width : 500
		});
		var form = new Ext.FormPanel({
			labelWidth : 65, // label settings here cascade unless
			layout : 'form',
			bodyBorder : false,
			// renderTo:'user-blog-article-discus',
			border : false,
			url : BP + 'articleAction.do?method=save',
			frame : false,
			bodyStyle : 'padding:5px 5px 0;width:500px;',
			width : 500,
			buttonAlign : 'center',
			// height : 780,
			autoHeight : true,
			autoWidth : true,
			margins : '5 5 5 5',
			monitorValid : true,
			defaultType : 'textfield',
			items : [discusEditor],
			buttons : [{
				id : 'submit',
				text : '提交',
				handler : function(btn) {
					form.getForm().submit({
						url : BP + 'discusAction.do?method=save',
						method : 'POST',
						params : {
							articleId : id,
							entityClass : 'com.faceye.components.blog.dao.model.Discus'
						},
						success : function(f, action) {
							f.reset();
							Ext.getCmp('user-blog-grid-article-discus').store
									.reload();
						}
					});
				}
			}]
		});
		return form;
		// var panel=Ext.getCmp('default-center-body-container');
		// panel.doLayout();
		// panel.add(form);
		// panel.doLayout();
		// form.renderTo('user-blog-article-discus');
	}
};