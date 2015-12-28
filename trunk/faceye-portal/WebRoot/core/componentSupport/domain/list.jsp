<%@ include file="/templates/headers/header.jsp"%>
<html>
	<head>
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css"
			href="<c:url value="/scripts/ext/resources/css/ext-all.css"/>" />
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/adapter/ext/ext-base.js"/>"></script>
		<!-- ENDLIBS -->
		<script type="text/javascript"
			src="<c:url value="/scripts/ext/ext-all.js"/>"></script>
		<!-- Common Styles for the examples -->
		<script type="text/javascript"
			src="<c:url value="/scripts/common/common.js"/>"></script>
			<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/ext/examples/form/forms.css"/>" />
	</head>
	<script type="text/javascript"><%--
	var list={cName:'${tt}'};
	alert(list);
	Ext.apply(Domain,list);
    Ext.onReady(Domain.init, Domain);
--%></script>
	<body>

		<div style="width:100%;" class="x-box-blue">
			<div class="x-box-tl">
				<div class="x-box-tr">
					<div class="x-box-tc"></div>
				</div>
			</div>
			<div class="x-box-ml">
				<div class="x-box-mr">
					<div class="x-box-mc">
						<h3 style="margin-bottom:0px;">
							Domain Entity List
						</h3>
						<div id="list-tool-bar" class="x-box-tl">
							This is a tool Bar div
							<html:link
								href="domainAction.do?method=update&entityClass=com.zaodian.core.componentsupport.dao.model.Domain">Add DOMAIN</html:link>
						</div>
						<div id="form-dialog" style="visibility:hidden">
							<div class="x-dlg-hd">
								Add New Record
							</div>

							<div class="x-dlg-bd">
								<div id="form-dialog-inner" class="x-layout-inactive-content">
									<div id="form"></div>
								</div>
							</div>
						</div>
						<div id="topic-grid"
							style="border:1px solid #99bbe8;overflow: hidden; width: 100%; height: 470px;position:relative;left:0;top:0;"></div>
					</div>
				</div>
			</div>
			<div class="x-box-bl">
				<div class="x-box-br">
					<div class="x-box-bc"></div>
				</div>
			</div>
		</div>


	</body>
</html>
