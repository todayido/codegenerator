package com.${className2}.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.${className2}.model.${className1};
import com.${className2}.service.${className1}Service;
import com.core.controller.BaseController;
import com.core.controller.IController;
import com.core.model.DataAndView;
import com.core.model.SkipType;
import com.core.service.SequenceGeneratorService;

@Controller
public class ${className1}Controller extends BaseController implements IController {

    private ${className1} ${className2};
    @Resource
	private SequenceGeneratorService sequenceGeneratorService;
	@Resource
	private ${className1}Service ${className2}Service;
	
	
	// index
	public DataAndView ${className2}Index(HttpServletRequest request) throws Exception {
		return null;
	}
	
	// list
	public DataAndView ${className2}List(HttpServletRequest request){
		int page = Integer.parseInt(request.getParameter("page")); // cunrrent page
		int rows = Integer.parseInt(request.getParameter("rows")); // display count per page
		
		// seach params
		<#list conditionsList as conds>
		String ${conds} = request.getParameter("${conds}");
		</#list>
		
		Map paramsMap = new HashMap<String, String>();
		<#list conditionsList as conds>
		paramsMap.put("${conds}", ${conds});
		</#list>
		
		// page params
		paramsMap.put("begin", (page - 1) * rows);
		paramsMap.put("end", rows);
		
		JSONObject jsonObj = new JSONObject();// new一个JSON
		jsonObj.accumulate("total", ${className2}Service.get${className1}Count(paramsMap));// total
		jsonObj.accumulate("rows", ${className2}Service.getAll${className1}s(paramsMap));// rows:data set
		
		return new DataAndView("${className2}List", jsonObj, null, SkipType.FORWARD);
	}
	
	// add
	public DataAndView ${className2}Add(HttpServletRequest request){
		return null;
	}
	
	// add submit
	public DataAndView ${className2}AddSubmit(HttpServletRequest request)  {
		// bind form object
		${className1} ${className2} =  new ${className1}();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(${className2});
		binder.bind(request);
		
		${className2}.set<@upperFC>${primaryKey}</@upperFC>(sequenceGeneratorService.getCurrentPrimaryKey("${tableName}"));
		int row = ${className2}Service.save${className1}(${className2});
		
		return new DataAndView("flag", row, null, SkipType.FORWARD);
	}
	
	// modify
	public DataAndView ${className2}Modify(HttpServletRequest request){
		String ${primaryKey} = request.getParameter("${primaryKey}");
		${className2} = ${className2}Service.get${className1}ById(${primaryKey});
		return new DataAndView("${className2}", ${className2}, null, SkipType.FORWARD);
	}
	
	//modify submit
	public DataAndView ${className2}ModifySubmit(HttpServletRequest request){
		// bind form object
		${className1} ${className2} =  new ${className1}();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(${className2}); 
		binder.bind(request);
		
		int row = ${className2}Service.modify${className1}(${className2});
		return new DataAndView("flag", row, null, SkipType.FORWARD);
	}
	
	// delete
	public DataAndView ${className2}Delete(HttpServletRequest request){
		String params[] = request.getParameterValues("params[]");
		int flag = ${className2}Service.delete${className1}ById(params);
		return new DataAndView("flag", flag, null, SkipType.FORWARD);
	}
	
	public DataAndView execute(HttpServletRequest request) throws Exception {
		return null;
	}

}
