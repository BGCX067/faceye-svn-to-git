package com.faceye.core.service.security.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.faceye.core.service.controller.BaseHibernateService;
import com.faceye.core.service.security.dao.iface.ITreeDao;
import com.faceye.core.service.security.model.Tree;
import com.faceye.core.service.security.service.iface.ITreeService;
import com.faceye.core.util.helper.StringPool;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.core.service.security.service.controller.TreeService.java
 * @Description:树形控制
 */
public class TreeService extends BaseHibernateService implements ITreeService {
	private static List treeSource = null;

	private static List transTreeSource = null;
	

	public String treeJSON() {
		// TODO Auto-generated method stub
		try{
		List trees = this.getTransedTrees();
		String result = this.treeJSON(trees);
		return result;
		}catch(Exception e){
			log.info(">>>>faceye error in method:treeJSON() is"+e.toString());
			return null;
		}
		
	}

	public String treeJSON(List source, String treeid) {

		StringBuffer json = new StringBuffer();
		if (StringUtils.isEmpty(treeid)) {
			return json.toString();
		} else {
			List directChildrenTrees = this.getDirectChildrenTrees(source,
					treeid);
			if (directChildrenTrees != null && !directChildrenTrees.isEmpty()) {
				json.append("[");
				Iterator it = directChildrenTrees.iterator();
				while (it.hasNext()) {
					Map item = (Map) it.next();
					json.append(this.transTree2JSON(source, item));
					json.append(StringPool.CHARACTER_COMMA);
				}
				json.deleteCharAt(json.lastIndexOf(StringPool.CHARACTER_COMMA));
				json.append("]");
			}
		}

		return json.toString();
	}

	public String treeJSON(List source) {
		// TODO Auto-generated method stub
		if(source==null||source.isEmpty()){
			return null;
		}
		List roots = this.getRoots(source);
		Iterator it = roots.iterator();
		StringBuffer json = new StringBuffer();
		json.append("[");
		while (it.hasNext()) {
			Map item = (Map) it.next();
			json.append(this.transTree2JSON(source, item));
			json.append(",");

		}
		json.deleteCharAt(json.lastIndexOf(","));
		json.append("]");
		return json.toString();
	}

	public boolean isHaveParent(List source, String treeid) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (source == null || source.size() < 1) {
			return result;
		}
		Map currentTree = this.getTree(source, treeid);
		if (currentTree.get(StringPool.TREE_PARENTID) == null) {
			result = false;
		} else {
			Map parentTree = this.getTree(source, currentTree.get(
					StringPool.TREE_PARENTID).toString());
			if (parentTree == null) {
				result = false;
			} else {
				if (source.contains(parentTree)) {
					result = true;
				} else {
					result = false;
				}
			}
		}

		return result;
	}

	public boolean isHaveParent(List source, Tree tree) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (!source.contains(tree)) {
			return result;
		} else {
			if (tree.getParentTree() != null) {
				result = true;
			}
		}
		return result;
	}

	public boolean isHaveChildren(List source, Tree tree) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (tree.getChildrenTrees() != null
				&& !tree.getChildrenTrees().isEmpty()) {
			result = true;
		}
		return result;
	}
