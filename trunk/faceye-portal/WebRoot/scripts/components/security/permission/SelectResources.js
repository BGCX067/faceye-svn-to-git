com.faceye.compoents.core.security.SelectResources ={
	init:function(permissionId){
		Ext.QuickTips.init();
        Ext.form.Field.prototype.msgTarget = 'side';
//        var url=location.search;
//	    var params=Ext.urlDecode(url.substring(1));
//	    var userId='';
//	    if(params.userId){
//		userId=params.userId;
//	    }
	    /**
	     * 取得用户未拥有的角色,准备进行授权
	     */
      var fromStore = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: BP+'resourceAction.do?method=getResourcesForPermission&exists=false&permissionId='+permissionId
        }),
        reader: new Ext.data.JsonReader({
            root: 'root',
            totalProperty: 'total',
            id: 'id',
            fields: [
               'id','name'
            ]
        }
        )
      });
  
      /**
       * 取得用户已存在的角色
       */
        var toStore = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: BP+'resourceAction.do?method=getResourcesForPermission&exists=true&permissionId='+permissionId
        }),
        reader: new Ext.data.JsonReader({
            root: 'root',
            totalProperty: 'total',
            id: 'id',
            fields: [
               'id','name'
            ]
        }
        )
      });
          
      fromStore.load();
      toStore.load();
		var formItemSelector =new Ext.form.FormPanel({ 
				labelWidth: 25,
				labelAlign:'top',
				width:450,
				url:BP+'permissionAction.do?method=permission',
//				margins:'50 50 50 50',
//                autoScroll:true,
                bodyStyle:'padding:5px 5px 5px 5px',
				items:[{
					xtype:"itemselector",
//					url:BP+'userAction.do?method=permission&permissionId='+permissionId,
					name:"resourceIds",
//                    autoScroll:true,
					fieldLabel:"为权限选择资源",
//					bodyStyle:'padding:5px 15px 15px 15px',
//                    margins:'15 15 15 15',
					dataFields:["id", "name"],
//					fromData:[[123,"One Hundred Twenty Three"],
//						["1", "One"], ["2", "Two"], ["3", "Three"], ["4", "Four"], ["5", "Five"],
//						["6", "Six"], ["7", "Seven"], ["8", "Eight"], ["9", "Nine"]],
//					toData:[["10", "Ten"]],
                    fromStore:fromStore,
                    toStore:toStore,
					msWidth:200,
					msHeight:200,
					valueField:"id",
					displayField:"name",
					//imagePath:"ext-ux/multiselect",
					//switchToFrom:true,
					toLegend:"已有资源",
					fromLegend:"待选资源",
					toTBar:[{
						text:"清除",
						handler:function(){
							var i=formItemSelector.getForm().findField("resourceIds");
							i.reset.call(i);
						}
					}]
				}]
			});
			
			var win;
			if(!win){
				win=new Ext.Window({
            			layout:'fit',
            			//模式窗口
            			modal:true,
            			width:450,
                        height:300,
                        closeAction:'hide',
                        plain: true,
//                        margins:'5 5 5 5',
                        title:'为权限选择资源',
                        buttonAlign:'center',
                        buttons: [{
                           text:'确定',
//                           scope:com.faceye.compoents.core.security.SelectResources,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                           formItemSelector.getForm().submit({
                           		method:'POST',
                           		params:{
                           		permissionId:permissionId
                           		},
                           		waitMsg:'正在保存数据...',	
                           		success:function(form,action){
                           			Ext.Msg.alert('权限选择资源','权限选择资源成功!');
                           			this.disabled=false; 
                           			win.hide();
                           			//重新加载数据到grid
                           			store.load();
                           		},
                           		failure:function(){
                           			Ext.Msg.alert('权限选择资源','权限选择资源失败!');
                           			this.disabled=false;
                           		}
                           	 }
                           	);
                           }
                              },{
                           text: '放弃',
                           handler: function(){
                           	　 formItemSelector.getForm().reset();
                              win.hide();
                               }
                          }
                          ]
            		});
            		win.add(formItemSelector);
			}
			win.show();
//			formItemSelector.render(Ext.getBody());
	}
};
	 