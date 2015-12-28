
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
 * 定义头部导航区域面板
 */
 var HeaderPanel=new Ext.Panel({
 	                        id:'header-panel',
	                        layout:'fit',
// 	                       	margins:'5 5 5 5',
//                            bodyStyle:'padding:5px 5px 5px 5px',
//                            cmargins:'5 5 5 5',
 	                       	region:'north',
// 	                       	title:'bottom',
 	                       	height:40
 });
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
 CenterCenterColumn.add(innerPanel);	                       	
 	                       	
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

var BottomLayout=new Ext.Panel({
	                        id:'bottom-layout',
	                        layout:'fit',
// 	                       	margins:'5 5 5 5',
                            bodyStyle:'padding:5px 5px 5px 5px',
 	                       	region:'south',
// 	                       	title:'bottom',
 	                       	height:100,
 	                       	html:'<p align="center">2008 <a href="http://www.faceye.com"> www.faceye.com</a> All Rights Reserved <a mail="myecsun@hotmail.com">myecsun@hotmail.com</a></p>'
 	                       }
);

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
var Home={
	init:function(){
      Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
      FullPanel.render(Ext.getBody());
//      FullPanel.doLayout();
	}
};
