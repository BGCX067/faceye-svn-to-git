/**
 * 博客本周点击排行榜.
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools
		});
		var store = new Ext.data.Store({
			 baseParams : {
			 timePeriod : 'month'
			 },
			proxy : new Ext.data.HttpProxy({
				url : BP + 'blogManagerAction.do?method=blogClickOrderList'
			}),
			reader : new Ext.data.JsonReader({
				root : 'root',
				totalProperty : 'total',
				id : 'id',
				fields : ['blog_click_count', 'portal_id', 'user_id',
						'username']
			})
		});
		store.load({
			params : {
				start : 0,
				limit : 15
			}
		});
function renderTopic(value, metadata, record, rowIndex, colIndex, store) {
			var html = '<p><a href="'+BP+'portalContainerAction.do?method=my&user={2}">{0}({1})</a></p>';
			return String.format(html, record.data.username, record.data.blog_click_count,record.data.username);
		}
		var cm = new Ext.grid.ColumnModel([
		// new Ext.grid.CheckboxSelectionModel(),
				{
					id : 'user_id', // id assigned so we can apply custom css
									// (e.g.
					dataIndex : 'user_id',
					hidden : true
				}, {
					// header : "标题",
					dataIndex : 'username',
				 renderer : renderTopic
				}]);

		var grid = new Ext.grid.GridPanel({
			// el:'topic-grid',
			// renderTo:outGridPanel,
			// title : '我的博客',
			id : 'user-blog-week-click-order-list-grid_'+Ext.id,
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
				showPreview : true
			},
//			tbar : [{
//				id : 'title-only',
//				text : '只看标题',
//				iconCls : 'detailChange',
//				tooltip : '改变标题的显示方式~',
//				handler : function(btn) {
//					toggleDetails(btn);
//				}
//			}],
			bbar : com.faceye.ui.util.PaggingToolBar(15, store)
		});
		portlet.add(grid);
		return portlet;
	}
};