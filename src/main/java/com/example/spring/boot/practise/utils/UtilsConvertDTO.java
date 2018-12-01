package com.example.spring.boot.practise.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.boot.practise.dto.UserDTO;
import com.example.spring.boot.practise.entities.User;

public class UtilsConvertDTO {
	
	public static UserDTO convertUserToUserDTO(User user) {
		return new UserDTO(user);
	}
	
	public static List<UserDTO> convertListUserToListUserDTO(List<User> listUsers) {
		List<UserDTO> listDTOs = new ArrayList<UserDTO>();
		listDTOs = listUsers.stream()
							.map(UtilsConvertDTO::convertUserToUserDTO)
							.collect(Collectors.toList());
		return listDTOs;
	}
	
	public static User convertUserDTOToUser(UserDTO userDTO) throws ParseException {
		return new User(userDTO);
	}
	
	

}
