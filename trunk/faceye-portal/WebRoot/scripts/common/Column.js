/**
 * 栏目管理
 */
var Column={
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
				margins:'5 5 5 0',
				html:'<iframe id="content-iframe" frameborder="no" style="width:100%;height:100%"></iframe>'
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
                                                 if(node.attributes.link!='#'){
                                                 Ext.get('content-iframe').dom.src = node.attributes.link+'&node='+node.id;
                                                 }
                                                 node.toggle();
//                                                 return true;
                                                 }
                                              }); 
       /**
        * 加入节点右键点击事件
        * 将出现一个浮动层,进行节点的维护,包括节点的新增(当前节点的子节点,也可以更换父节点),修改,删除,等一系列动作.
        */
         /**
          * 销毁tree的时候,会将tree依附的dom 节点同时销毁,所以当再次生成树结构的时候,将找不一指定的dom节点,这个时候,就会出现问题
          */
                                  var navTree;
         
                                   if(!navTree){
                                     navTree=new Ext.tree.TreePanel({
                                     	 id:'tree-node-eidt',
                                     	 el:'tree-node-edit',
//                                         renderTo:winContent,
                                         autoScroll:true,
                                         animate:true,
                                         enableDD:true,
                                         border:false,
                                         containerScroll:true,
                                         rootVisible:false,
                                         loader: new Ext.tree.TreeLoader({
                                                             dataUrl:BP+'columnAction.do?method=node'
                                                              })
                                         });
                                         
//                                    Ext.tree.AsyncTreeNode
                                     navRoot = new Ext.tree.AsyncTreeNode({
                                        text: '操作功能区',
                                        draggable:false,
                                        id:'source'
//                                        allowDrag:false,
//                                        allowDrop:false
                                        }); 
                                   
//                                     var node1=new Ext.tree.TreeNode({
//                                       	text:'子节点',
//                                       	id:'a1',
//                                       	leaf:false,
//                                       	cls:'album-node',
//                                       	link:'#'
//                                       });
                                     navTree.setRootNode(navRoot);
                                     navTree.render();       
                                     navRoot.expand();     
                                   }
         /**
          * 加入节点右健单击事件.
          */
         tree.on('contextmenu',function(node){   
//                                if(win){
//                                	win.initComponent() ;
//                                }
           var win; 
//          var navTree;
          var navRoot;
          var winNav;
          var winContent;
                                   if(!win){
                                   	//生成用于维护节点的window
                                       win=new Ext.Window({
                                                       	title:'节点(<font color="red">'+node.text+'</font>)维护',
                                                       	closable:true,  
                                                       	layout:'border',
                                                       	plain:true,
                                                       	width:580,
                                                       	height:230,
                                                       	modal:true,
                                                       	autoScroll:true
                                                       	});
                                        }
                                                       	 /**
                                                       	  * 为维护树节点生成的panel
                                                       	  */
                                                                
                                if(!winNav){
                                	//生成windown中的节点操作导航树                               	  
                                winNav =new Ext.Panel({
                                 	                  id:'win-nav',
                                                      title: '操作功能',
                                                      region: 'west',
                                                      split: true,
                                                      layout:'fit',
                                                      width: 130,
                                                      collapsible: true,
                                                      margins:'3 0 3 3',
                                                      cmargins:'3 3 3 3'
                                                      });
                                }
                                if(!winContent){
                                  winContent=new Ext.Panel({
//                                                         title: 'Navigation',
                                                         id:'win-content',
                                                         region: 'center',
                                                         buttonAlign:'center',
                                                         split: false,
                                                         layout:'fit',
                                                         width: 470,
                                                         collapsible: false,
                                                         margins:'3 3 3 0'
//                                                         html:'<p>栏目名称:'+node.text+'</p>'+
//                                                                      '<p>父栏目:'+node.parentNode.text+'</p>'+
//                                                                      '<p>栏目URL:'+node.link+'</p>'
//                                                         html:'<iframe id="tree-node-edit-iframe" frameborder="no" style="width:100%;height:94%"></iframe>'
//                                                         cmargins:'3 3 3 3'
                                                         });
                                }                  
                                                         
                                  /**
                                   * 给winNav添加一个树形目录
                                   */
//                                     navRoot.appendChild(node1);
                                     navTree.on('click',function(navNode){
                                     	                                     	
                                     	 var parentIdText=new Ext.form.TextField({
                                                             inputType:'trigger',
                                                             readOnly:true,
                                                             name:'parentId',
                                                             width:215,
                                                             hidden:true,
                                                             hideLabel:true
                                                            });
                                                            //定义父栏目名称
                                             var parentNameText= new Ext.form.TextField({
                                                                  fieldLabel: '父栏目名字',
                                                                  inputType:'trigger',
                                                                  name: 'parentName',
                                                                  readOnly:true,
//                                                                  defaults:{width:180},
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
                                                 var tree0;
                                                 child.on('click',function(){
                                                 	onChildClick();
//                                   	               var e = field.el.up( '.x-form-item' );
//                                   	               var e = ct.getEl().up( '.x-form-item' );

//                                   	               parentNameText.setValue('parentName');
                                   	               //定义装载选择树的window
//                                   	               winT.destroy();
                                   	               if(!winT && !tree0){
                                   	               	winT=new Ext.Window({
                                   	               		layout:'fit',
                                   	               		title:'选择父节点',
                                   	               		width:200,
                                   	               		height:300,
                                   	               		plain: true,
                                   	               		modal:true,
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
                                   	               	 //生成树形结构,供选择使用
                                   	               Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
                                   	               
//                                                   var Tree=Ext.tree;
                                   	               tree0=new Ext.tree.TreePanel(
                                   	                {
                                                      el:'sub-tree-click',
                                                      id:'sub-tree-click-id',
//                                                      renderTo:winT,
                                                      autoScroll:true,
                                                      animate:true,
                                                      enableDD:true,
                                                      containerScroll: true, 
                                                      loader: new Ext.tree.TreeLoader({
                                                                            dataUrl:BP+'columnAction.do?method=tree'
                                                                            })
                                                      }
                                   	               );
                                   	               var root0 = new Ext.tree.AsyncTreeNode({
                                                                                text: 'Common Platform',
                                                                                draggable:false,
                                                                                id:'source'
                                                                               });
                                                   tree0.setRootNode(root0);
                                                   tree0.on('click', function (n){
                                                   	/**
                                                   	 * 当点击节点的时候，将当前点击节点的值设为正在添加新节点的父节点
                                                   	 */
                                                   	parentIdText.setValue(n.id);
                                                   	parentNameText.setValue(n.text);
                                                   if(n.isLeaf()){ 
                                                                 //Ext.get('content-iframe').dom.src = node.attributes.link+'&node='+node.id;
                                                                 return true;
                                                                }else{
                                                                   /**
                                                                    *open node by single click,not double click.
                                                                    */
                                                                      n.toggle();
                                                                      }
                                                              }); 
                                                     // render the tree
                                                     tree0.render();
                                                     root0.expand();
                                                     winT.add(tree0);
                                   	               }
                                   	               winT.show();
                                                });
                               		}
//                               		ct.el.on('click',function(){
//                               			alert('test');
//                               		},this)
                               	}
                               }
                           });
                           
                                     	//显示节点明细
                                     	if(navNode.id=='1'){
                                     		//生成节点明细模板
                                     		 var tpl=new Ext.Template(
                                                      '<p>栏目名称: {name}</p>' +
                                                      '<p>栏目URL:  {url}</p>' +
                                                      '<p>栏目描述: {description}</p>' +
                                                      '<p>父节点:{parentName}</p>'
                                                      );
                                             var nodes=new Ext.data.JsonStore({
                                                           url:BP+'columnAction.do?method=detail',
                                                           root:'rows',
                                                           fields:['id','name','description','url','parentId','parentName']
            		                                      });
            		                         nodes.load({
            			                              params:{
            				                          id:node.id,
            				                          entityClass:'com.faceye.core.service.security.model.Column'
            			                              },
            			                     callback:function(r,options,success){
            				                              var record=nodes.getAt(0);
            				                              tpl.overwrite(winContent.body,record.data);
            			                                 }
            		                                     });
                                     	    
                                     	}else if (navNode.id=='2'){
                                     		clearChildren(winContent);
                                           var uForm=new Ext.form.FormPanel({
                                           	id:'save',
                                           	labelWidth: 80, // label settings here cascade unless overridden
                                           url:BP+'columnAction.do?method=save&entityClass=com.faceye.core.service.security.model.Column',
                                           frame:true,
                                           // title: 'Simple Form',
                                           bodyStyle:'padding:5px 5px 0',
                                           width: 380,
//                                         defaults: {width: 210},
//                                         renderTo:win,
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
                                           },parentIdText,parentNameText
                                           ],
                                           buttons: [{
                           text:'确定',
//                           scope:ColumnController,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                           uForm.getForm().submit({
                           		method:'POST',
                           		waitMsg:'正在保存数据',
                           		//reset:BP+'treeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
                           		success:function(form,action){
//                                    grid.getView().refresh();
                           			form.reset();
                           			tree.root.reload();
                           			Ext.Msg.alert('栏目保存','栏目保存成功!');
//                           			this.disabled=false;
//                           			win.hide();
                           			//重新加载数据到grid
//                           			store.load();
                           		},
                           		failure:function(){
                           			Ext.Msg.alert('栏目保存','栏目保存失败!');
                           			this.disabled=false;
                           		}
                           	 }
                           	);
                           }
                              },{
                          	text:'重置',
                          	handler:function(){
                          		uForm.getForm().reset();
                          	}
                          }
                          ]
                        });
                        /**
                         * 当生成Form的时候,为父节点提供默认值
                         */
                                          parentIdText.setValue(node.id);
                                          parentNameText.setValue(node.text);
                                         
                                           winContent.add(uForm);
                                           winContent.doLayout();
                              
                                     	}else if(navNode.id=='3'){
                                     		clearChildren(winContent);                         		
                                     		 var uForm=new Ext.form.FormPanel({
                                     		 id:'update',
            	 	                         labelWidth: 80, // label settings here cascade unless overridden
                                             url:BP+'columnAction.do?method=save&entityClass=com.faceye.core.service.security.model.Column',
                                             frame:true,
                                            // title: 'Simple Form',
                                             bodyStyle:'padding:5px 5px 0',
                                             width: 450,
//                                           defaults: {width: 230},
                                             layout:'form',
                                             defaultType: 'textfield',
                                             waitMsg:'正在加载数据,请稍候...',
//                                             autoLoad:{
//                                             	url:BP+'columnAction.do',
//                                     		 	params:{
//                                     		 	 id:node.id,
//                                     		 	 method:'update',
//                                     		 	 entityClass:'com.faceye.core.service.security.model.Column'
//                                     		    }
//                                             },
                                             reader:new Ext.data.JsonReader({
                                                       root: 'rows',
//                                                     totalProperty: 'total',
                                                       success:true,
                                                       fields: [
                                                             'id','name','url','description','url','parentId','parentName'
                                                            ]
                                                     }
                                                    ),
                                             items: [
                                                  {
                           	                       xtype:'hidden',
                                                   fieldLabel:'ID',
                                                   name:'id'	
                                                  },
//                                               parentIdText,
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
                                                   ],
                                                    buttons: [{
                           text:'确定',
//                           scope:ColumnController,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                           uForm.getForm().submit({
                           		method:'POST',
                           		waitMsg:'正在保存数据',
                           		//reset:BP+'treeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
                           		success:function(form,action){
//                                    grid.getView().refresh();
                           			form.reset();
                           			tree.root.reload();
                           			Ext.Msg.alert('栏目保存','栏目保存成功!');
                           			this.disabled=false; 
//                           			win.hide();
//                                    narTree.doLayout();
                           			//重新加载数据到grid
//                           			store.load();
                           		},
                           		failure:function(){
                           			Ext.Msg.alert('栏目保存','栏目保存失败!');
                           			this.disabled=false;
                           		}
                           	 }
                           	);
                           }
                              },{
                          	text:'重置',
                          	handler:function(){
                          		uForm.getForm().reset();
                          	}
                          }
                          ]
            	                                 });
            	                                //向form加载数据
            	                                
            	                             
            	                                uForm.load({
            	                                 	url:BP+'columnAction.do',
                                     		 	    params:{
                                     		 	      id:node.id,
                                     		 	      method:'update',
                                     		 	      entityClass:'com.faceye.core.service.security.model.Column'
                                     		 	     }
            	                                   });
            	                           
            	                           winContent.add(uForm);
                                           winContent.doLayout();
            	                         
                                     	}else if(navNode.id='4'){
                                     		// 进行栏目的删除操作,栏目的删除操作包括单个栏目的删除,和栏目的强制删除
                                     		//强制删除,将删除栏目,栏目以下的所有子栏目,及栏目中的数据/
                                     		clearChildren(winContent);
                                     		var removePanel=new Ext.Panel({
                                     			id:'remove',
                                     			layout:'fit',
                                     			border:false
                                     		});
                                     		
                                     		var button=new Ext.Button({
                                     			id:'button',
                                     			text:'删除当前节点',
                                     			handler:function(){
                                     				if(node.isLeaf()){
                                     					Ext.Ajax.request({
                                     						url:BP+'columnAction.do?method=remove',
                                     						params:{
                                     							id:node.id,
                                     							entityClass:'com.faceye.core.service.security.model.Column'
                                     						},
                                     						failure:function(){
            				                                   Ext.Msg.alert('栏目删除','栏目删除失败！');
            			                                       },
            			                                    success:function(){
            			                                    	tree.root.reload();
            				                                   Ext.Msg.alert('栏目删除','栏目删除成功！');
            			                                       }
                                     					});
                                     				}else{
                                     					Ext.Msg.alert('删除栏目','本栏目下还有子栏目,如果要删除本栏目,请首先清空其子栏目');
                                     				}
                                     			}
                                     		});
                                     		removePanel.add(button);
//                                     		var strongButton=new Ext.Button({
//                                     			text:'强制删除',
//                                     			handler:function(){
//                                     				alert(1);
//                                     			}
//                                     		});
                                            
                                            winContent.add(removePanel);
//                                     		winContent.add(button);
//                                     		winContent.add(strongButton);
                                     		winContent.doLayout();
                                     	}
                                     });
                                      /**
                                       * 为wincontet加载新child之前,先清空它的先有child
                                       */
                                      function clearChildren(winContent){
                                      	if(winContent.findById('save')){
                                      		winContent.remove('save');
                                      	}
                                      	if(winContent.findById('update')){
                                      		winContent.remove('update');
                                      	}  
                                      	if(winContent.findById('remove')){
                                      		winContent.remove('remove');
                                      	}
                                      }
                                    /**
                                     * 添加节点控制树结束
                                     */ 
//                                   if(!winNav.findById('tree-node-edit')){
//                                   	  alert(01011);                
//                                      
//                                   }
                                     winNav.add(navTree);
//                                     navTree.applyTo(winNav);
//                                   winNav.add(navTree);

                                 win.add(winNav);
                                 win.add(winContent);
                                  
//                                 win.doLayout();
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

function onChildClick(){
		if(Ext.getCmp('sub-tree-click-id')){
                                      		Ext.get('sub-tree-click').remove(true);
                                      		Ext.getBody().createChild({tag:'div',id:'sub-tree-click'});
                                      	}
                                      	if(!Ext.get('sub-tree-click')){
                                      		Ext.getBody().createChild({tag:'div',id:'sub-tree-click'});
                                      	}
}
	