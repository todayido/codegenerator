<#macro jinghao value>${r"#{"}${value}}</#macro>
<#macro meiyuan value>${r"${"}${value}}</#macro>
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.model.${className1}">
	<resultMap type="${className2}" id="${className2}Result">
	   	<#list properties as pro>
	   	<result property="${pro.column_name}" column="${pro.column_name}" />
	   	</#list>
	</resultMap>
	<!-- list -->
	<select id="get${className1}List" parameterType="map" resultMap="${className2}Result">
		SELECT t.* 
		  FROM ${tableName} t
		<where>
 	    	<#list conditionsList as condition>
	    	<if test="${condition} != null and ${condition} != ''">
	    	and t.${condition} like CONCAT(CONCAT('%', <@jinghao '${condition}'/>),'%')
	    	</if>
		    </#list>
 	    </where>
	</select>
	<!-- total count -->
	<select id="get${className1}Count" parameterType="map" resultType="int">
		SELECT COUNT(1) 
		  FROM ${tableName} t
		<where>
 	    	<#list conditionsList as condition>
	    	<if test="${condition} != null and ${condition} != ''">
	    	and t.${condition} like CONCAT(CONCAT('%', <@jinghao '${condition}'/>),'%')
	    	</if>
		    </#list>
 	    </where>
	</select>
	<!-- get info by primary key -->
 	<select id="get${className1}ById" parameterType="string" resultMap="${className2}Result">
	 	SELECT t.*
	      FROM ${tableName} t 
	     WHERE t.${primaryKey} = <@jinghao '${primaryKey}'/>
 	</select>
 	<!-- info save -->
	<insert id="save${className1}" parameterType="${className2}">
		INSERT  INTO ${tableName}
    	(
	    	<#list properties as pro>${pro.column_name}<#if pro_has_next>,</#if></#list>
    	) 
    	VALUES 
    	(
    		<#list properties as pro><@jinghao '${pro.column_name}'/><#if pro_has_next>,</#if></#list>
	    )
 	</insert>
 	<!-- info modify -->
	<update id="modify${className1}" parameterType="${className2}">
		update ${tableName}
 		<set>
 			<#list properties as pro>
 			<#if pro.column_name != primaryKey>
 			<if test="${pro.column_name} != null">
	    		${pro.column_name}=<@jinghao '${pro.column_name}'/>,
	    	</if>
	    	</#if>
			</#list>
 		</set>
 		<where>
 			${primaryKey} = <@jinghao '${primaryKey}'/>
 		</where>
 	</update>
 	<!-- info delete -->
	<delete id="delete${className1}ById" parameterType="string"> 
		DELETE FROM ${tableName} WHERE ${primaryKey} = <@jinghao 'value'/>
	</delete>
</mapper>