package com.enviseo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enviseo.model.Field;
import com.enviseo.persistence.FieldPersistence;

@Controller
public class FieldController {

	@Autowired
	private FieldPersistence fieldPersistence;

	@RequestMapping(value = "/createField", method = RequestMethod.POST)
	public String createForm(@ModelAttribute Field field, ModelMap model, HttpSession session) {
		// Register new field
		this.fieldPersistence.addField(field);
		return "redirect:showForm";
	}
}