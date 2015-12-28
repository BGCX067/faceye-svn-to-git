
/**
 * 定义搜索工具条
 */
//var baiduSearch=new Ext.form.FormPanel({
// 	                       		layout:'form',
// 	                       		id:'baidu-search',
// 	                       		url:'http://www.baidu.com/baidu',
// 	                       		defaultType: 'textfield',
// 	                       		hideBorders:true,
// 	                       		border:false,
// 	                       		items:[{   
// 	                       			fieldLabel: 'Name',
// 	                       			width:80,
//                                   name: 'word'
//                                   },{
//                                   	name:'tn',
//                                   	inputType:'hidden'
//                                  }],
//                                  buttons:[{
//                                  	text:'百度搜索',
//                                  	type:'submit',
//                                  	handler:function(){
//                                  		baiduSearch.getForm().submit({
//                                  			method:'POST',
//                           		            waitMsg:'正在检索,请稍候...',
//                           		            success:function(form,action){
//                           		            	
//                           		            }
//                                  		});
//                                  	}
//                                  }]
// 	                       	}
//);

 /**
  * 定义中央-中央区域
  */
var CenterCenterColumn= new Ext.Panel({
// 	                       		title:'center',
                                id:'center-center-column',
 	                       		columnWidth:.60,
 	                       		border:false,
 	                       		bodyStyle:'padding:5px 0 5px 5px'
// 	                       		html:'<p>Content column</p>'
 	                       	}
 	                       	);
// CenterCenterColumn.add(innerPanel);

