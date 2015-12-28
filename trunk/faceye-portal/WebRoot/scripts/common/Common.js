var DataType={
	init:function(){
		 	 // create the Data Store
    var store = new Ext.data.Store({
        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
        proxy: new Ext.data.HttpProxy({
            url: BP+'dataTypeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType'
        }),

        // create reader that reads the Topic records
        reader: new Ext.data.JsonReader({
            root: 'root',
            totalProperty: 'total',
            id: 'v0v',
            fields: [
               'v0v','v1v','v2v'
            ]
        }
        )

        // turn on remote sorting
       // remoteSort: true
    });
   // store.setDefaultSort('lastpost', 'desc');
    // pluggable renders
    function renderTopic(value, p, record){
//        return String.format(
//                '<b><a href="http://extjs.com/forum/showthread.php?t={2}" target="_blank">{0}</a></b><a href="http://extjs.com/forum/forumdisplay.php?f={3}" target="_blank">{1} Forum</a>',
//                value, record.data.forumtitle, record.id, record.data.forumid);
//         return new Ext.Button({
//         	autoShow:true,
//         	text:record.data.v1v,
//         	handler:function(){
//         		alert('click me');
//         	}
//         })
          //取得当前正在点击的记录的ID
          var id=record.data.v0v;
          return String.format('<a href="#" onclick="onClickLink(\''+id+'\')">{0}</a>',record.get('v1v'));
    }
  
    function renderLast(value, p, r){
        return String.format('{0}<br/>by {1}', value.dateFormat('M j, Y, g:i a'), r.data['lastposter']);
    }
    

   // the column model has information about grid columns
    // dataIndex maps the column to the specific data field in
    // the data store
    var cm = new Ext.grid.ColumnModel([
        new Ext.grid.CheckboxSelectionModel(),{
        	id: 'v0v', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
        	header:'ID',
        	dataIndex:'v0v',
        	hidden:true
        },
        {
           header: "名字",
           dataIndex: 'v1v',
           renderer:renderTopic
        },{
           header: "类名",
           dataIndex: 'v2v',
           hidden: false
        }
        ]);

    // by default columns are sortable
    cm.defaultSortable = true;
    var win;
    var grid = new Ext.grid.GridPanel({
        el:'topic-grid',
        title:'数据类型',
        autoHeight:true,
        loadMask:true,
        stripeRows: true,
        trackMouseOver:true,
        layoutConfig:{
        	  autoWidth:true
        },
        //autoExpandMax:1000,
        //width:900,
        store: store,
        cm: cm,
        trackMouseOver:false,
        //selectRow:Ext.emptyFn，控制选中的记录是否高亮度显示
       // sm: new Ext.grid.RowSelectionModel({selectRow:Ext.emptyFn}),
        sm:new Ext.grid.CheckboxSelectionModel(),
        loadMask: true,
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true
//            getRowClass : function(record, rowIndex, p, store){
//                if(this.showPreview){
//                    p.body = '<p>'+record.data.v1v+'</p>';
//                    return 'x-grid3-row-expanded';
//                }
//                return 'x-grid3-row-collapsed';
//            }
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示的是 {0} - {1} of {2}',
            emptyMsg: "没有可以显示的结果集",
            items:[
                '-', {
                pressed: true,
                enableToggle:true,
                text: 'Show Preview',
                cls: 'x-btn-text-icon details',
                toggleHandler: toggleDetails
            }]
        }),
        tbar:[{
        	text:'添加',
            tooltip:'Add a new row',
            iconCls:'add',
            handler:function(){
            	/**
            	 * ----------------Start Add button config-----------------------
            	 */      	 
            	if(!win){
                 var innerForm=new Ext.FormPanel({
            	 	labelWidth: 125, // label settings here cascade unless overridden
                    url:'http://localhost:8088/faceye/dataTypeAction.do?method=save',
                    frame:true,
                    // title: 'Simple Form',
                    bodyStyle:'padding:5px 5px 0',
                    width: 450,
                    defaults: {width: 230},
                    layout:'form',
                    defaultType: 'textfield',
                    items: [{
                             fieldLabel: 'Name',
                             name: 'name',
                             allowBlank:false
                           },{
                             fieldLabel: 'Type Name',
                             name: 'typeName'
                           },{
                             fieldLabel: 'Type Num In SQL',
                             name: 'typeNumInSql'
                           },{
                             fieldLabel: 'typeOfClass',
                             name: 'typeOfClass'
                            //vtype:'email'
                           }
                          ]
            	    });
            		win=new Ext.Window({
            			layout:'fit',
            			width:450,
                        height:200,
                        closeAction:'hide',
                        plain: true,
                        buttonAlign:'center',
                        buttons: [{
                           text:'Submit',
                           scope:DataType,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                           innerForm.getForm().submit({
                           		method:'POST',
                           		waitMsg:'Saving Data Now',
                           		//reset:BP+'dataTypeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
                           		success:function(form,action){
//                                    grid.getView().refresh();
                           			form.reset();
                           			Ext.Msg.alert('数据类型保存','数据类型保存成功!');
                           			this.disabled=false; 
                           			win.hide();
                           			//重新加载数据到grid
                           			store.load();
                           		},
                           		failure:function(){
                           			Ext.Msg.alert('数据类型保存','数据类型保存失败!');
                           			this.disabled=false;
                           		}
                           	 }
                           	);
                           }
                              },{
                           text: 'Close',
                           handler: function(){
                              win.hide();
                               }
                          },{
                          	text:'重置',
                          	handler:function(){
                          		innerForm.getForm().reset();
                          	}
                          }
                          ]
            		});
            		win.add(innerForm);
            	    
            	}
        		win.show(this);
         
            	
            	 /**
            	  * ---------------End Add button config------------------------
            	  */
            	
               }
        },{
        	text:'编辑',
        	tooltip:'编辑选中的记录，一次只可以编辑一条。',
            iconCls:'option',
            handler:function(){
            	
            	var selectionModel=grid.getSelectionModel();
            	//取得共选择了多少条记录。
            	var selectedCount=selectionModel.getCount();
            	if(selectedCount==0){
            		Ext.Msg.alert('编辑数据','您没有选中任何数据，请选择您要编辑的数据！');
            		return;
            	}else if(selectedCount>1){
            		Ext.Msg.alert('编辑数据','您只能选择一条数据，不能同时选择多条数据，请选择您要编辑的数据！');
            		return;
            	}else{
            		//取得被选中的数据
            		var record=selectionModel.getSelected();
            		//取得被选中数据的主键
            		var id=record.id;
                    var updateWin;
                    
                    if(!updateWin){
                    	var updateForm=new Ext.FormPanel({
            	 	labelWidth: 125, // label settings here cascade unless overridden
                    url:BP+'dataTypeAction.do?method=save',
                    frame:true,
                    // title: 'Simple Form',
                    bodyStyle:'padding:5px 5px 0',
                    width: 450,
                    defaults: {width: 230},
                    layout:'form',
                    defaultType: 'textfield',
                    reader:new Ext.data.JsonReader({
                        root: 'rows',
//                        totalProperty: 'total',
                        success:true,
                       fields: [
                             'id','name','typeName','typeNumInSql','typeOfClass'
                            ]
                        }
                     ),
                    items: [
                           {
                           	xtype:'hidden',
                            fieldLabel:'ID',
                            name:'id'	
                            },
                          {
                             fieldLabel: 'Name',
                             name: 'name',
                             allowBlank:false
                           },{
                             fieldLabel: 'Type Name',
                             name: 'typeName'
                           },{
                             fieldLabel: 'Type Num In SQL',
                             name: 'typeNumInSql'
                           },{
                             fieldLabel: 'typeOfClass',
                             name: 'typeOfClass'
                            //vtype:'email'
                           }
                          ]
            	    });
            	  
                    	updateWin=new Ext.Window({
            			layout:'fit',
            			width:450,
                        height:200,
                        closeAction:'hide',
                        plain: true,
                        buttonAlign:'center',
                        buttons: [{
                           text:'提交',
                           scope:DataType,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                           updateForm.getForm().submit({
                           		method:'POST',
                           		waitMsg:'正在保存数据......',
                           		//reset:BP+'dataTypeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
                           		success:function(form,action){
//                                    grid.getView().refresh();	
                           			Ext.Msg.alert('数据类型保存','数据类型保存成功!');
                           			form.reset();
                           			this.disabled=false; 
                           			updateWin.hide();
                           			//重新加载数据到grid
                           			store.load();
                           		},
                           		failure:function(){
                           			Ext.Msg.alert('数据类型保存','数据类型保存失败!');
                           			this.disabled=false;
                           		}
                           	 }
                           	);
                           }
                              },{
                           text: '关闭',
                           handler: function(){
                              updateWin.hide();
                               }
                          },{
                          	text:'放弃',
                          	handler:function(){
                          		updateForm.getForm().reset();
                          		updateWin.hide();
                          	}
                          }
                          ]
            		});
                    updateWin.add(updateForm);
            		updateForm.getForm().load({
            	    	url:BP+'dataTypeAction.do?method=detail&entityClass=com.faceye.core.componentsupport.dao.model.DataType&id='+id,
            	    	waitMsg:'正在加载数据，请稍后...'
            	    });
                    }
                    updateWin.show(this);
//                    loadStore.load();
//                    alert(loadStore.getTotalCount());
//                    var loadRecord=loadStore.getAt(0);
//                    alert(loadRecord.name)
                    //alert(loadRecord.id);
                   
            	}
            }
        },{
        	text:'删除',
            tooltip:'删除选中的记录，一次可以删除多条。',
            iconCls:'remove',
            handler:function(){
            	var selectionModel=grid.getSelectionModel();
            	//取得共选择了多少条记录。
            	var selectedCount=selectionModel.getCount();
            	if(selectedCount==0){
            		Ext.Msg.alert('删除操作','您没有选中要删除的数据，请选择您准备删除的数据');
            		return;
            	}else{
            		//取得要删除的数据的ID
            		Ext.Msg.confirm('删除数据','您确认要删除选中的数据吗?',function(){
            			alert(button.id);
            		var records = selectionModel.getSelections();
            		var _ids='';
            		for(var i=0;i<records.length;i++){
            			_ids+=records[i].id;
            		    _ids+='_';
            		    }
            		//发送删除数据的请求		
            		Ext.Ajax.request({
            			url:BP+'dataTypeAction.do?method=remove',
            			failure:function(){
            				Ext.Msg.alert('数据类型删除','数据类型删除失败！');
            			},
            			success:function(){
            				Ext.Msg.alert('数据类型删除','数据类型删除成功！');
            			},
            			params:{
            				entityClass:'com.faceye.core.componentsupport.dao.model.DataType',
            				ids:_ids
            			}
            		});
            		store.load();
            		});
            	}
            	
            }
        }
        ]
        
    });
    grid.render();
    // trigger the data store load
    store.load({params:{start:0, limit:15}});
    function toggleDetails(btn, pressed){
        var view = grid.getView();
        view.showPreview = pressed;
        view.refresh();
    }
	}
};

  function onClickLink(id){
    	var win;
    	if(!win){
    		win=new Ext.Window({
            			layout:'fit',
            			width:450,
                        height:200,
                        closeAction:'hide',
                        title:'数据类型明细',
                        plain: true,
                        buttonAlign:'center',
                        buttons:[{
                        	text:'关闭',
                            type:'submit',
                            disabled:false,
                            handler:function(){
                            	   win.hide(this);
                                    }
                               }
                        	]
                        }
                        );
               var form=new Ext.FormPanel({
            	 	labelWidth: 125, // label settings here cascade unless overridden
                    url:BP+'dataTypeAction.do?method=save',
                    frame:true,
                    // title: 'Simple Form',
                    bodyStyle:'padding:5px 5px 0',
                    width: 450,
                    defaults: {width: 230},
                    layout:'form',
                    defaultType: 'textfield',
                    reader:new Ext.data.JsonReader({
                        root: 'rows',
//                        totalProperty: 'total',
                        success:true,
                       fields: [
                             'id','name','typeName','typeNumInSql','typeOfClass'
                            ]
                        }
                     ),
                    items: [
                           {
                           	xtype:'hidden',
                            fieldLabel:'ID',
                            name:'id'	
                            },
                          {
                             fieldLabel: 'Name',
                             name: 'name',
                             allowBlank:false
                           },{
                             fieldLabel: 'Type Name',
                             name: 'typeName'
                           },{
                             fieldLabel: 'Type Num In SQL',
                             name: 'typeNumInSql'
                           },{
                             fieldLabel: 'typeOfClass',
                             name: 'typeOfClass'
                            //vtype:'email'
                           }
                          ]
            	    });
            	    win.add(form);
            	    form.getForm().load({
            	    	url:BP+'dataTypeAction.do?method=detail&entityClass=com.faceye.core.componentsupport.dao.model.DataType&id='+id,
            	    	waitMsg:'正在加载数据，请稍后...'
            	    });
    	}
    	win.show(this);
      }


 