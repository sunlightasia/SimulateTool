package com.enviseo.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enviseo.model.Site;
import com.enviseo.service.SiteService;
import com.enviseo.util.CommonUtils;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.opensymphony.xwork2.ActionSupport;

@Component
public class FormAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6961328082804919625L;
	private Map<String, Object> session = null;

	@Autowired
	private String siteUrl;
	@Autowired
	private String siteName;
	@Autowired
	private SiteService siteService;

	@Actions(@Action(value = "/registerAccount", results = { @Result(name = "success", location = "/WEB-INF/pages/accountInfo.jsp") }))
	public String registerAccoutn() throws IOException {
		final WebClient webClient = new WebClient();

		// Get the first page
		final HtmlPage page1 = webClient.getPage("http://www.soup.io/");

		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		List<HtmlForm> forms = page1.getForms();
		for (HtmlForm f : forms) {
			if (f.getActionAttribute().equals("https://www.soup.io/signup")) {
				final HtmlTextInput userNameField = f.getInputByName("user[login]");
				final HtmlPasswordInput passwordField = f.getInputByName("user[password]");
				final HtmlTextInput emailField = f.getInputByName("user[email]");
				// Change the value of the text field
				userNameField.setValueAttribute(UUID.randomUUID().toString().replaceAll("-", ""));
				passwordField.setValueAttribute("123456");
				emailField.setValueAttribute(UUID.randomUUID().toString().replaceAll("-", "") + "@gmail.com");

				// Save to request
				// req.setAttribute("userName",
				// userNameField.getValueAttribute());
				// req.setAttribute("password",
				// passwordField.getValueAttribute());
				// req.setAttribute("email", emailField.getValueAttribute());

				final HtmlSubmitInput button = f.getInputByName("commit");

				// Now submit the form by clicking the button and get back the
				// second
				// page.
				final HtmlPage page2 = button.click();

				// req.setAttribute("page", page2);
			}
		}

		// req.getRequestDispatcher("responseHtmlUnit.jsp").forward(req, resp);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Actions(@Action(value = "/showForm", results = { @Result(name = "success", location = "/WEB-INF/pages/form.jsp") }))
	public String showForm() {
		List<Site> sites = (List<Site>) this.getSession().get("listSite");

		// If site not have in session
		if (CommonUtils.isEmpty(sites)) {
			// Get in data base
			sites = this.siteService.listSite();
			// If database have nothing, initial a new list
			if (CommonUtils.isEmpty(sites)) {
				// Setup dummy data.
				sites = new ArrayList<Site>();
				String[] siteArr = new String[] { "http://www.basekit.com/login", "http://a.jimdo.com/app/auth/signin/", "https://www.yola.com/",
						"http://a.jimdo.com/app/auth/signin/", "http://a.jimdo.com/app/auth/signin/", "https://www.snappages.com/login",
						"http://my.dudamobile.com/login", "http://www.moonfruit.com/", "http://webstarts.com/", "https://login.live.com/login.srf",
						"http://www.wetpaint.com/", "http://www.piczo.com/" };
				for (int i = 0; i < siteArr.length; i++) {
					Site site = new Site();
					site.setId(String.valueOf(i + 1));
					site.setName(siteArr[i].replace("http://", "").replace(".com", ""));
					site.setUrl(siteArr[i]);
				}
			}

			// Set list to session
			this.getSession().put("listSite", sites);
		}

		// Set to view
		this.setSiteUrl(sites.get(0).getUrl());
		this.setSiteName(sites.get(0).getName());
		return SUCCESS;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}
}