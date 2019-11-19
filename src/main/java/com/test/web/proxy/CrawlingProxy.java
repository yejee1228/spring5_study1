package com.test.web.proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.apache.log4j.net.SyslogAppender;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;



@Component("crawler") @Lazy
public class CrawlingProxy extends Proxy{
	@Autowired Inventory<HashMap<String,String>> inventory;
	@Autowired Box<String> box;
	
	public ArrayList<HashMap<String,String>> engCrawling() {
		String url = "https://endic.naver.com/?sLn=kr";
		inventory.clear();
		try {
			Document rawData = Jsoup.connect(url).timeout(10 * 1000).get();
			Elements word = rawData.select("div[class=\"txt_origin\"] a");
			Elements text = rawData.select("div[class=\"txt_trans\"]");
			HashMap<String,String> map = null;
			for (int i = 0; i<word.size(); i++) {
				map = new HashMap<>();
				map.put("word", word.get(i).text());
				map.put("text", text.get(i).text());
				inventory.add(map);
			}
			System.out.println("inventory에 담긴: " + inventory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		inventory.get().forEach(System.out::println);
		return inventory.get();
	}
	
	
	public ArrayList<HashMap<String,String>> cgvCrawl(){
		inventory.clear();
		try {
			//final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String url = "http://www.cgv.co.kr/movies/" ;
			
			/*Connection.Response homePage = Jsoup.connect(url) 
					.method(Connection.Method.GET) 
					.userAgent(USER_AGENT) 
					.execute();*/
			//Document temp = homePage.parse();
			Document temp = Jsoup.connect(url).timeout(10 * 1000).get();
			Elements element = temp.select("div.sect-movie-chart");  
			Elements title = element.select("strong.title");
			Elements percent = element.select("strong.percent");
			Elements txtinfo= element.select("span.txt-info");
			Elements img = temp.select("span.thumb-image");
			HashMap<String, String> map = null;
			for(int i=0;i<title.size();i++) {
				map=new HashMap<>();
				map.put("seq", String.valueOf(i+1));
				map.put("title", title.get(i).text());
				map.put("percent", percent.get(i).text());
				map.put("txtinfo", txtinfo.get(i).text());
				map.put("img", img.get(i).select("img").attr("src"));
				inventory.add(map);
			}
			inventory.get().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inventory.get();
	}	
	public ArrayList<HashMap<String,String>> bugsCrawl(){
		inventory.clear();
		try {
			String url = "https://music.bugs.co.kr/chart" ;
			
			Document temp = Jsoup.connect(url).timeout(10 * 1000).get();
			Elements ranking = temp.select("div.ranking");
			Elements img = temp.select("a.thumbnail");
			Elements artist = temp.select("p.artist");
			Elements title= temp.select("p.title");
			Elements album = temp.select("a.album");
			HashMap<String, String> map = null;
			for(int i=0;i<title.size();i++) {
				map=new HashMap<>();
				map.put("ranking", ranking.get(i).select("strong").text());
				map.put("img", img.get(i).select("img").attr("src"));
				map.put("artist", artist.get(i).text());
				map.put("title", title.get(i).text());
				map.put("album", album.get(i+1).text());
				inventory.add(map);
			}
			inventory.get().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inventory.get();
	}	
}
