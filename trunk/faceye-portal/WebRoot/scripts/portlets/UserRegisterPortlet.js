/**
 * 用户注册
 */
com.faceye.portal.portlet.SinglePortlet = {
	init : function(id, name) {
		var portlet = new Ext.ux.Portlet({
			id : id + '_' + Ext.id(),
			title : name,
			tools : com.faceye.portal.PortletTools
		});
		Ext.form.Field.prototype.msgTarget = 'under';
		var registerForm = new Ext.FormPanel({
			labelWidth : 100, // label settings here cascade unless overridden
			url : BP + 'userAction.do?method=save',
			frame : true,
			bodyStyle : 'padding:5px 5px 0',
			autoWidth : true,
			monitorValid : true,
			layout : 'form',
			defaultType : 'textfield',
			items : [{
				id : 'user-register-username',
				fieldLabel : '<font color="red">*</font>用户名',
				name : 'username',
				validateOnBlur : true,
				width : 200,
				vtype : 'alphanum',
				maxLength : 25,
				maxLengthText : '名称长度不能超过25个有效字符!',
				allowBlank : false,
				blankText : '用户名不能为空!',
				vtypeText : '用户名只能包含数字(0~9)、字母(aA~Zz),不能包含其它特殊字符!'
//				plugins : [Ext.ux.plugins.RemoteValidator],
//				rvOptions : {
//					url : BP + 'userAction.do?method=isUserNameExists&username='+getValue()
//
//				}
			}, {
				id : 'user-register-password',
				fieldLabel : '<font color="red">*</font>密码',
				name : 'password',
				minLength : 6,
				width:200,
				inputType : 'password',
				validateOnBlur : true,
				minLengthText : '密码长度为6-18位!',
				maxLength : 18,
				allowBlank : false,
				maxLength : '密码长度为6-18位!',
				blankText : '密码不能为空!'
			}, {
				fieldLabel : '<font color="red">*</font>重复输入密码',
				name : 'repassword',
				minLength : 6,
				width:200,
				allowBlank : false,
				validateOnBlur : true,
				inputType : 'password',
				minLengthText : '密码长度为6-18位!',
				maxLength : 18,
				validator : function() {
					if (this.getValue() != Ext.getCmp('user-register-password')
							.getValue()) {
						return false;
					} else {
						return true;
					}
				},
				invalidText : '两次输入的密码不一致，请确认重新输入!',
				maxLength : '密码长度为6-18位!',
				blankText : '第二次输入密码不能为空!'
			}, {
				fieldLabel : '<font color="red">*</font>电子邮件',
				width : 200,
				name : 'email',
				vtype : 'email',
				 validateOnBlur : true,
				maxLength : 100,
				minLengthText : '电子邮件地址长度不能超过100个有效字符!',
				allowBlank : false,
				vtypeText : '请输入合法的电子邮件地址(例如:faceye@sohu.com)!',
				blankText : '电子邮件不能为空!'
//				plugins : [Ext.ux.plugins.RemoteValidator],
//				rvOptions : {
//					url : BP + 'userAction.do?method=isEmailExists',
//					params : {
//						email : 'ecsun@sohu.com'
//					}
//				}
			}, {
				name : 'id',
				hidden : true,
				hideLabel : true
			}],
			buttons : [{
				text : '注册',
				formBind : true,
				handler : function() {
					registerForm.form.submit({
						success : function(form, action) {
							waitMsg : '正在注册,请稍后...'
							Ext.Msg.alert('用户注册', '注册成功');
							form.reset();
							window.location = BP
									+ 'default.do?method=forward&forward=login'
						},
						failure : function(form, action) {
							Ext.Msg.alert('用户注册', '注册失败');
						}
					});
				}

			}]
		});
		portlet.add(registerForm);
		return portlet;
	}
};