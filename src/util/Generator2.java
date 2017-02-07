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

public class Generator2 {

	public static void main(String[] args) {
		System.out.println("---------������ɿ�ʼ------------");
		Configuration cfg = new Configuration();
		try {
			cfg.setClassForTemplateLoading(Generator.class, "/ftl"); 
			cfg.setDefaultEncoding("UTF-8");  
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter()); 
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			Properties properties = new Properties();
			
			InputStream in = Object.class.getResourceAsStream("/generator.properties");
			in.close();
			in = Object.class.getResourceAsStream("/generator.properties");
			properties.load(in);

			String packageName = properties.getProperty("package_name").trim();
			String className1 = properties.getProperty("class_name1").trim();
			String className2 = properties.getProperty("class_name2").trim();
			String tableName = properties.getProperty("table_name").trim();
			String description = properties.getProperty("description").trim();
			String tablePrimaryKey = properties.getProperty("table_primarykey").trim();
			String canditions = properties.getProperty("canditions").trim();

			Map data = new HashMap();
			data.put("package", packageName); //
			data.put("className1", className1); //
			data.put("className2", className2); //
			data.put("tableName", tableName); //
			data.put("description", description); //
			data.put("primaryKey", tablePrimaryKey);//
			List pros = TableInfo.getTableInfo(tableName);
			data.put("properties", pros);
			
			
			String [] conds = canditions.split(",");
			List conditionsList = new ArrayList();
			for(int i=0; i<conds.length; i++){
				conditionsList.add(conds[i]);
			}
			data.put("conditionsList", conditionsList);
			
			create();
			Template t = cfg.getTemplate("vo.ftl"); // VOָ��ģ��
			
			File file =new File("src/files/model/");    
			if  (!file .exists()  && !file .isDirectory())      
			{       
			    file.mkdir();    
			}
			FileOutputStream fos = new FileOutputStream(new File("src/files/model/"
					+ className1 + ".java")); // java�ļ������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("vo:������ɳɹ�������");
			fos.flush();
			fos.close();
			System.out.println("controllerIndex:������ɿ�ʼ");
			t = cfg.getTemplate("ComponentControllerIndex.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "ControllerIndex.java")); // mapping�ļ������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("---------������ɳɹ�������------------");
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
	}

}
