package com.example.spring.boot.practise.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.boot.practise.dto.DataTableParamsDTO;
import com.example.spring.boot.practise.dto.DataTableResultDTO;
import com.example.spring.boot.practise.dto.UserDTO;
import com.example.spring.boot.practise.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("userDTO")
public class UserController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	//View's path
	private static final String TEMPLATE_NEW_USER = "user/newUser";
	private static final String TEMPLATE_SHOW_USER = "user/showUser";
	private static final String TEMPLATE_SHOW_ALL_USERS = "user/showAllUsers";
	
	//Keys model
	private static final String KEY_USERDTO = "userDTO";
	private static final String KEY_USER_ALIAS = "userAlias";
	private static final String KEY_USER_NOT_FOUND = "userNotFound";

	//Redirects
	private static final String REDIRECT_SHOW_USER = "redirect:showUser/";
	
	@Autowired
	private UserService userService;
	

	/**
	 * Shows template to create a new User
	 * @return ModelAndView with an empty UserDTO and a template to create it
	 */
	@GetMapping("/newUser")
	public ModelAndView newUser(Model model) {
		LOG.debug("Call to newUser()");
		
		ModelAndView mav = new ModelAndView(TEMPLATE_NEW_USER);
		mav.addObject(KEY_USERDTO, new UserDTO());
		
		LOG.debug("Finishing call to newUser()");
		return mav;
	}
	
	/**
	 * Create a new user
	 * @param UserDTO newUserDTO 
	 * @return template showUSer
	 */
	@PostMapping("/newUser")
	public String saveNewuser(@Valid @ModelAttribute UserDTO newUserDTO, BindingResult bindingResult, Model model, SessionStatus status) {
		LOG.debug("Call to newUser() with data: {}",newUserDTO);

		if(bindingResult.hasErrors()) {	
			LOG.debug("Finishing call to newUser()  without save because new user has invalid input data");
			return TEMPLATE_NEW_USER;
		}else {
			UserDTO userDTO=null;
			try{
				userDTO = userService.saveOrUpdate(newUserDTO);
				if(userDTO == null)
					throw new Exception(); //TODO: improve this code, no throw exception
			}catch(Exception e) {
				LOG.error(new StringBuilder("Exception in newUser when saving new user. Exception: ").append(e.getMessage()).toString());
				LOG.debug("Finishing call to newUser() without save");
				return TEMPLATE_NEW_USER;	
			}
			status.setComplete();
			LOG.debug("Finishing call to newUser() after save new user: {}", userDTO);
			return REDIRECT_SHOW_USER+userDTO.getUserAlias();
		}
	}
	
	/**
	 * Show a template with informations about user with userAlias indicated in url
	 * @param String userAlias
	 * @return ModelAndView with template and information about an user
	 * @throws Exception
	 */
	@GetMapping("/showUser/{userAlias}")
	public ModelAndView showUser(@PathVariable String userAlias) throws Exception {
		LOG.debug("Call to showUser() with param: {}", userAlias);
		
		ModelAndView mav = new ModelAndView(TEMPLATE_SHOW_USER);
		
		UserDTO userDTO = userService.getUserByUserAlias(userAlias);
		if(userDTO != null) {
			mav.addObject(KEY_USERDTO, userDTO);
			LOG.debug("user found: {}",userDTO);
		}else {
			mav.addObject(KEY_USER_NOT_FOUND, Boolean.TRUE);
			mav.addObject(KEY_USER_ALIAS, userAlias);
			LOG.debug("user: {} not found", userAlias);
		}
		
		LOG.debug("Finishing call to showUser()");
		return mav;
	}
	
	/**
	 * Show a template with a table of all users with their information
	 * @return a template with a table of all users an their information
	 */
	@GetMapping("/showAllUser")
	public String showAllUsers() {
		return TEMPLATE_SHOW_ALL_USERS;
	}
	
	/**
	 * Return a DataTableResultDTO with list of users
	 * @param params
	 * @return
	 */
	@PostMapping("/table/users")
	public @ResponseBody DataTableResultDTO<UserDTO> getListOfUsers(@ModelAttribute DataTableParamsDTO params){
		Page<UserDTO> pageUserDTO = userService.getUsersPaged(params.getPageRequest());
		return new  DataTableResultDTO<>(pageUserDTO, params.getsEcho(), null);
	}
	

	/**
	 * Delete a user by id
	 * @param id
	 * @return True if user with id given was deleted, false in other case
	 */
	@DeleteMapping(value="/delete/{id}")
	public @ResponseBody Boolean deleteUser(@PathVariable Long id) {
		LOG.debug("Call to deleteUser()");	
		try {
			 Boolean userDeleted = userService.deleteUser(id);
			 if(userDeleted)
				 return Boolean.TRUE;
			 else
				 return Boolean.FALSE;
		} catch (Exception e) {
			LOG.error(new StringBuilder("Error deleting user with id: ").append(id).toString());
			LOG.debug("Finishing call to deleteUser() ");
			return Boolean.FALSE;
		}
	}
	
	/**
	 * Edit User's information using newUser template
	 * @param userAlias
	 * @param model
	 * @return redirect to template showUser which shows user's information
	 */
	@GetMapping("/editUser/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		LOG.debug("Call to editUser()");
		
		try{
			UserDTO userDTO = userService.getUserById(id);
			if(userDTO== null)
				throw new Exception();
			else {
				model.addAttribute(KEY_USERDTO, userDTO);
			}
			
		}catch(Exception e) {
			LOG.error(new StringBuilder("Error modifiying user :").append(e.getMessage()).toString());
		}
		
		LOG.debug("Finishing call to editUser() ");
		return TEMPLATE_NEW_USER;
	}
	
	
}
