/*
 * Ext JS Library 2.0.2 Copyright(c) 2006-2008, Ext JS, LLC. licensing@extjs.com
 * 
 * http://extjs.com/license
 */

FeedGrid = function(viewer, config) {
	this.viewer = viewer;
	Ext.apply(this, config);

	this.store = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : BP+'feedAction.do?method=parseFeed'
		}),

		reader : new Ext.data.XmlReader({
			record : 'item'
		}, ['title', 'author', {
			name : 'pubDate',
			type : 'date'
		}, 'link', 'description', 'content'])
	});
	this.store.setDefaultSort('pubDate', "DESC");

	this.columns = [{
		id : 'title',
		header : "标题",
		dataIndex : 'title',
		sortable : true,
		width : 420,
		renderer : this.formatTitle
	}, {
		header : "作者",
		dataIndex : 'author',
		width : 100,
		hidden : true,
		sortable : true
	}, {
		id : 'last',
		header : "发布日期",
		dataIndex : 'pubDate',
		width : 150,
		renderer : this.formatDate,
		sortable : true
	}];

	FeedGrid.superclass.constructor.call(this, {
		region : 'center',
		id : 'topic-grid',
		border:false,
		hideHeaders : true,
		loadMask : {
			msg : '正在加载...'
		},

		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),
		viewConfig : {
			forceFit : true,
			enableRowBody : true,
			showPreview : 0,
			getRowClass : this.applyRowClass
		}
	});

	this.on('rowcontextmenu', this.onContextClick, this);
};

Ext.extend(FeedGrid, Ext.grid.GridPanel, {

	onContextClick : function(grid, index, e) {
		if (!this.menu) { // create context menu on first right click
			this.menu = new Ext.menu.Menu({
				id : 'grid-ctx',
				items : [{
					text : '在新标签中查看',
					iconCls : 'new-tab',
					scope : this,
					handler : function() {
						this.viewer.openTab(this.ctxRecord);
					}
				}, {
					iconCls : 'new-win',
					text : '去原文地址',
					scope : this,
					handler : function() {
						window.open(this.ctxRecord.data.link);
					}
				}, '-', {
					iconCls : 'refresh-icon',
					text : '刷新',
					scope : this,
					handler : function() {
						this.ctxRow = null;
						this.store.reload();
					}
				}]
			});
			this.menu.on('hide', this.onContextHide, this);
		}
		e.stopEvent();
		if (this.ctxRow) {
			Ext.fly(this.ctxRow).removeClass('x-node-ctx');
			this.ctxRow = null;
		}
		this.ctxRow = this.view.getRow(index);
		this.ctxRecord = this.store.getAt(index);
		Ext.fly(this.ctxRow).addClass('x-node-ctx');
		this.menu.showAt(e.getXY());
	},

	onContextHide : function() {
		if (this.ctxRow) {
			Ext.fly(this.ctxRow).removeClass('x-node-ctx');
			this.ctxRow = null;
		}
	},

	loadFeed : function(url) {
		this.store.baseParams = {
			feed : url
		};
		this.store.load();
	},

	togglePreview : function(show) {
		this.view.showPreview = show;
		this.view.refresh();
	},

	// within this function "this" is actually the GridView
	applyRowClass : function(record, rowIndex, p, ds) {
		if (this.showPreview == 1) {
			var xf = Ext.util.Format;
			p.body = '<p>'
					+ xf.ellipsis(xf.stripTags(record.data.description), 200)
					+ '</p>';
			p.body += '<strong>推荐 收藏 发给朋友 转到小组</strong>';
			return 'x-grid3-row-expanded';
		} else if (this.showPreview == 2) {
			p.body = '<p>' + record.data.description + '</p>';
			p.body += '<strong>推荐 收藏 发给朋友 转到小组</strong>';
			return 'x-grid3-row-expanded';
		} else if (this.showPreview == 0) {
			return 'x-grid3-row-collapsed';
		} else {
			return 'x-grid3-row-collapsed';
		}
		// if(this.full){
		// p.body = '<p>' + record.data.description + '</p>';
		// p.body+='<strong>推荐 收藏 发给朋友 转到小组</strong>';
		// return 'x-grid3-row-expanded';
		// }
		// return 'x-grid3-row-collapsed';
	},

	formatDate : function(date) {
		if (!date) {
			return '';
		}
		var now = new Date();
		var d = now.clearTime(true);
		var notime = date.clearTime(true).getTime();
		if (notime == d.getTime()) {
			return 'Today ' + date.dateFormat('g:i a');
		}
		d = d.add('d', -6);
		if (d.getTime() <= notime) {
			return date.dateFormat('D g:i a');
		}
		return date.dateFormat('n/j g:i a');
	},

	formatTitle : function(value, p, record) {
		return String
				.format(
						'<div class="topic"><a href="#" onclick="onRowHrefClick(\''
								+ record
								+ '\')"><b>{0}</b></a><a href="#" onclick="toOriginalArticle(\''
								+ record + '\')"><b>查看原文</b></a><span class="author">{1}</span></div>', value,
						record.data.author, record.id, record.data.forumid);
	}
});
/**
 * 去原文地址
 */
function toOriginalArticle(record) {
	var mainPanel = Ext.getCmp('main-tabs');
	record = (record && record.data) ? record : mainPanel.gsm.getSelected();
	window.open(record.data.link);
}
/**
 * 当点击链接时，在新标签页中打开明细信息。
 */
function onRowHrefClick(record) {
	var mainPanel = Ext.getCmp('main-tabs');
	record = (record && record.data) ? record : mainPanel.gsm.getSelected();
	var d = record.data;
	var id = !d.link ? Ext.id() : d.link.replace(/[^A-Z0-9-_]/gi, '');
	var tab;
	if (!(tab = mainPanel.getItem(id))) {
		tab = new Ext.Panel({
			id : id,
			cls : 'preview single-preview',
			title : d.title,
			tabTip : d.title,
			html : FeedViewer.getTemplate().apply(d),
			closable : true,
			listeners : FeedViewer.LinkInterceptor,
			autoScroll : true,
			border : true,
			tbar : [{
				text : '查看原文',
				iconCls : 'new-win',
				handler : function() {
					window.open(d.link);
				}
			}]
		});
		mainPanel.add(tab);
	}
	mainPanel.setActiveTab(tab);
}