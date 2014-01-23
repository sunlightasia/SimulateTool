package com.enviseo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enviseo.model.Site;
import com.enviseo.persistence.SitePersistence;

@Controller
public class SiteController {

	@Autowired
	private SitePersistence sitePersistence;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/nextSite", method = RequestMethod.GET)
	public String nextSite(ModelMap model, HttpSession session) {
		List<Site> sites = (List<Site>) session.getAttribute("listSite");
		Integer index = (Integer) session.getAttribute("indexSite");
		if (index == null) {
			index = 0;
		} else {
			if (index < sites.size() - 1) {
				index++;
			}
		}
		// Set to session
		session.setAttribute("indexSite", index);
		return "redirect:showForm";
	}

	@RequestMapping(value = "/prevSite", method = RequestMethod.GET)
	public String prevSite(ModelMap model, HttpSession session) {
		Integer index = (Integer) session.getAttribute("indexSite");
		if (index == null) {
			index = 0;
		} else {
			if (index > 0) {
				index--;
			}
		}
		// Set to session
		session.setAttribute("indexSite", index);
		return "redirect:showForm";
	}
}