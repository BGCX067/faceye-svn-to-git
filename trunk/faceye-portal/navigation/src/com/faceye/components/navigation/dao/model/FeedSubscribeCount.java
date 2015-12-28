package com.faceye.components.navigation.dao.model;

import java.util.Date;

import com.faceye.core.dao.hibernate.model.BaseObject;
/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.navigation.dao.model.FeedScbscribeCount.java
 * @Description:feed订阅记数器
 */
public class FeedSubscribeCount extends BaseObject {
   private Feed feed;
   private Integer totalCount;
   private Date createDate;
public Feed getFeed() {
	return feed;
}
public void setFeed(Feed feed) {
	this.feed = feed;
}
public Integer getTotalCount() {
	return totalCount;
}
public void setTotalCount(Integer totalCount) {
	this.totalCount = totalCount;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
}
