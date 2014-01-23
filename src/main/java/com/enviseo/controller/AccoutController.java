package com.enviseo.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enviseo.model.Account;
import com.enviseo.model.Field;
import com.enviseo.model.Form;
import com.enviseo.model.Site;
import com.enviseo.persistence.AccountPersistence;
import com.enviseo.persistence.FieldPersistence;
import com.enviseo.persistence.FormPersistence;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Controller
public class AccoutController {

	@Autowired
	private FormPersistence formPersistence;
	@Autowired
	private FieldPersistence fieldPersistence;
	@Autowired
	private AccountPersistence accountPersistence;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/registerAccount", method = RequestMethod.GET)
	public String createForm(ModelMap model, HttpSession session) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		// Get current site id
		List<Site> sites = (List<Site>) session.getAttribute("listSite");
		Integer index = (Integer) session.getAttribute("indexSite");
		Site site = sites.get(index);

		// Find form of this site.
		Form form = this.formPersistence.findOneByNameBySiteId("registerYola", site.getId());

		// Find fiels of form
		List<Field> fieldList = this.fieldPersistence.findByFormId(form.getId());

		final WebClient webClient = new WebClient();

		// Get the first page
		final HtmlPage homepage = webClient.getPage("https://www.yola.com/fr/signup");

		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		List<HtmlForm> forms = homepage.getForms();
		for (HtmlForm f : forms) {
			if (f.getActionAttribute().equals(form.getUrl())) {
				for (Field field : fieldList) {
					if (field.getType().equals("text"))
						f.getInputByName(field.getName()).setValueAttribute("buonca9999");
					if (field.getType().equals("password"))
						f.getInputByName(field.getName()).setValueAttribute("abc123!");
					if (field.getType().equals("email"))
						f.getInputByName(field.getName()).setValueAttribute("jodelsimulate96213@gmail.com");
				}
				// final HtmlTextInput userNameField =
				// f.getInputByName("user[login]");
				// final HtmlPasswordInput passwordField =
				// f.getInputByName("user[password]");
				// final HtmlTextInput emailField =
				// f.getInputByName("user[email]");
				// // Change the value of the text field
				// userNameField.setValueAttribute(UUID.randomUUID().toString().replaceAll("-",
				// ""));
				// passwordField.setValueAttribute("123456");
				// emailField.setValueAttribute(UUID.randomUUID().toString().replaceAll("-",
				// "") + "@gmail.com");

				// create button submit
				HtmlButton submitButton = (HtmlButton) homepage.createElement("button");
				submitButton.setAttribute("type", "submit");

				// append to form
				f.appendChild(submitButton);

				// Click button
				HtmlPage newPage = submitButton.click();

				// Create new account
				Account acc = new Account();
				acc.setEmail("jodelsimulate96213@gmail.com");
				acc.setUsername("buonca9999");
				acc.setPwd("site");
				this.accountPersistence.addAccount(acc);
			}
		}

		return "redirect:showForm";
	}
}