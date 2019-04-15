package com.example.spring.boot.practise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.boot.practise.util.Constants;

@Controller
@RequestMapping(path="/")
public class MainController extends BaseController{
		

	@GetMapping("/")
	public String initPage(Model model) {
		addLayoutParamsToModel(model, Constants.TEMPLATE_PATH_INDEX, Constants.TEMPLATE_INDEX);
		return Constants.TEMPLATE_LAYOUT;
	}
	
	
}
