package com.example.spring.boot.practise.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.boot.practise.entities.User;

//Esta interfaz será auto implementada por spring dentro de un 
//bean llamado UserRepository. CRUD.
public interface UserRepository  extends CrudRepository<User, Long>{
	
	@Transactional(readOnly=true)
	public User findUserByUserAlias(String userAlias) throws Exception;  
	
}
