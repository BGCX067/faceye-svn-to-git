package com.faceye.components.opensource.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.components.opensource.dao.model.OpenSource;
import com.faceye.components.opensource.service.iface.IOpenSourceService;
import com.faceye.core.service.security.model.Column;
import com.faceye.core.service.security.service.iface.IColumnService;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.CollectionUtil;
import com.faceye.core.web.controller.ExtTemplateAction;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.opensource.web.controller.OpenSourceAction.java
 * @Description:开源栏目控制器
 */
public class OpenSourceAction extends ExtTemplateAction {
	private IOpenSourceService openSourceService = null;
	private IColumnService columnService=null;
	private ITreeService treeService=null;
	public ITreeService getTreeService() {
	return treeService;
}

public void setTreeService(ITreeService treeService) {
	this.treeService = treeService;
}

	@Override
	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub

	}

	public IOpenSourceService getOpenSourceService() {
		return openSourceService;
	}

	public void setOpenSourceService(IOpenSourceService openSourceService) {
		this.openSourceService = openSourceService;
	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = super.getRequestParameterMap(request);
		String json = "";
		if (params.containsKey("node")) {
			if (StringUtils.isNotEmpty(params.get("node").toString())) {
				Column column=(Column)super.getEntity(Column.class, params.get("node").toString());
				//判断当前栏目是否有子栏目，如果有子栏目，则查询所有子栏目的数据
				List columns=this.getColumnService().transferColumns(null);
				List children=null;
				if(this.getTreeService().isHaveChildren(columns, column.getId())){
					children=this.getTreeService().getAllChildrenTrees(columns, column.getId(), new ArrayList());
					if(children!=null&&!children.isEmpty()){
						String columnids=CollectionUtil.getCollectionUtil().listElemets2String(children,"id");
						json=this.getOpenSourceService().getOpenSource(columnids, super.getHttp().getCurrentIndex(request));
					}
				}else{
				
				json = this.getOpenSourceService().getOpenSource(
						params.get("node").toString(),
						this.getHttp().getCurrentIndex(request));
				}
			} else {
				json = this.getOpenSourceService().getOpenSource(
						this.getHttp().getCurrentIndex(request));

			}
		} else {
			json = this.getOpenSourceService().getOpenSource(
					this.getHttp().getCurrentIndex(request));

		}
		this.jsonPrint(response, json);
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OpenSource o = (OpenSource) super.initEntity(request, form);
		Map params = super.getRequestParameterMap(request);
		if (params.get("columnId") != null) {
			Column column = (Column) super.getEntity(Column.class.getName(),
					params.get("columnId").toString());
			o.setColumn(column);
		}
		this.getService().getBaseService().getBaseHibernateService()
				.saveOrUpdateObject(o);

		return null;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		OpenSource o=(OpenSource)super.getEntity(request);
		String record="[{\"id\":\""+o.getId()+"\",\"name\":\""+o.getName()+"\",\"url\":\""+o.getUrl()+"\",\"content\":\""+o.getContent()+"\",\"columnId\":\""+o.getColumn().getId()+"\",\"columnName\":\""+o.getColumn().getName()+"\"}]";
		String json="{\"success\":true,\"rows\":"+record+"}";
		this.jsonPrint(response, json);
		return null;
	}

	public IColumnService getColumnService() {
		return columnService;
	}

	public void setColumnService(IColumnService columnService) {
		this.columnService = columnService;
	}

}
