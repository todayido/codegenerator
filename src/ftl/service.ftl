package ${package}.service;

import java.util.List;
import java.util.Map;

import ${package}.model.${className1};

public interface ${className1}Service {

	public abstract int save${className1}(${className1} ${className2});

	public abstract int delete${className1}ById(String[] params);

	public abstract int modify${className1}(${className1} ${className2});

	public abstract ${className1} get${className1}ById(String ${primaryKey});

	public abstract int get${className1}Count(Map<?, ?> paramsMap);

	public abstract List<${className1}> getAll${className1}s(Map<?, ?> paramsMap);
}

