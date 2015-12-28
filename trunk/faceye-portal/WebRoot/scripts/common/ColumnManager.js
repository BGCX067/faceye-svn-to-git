/**
 * 栏目管理
 */
var ColumnManager={
	init:function(){
		var layout=new Ext.Viewport({
			layout:'border',
			title:'Column Manager Controller',
			renderTo:Ext.getBody(),
		    items:[{
				region:'west',
				layout:'fit',
				width:200,
				title:'栏目结构',
				border:true,
				collapsible: true,
				split:true,
				autoScroll:true,
				margins:'5 0 5 5',
				html:'<div id="tree-viewer" style="overflow:auto;height:400px;border:0px solid #c3daf9;"></div>'
			},{
				region:'center',
				layout:'fit',
				title:'栏目管理',
				margins:'5 5 5 5',
				html:'<iframe id="content-iframe" frameborder="no" style="width:100%;height:94%"></iframe>'
			}
			]
		}
		);
		
		//生成树形结构
          Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
                                   	               
          var Tree=Ext.tree;
          tree=new Tree.TreePanel(
          {
           el:'tree-viewer',
//         renderTo:winT,
           autoScroll:true,
           animate:true,
           enableDD:true,
           border:false,
           containerScroll: true, 
           loader: new Tree.TreeLoader({
                                  dataUrl:BP+'columnAction.do?method=tree'
                                 })
            }
          );
           var root = new Tree.AsyncTreeNode({
                                        text: '栏目结构',
                                        draggable:false,
                                        id:'source'
                                        });
           tree.setRootNode(root);
           tree.on('click', function (node){
                                  if(node.isLeaf()){ 
                                                Ext.get('content-iframe').dom.src = node.attributes.link+'&node='+node.id;
                                                return true;
                                              }else{
                                                 /**
                                                 *open node by single click,not double click.
                                                 */
                                                  node.toggle();
                                                 }
                                              }); 
       /**
        * 加入节点右键点击事件
        * 将出现一个浮动层,进行节点的维护,包括节点的新增(当前节点的子节点,也可以更换父节点),修改,删除,等一系列动作.
        */
                                                       
         tree.on('contextmenu',function(node){
                                 //生成操作窗口
                                 var win;
                                 if(!win){
                                       win=new Ext.Window({
                                                       	title:'节点('+node.text+')维护',
                                                       	closable:true,
                                                       	layout:'border',
                                                       	plain:true,
                                                       	width:450,
                                                       	height:200
                                                       	});
                                       }
                                                       	 /**
                                                       	  * 为维护树节点生成的panel
                                                       	  */
                                 var winNav=new Ext.Panel({
                                                      title: 'Navigation',
                                                      region: 'west',
                                                      split: true,
                                                      width: 100,
                                                      collapsible: true,
                                                      margins:'3 0 3 3',
                                                      cmargins:'3 3 3 3',
                                                      html:'<div id="nav-tree-viewer"></div>'
                                                      });
                                  var winContent=new Ext.Panel({
//                                                       title: 'Navigation',
                                                         region: 'center',
                                                         split: false,
                                                         width: 300,
                                                         collapsible: false,
                                                         margins:'3 3 3 0',
                                                         html:'<p>节点名称:'+node.text+'</p>'+
                                                                      '<p>父节点:'+node.parentNode.text+'</p>'+
                                                                      '<p>节点URL:'+node.link+'</p>'
//                                                                   cmargins:'3 3 3 3'
                                                         });
                                                         
                                                        
                                  /**
                                   * 给winNav添加一个树形目录
                                   */
//                                     var navTree=new Ext.tree.TreePanel({
//                                     	 el:'nav-tree-viewer',
////                                       renderTo:winT,
//                                         autoScroll:true,
//                                         animate:true,
//                                         enableDD:true,
//                                         border:false,
//                                         containerScroll: true
//                                     });
//                                     alert(1);
//                                     var navRoot = new Ext.tree.AsyncTreeNode({
//                                        text: '操作功能区',
//                                        draggable:false,
//                                        id:'source'
//                                        }); 
//                                        alert(2);
//                                     var node1=new Ext.tree.TreeNode({
//                                       	text:'添加子节点',
//                                       	id:'a1',
//                                       	leaf:false,
////                                       	cls:'album-node',
//                                       	link:'#'
//                                       });
//                                       alert(node1);
//                                     navRoot.appendChild(node1);
//                                     alert(3);
//                                     navTree.render();
//                                     navRoot.expand();                 
                                    /**
                                     * 添加节点控制树结束
                                     */                     

                                  win.add(winNav);
                                  win.add(winContent);
//                                  win.add(tabs);
                                  win.show();
                     },this);
                                                       
                    /**
                     * ---结束节点右击事件定义
                     */
                                                     // render the tree
       tree.render();
       root.expand();
	}
};





