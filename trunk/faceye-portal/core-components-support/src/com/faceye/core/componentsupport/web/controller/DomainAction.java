package com.faceye.core.componentsupport.web.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.core.componentsupport.dao.model.Domain;
import com.faceye.core.componentsupport.dao.model.DomainQuery;
import com.faceye.core.componentsupport.dao.model.Property;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;
/**
 * 
 * @author：宋海鹏
　* @Copy Right:早点网络
 * @System:早点网络支持系统
　* @Create Time:2007-9-28
 *　@Package And File Name:com.faceye.core.componentsupport.web.controller.DomainAction.java
 * @Description:域操作
 */
public class DomainAction extends BaseComponentSupportAction {

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}
	/**
	 * 基于JSON的通用数据列表生成方案
	 */
	public ActionForward generatorJSONList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
//			Map tree=this.getService().getTreeService().getTree(super.getHttp().getParameterMap(request).get("node").toString());
//			getHttp().setRequestAttribute(request, "url", tree.get(StringPool.TREE_URL).toString());
			//Map params=super.getHttp().getParameterMap(request);
			Map source=this.getHttp().getParameterMap(request);
			String domain_id=source.get(StringPool.TREE_DOMAIN).toString();
			Domain domain=(Domain)super.getEntity(Domain.class, domain_id);
			DomainQuery domainQuery=(DomainQuery)domain.getDomainQueries().iterator().next();
			String queryType=domainQuery.getQueryType().getTypeName();
			String hql="";
			String propertyName="";
			if(queryType.equals(StringPool.QUERY_TYPE_BY_HQL)){
				Set domainQueryProperty=domainQuery.getProperties();
				Iterator it=domainQueryProperty.iterator();
				String [] arr=new String[domainQueryProperty.size()];
				int i=0;
				hql="select ";
				while(it.hasNext()){
					Property item=(Property) it.next();
				    arr[i]=item.getPropertyName();
				    i++;
				    hql+=item.getPropertyName();
				    propertyName+=item.getName();
				    	if(it.hasNext()){
				    	hql+=",";
				    	//propertyName+=",";
				    }
				}
				hql+=" from "+domainQuery.getDomain().getDomainName();
				propertyName+="";
			}
			super.getHttp().setRequestAttribute(request, "tt", propertyName);
			System.out.println(propertyName);
			System.out.println("Current HQL is:"+hql);
			//String hql="select t.id,t.name from "+Domain.class.getName()+ " t";
			//String hql="select t.id,t.name from "+Tree.class.getName()+" t";
			System.out.println(super.getHttp().getCurrentIndex(request));
			response.setCharacterEncoding("UTF-8");
			PaginationSupport ps=super.getService().getBaseService().getBaseHibernateService().getPageByHQL(hql, super.getHttp().getCurrentIndex(request));
			JSONArray jsonArray=JSONArray.fromObject(ps.transItems());
			response.getWriter().print("{\"total\":"+ps.getTotalCount()+",\"root\":"+jsonArray.toString()+"}"); 
			System.out.println("{\"total\":"+ps.getTotalCount()+",\"root\":"+jsonArray.toString()+"}");
			return null;
		} catch (Exception e) {
			return super.throwErrors(mapping, form, request, response, e);
		}
	}

}
