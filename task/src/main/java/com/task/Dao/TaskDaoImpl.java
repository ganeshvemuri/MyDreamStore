package com.task.Dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.model.Task;

@Transactional
@Repository
public class TaskDaoImpl implements TaskDao {
	
	@Autowired
	SessionFactory sessionfactory;

	public void saveTask(Task task) {
		sessionfactory.getCurrentSession().save(task);
	}

	public void updateTask(Task task) {
		Session session=sessionfactory.getCurrentSession();		
		session.update(task);
	}

	public void deleteTask(int task_Id) {
		Session session=sessionfactory.getCurrentSession();		
		Task task=(Task)session.get(Task.class,new Integer(task_Id));
		session.delete(task);
	}

	public List<Task> viewTask(String postedby) {
	
		System.out.println("in view task");
		Session session=sessionfactory.getCurrentSession();
		Criteria ct=session.createCriteria(Task.class);
		ct.add(Restrictions.eq("postedby",postedby));
		ct.add(Restrictions.eq("status",true));
		List<Task> list=ct.list();	
		return list;
		
	}
	
	public List<Task> viewMyTask(String postedby) {
		System.out.println("in view my task");
		Session session=sessionfactory.getCurrentSession();
		Criteria ct=session.createCriteria(Task.class);
		ct.add(Restrictions.eq("postedby",postedby));
		ct.add(Restrictions.eq("status",false));
		List<Task> list=ct.list();	
		return list;
	}

	
	
	
}
