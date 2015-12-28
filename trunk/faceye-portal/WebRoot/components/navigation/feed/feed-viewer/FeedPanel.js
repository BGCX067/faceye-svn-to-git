/*
 * Ext JS Library 2.0.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

FeedPanel = function() {
    FeedPanel.superclass.constructor.call(this, {
        id:'feed-tree',
        region:'west',
        title:'我的订阅',
        split:true,
        width: 180,
        minSize: 150,
        maxSize: 250,
        collapsible: true,
        margins:'5 0 5 5',
        cmargins:'5 5 5 5',
        rootVisible:false,
        lines:true,
        useArrows:true,
        autoScroll:true,
        enableDD:true,
        animate:true,
        draggable:false,
        //root: new Ext.tree.AsyncTreeNode({text:'Feed Viewer',id:'source',draggable:false}),
        root:new Ext.tree.AsyncTreeNode({
        loader:new Ext.tree.TreeLoader({
        //dataUrl:BP+'userResourceCategoryAction.do?method=getUserDefaultRoot'
        dataUrl:BP+'userResourceCategoryAction.do?method=tree'
        })
        }),
        collapseFirst:false,
        //loader: new Ext.tree.TreeLoader({
           // dataUrl:BP+'userResourceCategoryAction.do?method=tree'
        //}),
        tbar: [{
            id:'delete',
            iconCls:'delete-icon',
            text:'删除',
            handler: function(){
                var s = this.getSelectionModel().getSelectedNode();
                if(s){
                    this.removeFeed(s.attributes.id);
                }
            },
            scope: this
        }]
    });
    
    //this.feeds = this.root.appendChild(
       // new Ext.tree.TreeNode({
          //  text:'我的频道',
          //  cls:'feeds-node',
          //  expanded:true
        //})
    //);

    this.getSelectionModel().on({
        'beforeselect' : function(sm, node){
             return node.isLeaf()&&node.attributes.link!='#';
        },
        'selectionchange' : function(sm, node){
            if(node){
                this.fireEvent('feedselect', node.attributes);
            }
            this.getTopToolbar().items.get('delete').setDisabled(!node);
        },
        scope:this
    });

    this.addEvents({feedselect:true});

    this.on('contextmenu', this.onContextMenu, this);
};
  
Ext.extend(FeedPanel, Ext.tree.TreePanel, {

    onContextMenu : function(node,e){
        if(!this.menu){ // create context menu on first right click
            this.menu = new Ext.menu.Menu({
                id:'feeds-ctx',
                items: [{
                    id:'load',
                    iconCls:'load-icon',
                    text:'加载',
                    scope: this,
                    handler:function(){
                        this.ctxNode.select();
                    }
                },{
                    text:'删除本频道',
                    iconCls:'delete-icon',
                    scope: this,
                    handler:function(btn){
                        this.ctxNode.ui.removeClass('x-node-ctx');
                        this.removeFeed(this.ctxNode.attributes.id);
                        this.ctxNode = null;
                    }
                },'-',{
                    iconCls:'add-feed',
                    text:'添加频道',
                    handler: this.showWindow,
                    scope: this
                },{
                	iconCls:'add',
                	text:'添加频道分类',
                	handler:this.showAddNewResourceCategoryWindow,
                	scope:this
                }]
            });
            this.menu.on('hide', this.onContextHide, this);
        }
        /**
         *  用户资源分类上的右键菜单。
         */
        if(!this.userResourceCategoryOperationMenu){
        	 this.userResourceCategoryOperationMenu = new Ext.menu.Menu({
        	 id:'feeds-user-resource-category',
        	 items:[{
                    iconCls:'add-feed',
                    text:'添加频道',
                    handler: this.showWindow,
                    scope: this
                },{
                	iconCls:'add',
                	text:'添加频道分类',
                	handler:this.showAddNewResourceCategoryWindow,
                	scope:this
                },{
                	iconCls:'option',
                	text:'编辑本频道分类',
                	handler:this.showAddNewResourceCategoryWindow,
                	scope:this
                },{
                	iconCls:'remove',
                	text:'删除本频道分类',
                	handler:function(){
                		Ext.Ajax.request({
                			url:BP+'userResourceCategoryAction.do?method=remove',
                			params:{
                				id:this.ctxNode.attributes.id
                			},
                			success:function(){
                				Ext.getCmp('feed-tree').root.reload();
                			}
                		});
                	},
                	scope:this
                }]
        	 }
        	 );
        	 this.userResourceCategoryOperationMenu.on('hide',this.onContextHide,this);
        }
        
        if(this.ctxNode){
            this.ctxNode.ui.removeClass('x-node-ctx');
            this.ctxNode = null;
        }
        if(node.isLeaf()&&node.attributes.link!='#'){
            this.ctxNode = node;
            this.ctxNode.ui.addClass('x-node-ctx');
            this.menu.items.get('load').setDisabled(node.isSelected());
            this.menu.showAt(e.getXY());
        }
        if(!node.isLeaf()||(node.isLeaf()&&node.attributes.link=='#')){
        	this.ctxNode=node;
        	this.ctxNode.ui.addClass('x-node-ctx');
        	//this.userResourceCategoryOperationMenu.items.get('load').setDisabled(withLinkNode.isSelected());
        	this.userResourceCategoryOperationMenu.showAt(e.getXY());
        }
    },
    onContextHide : function(){
        if(this.ctxNode){
            this.ctxNode.ui.removeClass('x-node-ctx');
            this.ctxNode = null;
        }
    },
   
    showWindow : function(btn){
        if(!this.win){
            this.win = new FeedWindow();
            this.win.on('validfeed', this.addFeed, this);
        }
        this.win.show(btn);
    },
   
    /*
     * 添加频道分类
     */
    showAddNewResourceCategoryWindow:function(btn){
    if(Ext.getCmp('user-feed-category-window')){
    Ext.getCmp('user-feed-category-window').destroy();
    }
    	/**
    	 *  父节点分类
    	 */
    	 var parentIdText=new Ext.form.TextField({
    	        id:'parent-id-text',
            　　　	    fieldLabel:'父分类ID',
                inputType:'trigger',
                readOnly:true,
                name:'parentId',
                width:215,
                hidden:true,
                hideLabel:true
                });
    	var parentNameText= new Ext.form.TextField({
               	               id:'parent-name-Text',
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
                                                        html:'选择父分类'
                                                       });
                                                 if(Ext.getCmp('parentSelectTree')){
                                                 	Ext.get('parent-select-tree').remove(true);
                                                 	Ext.getBody().createChild({tag:'div',id:'parent-select-tree'});
                                                 }
                                                 var tree;
                                                 if(!tree){
                                                 //生成树形结构
                                   	               Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
                                   	               
                                                   var Tree=Ext.tree;
                                                 
                                   	               tree=new Tree.TreePanel(
                                   	                {
                                                      el:'parent-select-tree',
//                                                      renderTo:winT,
                                                      id:'parentSelectTree',
                                                      autoScroll:true,
                                                      animate:true,
                                                      enableDD:true,
                                                      containerScroll: true, 
                                                      rootVisible:false,
                                                      loader: new Tree.TreeLoader({
                                                                            dataUrl:BP+'userResourceCategoryAction.do?method=userResourceCategoryOnlyTree'
                                                                            })
                                                      }
                                   	               );
                                   	               var root = new Tree.AsyncTreeNode({
                                                                                text: '资源分类',
                                                                                draggable:false,
                                                                                id:'source'
                                                                               });
                                                   tree.setRootNode(root);
                                                 }
                                                 child.on('click',function(){                                                
                                                   var winT;
                                                   winT=new Ext.Window({
                                   	               		layout:'fit',
                                   	               		modal:true,
                                   	               		title:'选择父分类',
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
                                   	               	 
                                                  
                                                   tree.on('click', function (node){
                                                   	/**
                                                   	 * 当点击栏目的时候，将当前点击栏目的值设为正在添加新栏目的父栏目
                                                   	 */
                                                   	if(node.link!='#'){
                                                   	  parentIdText.setValue(node.id);
                                                   	  parentNameText.setValue(node.text);
                                                   	}
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
                                   	               
                                   	               winT.show(this);
                                                });
                               		}                  
                               	}
                               }
                           });
    	var innerForm=new Ext.form.FormPanel({
    	            id:'user-feed-category-form',
    		        labelWidth: 80, // label settings here cascade unless overridden
                    url:BP+'userResourceCategoryAction.do?method=save',
                    frame:true,
                    // title: 'Simple Form',
                    bodyStyle:'padding:5px 5px 0',
                    width: 370,
                    height:200,
//                    defaults: {width: 210},
                    renderTo:win,
                    layout:'form',
                    defaultType: 'textfield',
                    reader : new Ext.data.JsonReader({
				root : 'rows',
				success : true,
				fields : ['id', 'name', 'description', 'parentId', 'parentName']
			}),
                    items: [
                          {
                             fieldLabel: '节点名字',
                             name: 'name',
                             width:300,
                             allowBlank:false,
                             vtypeText:'节点名字不能为空'
                           },parentNameText,{
                             fieldLabel: '描述',
                             name: 'description',
                             width:300
                           },{
                    	    name:'id',
                    	    hidden:true,
                    	    hideLabel:true
                          },parentIdText
                          ]
    	});
    	if(btn.iconCls==='option'){
    	innerForm.getForm().load({
				url : BP + 'userResourceCategoryAction.do',
				method : 'POST',
				params : {
					method : 'update',
					entityClass : 'com.faceye.components.navigation.dao.model.UserResourceCategory',
					id : this.ctxNode.id
				},
				waitMsg : '正在加载数据，请稍后...'
			});
    	}else if(btn.iconCls==='add'){
    	if(this.ctxNode.attributes.link==='#'){
    	parentNameText.setValue(this.ctxNode.text);
    	parentIdText.setValue(this.ctxNode.id);
    	}else{
    	parentNameText.setValue(this.ctxNode.parentNode.text);
    	parentIdText.setValue(this.ctxNode.parentNode.id);
    	}
    	}
    	var win=new Ext.Window({
    	                id:'user-feed-category-window',
    		            layout:'fit',
            			//模式窗口
            			modal:true,
            			width:450,
                        height:200,
                        closeAction:'hide',
                        plain: true,
                        title:'添加新节点',
                        buttonAlign:'center',
                        buttons: [{
                           text:'确定',
                           scope:this,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                           innerForm.getForm().submit({
                           		method:'POST',
                           		waitMsg:'正在保存分类,请稍候...',
                           		//reset:BP+'treeAction.do?method=generatorJSONList&entityClass=com.faceye.core.componentsupport.dao.model.DataType',
                           		success:function(form,action){
//                                    grid.getView().refresh();
                           			form.reset();
                           			//alert(Ext.getCmp('feed-tree').root.reload());
                           			Ext.getCmp('feed-tree').root.reload();
                           			Ext.Msg.alert('分类保存','节分类保存成功!');
                           			this.disabled=false; 
                           			win.hide();
                           			//重新加载数据到grid
                           		},
                           		failure:function(){
                           			Ext.Msg.alert('分类保存','分类保存失败!');
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
    	win.show();
    },

    selectFeed: function(url){
        this.getNodeById(url).select();
    },

    removeFeed: function(id){
        var node = this.getNodeById(id);
        if(node){
            node.unselect();
            Ext.fly(node.ui.elNode).ghost('l', {
                callback: node.remove, scope: node, duration: .4
            });
            Ext.Ajax.request({
            url:BP+'userResourceCategoryAction.do?method=removeRelationShipBettonFeedAndUserResourceCategory',
            params:{
            feedId:node.id,
            categoryId:node.parentNode.id
            }
            });
        }
    },

    addFeed : function(attrs, inactive, preventAnim){
        var exists = this.getNodeById(attrs.url);
        if(exists){
            if(!inactive){
                exists.select();
                exists.ui.highlight();
            }
            return;
        }
        Ext.apply(attrs, {
            iconCls: 'feed-icon',
            leaf:true,
            cls:'feed',
            id: attrs.url
        });
        var node = new Ext.tree.TreeNode(attrs);
        this.feeds.appendChild(node);
        if(!inactive){
            if(!preventAnim){
                Ext.fly(node.ui.elNode).slideIn('l', {
                    callback: node.select, scope: node, duration: .4
                });
            }else{
                node.select();
            }
        }
        return node;
    },

    // prevent the default context menu when you miss the node
    afterRender : function(){
        FeedPanel.superclass.afterRender.call(this);
        this.el.on('contextmenu', function(e){
            e.preventDefault();
        });
    }
});
