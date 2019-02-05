package com.example.spring.boot.practise.controllers;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController extends BaseController implements ErrorController {
	
	//Keys model
	private static final String TEMPLATE_ERROR_404 = "error/404";

	@Override
	public String getErrorPath() {
		return "redirect:error";
	}
	
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Locale locale, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		
		if(status != null) {
			Integer statusCodeRequest = Integer.valueOf(status.toString());
			
			//Error 404
			if(statusCodeRequest.equals(HttpStatus.NOT_FOUND.value())) {
				return TEMPLATE_ERROR_404;
			}//Error 500
			else if(statusCodeRequest.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
				return TEMPLATE_ERROR_404;
			}
		}
		
		return TEMPLATE_ERROR_404;
	}

}
