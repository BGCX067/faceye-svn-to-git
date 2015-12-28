package com.faceye.core.util.helper;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.core.io.Resource;

public class QuerySqlConfig {
    private Resource resource;
	private Properties props = new Properties();
	private  HashMap propertiesSQL=new HashMap();
	
	
//	private String filePath=this.getPropertiesPath().getInputStream();

	private String readPropertiesFile(String key) {
		String result = null;
		try {
			props.load(this.getResource().getInputStream());
			result = props.getProperty(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

    private String getValueOfKeyFromProperties(String key){
    	String result = null;
		try {
			props.load(this.getResource().getInputStream());
			result = props.getProperty(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }
	private Enumeration getPropertiesKeys() {
		Enumeration en = null;
		try {
			props.load(this.getResource().getInputStream());
			en = props.propertyNames();
			// Enumeration r=props.propertyNames();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return en;
	}

	 public HashMap transPropertiesToMapWhitContainerOfMap(){
		 Enumeration en=this.getPropertiesKeys();
		 while(en.hasMoreElements()){
			 String key=(String) en.nextElement();
			 String value=this.getValueOfKeyFromProperties(key);
			 propertiesSQL.put(key, value);
		 }
		 return propertiesSQL;
	 }
	public static void main(String[] arg) {
		QuerySqlConfig editor = new QuerySqlConfig();
		HashMap map=editor.transPropertiesToMapWhitContainerOfMap();
		System.out.println(map.size());
//		String value = editor.readPropertiesFile("getUsers");
//		System.out.println(value);
//		List l=editor.transPropertiesToListWhitContainerOfMap();
//		Iterator it=l.iterator();
//		while(it.hasNext()){
//			HashMap item=(HashMap) it.next();
//			String key= (String) item.keySet().iterator().next();
//			String value=(String) item.get(key);
//			System.out.println(key+"="+value);
//		}

	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	
	
}
