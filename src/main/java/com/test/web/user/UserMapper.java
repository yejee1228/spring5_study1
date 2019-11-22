package com.test.web.user;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	public void insertUser(User user);
	public User selectUserByIdPw(User user);
	public int existId(String uid);
	public int rowCount();
	public List<User> selectAll();
}