<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>
		<title>Faceye Feed Reader</title>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/adapter/ext/ext-base.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/ext-all.js"/>"></script>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext/examples/grid/grid-examples.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/components/navigation/feed/FeedBuilder.js"/>"></script>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/css/Common.css"/>" />
		<script type="text/javascript" src="<c:url value="/scripts/ext/build/locale/ext-lang-zh_CN.js"/>"></script>
			
			
	<style type="text/css">
       html, body {
	font: normal 12px tahoma, arial, verdana, sans-serif;
	margin: 0;
	padding: 0;
	border: 0 none;
	overflow: hidden;
	height: 100%;
}

.ext-el-mask {
    background:#dadadb;
}
.x-grid3-td-title b {
    font-family:tahoma, verdana;
    display:block;
    color:#333;
}
.x-grid3-td-title b i {
    font-weight:normal;
    font-style: normal;
    color:#000;
}
.x-grid3-td-title .topic {
    padding-left:20px;
    background:transparent url(./images/faceye/feed-viewer/images/post.gif) no-repeat 0 2px;
}
.x-grid3-td-title .x-grid3-cell-inner {
    white-space:normal;
}
.x-grid3-td-title a {
    color: #385F95;
    text-decoration:none;
}
.x-grid3-td-title a:hover {
    text-decoration:underline;
}
.details .x-btn-text {
    background-image: url(./images/faceye/feed-viewer/images/details.gif);
}
.msg-view {
    background-image: url(./images/faceye/feed-viewer/images/application_view_list.png) !important;
}
.add-feed {
    background-image: url(./images/faceye/feed-viewer/images/rss_add.gif) !important;
}

.preview-bottom {
    background-image: url(./images/faceye/feed-viewer/images/preview-bottom.gif) !important;
}
.preview-right {
    background-image: url(./images/faceye/feed-viewer/images/preview-right.gif) !important;
}
.preview-hide {
    background-image: url(./images/faceye/feed-viewer/images/preview-hide.gif) !important;
}

.tabs {
    background-image: url( ./images/faceye/feed-viewer/images/tabs.gif ) !important;
}
.summary {
    background-image: url(./images/faceye/feed-viewer/images/details.gif) !important;
}
.x-grid3-row-body p {
    margin:5px 20px 10px 25px !important;
    color:#555;
}

.post-date {
    font-weight:bold;
    color:#333;
}

.author {
    color:#333;
}

.x-tree {
    background:#fff !important;
}
.x-border-layout-ct {
    background:transparent;
}
body.x-border-layout-ct {
    background:#c3d5ed url(./images/faceye/feed-viewer/images/bg.gif) repeat-x left top !important;
}
.x-layout-split {
    background-color:transparent !important;
}

.x-tree-node div.feeds-node{
    background:#eee url(./images/faceye/feed-viewer/images/cmp-bg.gif) repeat-x;
    margin-top:1px;
    border-top:1px solid #ddd;
    border-bottom:1px solid #ccc;
    padding-top:2px;
    padding-bottom:1px;
}
.feeds-node .x-tree-node-icon {
    display:none;
}
.forum-ct a span {
    font-weight:bold;
    color:#222;
}
.feed {
    border:1px solid #fff;
    margin:3px;
}
.feed .x-tree-ec-icon {
    display:none;
}

.feed-icon {
    background-image:url(./images/faceye/feed-viewer/images/rss.gif) !important;
}
.new-tab {
    background-image:url(./images/faceye/feed-viewer/images/new_tab.gif) !important;
}
.new-win {
    background-image:url(./images/faceye/feed-viewer/images/go-to-post.gif) !important;
}
.delete-icon {
    background-image:url(./images/faceye/feed-viewer/images/rss_delete.gif) !important;
}
.load-icon {
    background-image:url(./images/faceye/feed-viewer/images/rss_load.gif) !important;
}
.refresh-icon {
    background-image:url(./images/faceye/feed-viewer/images/table_refresh.png) !important;
}

.x-tree-selected {
    border:1px dotted #a3bae9;
    background:#DFE8F6;
}
.x-tree-node .x-tree-selected a span{
	background:transparent;
	color:#15428b;
    font-weight:bold;
}

#topic-grid .x-panel-tbar .x-toolbar {
   /* border-top:0 none; */
}


#main-tabs .x-tab-panel-body {
    background:transparent;
    border:0 none;
}

/* Preview classes */

.preview .x-panel-body {
    background:#fff;
    color:#222;
    font:normal 12px tahoma,verdana,arial,sans-serif;
}

.preview .x-panel-body p {
    line-height:18px;
    margin:8px 0;
}

.preview .x-panel-body ul {
    margin-left:18px;
}

.preview .x-panel-body ul li {
    display:list-item;
    list-style-image:none !important;
    list-style-position:outside !important;
    list-style-type:disc !important;
    margin-left:18px;
}

.preview .x-panel-body pre, #preview .x-panel-body code {
    background:#f1f1f1;
    display:block;
}

.preview .x-panel-body h4.post-author {
    font-weight:normal;
    color:#555;
    font-size:11px;
}
.preview .x-panel-body span.post-date {
    font-weight:normal;
    color:#555;
    float:right;
    font-size:11px;
}
.preview .x-panel-body div.post-data {
    background:#f1f2f4;
    padding:5px;
    border-bottom:1px solid #dadadb;
}
.preview .x-panel-body div.post-body {
    padding:10px;
}
/*.single-preview .x-toolbar, #right-preview .preview .x-toolbar {
    border-top:0 none;
}*/
.reading-menu .x-menu-item-checked {
   border:1px dotted #a3bae9 !important;
    background:#DFE8F6;
	padding:0;
}
.x-tab-panel-header {
    border-bottom-width:0 !important;
}
#suggested {
    border-top:0 none;
}
#suggested a {
    text-decoration:none;
    font-size:11px;
    color:#15428B;
    display:block;
    padding:3px;
    float:right;
    margin-right:4px;
}
#suggested a:hover {
    text-decoration:underline;
    color:#15428B;
}
.x-combo-list-item {
    zoom:1;
}
.x-combo-list-item strong {
    color:#777;
    font-size:11px;
    font-style: normal;
    float:left;
}
.x-combo-list-item em {
    color:#222;
    font-size:12px;
    font-style: normal;
    float:left;
    width:325px;
}
.x-node-ctx {
    background:#eee !important;
    border:1px solid #ccc !important;
}
    </style>
	</head>
	<script type="text/javascript">
Ext.onReady(com.faceye.ui.feed.FeedViewer.init,com.faceye.ui.feed.FeedViewer);
</script>
	<body>
		<div id="header"><div style="float:right;margin:5px;" class="x-small-editor"></div></div>

<!-- Template used for Feed Items -->
<textarea id="preview-tpl" style="display:none;">
    <div class="post-data">
        <span class="post-date">{pubDate:date("M j, Y, g:i a")}</span>
        <h3 class="post-title">{title}</h3>
        <h4 class="post-author">by {author:defaultValue("Unknown")}</h4>
    </div>
    <div class="post-body">{content:this.getBody}</div>
</textarea>
	</body>
</html>
