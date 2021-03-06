package com.example.spring.boot.practise.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.boot.practise.dto.DataTableParamsDTO;
import com.example.spring.boot.practise.dto.UserDTO;
import com.example.spring.boot.practise.entities.User;
import com.example.spring.boot.practise.repositories.UserRepository;
import com.example.spring.boot.practise.service.UserService;
import com.example.spring.boot.practise.util.UtilsConvertDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	@Transactional
	public UserDTO saveOrUpdate(UserDTO userDTO) throws Exception {
		User user = userRepository.save(new User(userDTO));
		if(user != null)
			return new UserDTO(user);
		else
			return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<UserDTO> getUsers() {
		List<User> listUsers = new ArrayList<>();
		Iterable<User> iterUser = userRepository.findAll();
		//Echa item of listAllUsers is copy to listUsers
		iterUser.forEach(listUsers::add);
		//Convert list of User to list of UserDTO
		if(!listUsers.isEmpty())
			return UtilsConvertDTO.convertListUserToListUserDTO(listUsers);
		else
			return new ArrayList<>();
	}


	@Override
	@Transactional
	public Boolean deleteUser(Long id) throws Exception {
		Boolean existUser = userRepository.existsById(id);
		if(existUser) {
			userRepository.delete(new User(id));
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
		
	
		
	}

	@Override
	@Transactional(readOnly=true)
	public UserDTO getUserByUserAlias(String userAlias) throws Exception {
		User user = userRepository.findUserByUserAlias(userAlias);
		if(user != null)
			return new UserDTO(user);
		else
			return null;
	}

	@Override
	@Transactional(readOnly=true)
	public UserDTO getUserById(Long id) {
		if(userRepository.existsById(id)) {
			Optional<User> user = userRepository.findById(id);
			if(user.isPresent())
				return new UserDTO(user.get());
			else 
				return null;
		}else {
			return null;
		}
	}
	
	@Override
	public Page<UserDTO> getUsersPaged(DataTableParamsDTO params) {
		Page<User> pageUser = userRepository.findByUserAliasLikeOrNameLike(params.getPageRequest(), params.getsSearch());
		return  new PageImpl<>(UtilsConvertDTO.convertListUserToListUserDTO(pageUser.getContent()), params.getPageRequest(), pageUser.getTotalElements());
	}


}