/**
 *  www.faceye.com网络支持系统
 * 作者:宋海鹏 ecsun@sohu.com/myecsun@hotmail.com
 * 说明:栏目维护
 */

var ColumnController={
	init:function(){
		 	 // create the Data Store
    var store = new Ext.data.Store({
        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
        proxy: new Ext.data.HttpProxy({
            url: BP+'columnAction.do?method=index'
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
           header: '栏目名称',
           dataIndex: 'v1v',
           renderer:renderTopic
        },{
           header: '栏目链接',
           dataIndex: 'v2v',
           hidden: false
        }
        ]);

    // by default columns are sortable
    cm.defaultSortable = true;
    //定义录入修改弹出　window
    var win;
   //定义编辑弹出window
     var updateWin;
    var grid = new Ext.grid.GridPanel({
        el:'topic-grid',
        title:'节点列表',
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
            　　　var parentIdText=new Ext.form.TextField({
            　　　	fieldLabel:'父栏目ID',
                inputType:'trigger',
                readOnly:true,
                name:'parentId',
                width:215,
                hidden:true,
                hideLabel:true
            　　　});
            		//构建父节点输入框
               var parentNameText= new Ext.form.TextField({
                               fieldLabel: '父栏目名字',
                               inputType:'trigger',
                               name: 'parentName',
                               readOnly:true,
//                               defaults:{width:180},
                               width:215,
                               listeners:{
                               render:function(ct){
                               		var parentEl=this.getEl().up('div.x-form-element');
//                               		alert(parentEl.getId());
                               		if(parentEl){
                               			var child=parentEl.createChild({
                                                        tag: 'button',
                                                        html:'选择父节点'
                                                       });
                                                 var winT;
                                                 var tree;
                                                 child.on('click',function(){
//                                   	               var e = field.el.up( '.x-form-item' );
//                                   	               var e = ct.getEl().up( '.x-form-item' );

//                                   	               parentNameText.setValue('parentName');
                                   	               //定义装载选择树的window
//                                   	               winT.destroy();
                                   	               if(!winT && !tree){
                                   	               	winT=new Ext.Window({
                                   	               		layout:'fit',
                                   	               		title:'选择父节点',
                                   	               		width:200,
                                   	               		height:300,
                                   	               		plain: true,
                                   	               		buttons:[{
                                   	               			text:'确定',
                                   	               			handler:function(){
                                   	               				winT.hide(this);
                                   	               			}
                                   	               		},{
                                   	               			text:'关闭',
                                   	               			handler:function(){
                                   	               				winT.hide(this);
                                   	               			}
                                   	               		}]
                                   	               	});
                                   	               	
                                   	               	 //生成树形结构
                                   	               Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
                                   	               
                                                   var Tree=Ext.tree;
                                   	               tree=new Tree.TreePanel(
                                   	                {
                                                      el:'sub-tree',
//                                                      renderTo:winT,
                                                      autoScroll:true,
                                                      animate:true,
                                                      enableDD:true,
                                                      containerScroll: true, 
                                                      loader: new Tree.TreeLoader({
                                                                            dataUrl:BP+'columnAction.do?method=tree'
                                                                            })
                                                      }
                                   	               );
                                   	               var root = new Tree.AsyncTreeNode({
                                                                                text: 'Common Platform',
                                                                                draggable:false,
                                                                                id:'source'
                                                                               });
                                                   tree.setRootNode(root);
                                                   tree.on('click', function (node){
                                                   	/**
                                                   	 * 当点击节点的时候，将当前点击节点的值设为正在添加新节点的父节点
                                                   	 */
                                                   	parentIdText.setValue(node.id);
                                                   	parentNameText.setValue(node.text);
                                                   if(node.isLeaf()){ 
                                                                 //Ext.get('content-iframe').dom.src = node.attributes.link+'&node='+node.id;
                                                                 return true;
                                                                }else{
                                                                   /**
                                                                    *open node by single click,not double click.
                                                                    */
                                                                      node.toggle();
                                                                      }
                                                              }); 
                                                     // render the tree
                                                     tree.render();
                                                     root.expand();
                                                     winT.add(tree);
                                   	               }
                                   	               
                                   	               winT.show(this);
                                                });
                               		}
//                               		ct.el.on('click',function(){
//                               			alert('test');
//                               		},this)
                                  
                               	}
                               }
                            //vtype:'email'
                           });
                          
                 var innerForm=new Ext.FormPanel({
            	 	labelWidth: 80, // label settings here cascade unless overridden
                    url:BP+'columnAction.do?method=save',
                    frame:true,
                    // title: 'Simple Form',
                    bodyStyle:'padding:5px 5px 0',
                    width: 370,
//                    defaults: {width: 210},
                    renderTo:win,
                    layout:'form',
                    defaultType: 'textfield',
                    items: [{
                    	    name:'id',
                    	    hidden:true,
                    	    hideLabel:true
                          },
                          {
                             fieldLabel: '栏目名字',
                             name: 'name',
                             width:300,
                             allowBlank:false,
                             vtypeText:'栏目名字不能为空'
                           },{
                             fieldLabel: '栏目URL',
                             name: 'url',
                             width:300
                           },{
                             fieldLabel: '描述',
                             name: 'description',
                             width:300
                           },parentNameText,parentIdText
                          ]
            	    });
            	   
//            	    innerForm.addListener('click', function(){alert('keup');});
            	    //当点击 父节点输入框时,激活click事件.
            	   
            		win=new Ext.Window({
            			layout:'fit',
            			width:450,
                        height:200,
                        closeAction:'hide',
                        plain: true,
                        title:'添加新节点',
                        buttonAlign:'center',
                        buttons: [{
                           text:'确定',
                           scope:ColumnController,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                           innerForm.getForm().submit({
                           		method:'POST',
                           		waitMsg:'Saving Data Now',
                           		//reset:BP+'treeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
                           		success:function(form,action){
//                                    grid.getView().refresh();
                           			form.reset();
                           			Ext.Msg.alert('节点保存','节点保存成功!');
                           			this.disabled=false; 
                           			win.hide();
                           			//重新加载数据到grid
                           			store.load();
                           		},
                           		failure:function(){
                           			Ext.Msg.alert('节点保存','节点保存失败!');
                           			this.disabled=false;
                           		}
                           	 }
                           	);
                           }
                              },{
                           text: '放弃',
                           handler: function(){
                           	　 innerForm.getForm().reset();
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
            	if(selectedCount === 0){
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
            	
                    
                    if(!updateWin){
                    	/**
                    	 * --开始定义父节点信息
                    	 */
                var parentIdText=new Ext.form.TextField({
            　　　	fieldLabel:'父栏目ID',
                inputType:'trigger',
                readOnly:true,
                name:'parentId',
                width:215,
                hidden:true,
                hideLabel:true
            　　　});
            		//构建父节点输入框
               var parentNameText= new Ext.form.TextField({
                               fieldLabel: '父栏目名字',
                               inputType:'trigger',
                               name: 'parentName',
                               readOnly:true,
//                               defaults:{width:180},
                               width:215,
                               listeners:{
                               render:function(ct){
                               		var parentEl=this.getEl().up('div.x-form-element');
//                               		alert(parentEl.getId());
                               		if(parentEl){
                               			var child=parentEl.createChild({
                                                        tag: 'button',
                                                        html:'选择父节点'
                                                       });
                                                 var winT;
                                                 var tree;
                                                 child.on('click',function(){
//                                   	               var e = field.el.up( '.x-form-item' );
//                                   	               var e = ct.getEl().up( '.x-form-item' );

//                                   	               parentNameText.setValue('parentName');
                                   	               //定义装载选择树的window
//                                   	               winT.destroy();
                                   	               if(!winT && !tree){
                                   	               	winT=new Ext.Window({
                                   	               		layout:'fit',
                                   	               		title:'选择父栏目',
                                   	               		width:200,
                                   	               		height:300,
                                   	               		plain: true,
                                   	               		buttons:[{
                                   	               			text:'确定',
                                   	               			handler:function(){
                                   	               				winT.hide(this);
                                   	               			}
                                   	               		},{
                                   	               			text:'关闭',
                                   	               			handler:function(){
                                   	               				winT.hide(this);
                                   	               			}
                                   	               		}]
                                   	               	});
                                   	               	
                                   	               	 //生成树形结构
                                   	               Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
                                   	               
                                                   var Tree=Ext.tree;
                                   	               tree=new Tree.TreePanel(
                                   	                {
                                                      el:'sub-tree',
//                                                      renderTo:winT,
                                                      autoScroll:true,
                                                      animate:true,
                                                      enableDD:true,
                                                      containerScroll: true, 
                                                      loader: new Tree.TreeLoader({
                                                                            dataUrl:BP+'columnAction.do?method=tree'
                                                                            })
                                                      }
                                   	               );
                                   	               var root = new Tree.AsyncTreeNode({
                                                                                text: 'Common Platform',
                                                                                draggable:false,
                                                                                id:'source'
                                                                               });
                                                   tree.setRootNode(root);
                                                   tree.on('click', function (node){
                                                   	/**
                                                   	 * 当点击节点的时候，将当前点击节点的值设为正在添加新节点的父节点
                                                   	 */
                                                   	parentIdText.setValue(node.id);
                                                   	parentNameText.setValue(node.text);
                                                   if(node.isLeaf()){ 
                                                                 //Ext.get('content-iframe').dom.src = node.attributes.link+'&node='+node.id;
                                                                 return true;
                                                                }else{
                                                                   /**
                                                                    *open node by single click,not double click.
                                                                    */
                                                                      node.toggle();
                                                                      }
                                                              }); 
                                                     // render the tree
                                                     tree.render();
                                                     root.expand();
                                                     winT.add(tree);
                                   	               }
                                   	               
                                   	               winT.show(this);
                                                })
                               		}
//                               		ct.el.on('click',function(){
//                               			alert('test');
//                               		},this)
                                  
                               	}
                               }
                            //vtype:'email'
                           });
                    	 /**
                    	  * 父节点信息定义完毕
                    	  */
                    	
                    var updateForm=new Ext.FormPanel({
            	 	labelWidth: 80, // label settings here cascade unless overridden
                    url:BP+'treeAction.do?method=save',
                    frame:true,
                    // title: 'Simple Form',
                    bodyStyle:'padding:5px 5px 0',
                    width: 450,
//                    defaults: {width: 230},
                    layout:'form',
                    defaultType: 'textfield',
                    reader:new Ext.data.JsonReader({
                        root: 'rows',
//                        totalProperty: 'total',
                        success:true,
                       fields: [
                             'id','name','url','description','parentid','parentName'
                            ]
                        }
                     ),
                    items: [
                           {
                           	xtype:'hidden',
                            fieldLabel:'ID',
                            name:'id'	
                            },
//                         parentIdText,
                         {
                             fieldLabel: '栏目名字',
                             name: 'name',
                             allowBlank:false,
                             width:300
                           },{
                             fieldLabel: '节点URL',
                             width:300,
                             name: 'url'
                           },{
                             fieldLabel: '节点描述',
                             width:300,
                             name: 'description'
                           },parentIdText,parentNameText
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
                           scope:ColumnController,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                           updateForm.getForm().submit({
                           		method:'POST',
                           		waitMsg:'正在保存数据......',
                           		//reset:BP+'treeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
                           		success:function(form,action){
//                                    grid.getView().refresh();	
                           			Ext.Msg.alert('节点保存','节点保存成功!');
                           			form.reset();
                           			this.disabled=false; 
                           			updateWin.hide();
                           			//重新加载数据到grid
                           			store.load();
                           		},
                           		failure:function(){
                           			Ext.Msg.alert('节点保存','节点保存失败!');
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
            	    	url:BP+'columnAction.do?method=update&entityClass=com.faceye.core.service.security.model.Column&id='+id,
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
            	}else if(selectedCount>1){
            		Ext.Msg.alert('删除操作','您只能选择一条记录进行删除，请选择您准备删除的数据');
            		return;
            	}else{
            		//取得要删除的数据的ID
            		Ext.Msg.confirm('删除数据','您确认要删除选中的数据吗?',function(btn,text){
            		if(btn=='yes'){
            		var records = selectionModel.getSelections();
            		var _ids='';
            		for(var i=0;i<records.length;i++){
            			_ids+=records[i].id;
            		    _ids+='_';
            		    }
            		//判断当前节点是不是叶子
            		var nodesStore=new Ext.data.JsonStore({
//            			 proxy: new Ext.data.HttpProxy({
//                                  url: BP+'treeAction.do?method=isLeaf'
//                                }),
//            			 reader: new Ext.data.JsonReader({
//                                  root: 'rows',
//                                  fields: [
//                                        'isLeaf'
//                                      ]
//                                  }
//                                 )
                         url:BP+'columnAction.do?method=isLeaf',
                         root:'rows',
                         fields:['isLeaf']
            		});
            		nodesStore.load({
            			params:{id:records[0].id},
            			callback:function(){
            				var record=nodesStore.getAt(0);
                            if(record.data['isLeaf'] == 'no'){
                             Ext.Msg.alert('删除数据','您不能删除本节点，其下还有其它子节点，如果您要删除本节点，请首先删除其子节点');
                             return;
                             }
                    
            		        //发送删除数据的请求		
            		       Ext.Ajax.request({
            			     url:BP+'columnAction.do?method=remove',
            			     failure:function(){
            				 Ext.Msg.alert('节点删除','节点删除失败！');
            			    },
            			   success:function(){
            				 Ext.Msg.alert('节点删除','节点删除成功！');
            			    },
            			   params:{
            				entityClass:'com.faceye.core.service.security.model.Column',
            				ids:_ids
            			    }
            		       });
//            		    store.load();
                        store.reload();
            			}
            		});
//            		alert(nodesStore);
//            		alert(nodesStore);
            		
            			}else{
            				return;
            			}
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
/**
 * 数据明细显示
 */
  function onClickLink(id){
    	var win;
    	if(!win){
    		win=new Ext.Window({
            			layout:'fit',
            			width:450,
                        height:200,
                        closeAction:'hide',
                        title:'栏目明细',
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
               var tpl=new Ext.Template(
               '<p>栏目名称:{name}</p>' +
               '<p>栏目URL:{url}</p>' +
               '<p>栏目描述:{description}</p>' +
               '<p>父节点:{parentName}'
               );
               var nodes=new Ext.data.JsonStore({
//            			 proxy: new Ext.data.HttpProxy({
//                                  url: BP+'treeAction.do?method=isLeaf'
//                                }),
//            			 reader: new Ext.data.JsonReader({
//                                  root: 'rows',
//                                  fields: [
//                                        'isLeaf'
//                                      ]
//                                  }
//                                 )
                         url:BP+'columnAction.do?method=detail',
                         root:'rows',
                         fields:['id','name','description','url','parentId','parentName']
            		});
//            		tpl.overwrite(win.body,nodes);
            		nodes.load({
            			params:{
            				id:id,
            				entityClass:'com.faceye.core.service.security.model.Column'
            			},
            			callback:function(r,options,success){
            				var record=nodes.getAt(0);
            				tpl.overwrite(win.body, record.data);
            			}
            		});
            		
    	}
    	win.show(this);
      };

