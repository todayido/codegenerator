package ${package}.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${package}.dao.${className1}Dao;
import ${package}.model.${className1};
import ${package}.service.${className1}Service;

@Service
public class ${className1}ServiceImpl implements ${className1}Service {

	@Resource
	${className1}Dao ${className2}Dao;
	
	public int delete${className1}ById(String[] params) {
		int row = 0;
		for(String ${primaryKey} : params){
			row =+ ${className2}Dao.delete${className1}ById(${primaryKey});
		}
		return row;
	}

	public List<${className1}> getAll${className1}s(Map<?, ?> paramsMap) {
		return ${className2}Dao.getAll${className1}s(paramsMap);
	}

	public ${className1} get${className1}ById(String ${primaryKey}) {
		return ${className2}Dao.get${className1}ById(${primaryKey});
	}

	public int get${className1}Count(Map<?, ?> paramsMap) {
		return ${className2}Dao.get${className1}Count(paramsMap);
	}

	public int modify${className1}(${className1} ${className2}) {
		return ${className2}Dao.modify${className1}(${className2});
	}

	public int save${className1}(${className1} ${className2}) {
		return ${className2}Dao.save${className1}(${className2});
	}

}

