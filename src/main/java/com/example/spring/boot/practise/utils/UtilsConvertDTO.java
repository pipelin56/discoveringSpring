package com.example.spring.boot.practise.utils;

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
		listDTOs = listUsers.stream().map(UtilsConvertDTO::convertUserToUserDTO).collect(Collectors.toList());
		return listDTOs;
	}
	
	public static User convertUserDTOToUser(UserDTO userDTO) {
		return new User(userDTO);
	}
	
	public static List<User> convertListUserDTOToListUser(List<UserDTO> listUserDTO){
		List<User> listUser = new ArrayList<User>();
		listUser = listUserDTO.stream().map(UtilsConvertDTO::convertUserDTOToUser).collect(Collectors.toList());
		return listUser;
	}

}
