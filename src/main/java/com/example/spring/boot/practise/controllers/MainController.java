package com.example.spring.boot.practise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class MainController extends BaseController{
		
	//Keys model
	private static final String TEMPLATE_INDEX = "index";

	@GetMapping("/")
	public String initPage() {
		return TEMPLATE_INDEX;
	}
	
	
}
