package ${package}.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import ${package}.dao.${className1}Dao;
import ${package}.model.${className1};

@Repository
public class ${className1}DaoImpl implements ${className1}Dao {

	private final String GET_LIST = "get${className1}List";
	private final String GET_COUNT = "get${className1}Count";
	private final String GET_BY_ID = "get${className1}ById";
	private final String MODIFY = "modify${className1}";
	private final String SAVE = "save${className1}";
	private final String DELETE_BY_ID = "delete${className1}ById";
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int delete${className1}ById(String ${primaryKey}) {
		return sqlSessionTemplate.delete(DELETE_BY_ID, ${primaryKey});
	}

	public List<${className1}> getAll${className1}s(Map<?, ?> paramsMap) {
		int offset = (Integer)paramsMap.get("begin");
		int limit = (Integer)paramsMap.get("end");
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSessionTemplate.selectList(GET_LIST, paramsMap, rowBounds);
	}

	public ${className1} get${className1}ById(String ${primaryKey}) {
		return sqlSessionTemplate.selectOne(GET_BY_ID, ${primaryKey});
	}

	public int get${className1}Count(Map<?, ?> paramsMap) {
		return sqlSessionTemplate.selectOne(GET_COUNT, paramsMap);
	}

	public int modify${className1}(${className1} ${className2}) {
		return sqlSessionTemplate.update(MODIFY, ${className2});
	}

	public int save${className1}(${className1} ${className2}) {
		return sqlSessionTemplate.insert(SAVE, ${className2});
	}

}

