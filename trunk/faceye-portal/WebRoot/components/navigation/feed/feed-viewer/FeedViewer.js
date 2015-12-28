/*
 * Ext JS Library 2.0.2 Copyright(c) 2006-2008, Ext JS, LLC. licensing@extjs.com
 * 
 * http://extjs.com/license
 */

FeedViewer = {};

Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.state.Manager.setProvider(new Ext.state.SessionProvider({
		state : Ext.appState
	}));
	var tpl = Ext.Template.from('preview-tpl', {
		compiled : true,
		getBody : function(v, all) {
			return Ext.util.Format.stripScripts(v || all.description);
		}
	});
	FeedViewer.getTemplate = function() {
		return tpl;
	}
	var feeds = new FeedPanel();
	/**
	 * 当点击节点时
	 */
	feeds.on('click', function(node) {
		if (!node.isLeaf()) {
			node.toggle();
		} else {
			if (!mainPanel.isVisible()) {
				mainPanel.show();
			}
		}
	});
	/**
	 * 当拖动节点时
	 */
	feeds.on('movenode', function(tree, node, oldParent, newParent, index) {
		var oldParentId = '';
		var newParentId = '';
		var nodeId = '';
		if (oldParent) {
			oldParentId = oldParent.id;
		}
		if (newParent) {
			newParentId = newParent.id;
		}
		if (node) {
			nodeId = node.id;
		}
		Ext.Ajax.request({
			url : BP+'userResourceCategoryAction.do',
			params : {
				method : 'order',
				nodeId : nodeId,
				oldParentId : oldParentId,
				newParentId : newParentId,
				index : index
			}
		});
	});
	/**
	 * 对面板的显示及隐藏进行控制
	 */
	function containerManager(container, showPanel) {
		if (!showPanel.isVisible()) {
			showPanel.show();
		}
		if (!container.findById(showPanel.id)) {
			container.add(showPanel);
		}
		for (var i = 0; i < container.items.length; i++) {
			if (container.items.itemAt(i).id !== showPanel.id
					&& container.items.itemAt(i).isVisible()) {
				container.items.itemAt(i).hide();
			}
		}
		container.doLayout();
	}
	var mainPanelContainer = new Ext.Panel({
		id : 'main-panel-container',
		autoScroll : false,
		layout : 'fit',
		margins:'3 3 3 0',
		border : true,
		region : 'center'
	});
	var mainPanel = new MainPanel();
	feeds.on('feedselect', function(feed) {
		mainPanel = Ext.getCmp('main-tabs');
		var blogManagerPanel = Ext.getCmp('user-blog-manager-panel');
		if (!mainPanel) {
			mainPanel = new MainPanel();
		}
		containerManager(mainPanelContainer, mainPanel);
		mainPanel.loadFeed(feed);
	});
	var leftControllerPanel = new Ext.Panel({
		region : 'west',
		id : 'west-panel',
		title : '我的操作区',
		split : true,
		width : 180,
		minSize : 150,
		maxSize : 250,
		collapsible : true,
		border:false,
		margins : '5 0 5 5',
		cmargins : '5 5 5 5',
		layout : 'accordion',
		layoutConfig : {
			animate : true
		},
		items : [feeds, {
			title : '我的博客',
//			border : false,
			autoScroll : true
		}]
	});
	if (!Ext.get('user-blog-manager-tree')) {
		Ext.getBody().createChild({
			tag : 'div',
			id : 'user-blog-manager-tree',
			style : 'overflow:auto;border:0px solid #c3daf9;'
		})
	}
	var tree = new Ext.tree.TreePanel({
		id : 'user-blog-manager-tree-panel',
		el : 'user-blog-manager-tree',
		header : false,
		autoScroll : true,
		autoHeight : true,
		animate : true,
		border : false,
		lines:true,
        useArrows:true,
		enableDD : false,
		containerScroll : true,
		rootVisible : false,
		loader : new Ext.tree.TreeLoader({
			dataUrl : BP + 'blogManagerAction.do?method=blogManagerTree'
		})
	});
	root = new Ext.tree.AsyncTreeNode({
		text : 'Common Platform',
		draggable : false,
		id : 'source'
	});
	tree.setRootNode(root);
	root.expand();
	tree.on('click', function(node) {
		if (node.isLeaf()) {

			if (node.id == '1') {
				var tree = com.faceye.portal.portlet.BlogArticleCategory.init();
				containerManager(mainPanelContainer, tree);
			}
			if (node.id == '2') {
				var blogManagerPanel = Ext.getCmp('user-blog-manager-panel');
				if (!blogManagerPanel) {
					blogManagerPanel = com.faceye.portal.portlet.BlogManagerPanel
							.init();
				}
				containerManager(mainPanelContainer, blogManagerPanel);
			}
		}
	});
	leftControllerPanel.items.itemAt(1).add(tree);
	mainPanelContainer.add(mainPanel);
	mainPanelContainer.doLayout();
	var viewport = new Ext.Viewport({
		layout : 'border',
		id : 'feed-viewport',
		items : [com.faceye.ui.HeaderPanel.init(), leftControllerPanel,
				mainPanelContainer]
	});

});
// This is a custom event handler passed to preview panels so link open in a new
// windw
FeedViewer.LinkInterceptor = {
	render : function(p) {
		p.body.on({
			'mousedown' : function(e, t) { // try to intercept the easy way
				t.target = '_blank';
			},
			'click' : function(e, t) { // if they tab + enter a link, need to
				// do it old fashioned way
				if (String(t.target).toLowerCase() != '_blank') {
					e.stopEvent();
					window.open(t.href);
				}
			},
			delegate : 'a'
		});
	}
};