/**
 * 开发期间，树不加载入内存
 * @return
 */
	private List getTrees() {
//		
//		if (treeSource == null || treeSource.size() < 1) {
//			treeSource = this.loadAllObjects(Tree.class);
//		}
		return this.loadAllObjects(Tree.class);
	}

	public List getTransedTrees() {
//		return transTreeSource==null? this.transTrees(this.getTrees()):transTreeSource;
		return this.transTrees(this.getTrees());
	}

	private Map transTree(Tree tree) {
		Map result = new HashMap();
		if (tree != null) {
			result.put(StringPool.TREE_ID, tree.getId());
			result.put(StringPool.TREE_NAME, tree.getName());
			if (tree.getParentTree() != null) {
				result.put(StringPool.TREE_PARENTID, tree.getParentTree()
						.getId());
			} else {
				result.put(StringPool.TREE_PARENTID, null);
			}
			if(StringUtils.isNotEmpty(tree.getAction())){
				result.put(StringPool.TREE_ACTION, tree.getAction());
			}else{
				result.put(StringPool.TREE_ACTION, null);
			}
			if(StringUtils.isNotEmpty(tree.getUrl())){
				result.put(StringPool.TREE_URL, tree.getUrl());
			}else{
				result.put(StringPool.TREE_URL, null);
			}
			//result.put(StringPool.TREE_HREF_TARGET, "list-iframe");
		}
		return result;
	}

	private List transTrees(List source) {
		List result = new ArrayList();
		Iterator it = source.iterator();
		while (it.hasNext()) {
			Tree item = (Tree) it.next();
			result.add(this.transTree(item));
		}
		return result;
	}

	public List getAllChildrenTrees(List source, String treeid, List result) {
		// TODO Auto-generated method stub
		List directChildrenTrees = this.getDirectChildrenTrees(source, treeid);
		Map currentTree = this.getTree(source, treeid);
		result.add(currentTree);
		Iterator it = directChildrenTrees.iterator();
		while (it.hasNext()) {
			Map item = (Map) it.next();
			if (this.isHaveParent(source, item)) {
				this.getAllChildrenTrees(source, item.get(StringPool.TREE_ID)
						.toString(), result);
			} else {
				result.add(item);
			}
		}
		return result;
	}

	public List getAllParentTrees(List source, String treeid, List result) {
		// TODO Auto-generated method stub
		Map tree = this.getTree(source, treeid);
		result.add(tree);
		if (tree.get(StringPool.TREE_PARENTID) != null) {
			if (this.getTree(source, tree.get(StringPool.TREE_PARENTID)
					.toString()) != null) {
				this.getAllParentTrees(source, tree.get(
						StringPool.TREE_PARENTID).toString(), result);
			}
		}
		return result;
	}

	public List getDirectChildrenTrees(List source, String treeid) {
		// TODO Auto-generated method stub
		List result = new ArrayList();
		Iterator it = source.iterator();
		while (it.hasNext()) {
			Map item = (Map) it.next();
			if (item.get(StringPool.TREE_PARENTID) != null) {
				if (item.get(StringPool.TREE_PARENTID).toString().equals(treeid)) {
					result.add(item);
				}
			}
		}
		return result;
	}

	public Map getDirectParentTree(List source, String treeid) {
		// TODO Auto-generated method stub
		Map reuslt = null;
		Map tree = this.getTree(source, treeid);
		if (tree.get(StringPool.TREE_PARENTID) != null) {
			reuslt = this.getTree(source, tree.get(StringPool.TREE_PARENTID)
					.toString());
		}

		return reuslt;
	}

	public List getRoots(List source) {
		// TODO Auto-generated method stub
		List result = new ArrayList();
		Iterator it = source.iterator();
		while (it.hasNext()) {
			Map item = (Map) it.next();
			if (!this.isHaveParent(source, item)) {
				result.add(item);
			}
		}
		return result;
	}

	public boolean isHaveChildren(List source, String treeid) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (source == null || source.isEmpty() || source.size() < 1
				|| StringUtils.isEmpty(treeid)) {
			return result;
		}
		Iterator it = source.iterator();
		while (it.hasNext()) {
			Map item = (Map) it.next();
			if (item.get(StringPool.TREE_PARENTID) != null) {
				if (item.get(StringPool.TREE_PARENTID).toString()
						.equals(treeid)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * ��ݽڵ�id���ؽڵ�
	 * 
	 * @param source
	 * @param treeid
	 * @return
	 */
	public Map getTree(List source, String treeid) {
		Map result = null;
		if (source.isEmpty() || source.size() < 1
				|| StringUtils.isEmpty(treeid)) {
			return null;
		}
		Iterator it = source.iterator();
		while (it.hasNext()) {
			Map item = (Map) it.next();
			if (item.get(StringPool.TREE_ID).toString().equals(treeid)) {
				result = item;
				break;
			}
		}
		return result;
	}
	
	public Map getTree(String treeid) {
		// TODO Auto-generated method stub
		return this.getTree(this.getTransedTrees(), treeid);
	}

	public boolean isHaveParent(List source, Map transedTree) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (source.isEmpty() || source.size() < 1 || source == null
				|| transedTree.isEmpty() || transedTree == null) {
			return result;
		} else {
			if (transedTree.get(StringPool.TREE_PARENTID) == null) {
				result = false;
			} else {
				Map parentTree = this.getTree(source, transedTree.get(
						StringPool.TREE_PARENTID).toString());
				if (parentTree.isEmpty() || parentTree == null) {
					result = false;
				} else {
					if (source.contains(parentTree)) {
						result = true;
					} else {
						result = false;
					}
				}
			}
		}
		return result;
	}

	private String transTree2JSON(List source, Map tree) {
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"text\":");
		json.append("\"");
		json.append(tree.get(StringPool.TREE_NAME).toString());
		json.append("\"");
		json.append(",");
		json.append("\"id\":");
		json.append("\"");
		json.append(tree.get(StringPool.TREE_ID).toString());
		json.append("\"");
		json.append(",");
		json.append("\"leaf\":");
		if (this
				.isHaveChildren(source, tree.get(StringPool.TREE_ID).toString())) {
			json.append("false");
		} else {
			json.append("true");
		}
		json.append(",");
		json.append("\"cls\":");
		json.append("\"file\"");
		if(tree.get(StringPool.TREE_ACTION)!=null){
			json.append(",");
			json.append("\"link\":");
			json.append("\"");
			json.append(tree.get(StringPool.TREE_ACTION).toString());
			json.append("\"");
			
			//link target
//			json.append(",");
//			json.append("\"hrefTarget\":");
//			json.append("\"");
//			json.append(tree.get(StringPool.TREE_HREF_TARGET).toString());
//			json.append("\"");
		}else{
			json.append(",");
			json.append("\"link\":");
			json.append("\"");
			json.append("#");
			json.append("\"");
		}
		
		json.append("}");
		return json.toString();
	}

 

	

}
