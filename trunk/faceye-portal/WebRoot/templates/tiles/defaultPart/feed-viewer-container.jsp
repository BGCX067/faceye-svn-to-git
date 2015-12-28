<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
  <title>RSS Feed Viewer 2.0</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />

    <!-- GC --> <!-- LIBS -->     
    <script type="text/javascript" src="<c:url value="/scripts/ext/adapter/yui/yui-utilities.js"/>"></script>    
    <script type="text/javascript" src="<c:url value="/scripts/ext/adapter/yui/ext-yui-adapter.js"/>"></script>     <!-- ENDLIBS -->
    <script type="text/javascript" src="<c:url value="/scripts/ext/ext-all.js"/>"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ext/examples/layout/feed-viewer.css"/>"/>
    <script type="text/javascript" language="javascript" src="<c:url value="/scripts/ext/examples/layout/feed-viewer.js"/>"></script>
</head>
<body id="feedViewer">&nbsp; 

<br><!-- EXAMPLES -->
    <div id="header" class="x-layout-inactive-content">
Misd Framework Desigin by Song Hai Peng
    </div>
    <div id="feeds" class="x-layout-inactive-content">
        <div id="tree-div"></div>
        <div id="myfeeds-body" class="feed-list"></div>
    </div>
    <div id="suggested" class="x-layout-inactive-content feed-list"></div>
    <div id="main" class="x-layout-inactive-content">
        <div id="feed-grid" class="x-layout-inactive-content"></div>
        <div id="preview" class="x-layout-inactive-content">
            <div id="preview-tb"></div>
            <div id="preview-body"></div>
        </div>
    </div>
    <div id="status" class="x-layout-inactive-content"></div>
    <div id="add-feed" style="visibility:hidden;">
        <img alt="tab-close" id="add-feed-close" src="<c:url value="/scripts/ext/examples/layout/images/tab-close-on.gif"/>" />
		<p id="add-title" class="active-msg">Please enter the URL for the feed:</p>
		<p id="invalid-feed"><img src="<c:url value="/scripts/ext/examples/layout/images/warning.gif"/>" width="16" height="16" align="absmiddle" alt="" />&nbsp;The feed URL specified is not a valid RSS feed.</p>
		<p id="loading-feed"><img src="<c:url value="/scripts/ext/examples/layout/images/wait.gif"/>" width="18" height="18" align="absmiddle" />&nbsp;Loading feed...</p>
		<input type="text" id="feed-url" name="feed-url" class="input-text"/><input id="add-btn" type="button" value="Add"/><br>
		<span class="eg">e.g. http://www.jackslocum.com/yui/feed/</span>
    </div>
</body>
</html>
