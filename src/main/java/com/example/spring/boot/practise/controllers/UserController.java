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

import com.example.spring.boot.practise.dto.DataTableParamsDTO;
import com.example.spring.boot.practise.dto.DataTableResultDTO;
import com.example.spring.boot.practise.dto.UserDTO;
import com.example.spring.boot.practise.service.UserService;
import com.example.spring.boot.practise.util.Constants;

@Controller
@RequestMapping("/user")
@SessionAttributes("userDTO")
public class UserController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * Shows template to create a new User
	 * @return ModelAndView with an empty UserDTO and a template to create it
	 */
	@GetMapping("/new")
	public String newUser(Model model) {
		LOG.debug("Call to newUser()");
		addLayoutParamsToModel(model, Constants.TEMPLATE_PATH_NEW_USER, Constants.TEMPLATE_NEW_USER);
		model.addAttribute(Constants.KEY_USERDTO, new UserDTO());
		
		LOG.debug("Finishing call to newUser()");
		return Constants.TEMPLATE_LAYOUT;
	}
	
	/**
	 * Create a new user
	 * @param UserDTO newUserDTO 
	 * @return template showUSer
	 */
	@PostMapping("/new")
	public String saveNewuser(@Valid @ModelAttribute UserDTO newUserDTO, BindingResult bindingResult, Model model, SessionStatus status) {
		LOG.debug("Call to newUser() with data: {}",newUserDTO);

		if(bindingResult.hasErrors()) {	
			LOG.debug("Finishing call to newUser()  without save because new user has invalid input data");
			addLayoutParamsToModel(model, Constants.TEMPLATE_PATH_NEW_USER, Constants.TEMPLATE_NEW_USER);
			return Constants.TEMPLATE_LAYOUT;
		}else {
			UserDTO userDTO=null;
			try{
				userDTO = userService.saveOrUpdate(newUserDTO);
				if(userDTO == null)
					throw new Exception(); //TODO: improve this code, no throw exception
			}catch(Exception e) {
				LOG.error(new StringBuilder("Exception in newUser when saving new user. Exception: ").append(e.getMessage()).toString());
				LOG.debug("Finishing call to newUser() without save");
				addLayoutParamsToModel(model, Constants.TEMPLATE_PATH_NEW_USER, Constants.TEMPLATE_NEW_USER);
				return Constants.TEMPLATE_LAYOUT;
			}
			status.setComplete();
			LOG.debug("Finishing call to newUser() after save new user: {}", userDTO);
			return Constants.REDIRECT_SHOW_USER+userDTO.getUserAlias();
		}
	}
	
	/**
	 * Show a template with informations about user with userAlias indicated in url
	 * @param String userAlias
	 * @return ModelAndView with template and information about an user
	 * @throws Exception
	 */
	@GetMapping("/show/{userAlias}")
	public String showUser(@PathVariable String userAlias, Model model) throws Exception {
		LOG.debug("Call to showUser() with param: {}", userAlias);
		
		
		UserDTO userDTO = userService.getUserByUserAlias(userAlias);
		if(userDTO != null) {
			model.addAttribute(Constants.KEY_USERDTO, userDTO);
			LOG.debug("user found: {}",userDTO);
		}else {
			model.addAttribute(Constants.KEY_USER_NOT_FOUND, Boolean.TRUE);
			model.addAttribute(Constants.KEY_USER_ALIAS, userAlias);
			LOG.debug("user: {} not found", userAlias);
		}
		addLayoutParamsToModel(model, Constants.TEMPLATE_PATH_SHOW_USER, Constants.TEMPLATE_SHOW_USER);
		LOG.debug("Finishing call to showUser()");
		return Constants.TEMPLATE_LAYOUT;
	}
	
	/**
	 * Show a template with a table of all users with their information
	 * @return a template with a table of all users an their information
	 */
	@GetMapping("/show/all")
	public String showAllUsers(Model model) {
		addLayoutParamsToModel(model, Constants.TEMPLATE_PATH_ALL_USER, Constants.TEMPLATE_ALL_USER);
		return Constants.TEMPLATE_LAYOUT;
	}
	
	/**
	 * Return a DataTableResultDTO with list of users
	 * @param params
	 * @return
	 */
	@PostMapping("/table")
	public @ResponseBody DataTableResultDTO<UserDTO> getListOfUsers(@ModelAttribute DataTableParamsDTO params){
		Page<UserDTO> pageUserDTO = userService.getUsersPaged(params);
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
	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		LOG.debug("Call to editUser()");
		
		try{
			UserDTO userDTO = userService.getUserById(id);
			if(userDTO== null)
				throw new Exception();
			else {
				model.addAttribute(Constants.KEY_USERDTO, userDTO);
				addLayoutParamsToModel(model, Constants.TEMPLATE_PATH_NEW_USER, Constants.TEMPLATE_NEW_USER);
			}
			
		}catch(Exception e) {
			LOG.error(new StringBuilder("Error modifiying user :").append(e.getMessage()).toString());
		}
		
		LOG.debug("Finishing call to editUser() ");
		return Constants.TEMPLATE_LAYOUT;
	}
	
	
}
