package com.faceye.core.service.security.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.core.service.security.model.Column;
import com.faceye.core.service.security.service.iface.IColumnService;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.web.controller.ExtTemplateAction;

public class ColumnAction extends ExtTemplateAction {
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

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub

	}
	/**
	 * 后台栏目列表
	 */
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String hql="select c.id,c.name,c.url from "+Column.class.getName()+ " c";
		PaginationSupport ps=super.getService().getBaseService().getBaseHibernateService().getPageByHQL(hql,super.getHttp().getCurrentIndex(request));
//		JSONArray json=JSONArray.fromObject(ps.transItems());
//		String printJson="{\"total\":"+ps.getTotalCount()+",\"root\":"+json.toString()+"}";
		super.jsonPrint(response, ps.json());
		return null;
	}

	public IColumnService getColumnService() {
		return columnService;
	}

	public void setColumnService(IColumnService columnService) {
		this.columnService = columnService;
	}
	
   /**
    * 生成栏目树形结构
    */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		List transferColumns=this.getColumnService().transferColumns(null);
		String json="";
			if (super.getHttp().getParameter(request, "node").equals("source")) {
				json=this.getTreeService().treeJSON(transferColumns);
			} else {
				String currentNode = super.getHttp().getParameter(request,
						"node");
				json = this.getTreeService().treeJSON(
						transferColumns,
						currentNode);
			}
			super.jsonPrint(response, json);
		return null;
	}
	
	/**
	 * 生成用于单个节点维护的树开结构.
	 * 主要用于右键弹出菜单中,节点的明细显示,节点编辑,为节点添加子节点(默认为添加当前节点的子节点,也可以添加为其它节点的子节点
	 */
	public ActionForward node(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		StringBuffer json=new StringBuffer();
		json.append("[");
		//添加"栏目明细"节点
		json.append("{");
		json.append("\"text\":");
		json.append("\"Column Detail\"");
		json.append(",");
		json.append("\"id\":\"1\"");
		json.append(",");
		json.append("\"cls\":\"file\"");
		json.append(",");
		json.append("\"leaf\":true");
		json.append(",");
		json.append("\"link\":\"columnAciton.do\"");
		json.append("}");
		//添加"添加子栏目"节点
		json.append(",");
		json.append("{");
		json.append("\"text\":");
		json.append("\"Add Sub Column\"");
		json.append(",");
		json.append("\"id\":\"2\"");
		json.append(",");
		json.append("\"cls\":\"file\"");
		json.append(",");
		json.append("\"leaf\":true");
		json.append(",");
		json.append("\"link\":\"#\"");
		json.append("}");
		//添加"编辑栏目"节点
		json.append(",");
		json.append("{");
		json.append("\"text\":");
		json.append("\"Edit Column\"");
		json.append(",");
		json.append("\"id\":\"3\"");
		json.append(",");
		json.append("\"cls\":\"file\"");
		json.append(",");
		json.append("\"leaf\":true");
		json.append(",");
		json.append("\"link\":\"#\"");
		json.append("}");
		//添加"删除栏目"节点
		json.append(",");
		json.append("{");
		json.append("\"text\":");
		json.append("\"Remove Column\"");
		json.append(",");
		json.append("\"id\":\"4\"");
		json.append(",");
		json.append("\"cls\":\"file\"");
		json.append(",");
		json.append("\"leaf\":true");
		json.append(",");
		json.append("\"link\":\"columnAciton.do\"");
		json.append("}");
		json.append("]");
		this.jsonPrint(response, json.toString());
		return null;
	}
	/**
	 * 保存栏目数据
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Map params=super.getRequestParameterMap(request);
		Column o=null;
		o=(Column)this.initEntity(request, form);
		if(params.get("parentId")!=null){
			if(StringUtils.isNotEmpty(params.get("parentId").toString())&&!StringUtils.equals("source", params.get("parentId").toString())){
				Column parent=(Column)super.getEntity(Column.class, params.get("parentId").toString());
				o.setParentColumn(parent);
			}else{
				o.setParentColumn(null);
			}
		}
		this.getColumnService().saveOrUpdateObject(o);
		return null;
	}
	
	
	
/**
 * 判断当前节点是否是叶节点
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 */
	public ActionForward isLeaf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Column o = (Column) super.getEntity(Column.class, request);
		String json = "";
		if (!o.getChildrenColumns().isEmpty()) {
			json = "[{\"isLeaf\":\"no\"}]";
		} else {
			json = "[{\"isLeaf\":\"yes\"}]";
		}
		String printJson="{\"rows\":" + json + "}";
		this.jsonPrint(response, printJson);
		return null;
	}
	
	
	public ActionForward columns(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	    String columnId=this.getHttp().getParameter(request, "columnId");
	    List columns=this.getColumnService().getColumns(null);
	    List transferColumns=null;
	    String printJson="";
	    if(StringUtils.isNotEmpty(columnId)){
	    	transferColumns=this.getTreeService().getDirectChildrenTrees(this.getColumnService().transferColumns(columns), columnId);
	    }else{
	    	transferColumns=this.getTreeService().getRoots(this.getColumnService().transferColumns(columns));
	    }
	    String json=this.getColumnService().columns2json(transferColumns);
		 printJson="{\"rows\":"+json+"}";
	    this.jsonPrint(response, printJson);
		return null;
	}
	
	public ActionForward remove(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	    Column o=(Column)super.getEntity(request);
	    this.getColumnService().removeObject(o);
		return null;
	}
}
