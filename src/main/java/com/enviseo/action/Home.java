package com.enviseo.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class Home extends ActionSupport {
	@Autowired
	String message = "Welcome to your 1st Maven Spring project !";

	@Actions(@Action(value = "/hello", results = { @Result(name = "success", location = "/WEB-INF/pages/hello.jsp") }))
	public String showMessage() {
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}