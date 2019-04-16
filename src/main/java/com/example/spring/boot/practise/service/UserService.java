package com.example.spring.boot.practise.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.spring.boot.practise.dto.DataTableParamsDTO;
import com.example.spring.boot.practise.dto.UserDTO;

public interface UserService {
	
	/**
	 * Save or update an user
	 * @param UserDTO 
	 * @return UserDTO userDTO saved or updated
	 * @throws Exception
	 */
	public UserDTO saveOrUpdate(UserDTO userDTO) throws Exception;
	
	/**
	 * Return a list of all user in database
	 * @return List<UserDTO>
	 */
	public List<UserDTO> getUsers();
	
	/**
	 * Return a UserDTO with userAlias received like parameter
	 * @param String
	 * @return UserDTO userDTO
	 * @throws Exception
	 */
	public UserDTO getUserByUserAlias(String userAlias) throws Exception;
	
	/**
	 * Delete a user with id received like parameter
	 * @param Long
	 * @return Boolean true if user could be deleted otherwise false.
	 * @throws Exception
	 */
	public Boolean deleteUser(Long id) throws Exception;

	/**
	 * Return a user with id equals to id recived like parameter
	 * @param Long
	 * @return UserDTO userDTO
	 */
	public UserDTO getUserById(Long id);
	
	/**
	 * Return a Page of all user in database
	 * @return Page<UserDTO>
	 */
	public Page<UserDTO> getUsersPaged(DataTableParamsDTO params);
	
}
