package com.example.spring.boot.practise.controllers;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;


public abstract class BaseController {
	
	
	private static final String KEY_LOCALE = "localeLanguage";
	private static final String KEY_LIST_OF_LANGS = "listOfLang";
	private static final String KEY_LIST_OF_ROWS_PER_PAGE = "listOfRowsPerPage";
	
	@ModelAttribute
	public void getListOfLanguages(Model model){
		model.addAttribute(KEY_LIST_OF_LANGS, Arrays.asList("es","en"));
	}
	
	@ModelAttribute
	public void getListRowsPerPage(Model model){
		model.addAttribute(KEY_LIST_OF_ROWS_PER_PAGE, Arrays.asList(5,10,20,50));
	}
	
	@ModelAttribute
	public void getLocaleSession(Model model, Locale locale) {
		model.addAttribute(KEY_LOCALE, locale.getLanguage());
	}
	
	public void addLayoutParamsToModel(Model model, String path, String template) {
		model.addAttribute("keyPathTemplate", path);
		model.addAttribute("keyTemplate", template);
	}

}
