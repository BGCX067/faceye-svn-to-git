/**
 * www.faceye.com网络支持系统
 * 作者:宋海鹏 ecsun@sohu.com/myecsun@hotmail.com
 * 说明:栏目展现
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
 	       	       	 		'<p><a href="http://sohu.com" target="_blank">{text}</a></p>' +
 	       	       	 		'<p>link is:{link}</p>'+
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
 			               // 为每个面版准备数据
 			            //生成装载内容的面板，主要解决面板间没有间隔的问题，所以使用两个面板组合在一起
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
 		                 var contentPanel=new Ext.Panel({
 	       	       	     layout:'fit',
 	       	       	     frame:false,
                         autoHeight:true,
                         title:r[i].data['text'],
 	       	       	     border:true
// 	       	       	     html:'<p><a href="http://www.sohu.com" target="_blank">SOHU</a></p>'
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