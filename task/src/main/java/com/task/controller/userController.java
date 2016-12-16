package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.Dao.userDao;
import com.task.model.Users;

@RestController
public class userController {

	@Autowired
	userDao userdao;
	
	
	/*REGISTRATION*/
		@RequestMapping(value="/saveUser",headers="Accept=Application/json",method=RequestMethod.POST)

		public void saveUser(@RequestBody Users user)
		{
			userdao.saveUser(user);
		}

		
	/*AUTHENTICATION*/
		@RequestMapping(value="/authenticate", method=RequestMethod.POST,headers="Accept=application/json")
		public int authenticateUser(@RequestBody Users user)
		{
			System.out.println("In Authenticate");
			System.out.println("username:"+user.getUsername());
			System.out.println("password:"+user.getPassword());
			int result=0;
			result=userdao.validateUser(user.getUsername(), user.getPassword());
			System.out.println("result is:"+result);
			
			return result;
		}
		
}
