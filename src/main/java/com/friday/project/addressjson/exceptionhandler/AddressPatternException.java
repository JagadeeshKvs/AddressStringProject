package com.friday.project.addressjson.exceptionhandler;

import java.util.Date;

public class AddressPatternException {

	private Date exceptionTime;
	private String description;
	private String errorMessage;

	public AddressPatternException(Date date, String message, String description) {

		this.errorMessage = message;
		this.exceptionTime = date;
		this.description = description;

	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
