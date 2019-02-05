package com.example.spring.boot.practise.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.boot.practise.entities.User;

/**
 * This class provide test cases for UserRepository in database(stored in memory)
 * @author Felipe Bedoya Castaño
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations="classpath:test.yml")
public class UserRepositoryTests {
	
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setUp() {
		//Given
		user = createNewUser();
		user = userRepository.save(user);
	}
	
	//Save an user in DB then OK
	@Test
	public void whenSaveUser_thenUserExistInDB() {
		//Given
		User user2 = createNewUser2();
		Optional<User> userBD = null;
		
		//When
		user2 = userRepository.save(user2);
		userBD = userRepository.findById(user2.getId());
		
		//Then
		assertThat(userBD).isNotEmpty();
		assertThat(userBD.get()).isEqualTo(user2);
	}
		
	//Find user by userAlias then OK
	@Test
	public void whenFindUserByUserAlias_thenReturnUser() {
		//When
		User userFound = null;
		try {
			userFound = userRepository.findUserByUserAlias("pipe");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//then
		assertThat(userFound.getId()).isEqualTo(user.getId());
		assertThat(userFound.getName()).isEqualTo(user.getName());
		assertThat(userFound.getSurnames()).isEqualTo(user.getSurnames());
		assertThat(userFound.getMobilNumber()).isEqualTo(user.getMobilNumber());
		assertThat(userFound.getEmail()).isEqualTo(user.getEmail());
	}
	
	//Find user by a non-existent userAlias then OK
	@Test
	public void whenFindUserByUserAlias_thenReturnNull() {
		//When
		User userFound = null;
		try {
			userFound = userRepository.findUserByUserAlias("userAliasNoValid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Then
		assertNull(userFound);
	}
	
	//Find user by id  then OK
	@Test
	public void whenFindUserById_thenReturnUser() {
		//When
		Optional<User> userFound = null;
		try {
			userFound = userRepository.findById(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//then
		assertThat(userFound).isNotEmpty();
	}
	
	//Find user by a non-existent id then OK
	@Test
	public void whenFindUserById_thenReturnUserEmpety() {
		//When
		Optional<User> userFound = null;
		try {
			userFound = userRepository.findById(9999L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//then
		assertThat(userFound).isEmpty();
	}
	
	//Delete user by id then OK
	@Test
	public void whenDeleteUserById_ThenDeleteUserFromBD(){
		//Given
		Long id = user.getId();
		Optional<User> userOptional = null;
		
		//When
		userRepository.deleteById(id);
		userOptional = userRepository.findById(id);
		
		//Then
		assertThat(userOptional).isEmpty();
	}
	
	//Delete user by id then OK
	@Test
	public void whenDeleteUserByUserEntity_ThenDeleteUserFromDB(){		
		//When
		userRepository.delete(user);
		Optional<User> userOptional = userRepository.findById(user.getId());
		
		//Then
		assertThat(userOptional).isEmpty();
	}
	
	//Delete all users then OK
	@Test
	public void whenDeleteAllUsers_ThenRemoveAllUsersFromDB(){	
		//Given
		Long id = user.getId();
		
		//When
		userRepository.deleteAll();
		Optional<User> userOptional = userRepository.findById(id);
		
		//Then
		assertThat(userOptional).isEmpty();
	}
	
	//Find all users in DB then OK
	@Test
	public void whenFindAllUser_ThenReturnAllUsers() {
		//Given
		User user2 = createNewUser2();
		user2 = userRepository.save(user2);
		//When
		Iterable<User> iterUsers = userRepository.findAll();
		//Then
		assertThat(iterUsers).containsOnly(user, user2);
	}
	
	//Save in DB an user with his name modified a then OK
	@Test
	public void whenUserIsModified_ThenSaveChangesInDB() {
		//Given
		String newNameUser = "new name";
		user.setName(newNameUser);
		//When
		user = userRepository.save(user);
		//Then
		assertThat(user).isNotNull();
		assertThat(user.getName()).isEqualTo(newNameUser);
	}
	
	
	private User createNewUser(){
		return new User( "pipe", "Felipe", "Bedoya Castaño", "testpipe@gmail.com", new Date(), "123456789");
	}
	
	private User createNewUser2(){
		return new User("pipe2", "Felipe2", "Bedoya Castaño", "testpipe22@gmail.com", new Date(), "222456789");
	}
	

}
