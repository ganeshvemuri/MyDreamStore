package com.task.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.model.Users;

@Transactional
@Repository
public class userDaoImpl implements userDao {
	
	
	@Autowired
	SessionFactory sessionfactory;

	public void saveUser(Users user) {
		user.setRole("ROLE_USER");
	sessionfactory.getCurrentSession().save(user);
	}

	public int validateUser(String username, String password) {

		int res=0;
		Session session=sessionfactory.getCurrentSession();
		Query result=session.createQuery("from Users u where u.username='"+username+"'");
		
		List<Users> user=result.list();
		System.out.println("users:"+user);
		
		if(user.size()==0)
		{
			res=0;
		}
		else
		{
			for(int i=0;i<user.size();i++)
			{
				System.out.println("inside loop");
				String name=user.get(i).getUsername();
				String pwd=user.get(i).getPassword();
				String dbrole=user.get(i).getRole();
				if(name.equals(username) && pwd.equals(password) && dbrole.equals("ROLE_USER"))
				{
					res=1;
					System.out.println("The Result is:" +res);
				}
						
			}
		}
		return res;
	}
	

	

}
