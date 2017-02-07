package util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TableInfo {

	public static List getTableInfo(String tableName) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SqlSessionFactory factory = (SqlSessionFactory) ac
				.getBean("sqlSessionFactory");
		
		SqlSession session = factory.openSession();
		
		return session.selectList("getTableInfo", tableName);

	}
}
