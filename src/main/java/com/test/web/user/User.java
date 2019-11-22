package com.test.web.user;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Lazy
@Data
@Component 
@AllArgsConstructor
@NoArgsConstructor
public class User{
	private String cid,pwd,cname,birth,gender,rowCount;
}