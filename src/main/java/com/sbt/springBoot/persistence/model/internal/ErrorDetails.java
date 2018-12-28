package com.sbt.springBoot.persistence.model.internal;

import java.io.Serializable;
import java.util.Date;

public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = -65601234441554207L;

	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}