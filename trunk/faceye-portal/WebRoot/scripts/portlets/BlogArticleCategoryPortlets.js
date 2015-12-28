/**
 * www.faceye.com网络支持系统 作者:宋海鹏
 * ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478 说明:Blog porlet
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
		var articleCategoryTreeEl = "blog-article-category-tree";
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools
		});
		if (!Ext.get(articleCategoryTreeEl)) {
			Ext.getBody().createChild({
				tag : 'div',
				id : articleCategoryTreeEl
			});
		}
		var tree = new Ext.tree.TreePanel({
			id : 'blog-article-category-tree',
			el : articleCategoryTreeEl,
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
				dataUrl : BP + 'articleCategoryAction.do?method=tree'
			})
		});
		root = new Ext.tree.AsyncTreeNode({
			text : 'Common Platform',
			draggable : false,
			id : 'source'
		});
		tree.on('click', function(node) {
			var blogGrid = Ext.getCmp('user-blog-grid');
			if (node.isLeaf()) {

			} else {
				node.toggle();
			}
			if (blogGrid) {
				blogGrid.store.load({
					params : {
						start : 0,
						limit : 15,
						categoryId : node.id
					}
				});
			}
		});
		tree.setRootNode(root);
		tree.render();
		root.expand();
		portlet.add(tree);
		return portlet;
	}
};