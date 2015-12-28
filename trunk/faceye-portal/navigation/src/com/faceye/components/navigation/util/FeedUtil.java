package com.faceye.components.navigation.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.faceye.core.util.helper.JSONUtil;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedUtil {
   public static  FeedUtil getInstance(){
	   return FeedUtilHolder.instance;
   }
   static class FeedUtilHolder{
	   static FeedUtil instance=new FeedUtil();
   }
   public String parseFeed(String url){
	   String result="";
	   try {
		URL feedUrl=new URL(url);
		SyndFeedInput feedInput=new SyndFeedInput();
		SyndFeed feed=feedInput.build(new XmlReader(feedUrl));
		List entities=feed.getEntries();
		/**
		 * http://www.dly.com/rss
		 * 
		 */
		Iterator it=entities.iterator();
		StringBuffer json=new StringBuffer("[");
		while(it.hasNext()){
			SyndEntry  entity=(SyndEntry) it.next();
			json.append(this.feedEntity2Json(entity));
			json.append(",");
		}
		json.deleteCharAt(json.lastIndexOf(","));
		json.append("]");
		result=JSONUtil.pageJson(entities.size(), json.toString());
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FeedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   return result;
   }
   public List parseFeed(String url,int totalCount){
	   return null;
   }
   
   
   private String feedEntity2Json(SyndEntry syndEntity){
	   StringBuffer json=new StringBuffer("{");
	   json.append("\"author\":");
	   json.append("\"");
	   json.append(syndEntity.getAuthor());
	   json.append("\",");
//	   json.append("\"description\":");
//	   json.append("\"");
//	   json.append(syndEntity.getDescription());
//	   json.append("\",");
	   json.append("\"link\":");
	   json.append("\"");
	   json.append(syndEntity.getLink());
	   json.append("\",");
	   json.append("\"publishdate\":");
	   json.append("\"");
	   json.append(syndEntity.getPublishedDate().toString());
	   json.append("\",");
	   json.append("\"title\":");
	   json.append("\"");
	   json.append(syndEntity.getTitle()).toString();
	   json.append("\"");
	   json.append("}");
	   return json.toString();
   }
}
