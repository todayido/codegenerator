<#macro jinghao value>${r"#{"}${value}}</#macro>
<#macro meiyuan value>${r"${"}${value}}</#macro>
<form action="/${className2}/${className2}ModifySubmit.htm" method="post">
    <input type="hidden" name="${primaryKey}" value="<@meiyuan '${className2}.${primaryKey}'/>"/>
    <#list properties as pro>
    <div class="form-div">
        <span>${pro.column_comment}：</span>   
        <input class="easyui-validatebox" type="text" name="${pro.column_name}" value="<@meiyuan '${className2}.${pro.column_name}'/>"/> 
    </div>
    </#list>
</form>