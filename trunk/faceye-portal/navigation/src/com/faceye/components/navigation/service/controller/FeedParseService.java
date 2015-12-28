package com.faceye.components.navigation.service.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import com.faceye.components.navigation.service.iface.IFeedParseService;
import com.faceye.components.navigation.util.FeedUtil;
import com.faceye.core.util.helper.PaginationSupport;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

public class FeedParseService implements IFeedParseService {
	
	/* (non-Javadoc)
	 * @see com.faceye.components.navigation.service.controller.IFeedParseService#getFeeds(java.lang.String, int, int)
	 */
 public PaginationSupport getFeeds(String url,int start,int pageSize){
	 
	 return null;
 }
	
 /* (non-Javadoc)
 * @see com.faceye.components.navigation.service.controller.IFeedParseService#getAllFeeds(java.lang.String)
 */
public String getAllFeeds(String url){
	 return FeedUtil.getInstance().parseFeed(url);
 }
	
  public static void main(String args[]){
	  try {
//		URL feedUrl=new URL(" http://www.people.com.cn/rss/politics.xml");
//		  URL feedUrl=new URL("http://rss.news.sohu.com/rss/yule.xml");
//		  URL feedUrl=new URL("http://ecsun.blog.sohu.com/rss");
		  URL feedUrl=new URL("http://ecsun.javaeye.com/rss");
//		  URL feedUrl=new URL("http://blog.sina.com.cn/myblog/index_rss.php?uid=1279884602");
//		  URL feedUrl=new URL("http://shaminnong.blog.sohu.com/rss")
		  
		SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));
        SyndFeedOutput output = new SyndFeedOutput();
//        Writer writer=new FileWriter("G:\feed.xml");
        Writer writer= new OutputStreamWriter(new FileOutputStream("G:\\feed-ecsun-javaeye.xml"), "UTF-8");
        output.output(feed, writer);
//        PrintWriter writer=new PrintWriter();
//        output.output(feed, new PrintWriter());
//        System.out.println(feed);
//        System.out.println(feed.getEntries().size());
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
  }

public SyndFeed getSyndFeed(String url) {
	// TODO Auto-generated method stub
	URL feedUrl;
	SyndFeedInput input;
	SyndFeed feed=null;
	try {
		feedUrl = new URL(url);
		input= new SyndFeedInput();
		feed= input.build(new XmlReader(feedUrl));
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
	
	return feed;
}
}
