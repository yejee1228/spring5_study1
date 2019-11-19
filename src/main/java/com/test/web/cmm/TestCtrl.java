package com.test.web.cmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.web.proxy.CrawlingProxy;
import com.test.web.proxy.Inventory;

@RestController
@RequestMapping("/test")
public class TestCtrl {
	@Autowired CrawlingProxy pxy;
	
	@GetMapping("/naver")
	public ArrayList<HashMap<String,String>> naver() {
		return pxy.engCrawling();
	}
	@GetMapping("/cgv")
	public ArrayList<HashMap<String,String>> cgv() {
		return pxy.cgvCrawl();
	}
	@GetMapping("/bugs")
	public ArrayList<HashMap<String,String>> bugs() {
		return pxy.bugsCrawl();
	}
	
}
