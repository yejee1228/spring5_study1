package com.test.web.cmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.web.proxy.Box;
import com.test.web.proxy.CrawlingProxy;
import com.test.web.proxy.Inventory;
import com.test.web.proxy.PageProxy;

@RestController
@RequestMapping("/test")
public class TestCtrl {
	@Autowired CrawlingProxy pxy;
	@Autowired PageProxy pager;
	@Autowired Box<Object> box;
	
	@GetMapping("/naver")
	public ArrayList<HashMap<String,String>> naver() {
		return pxy.engCrawling();
	}
	@GetMapping("/cgv")
	public ArrayList<HashMap<String,String>> cgv() {
		return pxy.cgvCrawl();
	}
	@GetMapping("/bugs/page/{page}")
	public Map<?,?> bugs(@PathVariable String page) {
		ArrayList<HashMap<String,String>> list = pxy.bugsCrawl();
		pager.setRowCount(list.size());
		pager.setPageSize(10);
		pager.setBlockSize(5);
		pager.setCurrPage(Integer.parseInt(page));
		pager.paging();
		ArrayList<HashMap<String,String>> temp = new ArrayList<>();
		for(int i= 0; i<list.size(); i++) {
			if(i>=pager.getStartRow() && i<=pager.getEndRow()) {
				temp.add(list.get(i));
			}else if(i>pager.getEndRow()) {break;}
		box.put("pager", pager);
		box.put("list", temp);
			
		}
		return box.get();
	}
	
}
