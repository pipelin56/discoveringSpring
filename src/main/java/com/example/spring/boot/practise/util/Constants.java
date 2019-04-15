package com.example.spring.boot.practise.util;

public class Constants {
	
	private Constants() {super();}
	
	/*KEYS MODEL*/
	public static final String KEY_TEMPLATE = "keyTemplate";
	public static final String KEY_TEMPLATE_PATH = "keyPathTemplate";
	public static final String KEY_USERDTO = "userDTO";
	public static final String KEY_USER_ALIAS = "userAlias";
	public static final String KEY_USER_NOT_FOUND = "userNotFound";
	
	
	/*TEMPLATES AND TEMPLATES'S PATHS*/
	public static final String TEMPLATE_INDEX = "index";
	public static final String TEMPLATE_LAYOUT = "layout";
	public static final String TEMPLATE_PATH_INDEX = "index";
	public static final String TEMPLATE_NEW_USER = "newUser";
	public static final String TEMPLATE_PATH_NEW_USER = "user/newUser";
	public static final String TEMPLATE_SHOW_USER = "showUser";
	public static final String TEMPLATE_PATH_SHOW_USER = "user/showUser";
	public static final String TEMPLATE_ALL_USER = "showAllUsers";
	public static final String TEMPLATE_PATH_ALL_USER = "user/showAllUsers";
	
	//Redirects
	public static final String REDIRECT_SHOW_USER = "redirect:showUser/";
}
