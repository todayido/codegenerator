<#macro meiyuan value>${r"${"}${value}}</#macro>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" name="${className2}Params.${primaryKey}" value="<@meiyuan '${className2}Params.${primaryKey}'/>"/>
<div class="info">
	<table border="0" cellspacing="0" cellpadding="0">
		<#list properties as pro>
	   	<tr>
			<td>${pro.column_comment}</td><td><input class="form-search-text" name="${className2}Params.${pro.column_name}" value="<@meiyuan '${className2}Params.${pro.column_name}'/>" /></td>
		</tr>
	   	</#list>
	</table> 
</div>