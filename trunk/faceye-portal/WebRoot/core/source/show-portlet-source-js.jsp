<%@ include file="/templates/headers/ext-taglib-header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/scripts/dp.SyntaxHighlighter/Styles/SyntaxHighlighter.css"/>" />
<script type="text/javascript"
	src="<c:url value="/scripts/dp.SyntaxHighlighter/Scripts/shCore.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/scripts/dp.SyntaxHighlighter/Scripts/shBrushCSharp.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/scripts/dp.SyntaxHighlighter/Scripts/shBrushJScript.js"/>"></script>
<title>Show Portlet Source JS Window</title>
</head>
<script type="text/javascript">
var url=location.search;
	var params=Ext.urlDecode(url.substring(1));
	var id='';
	if(params.id){
		id=params.id;
		Ext.onReady(function(){
		  var store=new Ext.data.JsonStore({
		    url:BP+'portletAction.do?method=get&id='+id,
		    root:'rows',
		    fields:['id','name','url','source','imageSrc','createTime','init','flag']
		  });
		  store.load({
		   callback:function(r,options,success){
		   //Ext.get('content-iframe').dom.src=r[0].get('url')
		      Ext.Ajax.request({
						url : r[0].get('url'),
						params : {
							id : id,
							name : name
						},
						success : function(response, options) {
							var source = response.responseText;
							Ext.get('js-source').update(source);
							  dp.SyntaxHighlighter.ClipboardSwf = '<c:url value="/scripts/dp.SyntaxHighlighter/Scripts/clipboard.swf"/>';
	                        dp.SyntaxHighlighter.HighlightAll('code');
							}
						});
		   }
		  });
		});
	}

</script>
<body>
  <div>
  <script type="text/javascript"><!--
google_ad_client = "pub-3298094603526581";
google_ad_slot = "3703147378";
google_ad_width = 468;
google_ad_height = 15;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
<br/>
  <a href="http://faceye.com/haipeng/article/402881e42187788c01218f49f53f0005.html">PortalContainer.js</a>|<a href="http://faceye.com/haipeng/article/402881e42187788c01218f4cc1ec0007.html">PortalColumn.js</a>|<a href="http://faceye.com/haipeng/article/402881e42187788c01218f4cc1ec0007.html">Portal.js</a>
  </div>
  
  <div>
  <pre name="code" id="js-source" class="javascript">

  </pre>
 
  </div>
  
  <div>
    <a href="http://faceye.com/haipeng/article/402881e42187788c01218f49f53f0005.html">PortalContainer.js</a>|<a href="http://faceye.com/haipeng/article/402881e42187788c01218f4cc1ec0007.html">PortalColumn.js</a>|<a href="http://faceye.com/haipeng/article/402881e42187788c01218f4cc1ec0007.html">Portal.js</a>
  </div>
   <script type="text/javascript"><!--
google_ad_client = "pub-3298094603526581";
google_ad_slot = "0930406883";
google_ad_width = 468;
google_ad_height = 60;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
  </div>
  
</body>
</html>