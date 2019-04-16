package com.example.spring.boot.practise.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.boot.practise.entities.User;
@Repository
public interface UserRepository  extends PagingAndSortingRepository<User, Long>{
	
	@Transactional(readOnly=true)
	public User findUserByUserAlias(String userAlias) throws Exception;  
	
	@Query(value = "SELECT u FROM User u WHERE u.userAlias LIKE CONCAT('%', ?1, '%') OR u.name LIKE CONCAT('%', ?1, '%')",
			countQuery = "SELECT COUNT(u.id) FROM User u WHERE u.userAlias LIKE CONCAT('%', ?1, '%') OR u.name LIKE CONCAT('%', ?1, '%')")
	public Page<User> findByUserAliasLikeOrNameLike(Pageable page, String search);
}
