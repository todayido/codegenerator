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
		<title>列表</title>
	</head>
	<%@ include file="/common/jscss.jsp"%>
	<link href="/global/styles/sg-base-gridtable.css?<%=Math.random()%>" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="/global/scripts/sg-utils-1.0.js"></script>
	<c:if test="<@meiyuan 'delSuc==\'1\''></@meiyuan>">
		<script type="text/javascript">
			$(function(){
				alert("删除成功!");
				searchForm.submit();
			});
		</script>
	</c:if>
<body>
<form id="searchForm" name="searchForm" action="<@meiyuan 'ctx'/>/${className2}/${className}_${className2}List.do" method="post">
	<div class="search">
		<#list conditionsList as condition>
		条件  <input class="form-search-text" name="${className2}Params.${condition}" value="<@meiyuan '${className2}Params.${condition}'/>" />
		</#list>
		<input class="form-search-btn" value="查询" type="submit" />
		<input class="form-search-btn" value="添加" type="button" onclick="Pop_Out('添加','${className2}/${className}_add${className}.do','460','300');"/>
		<input class="form-search-btn" value="删除" type="button" onclick="Utils_Batch_Delete('params','searchForm','${className2}/${className}_delete${className}s.do')"/>
	</div>
	<table class="gridtable" cellpadding="0" cellspacing="1">
	<tr>
		<th width="3%">序号</th>
		<th width="3%">
			<input type="checkbox" id="selectAll" name="selectAll" onclick="Utils_CheckBox_Selected('params',this.checked)"/>
		</th>
		<#list properties as pro>
		<th>${pro.column_comment}</th>
		</#list>
		<th>操作</th>
	</tr>
	<c:forEach items="<@meiyuan '${className2}List' />" var="${className2}" varStatus="index">
		<tr>
			<td align="center"><@meiyuan 'index.index+1'/></td>
			<td width="3%" align="center">
				<input type="checkbox" name="params" value="<@meiyuan '${className2}.${primaryKey}' />"/>
			</td>
			<#list properties as pro>
			<td><@meiyuan '${className2}.${pro.column_name}'/></td>
			</#list>
			<td align="center">
				<a href="javascript:void(0);" onclick="Pop_Out('信息修改','${className2}/${className}_modify${className}.do?${primaryKey}=<@meiyuan '${className2}.${primaryKey}'/>','460','300');">修改</a>
			</td>
		</tr>
	</c:forEach>
</table>
<div style="float:right;margin-top:10px;">
	<jsp:include page="/common/SimplePage.jsp"></jsp:include>
	<jsp:include page="/common/PopOut.jsp"></jsp:include>
</div>
</form>
</body>
</html>
