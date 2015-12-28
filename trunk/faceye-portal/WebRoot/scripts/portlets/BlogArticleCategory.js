/**
 * www.faceye.com网络支持系统 作者:宋海鹏
 * ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478 说明:Blog porlet
 */
com.faceye.portal.portlet.BlogArticleCategory = {
	init : function() {
		if (!Ext.get('blog-article-category-tree')) {
			Ext.getBody().createChild({
				tag : 'div',
				id : 'blog-article-category-tree'
			});
		}
		var tree;
		if (!Ext.getCmp('blog-article-category-tree-panel')) {
			tree = new Ext.tree.TreePanel({
				id : 'blog-article-category-tree-panel',
				el : 'blog-article-category-tree',
				header : true,
				title : '管理我的博客文章分类',
				autoScroll : true,
				autoHeight : false,
				animate : true,
				border : false,
				enableDD : true,
				// height:800,
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
			tree.on('contextmenu', function(node, e) {
				// if (!this.menu) { // create context menu on first right click
				this.menu = new Ext.menu.Menu({
					id : 'feeds-ctx',
					items : [{
						id : 'load',
						iconCls : 'load-icon',
						text : '添加',
						scope : this,
						handler : function(btn) {
							com.faceye.portal.portlet.BlogArticleCategoryEditWin
									.init(null);
						}
					}, {
						text : '编辑',
						iconCls : 'delete-icon',
						scope : this,
						handler : function(btn) {
							// alert(node.id);
							com.faceye.portal.portlet.BlogArticleCategoryEditWin
									.init(node.id);
						}
					}]
				});
				if (node.isLeaf() && node.text != '默认分类') {
					this.menu.add({
						iconCls : 'add-feed',
						text : '删除',
						handler : function(node) {
							Ext.Ajax.request({
								url : BP + 'articleCategory.do?method=remove',
								params : {
									id : node.id
								}
							});
						},
						scope : this
					});
				}
				// this.menu.on('hide', this.onContextHide, this);
				// }
				this.menu.showAt(e.getXY());
			});
			tree.on('click', function(node) {
				if (node.isLeaf()) {
//					node.toggle();
					return true;
				}else{
					node.toggle();
				}
			});
			tree.on('movenode', function(tree, node, oldParent, newParent,
					index) {
				var oldParentId = '';
				var newParentId = '';
				var articleCategoryId = '';
				if (oldParent) {
					oldParentId = oldParent.id;
				}
				if (newParent) {
					newParentId = newParent.id;
				}
				if (node) {
					articleCategoryId = node.id;
				}
				Ext.Ajax.request({
					url : BP + 'articleCategoryAction.do',
					params : {
						method : 'order',
						articleCategoryId : articleCategoryId,
						oldParentId : oldParentId,
						newParentId : newParentId,
						index : index
					}
				});
			});
			tree.on('render', function(p) {
				com.faceye.Cicerone.msg(null,
						'在分类上点击右键可以添加新博客文章分类,还可以用鼠标拖动排序呢~~');
			});
			tree.setRootNode(root);
			tree.render();
			root.expand();
		} else {
			tree = Ext.getCmp('blog-article-category-tree-panel');
		}
		return tree;
	}
};
com.faceye.portal.portlet.BlogArticleCategoryEditWin = {
	init : function(id) {
		var win;
		/**
		 * ----------------Start Add button config-----------------------
		 */
		if (!win) {
			var parentIdText = new Ext.form.TextField({
				fieldLabel : '栏目ID',
				inputType : 'trigger',
				readOnly : true,
				name : 'parentCategoryId',
				// width : 215,
				hidden : true,
				hideLabel : true
			});
			// 构建父节点输入框
			var parentNameText = new Ext.form.TextField({
				fieldLabel : '父分类',
				inputType : 'trigger',
				name : 'parentCategoryName',
				readOnly : true,
				// width : 215,
				listeners : {
					render : function(ct) {
						var parentEl = this.getEl().up('div.x-form-element');
						if (parentEl) {
							var child = parentEl.createChild({
								tag : 'button',
								html : '上级分类'
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
											// renderTo:winT,
											autoScroll : true,
											animate : true,
											enableDD : true,
											containerScroll : true,
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
								winT.show(this);
							});
						}

					}
				}
			});

			var innerForm = new Ext.FormPanel({
				labelWidth : 75, // label settings here cascade unless
				// overridden
				url : BP + 'articleCategoryAction.do?method=save',
				frame : true,
				// title: 'Simple Form',
				bodyStyle : 'padding:5px 5px 0',
				width : 340,
				height : 180,
				margins : '5 5 5 5',
				monitorValid : true,

				// defaults: {width: 110},
				// renderTo : win,
				layout : 'form',
				defaultType : 'textfield',
				reader : new Ext.data.JsonReader({
					root : 'rows',
					// totalProperty: 'total',
					success : true,
					fields : ['id', 'name', 'nodeOrder', 'portalContainerId',
							'parentCategoryName', 'parentCategoryId']
				}),
				items : [{
					fieldLabel : '分类名字<font color="red">*</font>',
					name : 'name',
					tabIndex : 0,
					// width : 200,
					maxLength : 20,
					msgTarget : 'under',
					allowBlank : false,
					blankText : '名称不能为空',
					maxLengthText : '名称长度不能超过20个有效字符!',
					allowBlank : false,
					validateOnBlur : true
				// tyle:'textfield',
						// vtypeText : '分类名字不能为空'
						}, parentNameText, {
							name : 'id',
							hidden : true,
							hideLabel : true,
							hidden : true
						}, parentIdText],
				buttons : [{
					text : '确定',
					// scope : OpenSource,
					type : 'submit',
					scope : this,
					formBind : true,
					disabled : false,
					buttonAlign : 'center',
					handler : function(btn) {
						innerForm.getForm().submit({
							method : 'POST',
							params : {
								entityClass : 'com.faceye.components.blog.dao.model.ArticleCategory'
							},
							waitMsg : '正在保存数据',
							// reset:BP+'treeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
							success : function(form, action) {
								form.reset();
								Ext.getCmp('blog-article-category-tree-panel')
										.getRootNode().reload();
								Ext.Msg.alert('分类保存', '分类保存成功!');
								this.disabled = false;
								win.hide();
							},
							failure : function() {
								Ext.Msg.alert('分类保存', '分类保存失败!');
								this.disabled = false;
							}
						});
					}
				}, {
					text : '放弃',
					handler : function(btn) {
						innerForm.getForm().reset();
						win.hide();
					}
				}, {
					text : '重置',
					handler : function(btn) {
						innerForm.getForm().reset();
					}
				}]
			});
			if (id) {
				innerForm.getForm().load({
					url : BP + 'articleAction.do',
					params : {
						method : 'update',
						entityClass : 'com.faceye.components.blog.dao.model.ArticleCategory',
						id : id
					},
					waitMsg : '正在加载数据，请稍后...'
				});
			}
			win = new Ext.Window({
				layout : 'fit',
				// 模式窗口
				modal : true,
				width : 340,
				height : 180,
				closeAction : 'hide',
				plain : true,
				// autoScroll:true,
				title : '添加博客文章分类'
			});
			win.add(innerForm);
		}
		win.show(this);

		/**
		 * ---------------End Add button config------------------------
		 */
	}
};