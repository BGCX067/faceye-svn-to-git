package com.faceye.components.blog.dao.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.model.Article.java
 * @Description:用于用户发表文章
 */
public class Article extends BaseObject {
	//文章内容
	private String content;
	private Date createTime=new Date();
	//文章所属分类
	private ArticleCategory articleCategory;
	//评论数量
	private Integer discusCount=new Integer(0);
	//访问数量
	private Integer clickCount=new Integer(0);
	public Integer getClickCount() {
		return clickCount;
	}
	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}
	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Map map(){
		Map map=new HashMap();
		map.put("id", this.getId());
		map.put("name", this.getName());
		map.put("content", this.getContent());
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		map.put("createTime", dateFormat.format(this.getCreateTime()));
		
		if(null!=this.getArticleCategory()){
			map.put("categoryId", this.getArticleCategory().getId());
			map.put("categoryName", this.getArticleCategory().getName());
		}else{
			map.put("categoryId", null);
			map.put("categoryName", null);
		}
		
		map.put("discusCount", this.getDiscusCount());
		map.put("clickCount", this.getClickCount());
		return map;
	}
//	public String json(){
//		StringBuffer sb=new StringBuffer("{");
//		sb.append("\"id\":");
//		sb.append("\"");
//		sb.append(this.getId());
//		sb.append("\"");
//		sb.append(",");
//		sb.append("\"name\":");
//		sb.append("\"");
//		sb.append(this.getName());
//		sb.append("\"");
//		sb.append(",");
//		sb.append("\"content\":");
//		sb.append("\"");
//		sb.append(this.getContent());
//		sb.append("\"");
//		sb.append(",");
//		sb.append("\"createTime\":");
//		sb.append("\"");
//		sb.append(this.getCreateTime());
//		sb.append("\"");
//		sb.append(",");
//		sb.append("\"categoryId\":");
//		sb.append("\"");
//		sb.append(this.getArticleCategory().getId());
//		sb.append("\"");
//		sb.append(",");
//		sb.append("\"categoryName\":");
//		sb.append("\"");
//		sb.append(this.getArticleCategory().getName());
//		sb.append("\"");
//		sb.append("}");
//		return sb.toString();
//	}
	public Integer getDiscusCount() {
		return discusCount;
	}
	public void setDiscusCount(Integer discusCount) {
		this.discusCount = discusCount;
	}
}
