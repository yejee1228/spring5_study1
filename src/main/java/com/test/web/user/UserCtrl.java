package com.test.web.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.web.proxy.Box;
import com.test.web.proxy.Proxy;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/users")
public class UserCtrl {
	private static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);

	@Autowired UserMapper userMapper;
	@Autowired Box<Integer> box;
	@Autowired Proxy pxy;
	
	public int rowCount(){
		int rowCount = userMapper.rowCount();
		pxy.printer("rowCount"+rowCount);
		box.put("rowCount", rowCount);
		return box.get("rowCount");
	}
	

}