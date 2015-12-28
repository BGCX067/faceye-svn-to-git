/**
 * Feed Subscribe
 */
Ext.ns('com.faceye.components.rss');
com.faceye.components.rss.FeedSubscribePanel = {
	init : function() {

		var panel = new Ext.Panel({
			id : 'feed-subscribe-panel',
			title : 'Fit Layout',
			region : 'center',
			layout : 'fit',
			margins : '0 5 5 0',
			items : {
				title : 'Inner Panel',
				html : '<p>This is the inner panel content</p>',
				border : true
			}
		});
		var viewPort = Ext.getCmp('feed-viewport');
		// var mainPanel=Ext.getCmp('preview');
		var mainPanel = viewPort.findById('main-tabs');
		// mainPanel.hide();
		// mainPanel.destroy();
		viewPort.remove(mainPanel);
		viewPort.add(panel);
		panel.show();
		viewPort.doLayout();
//		alert(viewPort.findById('feed-subscribe-panel').title);
	}
};
Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
com.faceye.components.rss.Util = {
	rssSubscribe : function(rssId) {
		var imgElement = Ext.get(rssId + '_img');
		var win = Ext.getCmp('subscribe-feed-win');
		if(win){
			win.destroy();
		}
		var tree = Ext.getCmp('subscribeSelectTree');
		if (!tree) {
			if(!Ext.get('subscribe-select-tree')){
				Ext.getBody().createChild({tag:'div',id:'subscribe-select-tree'});
			}
			tree = new Ext.tree.TreePanel({
				el : 'subscribe-select-tree',
				id : 'subscribeSelectTree',
				autoScroll : true,
				animate : true,
				enableDD : true,
				containerScroll : true,
				rootVisible : false,
				loader : new Ext.tree.TreeLoader({
					dataUrl : BP+'userResourceCategoryAction.do?method=userResourceCategoryOnlyTree'
				})
			});
			tree.on('click', function(node) {
				Ext.Ajax.request({
					url : BP+'feedAction.do?method=subscribe',
					params : {
						feedId : rssId,
						categoryId : node.id
					},
					success : function(response, options) {
						var beHideTree = Ext.getCmp('subscribeSelectTree');
						beHideTree.hide();
						var data = {
							name : '添加到"' + node.text + '成功!',
							url : BP+'userResourceCategoryAction.do?method=myFeedHome',
							linkName : '去我的频道看看'
						}
						var tpl = new Ext.XTemplate('<p>{name}</p>',
								'<p><a href="{url}">{linkName}</a></p>');
						tpl.overwrite(win.body, data);
						// 用于成功订阅后替换rss前的图片
						// 取得未订阅时rss前的图片
						var imgElement = Ext.get(rssId + '_img');
						imgElement.set({
							src : BP+'components/navigation/feed/feed-viewer/images/rss_added.gif'
						})
					}
				});
			});
			var root = new Ext.tree.AsyncTreeNode({
				text : '资源分类',
				draggable : false,
				id : 'source'
			});
			tree.setRootNode(root);
			tree.render();
			tree.expandAll();
			root.expand();

		}
			win = new Ext.Window({
				id : 'subscribe-feed-win',
				layout : 'fit',
				modal : true,
				closable : false,
				title : '订阅到...',
				width : 120,
				height : 200,
				autoScroll : true,
				pageX : imgElement.getX(),
				shadow : false,
				pageY : imgElement.getY() + 15,
				plain : true,
				buttons : [{
					text : '关闭',
					handler : function(btn) {
						win.hide(this);
					}
				}]
			});
			win.add(tree);
		win.setPagePosition(imgElement.getX(), imgElement.getY() + 15);
		win.show(this);

	}
};