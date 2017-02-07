package ${package}.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.${className2}.model.${className};
import com.${className2}.service.${className}Service;
import com.core.controller.BaseController;
import com.core.controller.IController;
import com.core.model.DataAndView;
import com.core.model.SimplePage;
import com.core.model.SkipType;

@Controller
public class ${className}Controller extends BaseController implements IController {

	// default value of the per page count
	private static int DEFAULT_PAGE_SIZE = 15;
	@Resource
	private ${className}Service ${className2}Service;
	private SimplePage simplePage;
	private List ${className2}List;
	private Map paramsMap;
	private ${className} ${className2}Params;
	private String[] params;
	
	public DataAndView execute(HttpServletRequest request){
		paramsMap = new HashMap<String, String>();
		if(${className2}Params != null){
		<#list conditionsList as condition>
			paramsMap.put("${condition}", ${className2}Params.get<@upperFC>${condition}</@upperFC>());
		</#list>
		}
		
		int count = ${className2}Service.get${className}Count(paramsMap);
		// page
		if(simplePage == null){
			simplePage = new SimplePage(1,DEFAULT_PAGE_SIZE,count); 
		}else{
			simplePage = new SimplePage(simplePage.getPageNo(),DEFAULT_PAGE_SIZE,count); 
		}
		paramsMap.put("begin", (simplePage.getPageNo() - 1) * simplePage.getPageSize());
		paramsMap.put("end", simplePage.getPageSize());
		
		${className2}List = ${className2}Service.getAll${className}s(paramsMap);

		DataAndView("${className2}List", ${className2}List, null, SkipType.FORWARD);
	}
	
	
	public SimplePage getSimplePage() {
		return simplePage;
	}

	public void setSimplePage(SimplePage simplePage) {
		this.simplePage = simplePage;
	}

	public ${className} get${className}Params() {
		return ${className2}Params;
	}

	public void set${className}Params(${className} ${className2}Params) {
		this.${className2}Params = ${className2}Params;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

}
