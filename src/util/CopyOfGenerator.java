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
		System.out.println("---------�������ɿ�ʼ------------");
		Configuration cfg = new Configuration();
		try {
			cfg.setClassForTemplateLoading(CopyOfGenerator.class, "/ftl"); // ָ��ģ�����ڵ�classpathĿ¼
			cfg.clearTemplateCache(); 
			cfg.setTemplateUpdateDelay(0);
		    cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(0, 0));
			cfg.setDefaultEncoding("UTF-8");  
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter()); // ���һ��"��"�����������������������ĸ��д
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			// ��ȡ�����ļ�
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
			data.put("className1", className1); // ������д
			data.put("className2", className2); // ����Сд
			data.put("tableName", tableName); // ����
			data.put("description", description); // ����
			data.put("primaryKey", tablePrimaryKey);//����
			List pros = TableInfo.getTableInfo(tableName);
			data.put("properties", pros);
			
			//��ѯ��������
			String [] conds = canditions.split(",");
			List conditionsList = new ArrayList();
			for(int i=0; i<conds.length; i++){
				conditionsList.add(conds[i]);
			}
			data.put("conditionsList", conditionsList);
			
			create();
			
			/**
			 * vo:��������
			 */
			System.out.println("vo:�������ɿ�ʼ");
			Template t = cfg.getTemplate("vo.ftl"); // VOָ��ģ��
			
			File file =new File("src/files/model/");    
			if  (!file .exists()  && !file .isDirectory())      
			{       
			    file.mkdir();    
			}
			FileOutputStream fos = new FileOutputStream(new File("src/files/model/"
					+ className1 + ".java")); // java�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("vo:�������ɳɹ�������");
			fos.flush();
			fos.close();

			/**
			 * mapping:��������
			 */
			System.out.println("mapping:�������ɿ�ʼ");
			t = cfg.getTemplate("mapping.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/model/"
					+ className1 + ".xml")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("mapping:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * conf:��������
			 */
			System.out.println("conf:�������ɿ�ʼ");
			t = cfg.getTemplate("conf.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/conf/component-"
					+ className2 + ".xml")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("conf:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * compnentAdd:��������
			 */
			System.out.println("compnentAdd:�������ɿ�ʼ");
			t = cfg.getTemplate("ComponentAdd.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "Add.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("compnentAdd:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * compnentAddSubmit:��������
			 */
			System.out.println("compnentAddSumit:�������ɿ�ʼ");
			t = cfg.getTemplate("ComponentAddSubmit.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "AddSubmit.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("compnentAddSubmit:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * controllerIndex:��������
			 */
			System.out.println("controllerIndex:�������ɿ�ʼ");
			t = cfg.getTemplate("ComponentControllerIndex.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "ControllerIndex.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerIndex:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * controllerList:��������
			 */
			System.out.println("controllerList:�������ɿ�ʼ");
			cfg.clearTemplateCache();
			t=null;
			t = cfg.getTemplate("ComponentControllerList.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "ControllerList.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerList:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * controllerDelete:��������
			 */
			System.out.println("controllerDelete:�������ɿ�ʼ");
			cfg.clearTemplateCache();
			t=null;
			t = cfg.getTemplate("ComponentDelete.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "Delete.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerDelete:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * controllerModify:��������
			 */
			System.out.println("controllerModify:�������ɿ�ʼ");
			t=null;
			t = cfg.getTemplate("ComponentModify.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "Modify.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerModify:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * controllerModifySubmit:��������
			 */
			System.out.println("controllerModifySubmit:�������ɿ�ʼ");
			t = cfg.getTemplate("ComponentModifySubmit.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/controller/"
					+ className1 + "ModifySubmit.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("controllerModifySubmit:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * dao:��������
			 */
			System.out.println("dao:�������ɿ�ʼ");
			t = cfg.getTemplate("dao.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/dao/"
					+ className1 + "Dao.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("dao:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * daoimpl:��������
			 */
			System.out.println("daoimpl:�������ɿ�ʼ");
			t = cfg.getTemplate("daoimpl.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/dao/impl/"
					+ className1 + "DaoImpl.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("daoimpl:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * service:��������
			 */
			System.out.println("service:�������ɿ�ʼ");
			t = cfg.getTemplate("service.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/service/"
					+ className1 + "Service.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("service:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * serviceimpl:��������
			 */
			System.out.println("serviceimple:�������ɿ�ʼ");
			t = cfg.getTemplate("serviceimpl.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/service/impl/"
					+ className1 + "ServiceImpl.java")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("serviceimpl:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * view-add:��������
			 */
			System.out.println("view-add:�������ɿ�ʼ");
			t = cfg.getTemplate("view-ComponentAdd.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/view/"
					+ className1 + "Add.ftl")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("view-add:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * view-list:��������
			 */
			System.out.println("view-list:�������ɿ�ʼ");
			t = cfg.getTemplate("view-ComponentList.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/view/"
					+ className1 + "List.ftl")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("view-list:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
			/**
			 * view-list:��������
			 */
			System.out.println("view-mpdify:�������ɿ�ʼ");
			t = cfg.getTemplate("view-ComponentModify.ftl"); // mappingָ��ģ��
			fos = new FileOutputStream(new File("src/files/view/"
					+ className1 + "Modify.ftl")); // mapping�ļ�������Ŀ¼
			t.process(data, new OutputStreamWriter(fos, "utf-8"));
			System.out.println("view-modify:�������ɳɹ�������");
			fos.flush();
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("---------�������ɳɹ�������------------");
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
