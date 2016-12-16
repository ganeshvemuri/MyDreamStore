package com.task.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {
	
	@Id@GeneratedValue
	private int task_Id;
	private String title;
	private String task_Description;
	private String deadline;
	private String postedby;
	public String getPostedby() {
		return postedby;
	}
	public void setPostedby(String postedby) {
		this.postedby = postedby;
	}
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public int getTask_Id() {
		return task_Id;
	}
	public void setTask_Id(int task_Id) {
		this.task_Id = task_Id;
	}
	public String getTitle() {
		return title;
	}
	public void setTask(String title) {
		this.title = title;
	}
	public String getTask_Description() {
		return task_Description;
	}
	public void setTask_Description(String task_Description) {
		this.task_Description = task_Description;
	}

}
