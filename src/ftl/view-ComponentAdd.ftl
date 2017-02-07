<form action="/${className2}/${className2}AddSubmit.htm" method="post">
<#list properties as pro>
    <div class="form-div">
        <span>${pro.column_comment}：</span>   
        <input class="easyui-validatebox" type="text" name="${pro.column_name}"/> 
    </div>
</#list>
</form>