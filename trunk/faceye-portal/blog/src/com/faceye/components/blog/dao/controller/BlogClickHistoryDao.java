package com.faceye.components.blog.dao.controller;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.faceye.components.blog.dao.iface.IBlogClickHistoryDao;
import com.faceye.components.blog.dao.model.BlogClickHistory;
import com.faceye.core.componentsupport.dao.controller.DomainDao;
import com.faceye.core.dao.jdbc.iface.IBaseJdbcDao;
import com.faceye.core.util.helper.DateUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;

/**
 * 
 * @author：宋海鹏
 * @Connection:E_mail:ecsun@sohu.com/myecsun@hotmail.com QQ:82676683
 * @Copy Right:www.faceye.com
 * @System:www.faceye.com网络支持系统
 * @Create Time:2007-9-22
 * @Package com.faceye.components.blog.dao.controller.BlogClickHistoryDao.java
 * @Description:博客点击记录DAO
 */
public class BlogClickHistoryDao extends DomainDao implements
		IBlogClickHistoryDao {
	private IBaseJdbcDao baseJdbcDao = null;

	public PaginationSupport getPageBlogClickHistory(
			Serializable portalContainerId, String startTime, String endTime,
			int startIndex, int pageSize) {
		String hql = "from " + BlogClickHistory.class.getName()
				+ " b where b.portalContainer.id=?";
		PaginationSupport ps = this.getPageByHQL(hql, portalContainerId,
				pageSize, startIndex);
		return ps;
	}

	public void save(BlogClickHistory entity) {
		super.saveOrUpdateObject(entity);
	}

	/**
	 * 点击量包括访问博客首页的量，访问单篇文章的量，其中，访问者包括FaceYe网友（既未注册用户) 同时，包括已注册用户.
	 */
	public PaginationSupport getBlogClickOrderList(String timePeriod,
			int pageSize, int startIndex) {
		if (StringUtils.isEmpty(timePeriod)) {
			timePeriod = StringPool.BLOG_PERIOD_LIST_ORDER_ALL;
		}
		Date[] date = this.getDate(timePeriod);
		String sql = "";
		if (null != date) {
			String start = DateUtil.parseDate(date[0], null);
			String end = DateUtil.parseDate(date[1], null);
			sql = "select * from (select  sum(xx.blog_click_count) as 'blog_click_count',xx.portal_id as 'portal_id', xx.user_id as 'user_id', xx.username as 'username' from ("
					+ "select  sum(x.article_click_count) as 'blog_click_count' ,x.portal_id as 'portal_id',x.user_id as 'user_id',x.username as 'username'"
					+ " from "
					+ "(select p.id as 'portal_id',"
					+ "uba.name as 'article_name',"
					+ "count(ubac.article_id) as 'article_click_count',"
					+ "ubac.article_id   as 'article_id' ,u.id as 'user_id',u.name as 'username'"
					+ " from "
					+ "user_blog_article_click_count ubac,"
					+ "user_blog_article uba,"
					+ "user_blog_article_category ubc,"
					+ "sys_portal_container p,"
					+ "sys_user u"
					+ " where "
					+ "ubc.portalContainer_id=p.id "
					+ "and "
					+ "uba.id=ubac.article_id"
					+ " and "
					+ "uba.articleCategory_id=ubc.id "
					+ "and "
					+ "p.user_id=u.id"
					+ " and ubac.createTime between '"
					+ start
					+ "' and '"
					+ end
					+ "' group by ubac.article_id "
					+ " order by count(ubac.article_id) desc) x "
					+ " group by x.portal_id "
					+

					"union "
					+

					"select  count(ubch.id) as 'blog_click_count',p.id as 'portal_id',u.id as 'user_id',u.name as 'username' from "
					+ "user_blog_click_history ubch,"
					+ "sys_portal_container p,"
					+ "sys_user u"
					+ " where ubch.portalContainer_id =p.id and p.user_id=u.id "
					+ " and ubch.createTime between '"
					+ start
					+ "' and '"
					+ end + "' group by portal_id " +

					") xx group by xx.portal_id order by blog_click_count desc) xxx";

		} else {
			sql = "select * from (select  sum(xx.blog_click_count) as 'blog_click_count',xx.portal_id as 'portal_id', xx.user_id as 'user_id', xx.username as 'username' from ("
					+ "select  sum(x.article_click_count) as 'blog_click_count' ,x.portal_id as 'portal_id',x.user_id as 'user_id',x.username as 'username'"
					+ " from "
					+ "(select p.id as 'portal_id',"
					+ "uba.name as 'article_name',"
					+ "count(ubac.article_id) as 'article_click_count',"
					+ "ubac.article_id   as 'article_id' ,u.id as 'user_id',u.name as 'username'"
					+ " from "
					+ "user_blog_article_click_count ubac,"
					+ "user_blog_article uba,"
					+ "user_blog_article_category ubc,"
					+ "sys_portal_container p,"
					+ "sys_user u"
					+ " where "
					+ "ubc.portalContainer_id=p.id "
					+ "and "
					+ "uba.id=ubac.article_id"
					+ " and "
					+ "uba.articleCategory_id=ubc.id "
					+ "and "
					+ "p.user_id=u.id"
					+ " group by ubac.article_id "
					+ " order by count(ubac.article_id) desc) x "
					+ " group by x.portal_id "
					+

					"union "
					+

					"select  count(ubch.id) as 'blog_click_count',p.id as 'portal_id',u.id as 'user_id',u.name as 'username' from "
					+ "user_blog_click_history ubch,"
					+ "sys_portal_container p,"
					+ "sys_user u"
					+ " where ubch.portalContainer_id =p.id and p.user_id=u.id group by portal_id"
					+

					") xx group by xx.portal_id order by blog_click_count desc) xxx";
		}
		return this.getBaseJdbcDao().getPage(sql, pageSize, startIndex);
	}

	private Date[] getDate(String timePeriod) {
		Date date = new Date();
		Date[] result = new Date[2];
		// 取得一周的起止日期
		if (timePeriod.equals(StringPool.BLOG_PERIOD_LIST_ORDER_WEEK)) {
			result[0] = DateUtil.getFirstWeekDay(date);
			result[1] = DateUtil.getLastWeekDay(date);
			// 取得一月的起止日期
		} else if (timePeriod.equals(StringPool.BLOG_PERIOD_LIST_ORDER_MONTH)) {
			result[0] = DateUtil.getFirstMonthDay(date);
			result[1] = DateUtil.getLastMonthDay(date);
			// 取得一季度的起止日期
		} else if (timePeriod.equals(StringPool.BLOG_PERIOD_LIST_ORDER_QUARTER)) {
			result[0] = DateUtil.getFirstTenDaysDay(date);
			result[1] = DateUtil.getLastTenDaysDay(date);
		} else if (timePeriod.equals(StringPool.BLOG_PERIOD_LIST_ORER_YEAR)) {
			result[0] = DateUtil.getFirstDayOfYear(date);
			result[1] = DateUtil.getLastDayOfYear(date);
		} else {
			result = null;
		}

		return result;
	}

	public IBaseJdbcDao getBaseJdbcDao() {
		return baseJdbcDao;
	}

	public void setBaseJdbcDao(IBaseJdbcDao baseJdbcDao) {
		this.baseJdbcDao = baseJdbcDao;
	}

}
