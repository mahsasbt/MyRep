package com.sbt.springBoot.persistence.model.internal.response.user;

public class UserRes {
	private String userName;
	private String taskTitle;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
}