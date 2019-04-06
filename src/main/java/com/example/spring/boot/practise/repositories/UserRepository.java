package com.example.spring.boot.practise.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.boot.practise.entities.User;
@Repository
public interface UserRepository  extends PagingAndSortingRepository<User, Long>{
	
	@Transactional(readOnly=true)
	public User findUserByUserAlias(String userAlias) throws Exception;  
	
}
