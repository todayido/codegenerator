package  ${package}.model;

public class ${className1}{
<#list properties as pro>
    //${pro.column_comment}
    private String <@lowerFC>${pro.column_name}</@lowerFC>;
</#list>
<#list properties as pro>
    public void set<@upperFC><@lowerFC>${pro.column_name}</@lowerFC></@upperFC>(String <@lowerFC>${pro.column_name}</@lowerFC>){  
        this.<@lowerFC>${pro.column_name}</@lowerFC>=<@lowerFC>${pro.column_name}</@lowerFC>;  
    }
    public String get<@upperFC><@lowerFC>${pro.column_name}</@lowerFC></@upperFC>(){
        return this.<@lowerFC>${pro.column_name}</@lowerFC>;
    }
</#list>
}