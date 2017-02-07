<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<#macro jinghao value>${r"#{"}${value}}</#macro>
<#macro meiyuan value>${r"${"}${value}}</#macro>
<title><@meiyuan 'sys_html_title'/></title>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/global/scripts/jquery-easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="/global/scripts/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/global/styles/sg-easyui-1.0.css">
<link rel="stylesheet" type="text/css" href="/global/styles/sg-manager-1.0.css">
<script type="text/javascript" src="/global/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/global/scripts/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/global/scripts/sg-easyui-utils-1.0.js"></script>
<script type="text/javascript" src="/global/scripts/sg-easyui-validate-1.0.js"></script>
</head>
<script>
//分页
$(function(){
    var p = $('#dataTable').datagrid('getPager');  
    $(p).pagination({
        pageNumber:1,
        pageSize:10,//每页显示的记录条数，默认为10  
        pageList:[10,20,40,60],//可以设置每页记录条数的列表  
        beforePageText:'第',//页数文本框前显示的汉字  
        afterPageText:'页    共 {pages} 页',  
        displayMsg:'当前显示 {from} - {to} 条记录   共 {total} 条记录',  
    });  
});

//排序
function format(value,row,index){return row.${primaryKey};}
function sortRules(a,b){return (a>b?1:-1);}

//搜索
function searchData(){
    $('#dataTable').datagrid('load',{
        <#list conditionsList as condition>
        ${condition}:$('#${condition}').val()<#if condition_has_next>,</#if>
        </#list>
        }
    );
}  

//清空查询条件
function clearSearch(){
    $('input').val('');
}
</script>
<body>
<div id="toolbars">
	<table>
	    <tr>
	        <td>
	        <#list conditionsList as condition>
	        <span class="search-desc">条件：</span><input type="text" id="${condition}" name="${condition}"/>
	        </#list>
	        <a class="search-btn" href="javascript:void(0);" onclick="searchData();">查 找</a>
	        <a class="search-btn" href="javascript:void(0);" onclick="clearSearch();">清 空</a>
	        </td>
		</tr>
		<tr>
			<td>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
				   onclick="javascrip:$('#${className2}Dlg').utilsOpenDialogAdd('dataTable',500,350,'添加信息','/${className2}/${className2}Add.htm');">新增</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" 
				    onclick="javascript:$('#${className2}Dlg').utilsOpenDialogModify('dataTable','${primaryKey}',500,350,'信息修改','/${className2}/${className2}Modify.htm')">修改</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
				    onclick="javascript:$('#${className2}Dlg').utilsDeleteRows('dataTable','${primaryKey}','/${className2}/${className2}Delete.htm')">删除</a> 
			</td>
		</tr>
	</table>
</div>
<table id="dataTable" class="easyui-datagrid" url="/${className2}/${className2}List.htm" iconCls="icon-save" loadMsg="数据加载中,请稍后……"
       nowrap="true" toolbar="#toolbars" rownumbers="true" pagination="true" remoteSort="false" idField="${primaryKey}"  fit="true">
    <thead>
        <tr>
            <th field="${primaryKey}" checkbox="true"></th>
            <#list properties as pro>
	        <#if '${pro.column_name}'=='${primaryKey}'>
	        <th field="null-1" formatter="format" sortable="true"  sorter="sortRules">${pro.column_comment}</th>
	        <#else>
	        <th field="${pro.column_name}" sortable="true" sorter="sortRules">${pro.column_comment}</th>
	        </#if>
	        </#list>
        </tr>
    </thead>
</table>
<!-- 对话框 -->
<div id="${className2}Dlg"></div>
</body>
</html>