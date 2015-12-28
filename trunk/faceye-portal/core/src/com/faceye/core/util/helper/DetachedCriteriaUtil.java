package com.faceye.core.util.helper;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.ReflectionUtils;

public class DetachedCriteriaUtil {
	private DetachedCriteriaUtil() {
	}

	private static DetachedCriteriaUtil detachedCriteriaUtil = null;

	public static DetachedCriteriaUtil getDetachedCriteriaUtil() {
		if (detachedCriteriaUtil == null) {
			detachedCriteriaUtil = new DetachedCriteriaUtil();
		}
		return detachedCriteriaUtil;
	}

	public DetachedCriteria getDetachedCriteria(Class classz) {
		return classz == null ? null : DetachedCriteria.forClass(classz);
	}

	public DetachedCriteria getDetachedCriteria(Class classz, Map source) {
		DetachedCriteria detachedCriteria = null;
		if (source == null || source.isEmpty()) {
			detachedCriteria = getDetachedCriteria(classz);
		} else {
		    Iterator it=source.keySet().iterator();
		    while(it.hasNext()){
		    	String item=it.next().toString();
		    	if(StringUtils.isNotEmpty(item)){
		    		if(source.get(item)!=null){
		    		   String key=item.substring(StringPool.QUERY_PARAMS_IDENTIFER.length()-1);
		    		   Object value=source.get(item);
		    		   detachedCriteria.add(Restrictions.like(key, value));
		    		}
		    	}
		    }
//			detachedCriteria.add(Restrictions.);
		}
		return detachedCriteria;
	}
}
