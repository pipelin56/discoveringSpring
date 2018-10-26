package com.example.spring.boot.practise.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.boot.practise.dto.UserDTO;
import com.example.spring.boot.practise.entities.User;
import com.example.spring.boot.practise.repositories.UserRepository;
import com.example.spring.boot.practise.utils.UtilsConvertDTO;

@Controller
@RequestMapping("/user")
public class userController {
	
	@Autowired
	private UserRepository userRepository;
	

	/**
	 * Shows template to create a new User
	 * @return ModelAndView with an empty UserDTO and template to create it
	 */
	@GetMapping("/newUser")
	public ModelAndView newUser() {
		ModelAndView mav = new ModelAndView("newUser");
		mav.addObject("newUser", new UserDTO());
		return mav;
	}
	
	/**
	 * 
	 * @param insert in bbdd a new user
	 * @return
	 */
	@PostMapping("/addNewUser")
	public String addNewuser(@ModelAttribute @Valid UserDTO newUser, BindingResult bindingResult) {
		Boolean saved = Boolean.TRUE;
		
		if(!bindingResult.hasErrors()) {			
			User user=null;
			try {			
				user = userRepository.save(new User(newUser));
			}catch(Exception e) {
				saved = Boolean.FALSE;
			}
			
			if(saved)
				return "redirect:/user/showUser/"+user.getUserAlias();
			else
				return "redirect:newUser?saved=false";	
		}else
			return "redirect:newUser?validationError=true";
	}
	
	/**
	 * Show a template with informations about user with userAlias indicated in url
	 * @param userAlias
	 * @return ModelAndView with template and informations about an user
	 * @throws Exception
	 */
	@GetMapping("/showUser/{userAlias}")
	public ModelAndView showUser(@PathVariable String userAlias) throws Exception {
		ModelAndView mav = new ModelAndView("showUser");
		User user = userRepository.findUserByUserAlias(userAlias);
		if(user != null) {
			mav.addObject("user", user);
		}else {
			mav.addObject("noUserFound", Boolean.TRUE);
			mav.addObject("userAlias", userAlias);
		}
		return mav;
	}
	
	/**
	 * Show a template with a table of all users an their information
	 * @return
	 */
	@GetMapping("/showAllUser")
	public ModelAndView showAllUsers() {
		ModelAndView mav = new ModelAndView("showAllUsers");
		List<User> listUser = new ArrayList<User>();
		//Recuperamos todos los usuarios
		Iterable<User> listAllUsers = userRepository.findAll();
		//Cada elemento recuperado lo metemos en una lista
		listAllUsers.forEach(listUser::add);
		//Pasamos los objetos a DTO
		List<UserDTO> listUsers = UtilsConvertDTO.convertListUserToListUserDTO(listUser);
		
		if(!listUsers.isEmpty()) {
			mav.addObject("listOfUsers", listAllUsers);
		}else {
			mav.addObject("noUsersFound", Boolean.TRUE);
		}
		
		
		return mav;
	}

}
