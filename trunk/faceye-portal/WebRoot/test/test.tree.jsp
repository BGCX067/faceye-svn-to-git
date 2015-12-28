<%@ include file="/templates/headers/header.jsp"%>
<html>
<head>
<title>Reorder TreePanel</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />

   <script type="text/javascript" src="<c:url value="/scripts/ext/adapter/yui/yui-utilities.js"/>"></script>    
    <script type="text/javascript" src="<c:url value="/scripts/ext/adapter/yui/ext-yui-adapter.js"/>"></script>     <!-- ENDLIBS -->
    <script type="text/javascript" src="<c:url value="/scripts/ext/ext-all.js"/>"></script>
<script type="text/javascript" src="<c:url value="/scripts/ext/examples/tree/reorder.js"/>"></script>

<!-- Common Styles for the examples -->
<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ext/examples/examples.css"/>" />

</head>
<body>
<script type="text/javascript" src="<c:url value="/scripts/ext/examples/examples.js"/>"></script><!-- EXAMPLES -->

Test Tree Container:
<h1>Drag and Drop betweens two TreePanels</h1>
<p>The TreePanels have a TreeSorter applied in "folderSort" mode.</p>
<p>Both TreePanels are in "appendOnly" drop mode since they are sorted.</p>
<p>Drag along the edge of the tree to trigger auto scrolling while performing a drag and drop.</p>
<p>The data for this tree is asynchronously loaded with a JSON TreeLoader.</p>
<p>The js is not minified so it is readable. See <a href="two-trees.js">two-trees.js</a>.</p>

<div id="tree-div" style="overflow:auto; height:300px;width:250px;border:1px solid #c3daf9;"></div>

</body>
</html>
