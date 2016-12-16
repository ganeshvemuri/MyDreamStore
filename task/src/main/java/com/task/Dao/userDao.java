package com.task.Dao;

import com.task.model.Users;

public interface userDao {
	
	 void saveUser(Users user);
	 int validateUser(String username,String password);
	

}
