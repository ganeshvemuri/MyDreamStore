package com.task.Dao;

import java.util.List;

import com.task.model.Task;

public interface TaskDao {
	
	
	void saveTask(Task task);
	List<Task> viewTask(String postedby);//true
	void updateTask(Task task);
	void deleteTask(int task_id);
	
	List<Task> viewMyTask(String postedby);//false	

}
