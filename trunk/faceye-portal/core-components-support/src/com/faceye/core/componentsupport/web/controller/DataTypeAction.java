package com.faceye.core.componentsupport.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.faceye.core.componentsupport.dao.model.DataType;
import com.faceye.core.util.exception.ServiceException;
import com.faceye.core.util.helper.CollectionUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

public class DataTypeAction extends BaseComponentSupportAction {

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
	
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Serializable objectId = super.getHttp().getEntityId(request);
			String entityClass = super.getHttp().getParameter(request,
					StringPool.ENTITY_CLASS);
			ActionMessages messages = super.getMessages(request);
			Object o = null;
			if (objectId == null || StringUtils.isEmpty(entityClass)) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"system.exception.entity.on.detial"));
				if (!messages.isEmpty()) {
					super.saveMessages(request, messages);
				}
				return super.globalMessage(mapping, form, request, response,
						messages);
			} else {
				Class classz = Class.forName(entityClass).newInstance()
						.getClass();
				o = super.getEntity(classz, objectId);
				JSONArray jsonArray=JSONArray.fromObject(o);
				try {
					response.getWriter().print("{\"success\":true,\"rows\":"+jsonArray.toString()+"}");
					System.out.println("JSON IS:"+"{\"success\":true,\"rows\":"+jsonArray.toString()+"}");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				super.getHttp().setRequestAttribute(request, super.DETAIL, o);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.throwErrors(mapping, form, request, response, e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.throwErrors(mapping, form, request, response, e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.throwErrors(mapping, form, request, response, e);
		}
		return null;
	}
	
	
	public ActionForward generatorJSONList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		try {
//			Map tree=this.getService().getTreeService().getTree(super.getHttp().getParameterMap(request).get("node").toString());
//			getHttp().setRequestAttribute(request, "url", tree.get(StringPool.TREE_URL).toString());
			Map params=super.getRequestParameterMap(request);
			String hql="select t.id,t.name,t.typeOfClass from "+DataType.class.getName()+ " t";
			//String hql="select t.id,t.name from "+Tree.class.getName()+" t";
			PaginationSupport ps=super.getService().getBaseService().getBaseHibernateService().getPageByHQL(hql, super.getHttp().getCurrentIndex(request));
			JSONArray jsonArray=JSONArray.fromObject(ps.transItems());
			response.getWriter().print("{\"total\":"+ps.getTotalCount()+",\"root\":"+jsonArray.toString()+"}");
			System.out.println("{\"total\":"+ps.getTotalCount()+",\"root\":"+jsonArray.toString()+"}");
			return null;
		} catch (Exception e) {
			return super.throwErrors(mapping, form, request, response, e);
		}
	}
	public ActionForward save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		Map requestParameters=super.getRequestParameterMap(request);
		DataType dataType;
		if(requestParameters.get("id")==null){
		 dataType=new DataType();
		}else{
		 dataType=(DataType) this.getDomainService().loadObject(DataType.class, requestParameters.get("id").toString());
		}
		dataType.setName(requestParameters.get("name").toString());
		dataType.setTypeName(requestParameters.get("typeName").toString());
		dataType.setTypeNumInSql(Integer.parseInt(requestParameters.get("typeNumInSql").toString()));
		dataType.setTypeOfClass(requestParameters.get("typeOfClass").toString());
		super.getDomainService().saveOrUpdateObject(dataType);
		return null;
	}
	public ActionForward remove(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		Map params=super.getRequestParameterMap(request);
		try {
			this.getDomainService().removeMultiObjects(Class.forName(params.get("entityClass").toString()), params.get("ids").toString().split("_"));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
