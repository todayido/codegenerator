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

public class GeneratorOracle {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		try {
			cfg.setClassForTemplateLoading(CopyOfGenerator.class, "/ftl"); // ָ��ģ�����ڵ�classpathĿ¼
			cfg.clearTemplateCache(); 
			cfg.setTemplateUpdateDelay(0);
		    cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(0, 0));
			cfg.setDefaultEncoding("UTF-8");  
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter()); // ���һ��"��"�����������������������ĸ��д
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());

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
			// ��������
			Map data = new HashMap();
			data.put("package", packageName); // ����
			data.put("className1", className1); // �����д
			data.put("className2", className2); // ����Сд
			data.put("tableName", tableName); // ����
			data.put("description", description); // ����
			data.put("primaryKey", tablePrimaryKey);//����
			List pros = TableInfo.getTableInfo(tableName);
			data.put("properties", pros);
			
			String [] conds = canditions.split(",");
			List conditionsList = new ArrayList();
			for(int i=0; i<conds.length; i++){
				conditionsList.add(conds[i]);
			}
			data.put("conditionsList", conditionsList);
			
			create();
			
			/**
			 */
			Template t = cfg.getTemplate("vo.ftl"); 
			
			File file =new File("src/files/model/");    
			if  (!file .exists()  && !file .isDirectory())      
			{       
			    file.mkdir();    
			}
			FileOutputStream fos = new FileOutputStream(new File("src/files/model/"
					+ className1 + ".java")); // java vo
			t.process(data, new OutputStreamWriter(fos, "utf-8"));

			fos.flush();
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
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
