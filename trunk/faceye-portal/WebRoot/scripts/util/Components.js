/**
 * 系统通用的功能,如页头,页尾,用户登陆,用户注册等.
 */

/**
 * 用户登陆页面
 */

com.faceye.compoents.core.security.LoginForm = {
	init : function() {
		var container = com.faceye.ui.Container.init();
		var centerContainer = container
				.getComponent('default-center-body-container');
		var loginForm = new Ext.form.FormPanel({
			labelWidth : 80,
			// url:BP+'j_acegi_security_check',
			frame : true,
			title : '用户登陆',
			// el : 'login-form',
			// title: 'Simple Form',
			bodyStyle : 'padding:5px 5px 0',
			width : 370,
			// defaults: {width: 210},
			// renderTo : centerContainer,
			layout : 'form',
			onSubmit : Ext.emptyFn,
			defaultType : 'textfield',
			submit : function() {
				loginForm.getForm().getEl().dom.action = BP
						+ 'j_acegi_security_check';
				loginForm.getForm().getEl().dom.submit();
			},
			items : [{
				fieldLabel : '帐户',
				name : 'j_username',
				width : 200,
				allowBlank : false,
				vtypeText : '帐户不能为空',
				validateOnBlur:true,
				tabIndex : 1
			}, {
				fieldLabel : '密码',
				width : 200,
				validateOnBlur:true,
				name : 'j_password',
				inputType : 'password',
				tabIndex : 2
			}],
			buttons : [{
				text : '登陆',
				type : 'submit',
				tooltip : 'Login',
				clickEvent : 'click',
				handler : function() {
					loginForm.getForm().submit({
						url : BP+'j_acegi_security_check',
						method : 'POST',
						waitMsg : 'Processing the form now',
						waitTitle : 'Member Login',
						success : function(form, action) {
							Ext.Msg.alert('用户登陆', '登陆成功');
						},
						failiure : function(form, action) {
							if (action.failureType == 'server') {
								var obj = Ext.util.JSON
										.decode(action.response.responseText);
								Ext.Msg.alert('用户登陆', '登陆失败'+obj);
							}else{
								Ext.Msg.alert('用户登陆', '登陆失败');
							}

						}
					});

				}

			}]
		});
		centerContainer.add(loginForm);
		centerContainer.doLayout();
		container.render(Ext.getBody());
	}
};

/**
 *  结束栏目展现
 * 
 */
/**
 * 定义头部导航区域面板
 */
var HeaderPanel = new Ext.Panel({
	id : 'header-panel',
	layout : 'fit',
	region : 'north',
	contentEl : 'default-header',
	border : false

});
com.faceye.ui.HeaderPanel = {
	init : function() {
		var header;
		if (!header) {
			header = new Ext.Panel({
				id : 'header-panel',
				layout : 'fit',
				region : 'north',
				contentEl : 'default-header',
				border : false
			});
		}
		return header;
	}
};
/**
 * 版权部分
 */
var BottomLayout = new Ext.Panel({
	id : 'bottom-layout',
	layout : 'fit',
	margins : '5 5 5 5',
	// bodyStyle:'padding:5px 5px 5px 5px',
	region : 'south',
	// title:'bottom',
	height : 25,
	html : '<p align="center">2008 <a href="http://www.faceye.com"> www.faceye.com</a> All Rights Reserved <a mail="myecsun@hotmail.com">myecsun@hotmail.com</a></p>'
});
com.faceye.ui.BottomLayout = {
	init : function() {
		var bottom;
		if (!bottom) {
			bottom = new Ext.Panel({
				id : 'bottom-layout',
				layout : 'fit',
				style : '{padding:2px 2px 2px 2px}',
				region : 'south',
				// title:'bottom',
				height : 25,
				html : '<p align="center">2008 <a href="http://www.faceye.com"> www.faceye.com</a> All Rights Reserved <a mail="myecsun@hotmail.com">myecsun@hotmail.com</a></p>'
			});
		}
		return bottom;
	}
};
/**
 * log panel定义
 */
com.faceye.ui.LogPanel = {
	init : function() {
		var panel;
		if (!panel) {
			panel = new Ext.Panel({
				id : 'default-log-panel',
				// margins:'2 0 2 0',
				style : '{padding:2px 2px 2px 2px}',
				height : 65,
				contentEl : 'default-log-pic'
			});
		}
		return panel;
	}
};

/**
 * 定义通用的基础框架
 */
com.faceye.ui.Container = {
	init : function() {
		var bottom = com.faceye.ui.BottomLayout.init();
		var header = com.faceye.ui.HeaderPanel.init();
		var log = com.faceye.ui.LogPanel.init();
		var panel = new Ext.Panel({
			id : 'default-center-body-container',
			// bodyStyle : 'padding:5px',
			// margins : '5 5 5 5',
			// layout:'fit',
			style : 'padding:2px 2px 2px 2px',
			header : false
		});
		var container = new Ext.Panel({
			id : 'default-container',
			border : false,
			// layout:'fit',
			items : [header, log, panel, bottom]
		});
		// container.render(Ext.getBody());
		// // viewport.render(Ext.getBody());
		return container;
	}
};

/**
 * 定义一个简单的页面容器 主要去掉log 只留下顶部导航和尾部版权
 */
