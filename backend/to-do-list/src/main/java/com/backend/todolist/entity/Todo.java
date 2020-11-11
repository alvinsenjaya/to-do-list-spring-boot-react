package com.backend.todolist.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue
	private long _id;
	
	private String title;
	private boolean isCompleted;
	
	protected Todo() {
		
	}
	
	public Todo(String title) {
		super();
		this.title = title;
		this.isCompleted = false;
	}

	public long get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
