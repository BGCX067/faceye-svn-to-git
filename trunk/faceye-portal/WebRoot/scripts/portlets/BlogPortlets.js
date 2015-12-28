/**
 * www.faceye.com网络支持系统 作者:宋海鹏
 * 我的博客文章，取得并显示当前用户的博客文章。
 * ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683/技术交流群:56927478 说明:Blog porlet
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools
		});
		var store = new Ext.data.Store({
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
						'categoryName', 'categoryId','discusCount','clickCount']
			})
		});
		store.load({
			params : {
				start : 0,
				limit : 15
			}
		});
		function renderTopic(value, metadata, record, rowIndex, colIndex, store) {
			var html = '<p><a href="#" onclick="com.faceye.portal.portlet.ArticleEditForm.detail(\'{0}\')"><span id="user-blog-article-detail-title">{1}</span></a></p>' +
					'<hr id="user-blog-article-hr"/>'
					+ '<div id="user-blog-article-list-small-tool"><a href="#" onclick="com.faceye.portal.portlet.ArticleEditForm.detail(\'{0}\')">评论({5})</a> ' +
							'| <a href="#" onclick="com.faceye.portal.portlet.ArticleEditForm.detail(\'{0}\')">阅读({6})</a> ' +
							'| 固定链接' +
							'| 类别(<a href="#" onclick="com.faceye.portal.portlet.BlogUtil.reloadGridByCategory(\'{4}\')">{2}</a>) ' +
							'｜发表于{3}</div>';
			return String.format(html, record.data.id, record.data.name,
					record.data.categoryName, record.data.createTime,record.data.categoryId,record.data.discusCount,record.data.clickCount);
		}

		var cm = new Ext.grid.ColumnModel([
		// new Ext.grid.CheckboxSelectionModel(),
				{
					id : 'id', // id assigned so we can apply custom css (e.g.
					dataIndex : 'id',
					hidden : true
				}, {
					// header : "标题",
					dataIndex : 'name',
					renderer : renderTopic
				}]);
		// cm.defaultSortable = true;
		var grid = new Ext.grid.GridPanel({
			// el:'topic-grid',
			// renderTo:outGridPanel,
			// title : '我的博客',
			id:'user-blog-grid',
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
			// autoExpandMax:1000,
			// width:900,
			store : store,
			cm : cm,
			bodyStyle : 'width:100%;height:100%;',
			trackMouseOver : false,
			// selectRow:Ext.emptyFn，控制选中的记录是否高亮度显示
			sm : new Ext.grid.RowSelectionModel({
				selectRow : Ext.emptyFn
			}),
			// sm : new Ext.grid.CheckboxSelectionModel(),
			loadMask : true,
			viewConfig : {
				forceFit : true,
				enableRowBody : true,
				showPreview : true,
				getRowClass : function(record, rowIndex, p, store) {
					var xf = Ext.util.Format;
					if (this.showPreview) {
						p.body = '<div id="user-blog-article-content-summary">'
								+ xf.ellipsis(
										xf.stripTags(record.data.content), 500)
								+ '</div>';
						p.body += '<hr  id="user-blog-article-hr"/><div id="user-blog-article-list-small-tool"><p>评论()|阅读()|固定链接|类别('
								+ record.data.categoryName + ')｜发表于'
								+ record.data.createTime + '</p></div>';
						return 'x-grid3-row-expanded';
					}
					p.body += '<hr id="user-blog-article-hr"/><div id="user-blog-article-list-small-tool"><p>评论()|阅读()|固定链接|类别('
							+ record.data.categoryName + ')｜发表于'
							+ record.data.createTime + '</p></div>';
					return 'x-grid3-row-collapsed';
				}
			},
			tbar : [{
				id : 'title-only',
				text : '只看标题',
				iconCls : 'detailChange',
				tooltip : '改变标题的显示方式~',
				handler : function(btn) {
					toggleDetails(btn);
				}
			}],
			bbar : com.faceye.ui.util.PaggingToolBar(15, store)
		});
		// 如果用户已登陆,为用户添加新增文章的权限.
		com.faceye.ui.UserLogin.is().load({
			callback : function(r, options, success) {
				if (r[0].data.login === 'yes') {
					var topToolBar = grid.getTopToolbar();
					topToolBar.add('-',{
						id : 'add',
						text : '撰写新日志',
						tooltip : '添加新日志~~',
						iconCls : 'add',
						handler : function(btn) {
							com.faceye.portal.portlet.ArticleEditForm.to();
						}
					});
//					topToolBar.doLayout();
				}
			}
		});
		// grid.setHeight(500);
		function toggleDetails(btn) {
			var view = grid.getView();
			if (view.showPreview) {
				btn.setText('显示摘要');
				view.showPreview = false;
			} else {
				view.showPreview = true;
				btn.setText('只看标题');
			}
			view.refresh();
		}
		portlet.add(grid);
		return portlet;
	}
};
com.faceye.portal.portlet.BlogUtil={
	reloadGridByCategory:function(id){
		var grid=Ext.getCmp('user-blog-grid');
		grid.store.load({
			params : {
				start : 0,
				limit : 15,
				categoryId:id
			}
		});
	}
};