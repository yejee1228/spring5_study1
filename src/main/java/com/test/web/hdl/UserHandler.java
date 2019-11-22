package com.test.web.hdl;

import org.apache.ibatis.annotations.Insert;

import com.test.web.user.User;

public interface UserHandler {
	@Insert(" INSERT INTO USER (CID,CPW,CNAME,BIRTH,GEN) VALUES (" + 
			"    #{uid}, #{pwd},#{uname},#{birth}, #{gender}")
	public void insertUser(User u);
}
