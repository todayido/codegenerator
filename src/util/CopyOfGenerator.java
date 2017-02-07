package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CopyOfGenerator {

	public static void main(String[] args) {
		System.out.println("---------代码生成开始------------");
		Configuration cfg = new Configuration();
		try {
			cfg.setClassForTemplateLoading(CopyOfGenerator.class, "/ftl"); // 指定模板所在的classpath目录
			cfg.clearTemplateCache(); 
			cfg.setTemplateUpdateDelay(0);
		    cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(0, 0));
			cfg.setDefaultEncoding("UTF-8");  
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter()); // 添加一个"宏"共享变量用来将属性名首字母大写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			// 读取配置文件
			Properties properties = new Properties();
			InputStream in = Object.class.getResourceAsStream("/generator.properties");
			properties.load(in);

			String packageName = properties.getProperty("package_name").trim();
			String className1 = properties.getProperty("class_name1").trim();
			String className2 = properties.getProperty("class_name2").trim();
			String tableName = properties.getProperty("table_name").trim();
			String description = properties.getProperty("description").trim();
			String tablePrimaryKey = properties.getProperty("table_primarykey").trim();
			String canditions = properties.getProperty("canditions").trim();
			// 参数配置
			Map data = new HashMap();
			data.put("package", packageName); // 包名
			data.put("className1", className1); // 类名大写
			data.put("className2", className2); // 类名小写
			data.put("tableName", tableName); // 表名
			data.put("description", description); // 描述
			data.put("primaryKey", tablePrimaryKey);//主键
			List pros = TableInfo.getTableInfo(tableName);
			data.put("properties", pros);
			
			//查询条件设置
			String [] conds = canditions.split(",");
			List conditionsList = new ArrayList();
			for(int i=0; i<conds.length; i++){
				conditionsList.add(conds[i]);
			}
			data.put("conditionsList", conditionsList);
			
			create();
			
			/**
			 * vo:代码生成
			 */
			System.out.println("vo:代码生成开始");
			Template t = cfg.getTemplate("vo.ftl"); // VO指定模板
			
			File file =new File("src/files/model/");    
			if  (!file .exists()  && !file .isDirectory())      
			{       
			    file.mkdir();    
			}
			FileOutputStream fos = new FileOutputStream(new File("src/files/model/"
					+ className1 + ".java")); // java文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("vo:代码生成成功，结束");
			fos.flush();
			fos.close();

			/**
			 * mapping:代码生成
			 */
			System.out.println("mapping:代码生成开始");
			t = cfg.getTemplate("mapping.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/model/"
					+ className1 + ".xml")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("mapping:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * conf:代码生成
			 */
			System.out.println("conf:代码生成开始");
			t = cfg.getTemplate("conf.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/conf/component-"
					+ className2 + ".xml")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("conf:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * compnentAdd:代码生成
			 */
			System.out.println("compnentAdd:代码生成开始");
			t = cfg.getTemplate("ComponentAdd.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "Add.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("compnentAdd:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * compnentAddSubmit:代码生成
			 */
			System.out.println("compnentAddSumit:代码生成开始");
			t = cfg.getTemplate("ComponentAddSubmit.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "AddSubmit.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("compnentAddSubmit:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * controllerIndex:代码生成
			 */
			System.out.println("controllerIndex:代码生成开始");
			t = cfg.getTemplate("ComponentControllerIndex.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "ControllerIndex.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerIndex:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * controllerList:代码生成
			 */
			System.out.println("controllerList:代码生成开始");
			cfg.clearTemplateCache();
			t=null;
			t = cfg.getTemplate("ComponentControllerList.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "ControllerList.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerList:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * controllerDelete:代码生成
			 */
			System.out.println("controllerDelete:代码生成开始");
			cfg.clearTemplateCache();
			t=null;
			t = cfg.getTemplate("ComponentDelete.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "Delete.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerDelete:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * controllerModify:代码生成
			 */
			System.out.println("controllerModify:代码生成开始");
			t=null;
			t = cfg.getTemplate("ComponentModify.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "Modify.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerModify:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * controllerModifySubmit:代码生成
			 */
			System.out.println("controllerModifySubmit:代码生成开始");
			t = cfg.getTemplate("ComponentModifySubmit.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "ModifySubmit.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerModifySubmit:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * dao:代码生成
			 */
			System.out.println("dao:代码生成开始");
			t = cfg.getTemplate("dao.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/dao/"
					+ className1 + "Dao.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("dao:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * daoimpl:代码生成
			 */
			System.out.println("daoimpl:代码生成开始");
			t = cfg.getTemplate("daoimpl.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/dao/impl/"
					+ className1 + "DaoImpl.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("daoimpl:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * service:代码生成
			 */
			System.out.println("service:代码生成开始");
			t = cfg.getTemplate("service.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/service/"
					+ className1 + "Service.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("service:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * serviceimpl:代码生成
			 */
			System.out.println("serviceimple:代码生成开始");
			t = cfg.getTemplate("serviceimpl.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/service/impl/"
					+ className1 + "ServiceImpl.java")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("serviceimpl:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * view-add:代码生成
			 */
			System.out.println("view-add:代码生成开始");
			t = cfg.getTemplate("view-ComponentAdd.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/view/"
					+ className1 + "Add.ftl")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("view-add:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * view-list:代码生成
			 */
			System.out.println("view-list:代码生成开始");
			t = cfg.getTemplate("view-ComponentList.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/view/"
					+ className1 + "List.ftl")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("view-list:代码生成成功，结束");
			fos.flush();
			fos.close();
			
			/**
			 * view-list:代码生成
			 */
			System.out.println("view-mpdify:代码生成开始");
			t = cfg.getTemplate("view-ComponentModify.ftl"); // mapping指定模板
			fos = new FileOutputStream(new File("src/files/view/"
					+ className1 + "Modify.ftl")); // mapping文件的生成目录
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("view-modify:代码生成成功，结束");
			fos.flush();
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("---------代码生成成功，结束------------");
	}
	
	static void create(){
		File file = new File("src/files/model/");    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdir();    
		}
		file = new File("src/files/conf/");    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdir();    
		}
		file = new File("src/files/controller/");    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdir();    
		}
		
		file = new File("src/files/dao/");    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdir();    
		}
		
		file = new File("src/files/dao/impl");    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdir();    
		}
		
		file = new File("src/files/service/");    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdir();    
		}
		
		file = new File("src/files/service/impl");    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdir();    
		}
		
		file = new File("src/files/view/");    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdir();    
		}
		
	}

}