/**
 * ---------------------------------------------
 * www.faceye.com网络支持系统
 * 作者:宋海鹏 ecsun@sohu.com/myecsun@hotmail.com
 * 说明:栏目展现
 * ---------------------------------------------
 */
 var ColumnFaceController={
 	init:function(centerLeftColumn){
 		/**
 		 * 加载栏目数据
 		 * 根据栏目数据
 		 * 为每个指定的栏目
 		 * 生成一个panel
 		 * 并显示在首页的左边栏位置
 		 */
 		  var store=new Ext.data.JsonStore({
 			             url:BP+'columnAction.do?method=columns',
                         root:'rows',
                         fields:['text','id','leaf','cls','link']
 		    });
 	       store.load({
 	       	       params:{
 	                    columnId:'1'
 	       	           },
 	       	       callback:function(r,options,success){
 	       	       	
 	       	       	/**
 	       	       	 *生成用于展现开源栏目子栏目信息的模板
 	       	       	 */
 	       	       	 var opensourceTpl=new Ext.XTemplate('<div><tpl for=".">'+
 	       	       	 		'<p><a href="#" onClick="OpenSourceProject.init(\'{id}\')">{text}</a></p>' +
 	       	       	 		'</tpl></div>');
 	       	       	/**
 	       	       	 * 开始动态生成panel
 	       	       	 */
 		               for(var i=0;i<r.length;i++){
 		               	//生成容器面板，作为装载数据的母板
 			            var panel=new Ext.Panel({
 			              layout:'fit',
 			              height:100,
 			              border:false,
 			              autoHeight:true,
 			              bodyStyle:'padding:0 0 5px 0'
 			            });  
 		                 /**
 		                  * 创建数据列表
 		                  */
 		                var contentStore=new Ext.data.Store({
 	       	           	proxy:new Ext.data.HttpProxy({
 			             url:BP+'columnAction.do?method=columns'
 	       	           	}),
 	       	           	reader: new Ext.data.JsonReader({
                         root:'rows',
                         fields:['text','id','leaf','cls','link']
 	       	           	   })
 		                 });
 	       	             contentStore.load({
 		                 	params:{
 		                 		columnId:r[i].data['id']
 		                 	},
 		                 	callback:function(records,o,s){
 		                 	}
 		                 });
 		                 /**
 		                  * 创建data view 用于数据展现
 		                  */
                         var contentDataView=new Ext.DataView({
 		                 	store:contentStore,
 		                 	tpl:opensourceTpl,
 		                 	loadingText:'正在加载...',
 		                 	style:'overflow:auto',
 		                 	itemSelector:'div-column',
                            multiSelect: true,
                            autoHeight:true
 		                 });
 		                 //生成装载内容的面板，主要解决面板间没有间隔的问题，所以使用两个面板组合在一起
 		                var contentPanel=new Ext.Panel({
 	       	       	     layout:'fit',
 	       	       	     frame:false,
                         autoHeight:true,
                         collapsible:true,
                         title:r[i].data['text'],
 	       	       	     border:true
// 	       	       	     html:htmlSource
 	       	             });	             
 		                contentPanel.add(contentDataView);
                        panel.add(contentPanel);
 			            centerLeftColumn.add(panel);
 		               }
 		                centerLeftColumn.doLayout();  
 	                 }  
 	             });
 	}

 };
 
 
 /**
  * 当点击栏目链接时，打开opensource对应的面板
  */
  
  var OpenSourceProject={
  	init:function(columnId){
  		//开始加载本栏目的opensource信息
  	     var openSourceStore = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: BP+'openSourceAction.do?method=index'
        }),
        reader: new Ext.data.JsonReader({
            root: 'root',
            totalProperty: 'total',
            id: 'v0v',
            fields: [
               'v0v','v1v','v2v','v3v'
            ]
        }
        )
    });
 	       openSourceStore.load({
 	       	       params:{
 	                    node:columnId,
 	                    start:0,
                        limit:15
 	       	           }
 	       });
 	  function renderTopic(value, p, record){
 	  	  var html='<div id="opensource-content">' +
 	  	        '<p><strong>{1}</strong></p>' +
 	  	        '<p>{2}</p>' +
 	  	        '<p>改项目主页：<a href="{0}" target="_blank">{0}</a></p>'+
 	  	  		'</div>';
          return String.format(html,record.data['v2v'],record.data['v1v'],record.data['v3v']);
    };
    
    var cm = new Ext.grid.ColumnModel([
//        {
//        	id: 'v0v', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
//        	header:'ID',
//        	dataIndex:'v0v',
//        	hidden:true
//        },
        {
//           header: "项目名称",
           dataIndex: 'v1v',
           renderer:renderTopic
        }
//            {
//           header: "项目网址",
//           dataIndex: 'v2v',
//           hidden: false
//        },{
//        	header:'项目介绍',
//        	dataIndex:'v3v'
//        }
        ]);
        var grid = new Ext.grid.GridPanel({
        id:'opensource-grid',
//        title:'栏目列表',
        border:true,
//        renderTo:Ext.getBody(),
        autoHeight:true,
        loadMask:true,
        stripeRows: true,
        trackMouseOver:true,
        layoutConfig:{
        	  autoWidth:true,
        	  layout:'fit'
        },
        store: openSourceStore,
        cm: cm,
//        trackMouseOver:false,
        //selectRow:Ext.emptyFn，控制选中的记录是否高亮度显示
        sm: new Ext.grid.RowSelectionModel(),
//        sm:new Ext.grid.CheckboxSelectionModel(),
        loadMask: true,
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true
        },
        bbar: com.faceye.ui.util.PaggingToolBar(15,openSourceStore)
        });
//        function toggleDetails(btn, pressed){
//        var view = grid.getView();
//        view.showPreview = pressed;
//        view.refresh();
//        }; 
        //判断CenterCenterColumn中是否是已经有grid子面板
        if(CenterCenterColumn.findById('opensource-grid')){
        	CenterCenterColumn.remove('opensource-grid');
        	CenterCenterColumn.add(grid);
        }else{
  	      CenterCenterColumn.add(grid);
        }
        
//          CenterCenterColumn.add(grid);
          CenterCenterColumn.doLayout();
  	}
  };
  
  
