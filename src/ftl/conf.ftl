<?xml version="1.0" encoding="UTF-8"?>
<actions package="${className2}">
    <action id="${className2}Index" name="${description}首页" type="html" class="com.${className2}.controller.${className1}Controller" method="${className2}Index">
        <view name="${description}首页">classes/com/${className2}/view/${className1}List.ftl</view>
    </action>
    <action id="${className2}List" name="${description}列表" type="json" class="com.${className2}.controller.${className1}Controller" method="${className2}List"/>
    <action id="${className2}Add" name="${description}新增" type="html" class="com.${className2}.controller.${className1}Controller" method="${className2}Add">
       <view name="${description}新增">classes/com/${className2}/view/${className1}Add.ftl</view>
    </action>
    <action id="${className2}AddSubmit" name="${description}新增提交" type="json" class="com.${className2}.controller.${className1}Controller" method="${className2}AddSubmit"/>
    <action id="${className2}Modify" name="${description}修改" type="html" class="com.${className2}.controller.${className1}Controller" method="${className2}Modify">
       <view name="${description}修改">classes/com/${className2}/view/${className1}Modify.ftl</view>
    </action>
    <action id="${className2}ModifySubmit" name="${description}修改提交" type="json" class="com.${className2}.controller.${className1}Controller" method="${className2}ModifySubmit"/>
    <action id="${className2}Delete" name="${description}删除" type="json" class="com.${className2}.controller.${className1}Controller" method="${className2}Delete"/>
</actions>