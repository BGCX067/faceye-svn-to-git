<%@ include file="/templates/headers/headerWithToolBar.jsp"%>
<%@ include file="/templates/headers/ext.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title><fmt:message key="com.faceye.home.default.title"></fmt:message>${user.username}<fmt:message key="com.faceye.home.default.rssReader"></fmt:message></title>
    <script type="text/javascript" src="<c:url value="/scripts/portlets/BlogManagerPanel.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/scripts/portlets/BlogArticleCategory.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/SessionProvider.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/TabCloseMenu.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/FeedSubscribe.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/FeedViewer.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/FeedWindow.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/FeedGrid.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/MainPanel.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/components/navigation/feed/feed-viewer/FeedPanel.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/components/navigation/feed/feed-viewer/feed-viewer.css"/>" />

</head>
<body>
<!-- Template used for Feed Items --> 
<textarea id="preview-tpl" style="display:none;">
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
