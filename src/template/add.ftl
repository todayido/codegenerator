<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common/taglibs.jsp"%>
<#macro jinghao value>${r"#{"}${value}}</#macro>
<#macro meiyuan value>${r"${"}${value}}</#macro>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>list</title>
	</head>
	<%@ include file="/common/jscss.jsp"%>
	<link href="/global/styles/sg-base-gridtable.css?<%=Math.random()%>" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="/global/scripts/sg-utils-1.0.js"></script>
	<c:if test="<@meiyuan 'addSuc==\'1\''/>">
		<script type="text/javascript">
			$(function(){
				alert("added seccussfully!");
				//close popped window
				parent.Pop_Out_Close();
				//refresh parent iframe
				parent.location.reload();
			});
		</script>
	</c:if>
<body>
<form name="formInfo" id="formInfo" action="<@meiyuan 'ctx'/>/${className2}/${className}_add${className}.do" method="post">
	<jsp:include page="${className2}Form.jsp"></jsp:include>
	<input class="form-search-btn" value="Submit" type="submit" />
</form>
</body>
</html>
