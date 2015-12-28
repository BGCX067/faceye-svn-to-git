package com.faceye.core.service.security.service.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;

import com.faceye.core.service.controller.BaseHibernateService;
import com.faceye.core.service.security.cache.iface.ICommonCacheService;
import com.faceye.core.service.security.dao.iface.ITreeDao;
import com.faceye.core.service.security.model.Resource;
import com.faceye.core.service.security.model.Role;
import com.faceye.core.service.security.model.Tree;
import com.faceye.core.service.security.service.iface.IResourceService;
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
	private IResourceService resourceService = null;
	private static List transTreeSource = null;
	private ICommonCacheService commonCacheService = null;
	// tree在缓存中存储的Key.
	private final static String TREE_CACHE_KEY = "TREE";
	// 是否已经从缓存中加载
	private static boolean isLoadFromCache = false;

	private ITreeDao treeDao = null;

	public ITreeDao getTreeDao() {
		return treeDao;
	}

	public void setTreeDao(ITreeDao treeDao) {
		this.treeDao = treeDao;
	}

	public ICommonCacheService getCommonCacheService() {
		return commonCacheService;
	}

	public void setCommonCacheService(ICommonCacheService commonCacheService) {
		this.commonCacheService = commonCacheService;
	}

	public String treeJSON() {
		try {
			List trees = this.getTransedTrees();
			String result = this.treeJSON(trees);
			return result;
		} catch (Exception e) {
			log.error(">>>>faceye error in method:treeJSON() is" + e.toString());
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
		if (source == null || source.isEmpty()) {
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
		boolean result = false;
		if (tree.getChildrenTrees() != null
				&& !tree.getChildrenTrees().isEmpty()) {
			result = true;
		}
		return result;
	}

	/**
	 * 开发期间，树不加载入内存
	 * 
	 * @return
	 */
	private List getTrees() {
		this.initTreesInCache();
		List trees = this.getTreesFromCache();
		return trees;
	}

	private void initTreesInCache() {
		List trees;
		if (!this.isLoadFromCache) {
			trees = this.loadAllObjects(Tree.class);
			Element element = new Element(this.TREE_CACHE_KEY, trees);
			this.getCommonCacheService().putElementInCache(element);
			this.isLoadFromCache = true;
		}
	}

	public List getTransedTrees() {
		// return transTreeSource==null?
		// this.transTrees(this.getTrees()):transTreeSource;
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
			if (StringUtils.isNotEmpty(tree.getAction())) {
				result.put(StringPool.TREE_ACTION, tree.getAction());
			} else {
				result.put(StringPool.TREE_ACTION, null);
			}
			if (StringUtils.isNotEmpty(tree.getUrl())) {
				result.put(StringPool.TREE_URL, tree.getUrl());
			} else {
				result.put(StringPool.TREE_URL, null);
			}
			// result.put(StringPool.TREE_HREF_TARGET, "list-iframe");
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
		List result = new ArrayList();
		Iterator it = source.iterator();
		while (it.hasNext()) {
			Map item = (Map) it.next();
			if (item.get(StringPool.TREE_PARENTID) != null) {
				if (item.get(StringPool.TREE_PARENTID).toString()
						.equals(treeid)) {
					result.add(item);
				}
			}
		}
		return result;
	}

	public Map getDirectParentTree(List source, String treeid) {
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
		return this.getTree(this.getTransedTrees(), treeid);
	}

	public boolean isHaveParent(List source, Map transedTree) {
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
				if (null==parentTree||parentTree.isEmpty() ) {
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
		if (tree.containsKey(StringPool.TREE_CLS)
				&& null != tree.get(StringPool.TREE_CLS)) {
			json.append("\"");
			json.append(tree.get(StringPool.TREE_CLS).toString());
			json.append("\"");
		} else {
			json.append("\"file\"");
		}
		if (tree.containsKey(StringPool.TREE_ICON_Cls)
				&& null != tree.get(StringPool.TREE_ICON_Cls)) {
			json.append(",");
			json.append("\"iconCls\":");
			json.append("\"");
			json.append(tree.get(StringPool.TREE_ICON_Cls).toString());
			json.append("\"");
		}
		if (tree.containsKey(StringPool.TREE_ACTION)
				&& null != tree.get(StringPool.TREE_ACTION)) {
			json.append(",");
			json.append("\"link\":");
			json.append("\"");
			json.append(tree.get(StringPool.TREE_ACTION).toString());
			json.append("\"");
		} else {
			json.append(",");
			json.append("\"link\":");
			json.append("\"");
			json.append("#");
			json.append("\"");
		}
		if (tree.containsKey(StringPool.TREE_ORDER)
				&& null != tree.get(StringPool.TREE_ORDER)) {
			json.append(",");
			json.append("\"order\":");
			json.append("\"");
			json.append(tree.get(StringPool.TREE_ORDER).toString());
			json.append("\"");
		}
		json.append("}");
		return json.toString();
	}

	private String transTree2JSONWithCheckBox(List source, Map tree,
			Serializable roleId) {
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
		if (tree.containsKey(StringPool.TREE_ACTION)
				&& null != tree.get(StringPool.TREE_ACTION)) {
			json.append(",");
			json.append("\"link\":");
			json.append("\"");
			json.append(tree.get(StringPool.TREE_ACTION).toString());
			json.append("\"");
		} else {
			json.append(",");
			json.append("\"link\":");
			json.append("\"");
			json.append("#");
			json.append("\"");
		}

		json.append(",");
		json.append("\"checked\":");
		// json.append("true");
		json.append(this.isNodeChecked(tree.get(StringPool.TREE_ID).toString(),
				roleId));

		json.append("}");
		return json.toString();
	}

	private List getTreesByRole(Serializable roleId) {
		List result = null;
		Role role = (Role) this.loadObject(Role.class, roleId);
		Set trees = role.getTrees();
		if (null != trees && !trees.isEmpty()) {
			result = new ArrayList(trees);
		}
		return result;
	}

	private boolean isNodeChecked(Serializable nodeId, Serializable roleId) {
		boolean result = false;
		List trees = this.getTreesByRole(roleId);
		if (null != trees && !trees.isEmpty()) {
			Iterator it = trees.iterator();
			while (it.hasNext()) {
				Tree item = (Tree) it.next();
				if (item.getId().equals(nodeId)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public String treeJSONWithCheckBox(Serializable roleId) {
		try {
			List trees = this.getTransedTrees();
			String result = this.treeJSONWithCheckBox(trees, roleId);
			return result;
		} catch (Exception e) {
			log.error(">>>>faceye error in method:treeJSON() is" + e.toString());
			return null;
		}

	}

	public String treeJSONWithCheckBox(List source, String treeid,
			Serializable roleId) {
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
					json.append(this.transTree2JSONWithCheckBox(source, item,
							roleId));
					json.append(StringPool.CHARACTER_COMMA);
				}
				json.deleteCharAt(json.lastIndexOf(StringPool.CHARACTER_COMMA));
				json.append("]");
			}
		}
		return json.toString();
	}

	public String treeJSONWithCheckBox(List source, Serializable roleId) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		List roots = this.getRoots(source);
		Iterator it = roots.iterator();
		StringBuffer json = new StringBuffer();
		json.append("[");
		while (it.hasNext()) {
			Map item = (Map) it.next();
			json.append(this.transTree2JSONWithCheckBox(source, item, roleId));
			json.append(",");

		}
		json.deleteCharAt(json.lastIndexOf(","));
		json.append("]");
		return json.toString();
	}

	public void saveOrUpdateTree(Tree tree) {
		// 将树节点存储为一种系统资源
		if (StringUtils.isNotEmpty(tree.getUrl())) {
			Resource resourceUrl = new Resource();
			resourceUrl.setResourceStr(tree.getUrl());
			if (this.isNotUnique(resourceUrl, "resourceStr")) {
				resourceUrl = (Resource) this.getResourceService().getObject(
						Resource.class, "resourceStr", tree.getUrl());
			}
			resourceUrl.setName(tree.getName() + "_URL");
			resourceUrl.setResourceType(StringPool.SECURITY_RESOURCE_TYPE_URL);
			this.getResourceService().saveOrUpdateResource(resourceUrl);
		}
		// 将 action存储为一种资源
		if (StringUtils.isNotEmpty(tree.getAction())) {
			Resource resourceAction = new Resource();
			resourceAction.setResourceStr(tree.getAction());
			if (this.isNotUnique(resourceAction, "resourceStr")) {
				resourceAction = (Resource) this.getResourceService()
						.getObject(Resource.class, "resourceStr",
								tree.getAction());
			}
			resourceAction.setName(tree.getName() + "_Action");
			resourceAction
					.setResourceType(StringPool.SECURITY_RESOURCE_TYPE_URL);
			this.getResourceService().saveOrUpdateResource(resourceAction);
		}
		this.saveOrUpdateObject(tree);
		List trees = this.modifyCacheTrees(tree);
		Element element = new Element(this.TREE_CACHE_KEY, trees);
		this.getCommonCacheService().modifyElementInCache(element);
	}

	private List getTreesFromCache() {
		return (List) ((Element) this.getCommonCacheService()
				.getElementFromCache(this.TREE_CACHE_KEY)).getValue();
	}

	private Tree getTree(Tree tree) {
		Tree result = null;
		Iterator it = this.getTreesFromCache().iterator();
		while (it.hasNext()) {
			Tree item = (Tree) it.next();
			if (item.getId().equals(tree.getId())) {
				result = item;
				break;
			}
		}
		return result;
	}

	private List modifyCacheTrees(Tree tree) {
		List trees = this.getTreesFromCache();
		if (null == this.getTree(tree)) {
			trees.add(tree);
		} else {
			Tree treeInCache = this.getTree(tree);
			for (int i = 0; i < trees.size(); i++) {
				Tree item = (Tree) trees.get(i);
				if (item.getId().equals(treeInCache.getId())) {
					trees.set(i, tree);
					break;
				}
			}

		}
		return trees;
	}

	private void removeTreeFromCache(Tree tree) {
		Tree treeInCache = this.getTree(tree);
		List trees = this.getTreesFromCache();
		if (null != treeInCache) {
			trees.remove(treeInCache);
		}
		Element element = new Element(this.TREE_CACHE_KEY, trees);
		this.getCommonCacheService().modifyElementInCache(element);
	}

	public IResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public void removeTree(Serializable treeId) {
		Tree tree = (Tree) this.getObject(Tree.class, treeId);
		if (StringUtils.isNotEmpty(tree.getUrl())) {
			Resource resourceUrl = (Resource) this.getObject(Resource.class,
					"resourceStr", tree.getUrl());
			if (null != resourceUrl) {
				this.getResourceService().removeResource(resourceUrl.getId());
			}
		}
		if (StringUtils.isNotEmpty(tree.getAction())) {
			Resource resourceAction = (Resource) this.getObject(Resource.class,
					"resourceStr", tree.getAction());
			if (null != resourceAction) {
				this.getResourceService()
						.removeResource(resourceAction.getId());
			}
		}
		this.removeObject(tree);
		this.removeTreeFromCache(tree);
	}

	public void removeTrees(Serializable[] treeIds) {
		for (int i = 0; i < treeIds.length; i++) {
			this.removeTree(treeIds[i]);
		}

	}

}
