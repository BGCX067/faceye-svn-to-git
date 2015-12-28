var User={
init:function(){
	Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
	var form=new Ext.form.FormPanel({
		            labelWidth: 125, // label settings here cascade unless overridden
                    url:BP+'userAction.do?method=save',
                    frame:true,
                    // title: 'Simple Form',
                    bodyStyle:'padding:5px 5px 0',
                    width: 450,
                    defaults: {width: 230},
                    layout:'form',
                    defaultType: 'textfield',
                    title:'用户注册<div id="message"></div>',
                    renderTo:'user',
                    items:[{
                          fieldLabel: '电子邮件',
                          name: 'name',
                          allowBlank:false,
                          emptyText:'请输入您的有效电子邮件地址',
                          blankText:'电子邮件地址不能为空,否则无法完成注册.',
                          vtype:'emailMask'
                         },{
                         	fieldLabel:'密码',
                         	name:'password',
                         	allowBlank:false,
                         	inputType:'password',
                         	emptyText:'请输入有效密码（6-16位,只能是数字和字母).'
                         },{
                         	fieldLabel:'重复输入密码',
                         	name:'repassword',
                         	allowBlank:false,
                         	inputType:'password',
                         	emptyText:'重复输入密码,两次输入的密码必须一致.'
                         }
                    	],
                    	buttons:[{
                    		text:'注册',
                    		type:'submit',
                    		handler:function(){
                    		   form.getForm().submit({
                    		   	method:'post',
                    		   	waitMsg:'正在注册到系统......',
                    		   	failure:function(){
                    		   		var message=Ext.get("message");
                    		   		message.hightlight();
                    		   	},
                    		   	success:function(){
                    		   		var message=Ext.get("message");
                    		   		message.hightlight();
                    		   	}
                    		   	
                    		   });
                    	}
                    	},{
                    		text:'重置',
                    		type:'submit',
                    		handler:function(){
                    			form.getForm().reset();
                    		}
                    	  }
                    	 ]
	});
}
};