package com.example.spring.boot.practise.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path="/")
public class MainController {
		
	
	@GetMapping("/")
	public ModelAndView initPage() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("name", "Felipe");
		return mav;
	}
	
	
}
