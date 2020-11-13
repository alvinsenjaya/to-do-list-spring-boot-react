package com.backend.todolist.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue
	private long _id;
	
	private String title;
	private Date targetDate;
	private boolean isCompleted;
	
	protected Todo() {
		
	}
	
	public Todo(String title, Date targetDate) {
		super();
		this.title = title;
		this.targetDate = targetDate;
		this.isCompleted = false;
	}

	public long get_id() {
		return _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public String toString() {
		return "Todo [_id=" + _id + ", title=" + title + ", isCompleted=" + isCompleted + "]";
	}
	
	
}
