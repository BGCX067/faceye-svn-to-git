package com.faceye.core.service.security.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.faceye.core.service.security.cache.iface.ICommonCacheService;
import com.faceye.core.service.security.model.Tree;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.service.security.web.form.TreeForm;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;
import com.faceye.core.web.controller.ExtTemplateAction;

public class TreeAction extends ExtTemplateAction {
	private ICommonCacheService commonCacheService =null;
	private ITreeService treeService=null;
	public ITreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}

	protected void onInitEntity(HttpServletRequest request, Object o,
			ActionForm form) {
		// TODO Auto-generated method stub
		TreeForm tf = (TreeForm) form;
		if (StringUtils.isNotEmpty(tf.getParentId())) {
			Tree parent = (Tree) super.getEntity(Tree.class, tf.getParentId());
			((Tree) o).setParentTree(parent);
		}

	}

	@Override
	protected void onInitForm(HttpServletRequest request, ActionForm form,
			Object o) {
		// TODO Auto-generated method stub
		if (o != null) {
			TreeForm tf = (TreeForm) form;
			Tree parent = ((Tree) o).getParentTree();
			if (parent != null) {
				tf.setParentId(parent.getId());
				tf.setParentName(parent.getName());
			}

		}

	}

	/**
	 * 保存节点数据
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Map params = super.getRequestParameterMap(request);
			Tree o = null;
			if (params.get("id") != null) {
				if (StringUtils.isNotEmpty(params.get("id").toString())
						&& !StringUtils.equals(params.get("id").toString(),
								"source")) {
					o = (Tree) super.getEntity(Tree.class, params.get("id")
							.toString());
				} else {
					o = new Tree();
				}
			}
			o.setAction(params.get("action").toString());
			o.setName(params.get("name").toString());
			o.setUrl(params.get("url").toString());
			// 如果有父节点，则保存父节点
			if (params.get("parentId") != null) {
				if (StringUtils.isNotEmpty(params.get("parentId").toString())
						&& !StringUtils.equals("source", params.get("parentId")
								.toString())) {
						Tree parentTree = (Tree) super.getEntity(Tree.class,
								params.get("parentId").toString());
						o.setParentTree(parentTree);
					}else{
						o.setParentTree(null);
					}
				
			}
			this.getTreeService().saveOrUpdateTree(o);
			return null;
		} catch (Exception e) {
			log.info("faceye exception:" + e.toString() + " in method save()");
			return null;
		}
	}

	/**
	 * 生成树形结构,本树形结构用于后台维护主功能树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String json = "";
		// JSONArray ja=new JSONArray();
		if (super.getHttp().getParameter(request, "node").equals("source")) {
			json = this.getTreeService().treeJSON();
		} else {
			String currentNode = super.getHttp().getParameter(request, "node");
			json = this.getTreeService().treeJSON(
					this.getTreeService().getTransedTrees(),
					currentNode);
		}
		super.jsonPrint(response, json);
		// return mapping.findForward("system.admin.face");
		return null;
	}
	/**
	 * 构造带有checkbox的tree,为角色进行节点授权做准备
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward treeWithCheckBoxForPermission(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String json = "";
		// JSONArray ja=new JSONArray();
	    String roleId=this.getHttp().getParameter(request, "roleId");
		if (super.getHttp().getParameter(request, "node").equals("source")) {
			json = this.getTreeService().treeJSONWithCheckBox(roleId);
		} else {
			String currentNode = super.getHttp().getParameter(request, "node");
			json = this.getTreeService().treeJSONWithCheckBox(
					this.getTreeService().getTransedTrees(),
					currentNode,roleId);
		}
		super.jsonPrint(response, json);
		// return mapping.findForward("system.admin.face");
		return null;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String hql = "select t.id,t.name,t.url from " + Tree.class.getName()
				+ " t";
		PaginationSupport ps = super.getService().getBaseService()
				.getBaseHibernateService().getPageByHQL(hql,
						super.getHttp().getCurrentIndex(request));
		JSONArray json = JSONArray.fromObject(ps.transItems());
		String printJson = "{\"total\":" + ps.getTotalCount() + ",\"root\":"
				+ json.toString() + "}";
		this.jsonPrint(response, printJson);
		return null;
	}

	/**
	 * 数据修改
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map params = super.getRequestParameterMap(request);
		String entityClass = params.get(StringPool.ENTITY_CLASS).toString();
		try {
			Tree o = (Tree) super.getEntity(Class.forName(entityClass), params
					.get(StringPool.ENTITY_ID).toString());
			// JSONArray jsonArray=JSONArray.fromObject(o);
			response.setCharacterEncoding("UTF-8");
			String json = "[{\"id\":\"" + o.getId() + "\",\"name\":\""
					+ o.getName() + "\",\"action\":\"" + o.getAction()
					+ "\",\"url\":\"" + o.getUrl() + "\"";
			// +o.getParentTree().getId()+"\"}]"
			if (o.getParentTree() != null) {
				json += ",\"parentId\":\"";
				json += o.getParentTree().getId();
				json += "\",\"parentName\":\"";
				json += o.getParentTree().getName();
				json += "\"}]";
			} else {
				json += ",\"parentId\":\"";
				json += "source";
				json += "\",\"parentName\":\"";
				json += "Common Platform";
				json += "\"}]";
			}
			response.getWriter().print(
					"{\"success\":true,\"rows\":" + json + "}");
			System.out.println("{\"success\":true,\"rows\":" + json + "}");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward isLeaf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Tree o = (Tree) super.getEntity(Tree.class, request);
		String json = "";
		if (!o.getChildrenTrees().isEmpty()) {
			json = "[{\"isLeaf\":\"no\"}]";
		} else {
			json = "[{\"isLeaf\":\"yes\"}]";
		}
		try {
			response.getWriter().print("{\"rows\":" + json + "}");
			System.out.println("{\"rows\":" + json + "}\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = super.getRequestParameterMap(request);
		if (params.get(StringPool.ENTITY_ID) != null
				&& params.get(StringPool.ENTITY_CLASS) != null) {
			try {
				Tree o = (Tree) super.getEntity(Class.forName(params.get(
						StringPool.ENTITY_CLASS).toString()), params.get(
						StringPool.ENTITY_ID).toString());
				response.setCharacterEncoding("UTF-8");
				String json = "[{\"id\":\"" + o.getId() + "\",\"name\":\""
						+ o.getName() + "\",\"action\":\"" + o.getAction()
						+ "\",\"url\":\"" + o.getUrl() + "\"";
				// +o.getParentTree().getId()+"\"}]"
				if (o.getParentTree() != null) {
					json += ",\"parentId\":\"";
					json += o.getParentTree().getId();
					json += "\",\"parentName\":\"";
					json += o.getParentTree().getName();
					json += "\"}]";
				} else {
					json += ",\"parentId\":\"";
					json += "source";
					json += "\",\"parentName\":\"";
					json += "Common Platform";
					json += "\"}]";
				}
				response.getWriter().print(
						"{\"success\":true,\"rows\":" + json + "}");
				// response.getWriter().print(json);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Map params=this.getRequestParameterMap(request);
		this.getTreeService().removeTrees(params.get(StringPool.ENTITY_IDS).toString().split(StringPool.ENTITY_IDS_SPLIT_WITH));
		return null;
	}

	public ICommonCacheService getCommonCacheService() {
		return commonCacheService;
	}

	public void setCommonCacheService(ICommonCacheService commonCacheService) {
		this.commonCacheService = commonCacheService;
	}

}
