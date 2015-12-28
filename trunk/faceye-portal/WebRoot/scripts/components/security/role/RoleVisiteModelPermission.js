/**
*RoleController
*  角色访问权限管理
*  管理角色对模块的访问权限
*  www.faceye.com网络支持系统
* 作者:宋海鹏 ecsun@sohu.com/myecsun@hotmail.com
*/
Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
com.faceye.compoents.core.security.RoleVisiteModelPermission={
	init:function(roleId){
    Ext.QuickTips.init();
    if(Ext.getCmp('tree')){
    	Ext.get('tree-viewer').remove(true);
//      Ext.getCmp('tree').destroy(this);
        Ext.getBody().createChild({tag:'div',id:'tree-viewer'});
    }
	var tree;
    var root;
//    if(!tree){
    var Tree = Ext.tree;
    tree = new Ext.tree.TreePanel(
    {
        el:'tree-viewer',
        id:'tree',
        autoScroll:true,
        animate:true,
        border:false,
        enableDD:true,
        containerScroll: true,
        rootVisible:false,
        loader: new Ext.tree.TreeLoader({
			dataUrl:BP+'treeAction.do?method=treeWithCheckBoxForPermission&roleId='+roleId
//			uiProviders:{
//                'col': Ext.ux.CheckColumnNodeUI
//            }
		})
//		columns:[{
////			header:'node',
//			dataIndex:'name'
//		}]
//		
    }
    );  
    //Defint node click event,when node is clicked,send data to inner 'div' and show data in
    // set the root node
    root = new Tree.AsyncTreeNode({
        text: 'Common Platform',
        draggable:false,
        id:'source',
        checked:true
//        uiProvider: Ext.tree.CheckboxNodeUI
    });
    tree.setRootNode(root);
    /**
    *Open node URL in a target contanier
    */
    //全部展开
    tree.expandAll();
    tree.on('checkchange',function(node){
    	if(!node.isLeaf()){
    		node.toggle();
    	}
        fireCheckChange(node);
//      node.cascade(function(n){
//		    		n.getUI().toggleCheck();
//				});
      
    });
    
    /**
     * 当fire checkchange时执行
     */
     function fireCheckChange(node){
     	if(node.getUI().isChecked()){
        	checkedChildrenNodes(node);
        	checkedParentNodes(node);
        }else{
          //取得当前节点的所有子节点,包括当前节点
          var allChildrenNodes=getAllChildrenNodes(node);
          //如果当前节点的所有子节点中,不存在checked=true的节点,那么将当前节点置为checked=false.
//          
          //如果当前节点有子节点,同时,当前节点checked=false,那么将其所有子节点置为checked=false
          for(var i=0;i<allChildrenNodes.length;i++){
          	if(allChildrenNodes[i].getUI().isChecked()){
          		allChildrenNodes[i].getUI().toggleCheck();
          		
          	}
          }
          unCheckedParentNode(node);
        }
     }
    
    /**
     * 当点击父节点时
     * 将其所有子节点选中
     */
    function checkedChildrenNodes(node){
    	//取得本节点的所有子节点,子节点中包括其自己
        	var allChildrenNodes=getAllChildrenNodes(node);
        	if(allChildrenNodes.length>1){
        		for(var i=0;i<allChildrenNodes.length;i++){
        			if(!allChildrenNodes[i].getUI().isChecked()){
        				allChildrenNodes[i].getUI().toggleCheck();
        			}
        		}
        	}
    }
    
    /**
     * 当当前子节点的父节点的所有子节点中
     * 不存在checked=true的子节点时,父节点不被选中
     */
     function unCheckedParentNode(currentChildNode){
     	if(currentChildNode.parentNode){
     		var parentNode=currentChildNode.parentNode;
     		//取得本父节点下所有被选中的子节点
     		//包括本父节点本身
     		var allCheckedChildrenNodes=getCheckedNodes(parentNode);
     		if(allCheckedChildrenNodes.length === 1){
     			parentNode.getUI().toggleCheck();
     			parentNode.attributes.checked=false;
     		}
     		if(parentNode.parentNode){
     			unCheckedParentNode(parentNode);
     		}
     	}
     }
    /**
     * 当点击子节点时
     * 将父节点选中
     */
    function checkedParentNodes(node){
    		//取得本节点的所有父节点,父节点中包括其自己
        	var allParentNodes=getAllParentNodes(node);
        	if(allParentNodes.length>1){
        		for(var i=0;i<allParentNodes.length;i++){
        			if(!allParentNodes[i].getUI().isChecked()){
        				allParentNodes[i].getUI().toggleCheck();
        			}
        		}
        	}
    }
    /**
     * 取得所有子节点中checked 为true的节点ID
     * 包括本节点
     */
    function getCheckedNodesId(node){
    	var checked = [];
    	if( node.getUI().isChecked() || node.attributes.checked ) {
//    		alert('dfdf'+node.childNodes.length);
		checked.push(node.id);
		if( !node.isLeaf() ) {
			for(var i = 0; i < node.childNodes.length; i++ ) {
				checked = checked.concat( getCheckedNodesId(node.childNodes[i]) );
			}
		}
	}
	return checked;
    };
    /**
     * 取得所有子节点中checked为true的节点(TreeNode)
     * 包括本节点
     */
    function getCheckedNodes(node){
    	var checked = [];
    	if( node.getUI().isChecked() ) {
    		   checked.push(node);
		if( !node.isLeaf() ) {
			for(var i = 0; i < node.childNodes.length; i++ ) {
				checked = checked.concat( getCheckedNodes(node.childNodes[i]) );
			}
		}
	}
	return checked;
    };
    
    /**
     * 取得一个节点的所有子节点
     * 包括本节点
     */
     function getAllChildrenNodes(node){
     	var children = [];
     	children.push(node);
     	if(!node.isLeaf()){
     		for(var i=0;i<node.childNodes.length;i++){
     			children = children.concat(getAllChildrenNodes(node.childNodes[i]));
     		}
     	}
     	return children;
     };
    /**
     * 取得一个节点的所有父节点
     * 
     */
     function getAllParentNodes(node){
     	var parentNodes=[];
        parentNodes.push(node);
     	if(node.parentNode){
     		parentNodes = parentNodes.concat(getAllParentNodes(node.parentNode));
     	}
     	return parentNodes;
     };
     /**
      * 取得所有checked=true的节点ID
      */
      
      function getAllChecked(){
      	return getCheckedNodesId(root);
      }
     tree.on('click', function (node){ 
      if(node.isLeaf()){ 
//         Ext.get('content-iframe').dom.src = node.attributes.link+'&node='+node.id;
         //define grid;       
         return true;
     }else{
      /**
       *open node by single click,not double click.
       */
        node.toggle();
     }
    });
    
    tree.on('dblclick',function(node){
    	if(node.isLeaf()){
    		return true;
    	}else{
    		node.toggle();
    		fireCheckChange(node);
//    		this.fireEvent('checkchange', this.node, true);
    	}
    }); 
    // render the tree
//    }
    
    tree.render();
    root.expand(); 
		var win=new Ext.Window({
            			layout:'fit',
            			//模式窗口
            			modal:true,
            			width:450,
                        height:300,
                        closeAction:'hide',
                        plain: true,
//                        margins:'5 5 5 5',
                        
                        title:'为角色授权',
                        buttonAlign:'center',
                        buttons: [{
                           text:'确定',
                           scope:com.faceye.compoents.core.security.SelectRoles,
                           type:'submit',
                           disabled:false,
                           handler:function(){
                            var checked=getAllChecked().join(',');
                             Ext.Ajax.request({
            			     url:BP+'roleAction.do?method=permission',
            			     failure:function(){
            				 Ext.Msg.alert('角色授权','角色授权失败！');
            				 win.hide();
            			    },
            			   success:function(){
            				 Ext.Msg.alert('角色授权','角色授权成功！');
            				 win.hide();
            			    },
            			   params:{
            				treeIds:checked,
            				roleId:roleId
            			    }
            		       });
                            
                           }
                              },{
                           text: '放弃',
                           handler: function(){
//                           	　 formItemSelector.getForm().reset();
                              if(win.getComponent('tree')){
//                              	Ext.get('tree-viewer').remove();
//                              	win.destroy();
//                                win.remove(tree);
                                                 
                              }
                              win.hide();
//                              win.disable();
//                              tree.disable();
                               }
                          }
                          ]
            		});
            		
			win.add(tree);
			win.show();
	}
};