com.faceye.ui.SimpleContainer = {
	init : function() {
		var bottom = com.faceye.ui.BottomLayout.init();
		var header = com.faceye.ui.HeaderPanel.init();
		// var log = com.faceye.ui.LogPanel.init();
		var panel = new Ext.Panel({
			id : 'default-center-body-container',
			// bodyStyle : 'padding:5px',
			// margins : '5 5 5 5',
			layout : 'fit',
			style : 'padding:2px 2px 2px 2px',
			header : false
		});
		var container = new Ext.Panel({
			id : 'default-container',
			border : false,
			// layout:'fit',
			items : [header, panel, bottom]
		});
		// container.render(Ext.getBody());
		// // viewport.render(Ext.getBody());
		return container;
	}
};
/**
 * portlet 工具条
 */
com.faceye.portal.PortletTools = [{
	id : 'gear',
	qtip:'查看本portlet源码',
	handler : function(e,target,panel) {
	    if(Ext.getCmp('source-win')){
	    	Ext.getCmp("source-win").destroy();
	    }
	    var win=new Ext.Window({
	    	id:'source-win',
	    	layout:'fit',
	    	title:panel.title+'--<font color="green">portlet源码</font>',
	    	modal:true,
	    	width:700,
	    	height:550,
	    	maximizable:true,
	    	minimizable:true,
//	    	minWidth:400,
//	    	minHeight:500,
	    	html:'<iframe id="content-iframe" frameborder="no" style="width:100%;height:500px;" src="'+BP+'default.do?method=forward&forward=jsSource&id='+panel.id.split('_')[0]+'"></iframe>',
	    	autoScroll:false
	    });
//	    win.on('afterlayout',function(w,layout){
//	    	w.load({
//		    	url:BP+'core/source/show-portlet-source-js.jsp?id='+panel.id.split('_')[0],
//		    	 discardUrl: false,
//		    	 nocache: false,
//		    	 text: "正在加载源码,请稍候...",
//		    	 timeout: 30,
//		    	 scripts: false
//		    });
//	    });
	    win.show();
	}
}, {
	id : 'refresh',
	handler : function(e, target, panel) {
		panel.doLayout();
	}
}, {
	id : 'close',
	handler : function(e, target, panel) {
		Ext.Msg.confirm('删除自定义工具', '您确认要删除"' + panel.title + '"吗?', function(
				btn, text) {
			if (btn == 'yes') {
				var portletId = panel.id.split('_')[0];
				var portalId = panel.ownerCt.ownerCt.id;
				// 发送删除数据的请求
				Ext.Ajax.request({
					url : BP
							+ 'portalContainerAction.do?method=removeUserPortletSubscribe',
					failure : function() {
						Ext.Msg.alert('删除自定义工具条', panel.title + '删除失败！');
					},
					success : function() {
						panel.ownerCt.remove(panel, true);
						// Ext.Msg.alert('传统导航删除', '传统导航删除成功！');
					},
					params : {
						portletId : portletId,
						portalId : portalId
					}
				});
			} else {
				return;
			}
		});
	}
}];

/**
 * 向导
 */
com.faceye.Cicerone = {
	msg : function(title, format) {
		function createBox(t, s) {
			return [
					'<div class="msg">',
					'<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
					'<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>',
					t,
					'</h3>',
					s,
					'</div></div></div>',
					'<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
					'</div>'].join('');
		}
		if (!title) {
			title = 'FaceYe小贴士';
		}
		var msgCt;
		if (!msgCt) {
			msgCt = Ext.DomHelper.insertFirst(document.body, {
				id : 'msg-div'
			}, true);
		}
		msgCt.alignTo(document, 't-t', [588, 299]);
		var s = String.format.apply(String, Array.prototype.slice.call(
				arguments, 1));
		var m = Ext.DomHelper.append(msgCt, {
			html : createBox(title, s)
		}, true);
		m.slideIn('t').pause(3).ghost("t", {
			remove : true
		});
	}
};

com.faceye.SingleCicerone = {
	msg : function(title, format) {
		function createBox(t, s) {
			return [
					'<div class="msg">',
					'<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
					'<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>',
					t,
					'</h3>',
					s,
					'</div></div></div>',
					'<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
					'</div>'].join('');
		}
		if (!title) {
			title = 'FaceYe小贴士';
		}
		var msgCt;
		if (!msgCt) {
			msgCt = Ext.DomHelper.insertFirst(document.body, {
				id : 'msg-div'
			}, true);
		}
		msgCt.alignTo(document, 't-tr');
		var s = String.format.apply(String, Array.prototype.slice.call(
				arguments, 1));
		var m = Ext.DomHelper.append(msgCt, {
			html : createBox(title, s)
		}, true);
		m.slideIn('t').pause(2).ghost("t", {
			remove : true
		});
	}
};
/**
 * 判断用户是否已经登陆
 */
com.faceye.ui.UserLogin = {
	is : function() {
		var flag = false;
		var store = new Ext.data.JsonStore({
			url : BP + 'portalContainerAction.do?method=isAtMyPlace',
			root : 'rows',
			autoLoad : true,
			success : true,
			fields : ['login']
		});
		return store;
	}
}