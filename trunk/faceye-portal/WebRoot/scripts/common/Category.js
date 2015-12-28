/**
 * www.faceye.com网络支持系统 作者:宋海鹏 ecsun@sohu.com/myecsun@hotmail.com 说明:网站分类维护
 */

var westPanel = new Ext.Panel({
	region : 'west',
	layout : 'fit',
	width : 200,
	title : '网站分类结构',
	border : true,
	collapsible : true,
	split : true,
	autoScroll : true,
	margins : '5 0 5 5',
	html : '<div id="tree-viewer" style="overflow:auto;height:400px;border:0px solid #c3daf9;"></div>'
});

var centerPanel = new Ext.Panel({
	region : 'center',
	layout : 'fit',
	autoScroll : false,
	title : '网站分类管理',
	margins : '5 5 5 0',
	html : '<iframe id="content-iframe" frameborder="no" style="width:100%;height:100%"></iframe>'
});

var Category = {
	init : function() {
		var view = new Ext.Viewport({
			layout : 'border',
			border : false,
			renderTo : Ext.getBody(),
			items : [westPanel, centerPanel]
		});
		Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
		var categoryTree = new Ext.tree.TreePanel({
			el : 'tree-viewer',
			// renderTo:winT,
			autoScroll : true,
			animate : true,
			enableDD : true,
			border : false,
			containerScroll : true,
			loader : new Ext.tree.TreeLoader({
				dataUrl : BP+'categoryAction.do?method=tree'
			})
		});

		var categoryRoot = new Ext.tree.AsyncTreeNode({
			text : '网站分类',
			draggable : false,
			id : 'source'
		});

		categoryTree.setRootNode(categoryRoot);

		// 网站分类维护panel

		var editPanel = new Ext.Panel({
			layout : 'border',
			border : false,
			plain : true,
			modal : true,
			autoScroll : false
		});
		// 编辑功能树容器
		var editNav = new Ext.Panel({
			id : 'win-nav',
			title : '操作功能',
			region : 'west',
			split : true,
			layout : 'fit',
			width : 130,
			collapsible : true,
			margins : '3 0 3 3',
			cmargins : '3 3 3 3'
		});
		// 操作区容器，进行具体操作的地方。
		var editContent = new Ext.Panel({
			// title: 'Navigation',
			id : 'win-content',
			region : 'center',
			buttonAlign : 'center',
			split : false,
			border : false,
			layout : 'fit',
			collapsible : false,
			margins : '3 3 3 0'
		// html:'<p>栏目名称:'+node.text+'</p>'+
		// '<p>父栏目:'+node.parentNode.text+'</p>'+
		// '<p>栏目URL:'+node.link+'</p>'
		// html:'<iframe id="tree-node-edit-iframe" frameborder="no"
		// style="width:100%;height:94%"></iframe>'
		// cmargins:'3 3 3 3'
		});

		/**
		 * 开始生成操作功能树
		 */
		var operationTree = new Ext.tree.TreePanel({
			id : 'sub-tree',
			el : 'sub-tree',
			autoScroll : true,
			animate : true,
			enableDD : true,
			border : false,
			containerScroll : true,
			loader : new Ext.tree.TreeLoader({
				dataUrl : BP+'categoryAction.do?method=node'
			})
		});
		var operationRoot = new Ext.tree.AsyncTreeNode({
			text : '操作功能',
			draggable : false,
			id : 'source'
		});

		categoryTree.on('click', function(node) {

			if (node.isLeaf()) {
				// return true;
			} else {
				node.toggle();
				// return true;
			}
			operationTree.setRootNode(operationRoot);

			editNav.add(operationTree);
			// editNav.doLayout();
			operationTree.render();
			operationRoot.expand();
			/**
			 * 结束生成操作功能树
			 */

			editPanel.add(editNav);
			editPanel.add(editContent);
			centerPanel.add(editPanel);
			centerPanel.doLayout();

			// 当点击操作功能树时，触发相应操作事件
			operationTree.on('click', function(operationNode) {

				/**
				 * 定义进行分类新增及修改时所使用的父分类的信息
				 */

				var parentIdText = new Ext.form.TextField({
					fieldLabel : '父分类ID',
					inputType : 'trigger',
					readOnly : true,
					name : 'parentId',
					width : 215,
					hidden : true,
					hideLabel : true
				});
				// 构建父栏目输入框
				// if(Ext.getCmp('parentSelectTree')){
				// Ext.get('parent-select-tree').remove(true);
				// Ext.getBody().createChild({tag:'div',id:'parent-select-tree'});
				// }
				// if(Ext.getCmp('parentText')){
				// Ext.getCmm('parentText').destroy();
				// }
				var parentNameText = new Ext.form.TextField({
					id : 'parentText',
					fieldLabel : '父栏目名字',
					inputType : 'trigger',
					name : 'parentName',
					readOnly : true,
					// defaults:{width:180},
					width : 215,
					listeners : {
						render : function(ct) {
							var parentEl = this.getEl()
									.up('div.x-form-element');
							// alert(parentEl.getId());
							if (parentEl) {
								var child = parentEl.createChild({
									tag : 'button',
									html : '选择父分类'
								});

								var tree;
								// 生成树形结构
								Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';

								var Tree = Ext.tree;

								tree = new Tree.TreePanel({
									el : 'parent-select-tree',
									// renderTo:winT,
									id : 'parentSelectTree',
									autoScroll : true,
									animate : true,
									enableDD : true,
									containerScroll : true,
									loader : new Tree.TreeLoader({
										dataUrl : BP+'categoryAction.do?method=tree'
									})
								});
								var root = new Tree.AsyncTreeNode({
									text : '网站分类',
									draggable : false,
									id : 'source'
								});
								tree.setRootNode(root);
								child.on('click', function() {
									// var e = field.el.up( '.x-form-item' );
									// var e = ct.getEl().up( '.x-form-item' );

									// parentNameText.setValue('parentName');
									// 定义装载选择树的window
									// winT.destroy();

									var winT;
									winT = new Ext.Window({
										layout : 'fit',
										modal : true,
										title : '选择父分类',
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

									tree.on('click', function(node) {
										/**
										 * 当点击栏目的时候，将当前点击栏目的值设为正在添加新栏目的父栏目
										 */
										parentIdText.setValue(node.id);
										parentNameText.setValue(node.text);
										if (node.isLeaf()) {
											// Ext.get('content-iframe').dom.src
											// =
											// node.attributes.link+'&node='+node.id;
											return true;
										} else {
											/**
											 * open node by single click,not
											 * double click.
											 */
											node.toggle();
										}
									});
									// render the tree
									tree.render();
									root.expand();
									winT.add(tree);

									winT.show(this);
								});
							}
							// ct.el.on('click',function(){
							// alert('test');
							// },this)

						}
					}
				// vtype:'email'
				});
				/**
				 * 父栏目信息定义完毕
				 */
				// 当点击明细操作时
				if (operationNode.id === '1') {
					var tpl = new Ext.XTemplate('<p>分类名称:{name}</p>'
							+ '<p>父分类:{parentName}</p>');
					var categories = new Ext.data.JsonStore({
						url : BP+'categoryAction.do',
						root : 'rows',
						fields : ['id', 'name', 'parentId', 'parentName']
					});
					categories.load({
						params : {
							method : 'detail',
							id : node.id,
							entityClass : 'com.faceye.components.navigation.dao.model.Category'
						},
						callback : function(r, options, success) {
							var record = r[0];
							tpl.overwrite(editContent.body, record.data);
						}
					});
				} else if (operationNode.id === '2') {
					clearChildren();
					// 当点击添加子分类时
					// 当点击添加子节点时，默认父分类为当前点击分类
					parentIdText.setValue(node.id);
					parentNameText.setValue(node.text);
					var categoryForm = new Ext.form.FormPanel({
						id : 'save',
						labelWidth : 80, // label settings here cascade
											// unless overridden
						url : BP+'categoryAction.do?method=save',
						frame : true,
						bodyStyle : 'padding:5px 5px 0',
						width : 650,
						layout : 'form',
						style : 'bgColor:white;',
						border : false,
						defaultType : 'textfield',
						items : [{
							inputType : 'hidden',
							name : 'id'
						}, {
							fieldLabel : '分类名字',
							name : 'name',
							allowBlank : false,
							width : 300
						}, parentNameText, parentIdText],
						buttons : [{
							text : '确定',
							// scope:ColumnController,
							type : 'submit',
							disabled : false,
							handler : function() {
								categoryForm.getForm().submit({
									method : 'POST',
									params : {
										entityClass : 'com.faceye.components.navigation.dao.model.Category'
									},
									waitMsg : '正在保存数据',
									// reset:BP+'treeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
									success : function(form, action) {
										// grid.getView().refresh();
										form.reset();
										categoryTree.root.reload();
										Ext.Msg.alert('分类保存', '分类保存成功!');
									},
									failure : function() {
										Ext.Msg.alert('分类保存', '分类保存失败!');
										this.disabled = false;
									}
								});
							}
						}, {
							text : '重置',
							handler : function() {
								categoryForm.getForm().reset();
							}
						}]
					});
					editContent.add(categoryForm);
					editContent.doLayout();

				} else if (operationNode.id === '3') {
					clearChildren();
					// 当点击编辑子分类时
					var updateCategoryForm = new Ext.form.FormPanel({
						id : 'update',
						labelWidth : 80, // label settings here cascade
											// unless overridden
						url : BP+'categoryAction.do?method=save',
						frame : true,
						bodyStyle : 'padding:5px 5px 0',
						width : 650,
						layout : 'form',
						style : 'bgColor:white;',
						border : false,
						defaultType : 'textfield',
						waitMsg : '正在加载数据,请稍候...',
						reader : new Ext.data.JsonReader({
							root : 'rows',
							// totalProperty: 'total',
							success : true,
							fields : ['id', 'name', 'parentId', 'parentName']
						}),
						items : [{
							inputType : 'hidden',
							name : 'id'
						}, {
							fieldLabel : '分类名字',
							name : 'name',
							allowBlank : false,
							width : 300
						}, parentNameText, parentIdText],
						buttons : [{
							text : '确定',
							// scope:ColumnController,
							type : 'submit',
							disabled : false,
							handler : function() {
								updateCategoryForm.getForm().submit({
									method : 'POST',
									params : {
										entityClass : 'com.faceye.components.navigation.dao.model.Category'
									},
									waitMsg : '正在保存数据',
									// reset:BP+'treeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
									success : function(form, action) {
										// grid.getView().refresh();
										form.reset();
										categoryTree.root.reload();
										Ext.Msg.alert('分类保存', '分类保存成功!');
									},
									failure : function() {
										Ext.Msg.alert('分类保存', '分类保存失败!');
										this.disabled = false;
									}
								});
							}
						}, {
							text : '重置',
							handler : function() {
								updateCategoryForm.getForm().reset();
							}
						}]
					});
					updateCategoryForm.load({
						url : BP+'categoryAction.do',
						params : {
							id : node.id,
							method : 'update',
							entityClass : 'com.faceye.components.navigation.dao.model.Category'
						}
					});
					editContent.add(updateCategoryForm);
					editContent.doLayout();
				} else if (operationNode.id === '4') {
					clearChildren();
					// 当点击删除子分类时

					var removePanel = new Ext.Panel({
						id : 'remove',
						layout : 'fit',
						border : false
					});

					var button = new Ext.Button({
						id : 'button',
						text : '删除(' + node.text + ')分类',
						handler : function() {
							if (node.isLeaf()) {
								Ext.Ajax.request({
									url : BP+'categoryAction.do?method=remove',
									params : {
										id : node.id,
										entityClass : 'com.faceye.components.navigation.dao.model.Category'
									},
									failure : function() {
										Ext.Msg.alert('网站分类删除', '网站分类删除失败！');
									},
									success : function() {
										categoryTree.root.reload();
										Ext.Msg.alert('网站分类删除', '网站分类删除成功！');
									}
								});
							} else {
								Ext.Msg.alert('删除网站分类',
										'本网站分类下还有子网站分类,如果要删除本网站分类,请首先清空其子网站分类');
							}
						}
					});
					removePanel.add(button);
					editContent.add(removePanel);
					editContent.doLayout();
				} else {

				}
			});
			/**
			 * 在添加子panel之前，先清空以前添加的子panel
			 */
			function clearChildren() {
				if (editContent.findById('save')) {
					editContent.remove('save');
				}
				if (editContent.findById('update')) {
					editContent.remove('update');
				}
				if (editContent.findById('remove')) {
					editContent.remove('remove');
				}
				if (!Ext.get('parent-select-tree')) {
					Ext.getBody().createChild({
						tag : 'div',
						id : 'parent-select-tree'
					});
				}
				if (Ext.getCmp('parentSelectTree')) {
					Ext.get('parent-select-tree').remove(true);
					Ext.getBody().createChild({
						tag : 'div',
						id : 'parent-select-tree'
					});
				}
			}

		});
		categoryTree.render();
		categoryRoot.expand();
	}
};
