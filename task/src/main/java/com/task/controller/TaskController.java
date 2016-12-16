package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.Dao.TaskDao;
import com.task.model.Task;

@RestController
@ResponseBody
public class TaskController {
	
	@Autowired
	TaskDao taskDao;
	
	@RequestMapping(value="/saveTask",headers="Accept=Application/json",method=RequestMethod.POST)
		public void saveTask(@RequestBody Task task)
			{
				taskDao.saveTask(task);
			}
	
	@RequestMapping(value="/updateTask",headers="Accept=Application/json",method=RequestMethod.PUT)
		public void updateTask(@RequestBody Task task)
		{
			taskDao.updateTask(task);
		}
	@RequestMapping(value="/deleteTask/{task_id}",headers="Accept=Application/json",method=RequestMethod.DELETE)
		public void deleteTask(@PathVariable int task_id)
		{
			taskDao.deleteTask(task_id);
		}
	@RequestMapping(value="/viewTask/{postedby}",headers="Accept=Application/json",method=RequestMethod.GET)
	public List<Task> viewTask(@PathVariable("postedby") String postedby)
	{
		System.out.println("postedby:"+postedby);
		List<Task> task=taskDao.viewTask(postedby);
		return task;
			
	}
	
	@RequestMapping(value="/viewMyTask/{postedby}",headers="Accept=Application/json",method=RequestMethod.GET)
	public List<Task> viewMyTask(@PathVariable("postedby") String postedby)
	{
		System.out.println("postedby:"+postedby);
		List<Task> task=taskDao.viewMyTask(postedby);
		return task;
			
	}

}
