/**
＊Home Builder.js
* 首页构建
* 作者：宋海鹏
* QQ:82676683
* E_mail：ecsun@sohu.com
*/
/**
*构建首页导航区域
* 首页导航分类传统导航和feed导航,这两部分导航分为两个panel来实现.
* 每个导航都按其最基本的结构来做.
*/
Ext.namespace('com.faceye');
com.faceye.HomeBuilder={
	init:function(CenterCenterColumn){
		//传统导航面板
		var traditionContainer=new Ext.Panel({
		 id:'tradition-container',
	     layout:'fit',
	     autoHeight:true,
	     bodyStyle:'padding:0 0 5px 0',
	     border:false
	  });
	  /**
	   * 传统导航要装载数据的面板
	   */
	  	var traditionInnerContainer=new Ext.Panel({
		　 id:'tradition-inner-container',
	    　 layout:'fit',
	        border:true,
	        collapsible:true,
	     　height:200,
	        autoHeight:true,
	        bodyStyle:'padding:2px 5px 2px 5px;font-size:14;',
	        title:'传统导航'
//	     　margins:'0 0 5 0',
	  	 });
	  	 
	  	/**
	  	 * 为传统导航添加数据
	  	 * 默认显示门户网站导航数据
	  	 * 存在各个分类面板,主的推荐一些有价值的面板.
	  	 * 传入常量,门户网站ID：402881b31764776f0117649634060005
	  	 */ 
	  	 var traditionStore=new Ext.data.Store({
	  	 	proxy: new Ext.data.HttpProxy({
                  url:BP+'traditionAction.do'
            }),
            reader: new Ext.data.JsonReader({
                 root: 'root',
                 totalProperty: 'total',
                 id: 'id',
                 fields: [
                  'id','name','url','description','columnId','columnName','createDate','categoryId','categoryName'
                 ]
             })
	  	 });
	  	 //加载传统导航门户数据
	  	 traditionStore.load({
	  	 	params:{
	  	 		method:'getTraditions',
	  	 		categoryId:'402881b31764776f0117649634060005',
	  	 		start:'-1'
	  	 	}
	  	 });

	  	 /**
	  	  * 为显示传统导航数据,创建使用的模板.
	  	  */
	  	  var traditionTemplate=new Ext.XTemplate('<div id="tradition-view-template-div"><table id="tradition-view-template-table"><tr>' +
	  	  		'<tpl for=".">' +
	  	  		'<td><a href="{url}" target="_blank">{name}</a></td>' +
	  	  		'<tpl if="this.isEnter(xindex)==true">'+
	  	  		'</tr>' +
	  	  		'<tr>' +
	  	  		'</tpl>'+
	  	  		'</tpl></table></div>',{
	  	  			isEnter:function(index){
	  	  				if(index % 6 ===0){
	  	  					return true;
	  	  				}else{
	  	  					return false;
	  	  				}
	  	  			}
	  	  		});
	  	  		
	  	  /**
	  	   * 创建数据展示对像
	  	   */
	  	   var traditionDataView=new Ext.DataView({
	  	   	store:traditionStore,
	  	   	tpl:traditionTemplate,
	  	   	loadingText:'正在加载...',
 		    style:'overflow:auto',
 		    itemSelector:'div-column',
            multiSelect: true,
            autoHeight:true
	  	   });
	  	   
	  	   /**
	  	    * 创建数据对像
	  	    */
	  	    function loadDataView(store,tpl){
	  	    	var dataView=new Ext.DataView({
	  	   	        store:store,
	  	         	tpl:tpl,
	  	   	        loadingText:'正在加载...',
 		            style:'overflow:auto',
 		            itemSelector:'div-column',
                    multiSelect: true,
                    autoHeight:true
	  	         });
	  	         return dataView;
	  	    }
	  	   /**
	  	    * 传统导航table
	  	    */
	  	    var traditionTables=new Ext.TabPanel({
	  	      activeTab: 0,
	  	      autoHeight:true,
              plain:true,
              deferredRender:false,
              defaults:{autoScroll: false}
	  	    });
	  	    /**
	  	     * 门户
	  	     * 门户　categoryId:402881b31764776f0117649634060005
	  	     */
	  	     var portalTable=new Ext.Panel({
              	id:'portal-table',
              	title: '门户',
              	autoHeight:true,
                items:loadDataView(traditionStore,traditionTemplate)
              });
              traditionTables.add(portalTable);
	  	    /**
	  	     * 博客
	  	     * 博客 categoryID:402881ce179c528301179cd5c3850001
	  	     */
	  	  var blogStore=new Ext.data.Store({
	  	 	proxy: new Ext.data.HttpProxy({
                  url:BP+'traditionAction.do'
            }),
            reader: new Ext.data.JsonReader({
                 root: 'root',
                 totalProperty: 'total',
                 id: 'id',
                 fields: [
                  'id','name','url','description','columnId','columnName','createDate','categoryId','categoryName'
                 ]
             })
	  	 });
	  	  blogStore.load({
	  	   	params:{
	  	 		method:'getTraditions',
	  	 		categoryId:'402881ce179c528301179cd5c3850001',
	  	 		start:'-1'
	  	 	}
	  	  });
	  	     var blogTable=new Ext.Panel({
              	id:'table-blog',
              	title: '博客',
              	items:loadDataView(blogStore,traditionTemplate)
              });
              traditionTables.add(blogTable);
	  	    /**
	  	     * 视频
	  	     * 视频categoryId:402881ce179c528301179cd62eb80002
	  	     */
	  	     var videoStore=new Ext.data.Store({
	  	 	proxy: new Ext.data.HttpProxy({
                  url:BP+'traditionAction.do'
            }),
            reader: new Ext.data.JsonReader({
                 root: 'root',
                 totalProperty: 'total',
                 id: 'id',
                 fields: [
                  'id','name','url','description','columnId','columnName','createDate','categoryId','categoryName'
                 ]
             })
	  	 });
	  	   videoStore.load({
	  	   	params:{
	  	 		method:'getTraditions',
	  	 		categoryId:'402881ce179c528301179cd62eb80002',
	  	 		start:'-1'
	  	 	}
	  	  });
	  	     var videoTable=new Ext.Panel({
              	id:'table-video',
              	title: '视频',
              	items:loadDataView(videoStore,traditionTemplate)
              });
              traditionTables.add(videoTable);
	  	    /**
	  	     * 音乐
	  	     * 音乐categoryId:402881ce179c528301179cd66b3e0003
	  	     */
	  	     var musicStore=new Ext.data.Store({
	  	 	proxy: new Ext.data.HttpProxy({
                  url:BP+'traditionAction.do'
            }),
            reader: new Ext.data.JsonReader({
                 root: 'root',
                 totalProperty: 'total',
                 id: 'id',
                 fields: [
                  'id','name','url','description','columnId','columnName','createDate','categoryId','categoryName'
                 ]
             })
	  	 });
	  	 musicStore.load({
	  	 	params:{
	  	 		method:'getTraditions',
	  	 		categoryId:'402881ce179c528301179cd66b3e0003',
	  	 		start:'-1'
	  	 	}
	  	 });
	  	    var musicTable=new Ext.Panel({
              	id:'table-music',
              	title: '音乐',
              	items:loadDataView(musicStore,traditionTemplate)
	  	    });
	  	    traditionTables.add(musicTable);
	  	    /**
	  	     * 交友
	  	     * 交友  categoryId:402881ce179c528301179cd6b0e50004
	  	     */
	  	     var friendStore=new Ext.data.Store({
	  	 	proxy: new Ext.data.HttpProxy({
                  url:BP+'traditionAction.do'
            }),
            reader: new Ext.data.JsonReader({
                 root: 'root',
                 totalProperty: 'total',
                 id: 'id',
                 fields: [
                  'id','name','url','description','columnId','columnName','createDate','categoryId','categoryName'
                 ]
             })
	  	 });
	  	 friendStore.load({
	  	 	params:{
	  	 		method:'getTraditions',
	  	 		start:'-1',
	  	 		categoryId:'402881ce179c528301179cd6b0e50004'
	  	 	}
	  	 });
	  	     var friendTable=new Ext.Panel({
	  	     	id:'table-friend',
              	title: '交友',
              	items:loadDataView(friendStore,traditionTemplate)
	  	     });
	  	    traditionTables.add(friendTable);
	  	    /**
	  	     * IT技术
	  	     * IT技术categoryId:'402881ce179c528301179cd6dc020005', 'IT技术', ''
	  	     */
	  	     var itStore=new Ext.data.Store({
	  	 	proxy: new Ext.data.HttpProxy({
                  url:BP+'traditionAction.do'
            }),
            reader: new Ext.data.JsonReader({
                 root: 'root',
                 totalProperty: 'total',
                 id: 'id',
                 fields: [
                  'id','name','url','descriptio n','columnId','columnName','createDate','categoryId','categoryName'
                 ]
             })
	  	 });
	  	 itStore.load({
	  	 	params:{
	  	 		method:'getTraditions',
	  	 		start:'-1',
	  	 		categoryId:'402881ce179c528301179cd6dc020005'
	  	 	}
	  	 });
	  	 
	  	  var itTable=new Ext.Panel({
              	id:'table-it',
              	title: 'IT技术',
              	items:loadDataView(itStore,traditionTemplate)
              });
	  	   traditionTables.add(itTable);
	  	 /**
	  	  * 添加tradition navigation data-view到传统导航面板
	  	  */
          traditionInnerContainer.add(traditionTables);
	  	  traditionInnerContainer.doLayout();
	 /**
	  * 开始为传统导航装载数据
	  */
	  traditionContainer.add(traditionInnerContainer);
	   var feedContainer=new Ext.Panel({
	   		 id:'feed-container',
	         layout:'fit',
	         collapsible:true,
	         height:200,
	         title:'Feed导航'
	   });
	   CenterCenterColumn.add(traditionContainer);
	   CenterCenterColumn.add(feedContainer);
	}
};