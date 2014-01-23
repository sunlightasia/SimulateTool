package com.enviseo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enviseo.model.Form;
import com.enviseo.model.Site;
import com.enviseo.persistence.FormPersistence;
import com.enviseo.persistence.SitePersistence;
import com.enviseo.util.CommonUtils;

@Controller
public class FormController {

	@Autowired
	private SitePersistence sitePersistence;
	@Autowired
	private FormPersistence formPersistence;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showForm", method = RequestMethod.GET)
	public String showForm(ModelMap model, HttpSession session) {
		List<Site> sites = (List<Site>) session.getAttribute("listSite");
		Integer index = 0;
		// If site not have in session
		if (CommonUtils.isEmpty(sites)) {
			// Get in data base
			sites = sitePersistence.listSite();
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
					site.setName(siteArr[i].replace("http://", "").replace(".com", "").replace("www.", ""));
					site.setUrl(siteArr[i]);
					this.sitePersistence.addSite(site);

					sites.add(site);
				}
			}

			// Set list to session
			session.setAttribute("listSite", sites);
		} else {
			// Get index
			index = (Integer) session.getAttribute("indexSite");
		}

		// Set to model for view.
		model.addAttribute("siteUrl", sites.get(index).getUrl());
		model.addAttribute("totalSite", sites.size());
		model.addAttribute("indexSite", index);

		// Set to model for fields form.
		// Set list form of this site.
		List<Form> forms = this.formPersistence.findBySiteId(sites.get(index).getId());
		if (!CommonUtils.isEmpty(forms)) {
			model.addAttribute("formList", forms);
		}
		return "form";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createForm", method = RequestMethod.POST)
	public String createForm(@ModelAttribute Form form, ModelMap model, HttpSession session) {
		List<Site> sites = (List<Site>) session.getAttribute("listSite");
		Integer index = (Integer) session.getAttribute("indexSite");
		form.setSiteId(sites.get(index).getId());

		// Register new form
		this.formPersistence.addForm(form);
		return "redirect:showForm";
	}
}