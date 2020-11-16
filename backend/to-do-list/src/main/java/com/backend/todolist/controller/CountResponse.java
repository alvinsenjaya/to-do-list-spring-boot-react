package com.backend.todolist.controller;

public class CountResponse {
	private long count;
	
	protected CountResponse() {
		
	}

	public CountResponse(long count) {
		super();
		this.count = count;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}	
}
