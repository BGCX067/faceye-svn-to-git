package com.faceye.core.util.helper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.util.helper.SystemConfig.java
 * @Description:系统级配置信息
 */
public class SystemConfig {
	private Resource resource;

	private static HashMap map = null;

	private static Properties props = null;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	private Enumeration getPropertiesKeys() {
		Enumeration en = null;
		try {
			if (props == null) {
				props = new Properties();
				props.load(this.getResource().getInputStream());
			}
			en = props.propertyNames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return en;
	}

	private String getValueByGivenKey(String key) {
		String result = null;
		result = props.getProperty(key);
		if (StringUtils.isEmpty(result)) {
			result = null;
		}
		return result;
	}

	private HashMap putProperties2Map() {
		map = new HashMap();
		Enumeration en = this.getPropertiesKeys();
		String key = null;
		while (en.hasMoreElements()) {
			key = (String) en.nextElement();
			map.put(key, this.getValueByGivenKey(key));
		}
		return map;
	}
	
	public String getValueByKey(String key){
		String value="";
		if(null==map){
			this.getPropertiesMap();
		}
		if(map.containsKey(key)){
			if(null!=map.get(key)){
				return map.get(key).toString();
			}
		}
		return value;
	}
	
	public HashMap getPropertiesMap(){
		if(null==map){
			map=this.putProperties2Map();
		}
		return map;
	}
}
