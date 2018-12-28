package com.sbt.springBoot.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER_DETAILS")
public class User implements Serializable {

	private static final long serialVersionUID = -4563726201646653006L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_Sequence")
	@SequenceGenerator(name = "user_Sequence", sequenceName = "USER_SEQ")
	@Column(name = "USER_ID")
	private long userId;

	@NotEmpty
	@Column(name = "USER_NAME", unique = true)
	private String userName;

	@OneToMany(targetEntity = Task.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Task> taskList = new ArrayList<>();

	public User() {
	}

	public User(long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> task) {
		this.taskList = task;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", taskList=" + taskList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskList == null) ? 0 : taskList.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (taskList == null) {
			if (other.taskList != null)
				return false;
		} else if (!taskList.equals(other.taskList))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}