//OpenSourceProject.init('1');
 

 /**
  * 结束头部导航区域定义
  */
 /**
  * 开始组装头部导航区域，为本区域添加面板
  */

  //为头部导航区域添加baidu搜索
//    HeaderPanel.add(baiduSearch);

/**
 * 定义中央区域容器
 */
var CenterColumn=new Ext.Panel({
	                        region:'center',
	                        id:'center-column',
 	                       	border:false,
 	                       	layout:'column'
// 	                       	bodyStyle:'padding:0px 0 50px 0px'
// 	                       	title:'center'
// 	                       	margins:'500 50 0 50'
//                            bodyStyle:'padding:50px 5px 5px 5px'
});
/**
 * 定义中央左边栏
 */
 var CenterLeftColumn=new Ext.Panel({
// 	                       		title:'left',
                                id:'center-left-column',
 	                       		border:false,
// 	                       		layout:'fit',
 	                       		columnWidth:0.15,
 	                       		autoHeight:true,
// 	                       		                margins:'35 0 58 5',
//                cmargins:'35 5 58 5'
 	                       		bodyStyle:'padding:5px 0 5px 0px'
 	                       	}
 );
// CenterLeftColumn.afterlayout(CenterLeftColumn,ColumnFaceController.init(CenterLeftColumn));
ColumnFaceController.init(CenterLeftColumn);
//


var innerPanel=new Ext.Panel({
//	title:'inner',
	border:true,
	layout:'fit',
	html:'<p>dsfa</p>'
});
var innerPanel2=new Ext.Panel({
//	title:'inner2',
	border:true,
	autoWidth:false,
	layout:'fit',
	margins:'5 5 5 5',
	html:'<p>dsfafffffffffffffffffffffff</p>'
});
//CenterLeftColumn.add(innerPanel);
//CenterLeftColumn.add(innerPanel2);
 
	                       	
 	                       	
/**
*定义中央右边栏区域
*/
var CenterRightColumn=new Ext.Panel({
// 	                       		title:'center',
 	                       		columnWidth:.25,
 	                       		id:'center-right-column',
 	                       		border:false,
 	                       		bodyStyle:'padding:5px 0 5px 5px'
// 	                       		html:'<p>Content column</p>'
 	                       	}
);
CenterRightColumn.add(innerPanel2);


/**
 * 为中央区域添加内容面板
 */
 com.faceye.HomeBuilder.init(CenterCenterColumn);
/**
 * 为中央窗口区区域添加面板
 */
 //添加左边栏
 CenterColumn.add(CenterLeftColumn);
 //添加中央区域
 CenterColumn.add(CenterCenterColumn);
 //添加右边栏
 CenterColumn.add(CenterRightColumn);
 /**
  * 结束为中央区域添加面板
  */
  
/**
 * 定义版权区域
 */



/**
 * 定义包装整个页面的面板
 */
 
 var FullPanel=new Ext.Panel({
 	                       layout:'fit',
 	                       id:'full-panel',
// 	                       title:'main container',
// 	                       renderTo:Ext.getBody(),
// 	                       width:1200,
// 	                       height:3000,
//                           autoHeight:true,
// 	                       margins: '5 5 5 5',
// 	                       cmargins:'10 10 10 10',
// 	                       bodyStyle:'width:80%', 
                           bodyStyle:'padding:5px 5px 5px 5px',
 	                       border:false
 });
 /**
  * 结束定义包装整个页面的面板
  */
  /**
   * 为包装整个页面的面板添加子面板
   */
   //添加头部区域面板
   FullPanel.add(HeaderPanel);
   //添加中央区域面板
   FullPanel.add(CenterColumn);
   //添加版权面板
   FullPanel.add(BottomLayout);
 
 
 
 /**
 * 整个页面的输出
 */
var Default={
	
	init:function(){
      Ext.QuickTips.init();
      Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
      FullPanel.render(Ext.getBody());
//      FullPanel.doLayout();
	}
};
