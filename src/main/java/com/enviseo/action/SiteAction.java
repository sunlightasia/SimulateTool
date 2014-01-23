package com.enviseo.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class SiteAction extends ActionSupport {
	@Actions(@Action(value = "/nextSite", results = { @Result(name = "success", location = "/WEB-INF/pages/form.jsp") }))
	public String nextSite() {

		return SUCCESS;
	}
}
