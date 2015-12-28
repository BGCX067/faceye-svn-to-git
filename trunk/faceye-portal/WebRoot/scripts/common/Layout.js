/**
*Common Java Scripts
*/
Ext.BLANK_IMAGE_URL = 'scripts/ext/resources/images/vista/s.gif';
var Home={
  init : function(){
	Ext.QuickTips.init();
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
       var viewport = new Ext.Viewport({
            layout:'border',
            items:[new Ext.BoxComponent({ // raw
                    region:'north',
                    el: 'north',
                    height:32,
                    html:'<p> north</p>'
                }),{
                region:'west',
                id:'west-panel',
                title:'West',
                split:true,
                width: 200,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                margins:'35 0 5 5',
                cmargins:'35 5 5 5',
                layout:'accordion',
                layoutConfig:{
                    animate:true
                },
                items: [
               {
                    title:'Home Navigation',
                    autoScroll:true,
                    border:false,
                    iconCls:'nav',
                    html:'<div id="tree-viewer" style="overflow:auto;height:400px;border:0px solid #c3daf9;"></div>'
                   }
                ,{
                    title:'Settings',
                    html: '<p>Set The System Property</p>',
                    border:false,
                    autoScroll:true,
                    iconCls:'settings'
                }]
            },{
                region:'center',
                margins:'35 5 5 0',
                layout:'fit',
                border:true,
                autoScroll:false,
                    items:[{
                    baseCls:'x-plain',
                    layout:'fit',
//                    columnWidth:1,
                    bodyStyle:'padding:0px 0px 0px 0px',
                    items:[{
                    	autoScroll:false,
                    	border:false,
//                        title: 'Content',
                        html: '<iframe id="content-iframe" frameborder="no" style="width:100%;height:99.9%"></iframe>'
//                        <p><iframe id="content-iframe" frameborder="no" style="width:100%;height:100%"></iframe></p>
                    }]
                }]
            
            }]
        });
  /**  
   *----------------------------------------------------------
   *Start Tree Define
   *Define tree Struct
   *Tree Define By Song Hai Peng
   *----------------------------------------------------------
   */     
    var tree;
    var root;
    if(!tree){
    var Tree = Ext.tree;
    tree = new Tree.TreePanel(
    {
    	id:'default-manager-tree',
        el:'tree-viewer',
        autoScroll:true,
        animate:true,
        border:false,
        enableDD:true,
        containerScroll: true,
        rootVisible:false,
        loader: new Tree.TreeLoader({
            dataUrl:BP+'treeAction.do?method=tree'
        })
    }
    );  
    //Defint node click event,when node is clicked,send data to inner 'div' and show data in
    // set the root node
    root = new Tree.AsyncTreeNode({
        text: 'Common Platform',
        draggable:false,
        id:'source'
    });
    tree.setRootNode(root);
    /**
    *Open node URL in a target contanier
    */
     tree.on('click', function (node){ 
      if(node.isLeaf()){ 
         Ext.get('content-iframe').dom.src = node.attributes.link+'&node='+node.id;
         //define grid;
         return true;
     }else{
      /**
       *open node by single click,not double click.
       */
        node.toggle();
     }
    }); 
    // render the tree
    }
    tree.render();
    root.expand(); 
  }
};