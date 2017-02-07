<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC "-//Apache Software Foundation//DTD Struts Configuration 2.1//EN" "http://struts.apache.org/dtds/struts-2.1.dtd">
<struts>
	<package name="${className2}" namespace="/${className2}" extends="struts-my">
		<action name="${className}_*" class="${className2}Action" method="{1}">
			<result name="${className2}List">/${className2}/${className2}List.jsp</result>
			<result name="add${className}">/${className2}/add${className}.jsp</result>
			<result name="modify${className}">/${className2}/modify${className}.jsp</result>
			<interceptor-ref name="myStack"></interceptor-ref>
		</action>
	</package>
</struts>