<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Feed Viewer 3</title>

    <link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/adapter/ext/ext-base.js"/>"></script>
		<!-- ENDLIBS -->
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/ext-all.js"/>"></script>
			<script type="text/javascript"
			src="<c:url value="/scripts/util/Util.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/FeedSubscribe.js"/>"></script>
</head>
<body>
<div id="header"><div style="float:right;margin:5px;" class="x-small-editor"><!-- Template used for Feed Items --> 
</div></div><textarea id="preview-tpl" style="display:none;">
    <div class="post-data">
        <span class="post-date">{pubDate:date("M j, Y, g:i a")}</span>
        <h3 class="post-title">{title}</h3>
        <h4 class="post-author">by {author:defaultValue("Unknown")}</h4>
    </div>
    <div class="post-body">{content:this.getBody}</div>
</textarea>
<div id="parent-select-tree"></div>
</body>
</html>
