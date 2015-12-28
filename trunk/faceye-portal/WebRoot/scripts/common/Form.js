/**
 * *****************************************************
 * Form Controller Design
 * Design By:Song Hai Peng
 * E_mail:ecsun@sohu.com
 * QQ:82676683
 * *****************************************************
 */

/**
  * ---------------------------------------------------------------------------------------
  * Define common forms
  * Design By Song Hai Peng
  * version 1.0
  * All Right Reserver www.faceye.com
  * ---------------------------------------------------------------------------------------
  */
  var UserFormController={
  	init:function(){
  		Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';
      var simple = new Ext.form.Form({
        labelWidth: 75, // label settings here cascade unless overridden
        url:BP+'userAction.do?method=save&entityClass=com.faceye.core.service.security.model.User'
    });
    simple.add(
        new Ext.form.TextField({
            fieldLabel: 'UserName',
            name: 'username',
            width:175,
            allowBlank:false
        }),

        new Ext.form.TextField({
            fieldLabel: 'Password',
            name: 'password',
            width:175
        }),
        new Ext.form.TextField(
        {
        	fieldLabel:'Re Password',
            name:'repassword',
            width:175
        }
        )
        	
    );

    simple.addButton('Save',function(){simple.submit();return true;});
    simple.addButton('Cancel');
    simple.render('form-ct');
  	}
  };
  