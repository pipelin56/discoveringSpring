package com.example.spring.boot.practise.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.spring.boot.practise.dto.UserDTO;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String userAlias;
	private String name;
	private String surnames;
	private String email;
	private Date dateOfBirth;
	private String movilNumber;
	
	@Column(name="is_active", columnDefinition="boolean default true")//Default value, true.
	private Boolean isActive = Boolean.TRUE;
	
	public User() {}
	
	public User(String userAlias, String name, String surnames, String email, Date dateOfBirth, String movilNumber) {
		super();
		this.userAlias=userAlias;
		this.name = name;
		this.surnames = surnames;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.movilNumber=movilNumber;
	}	
	
	public User(Long id, String userAlias, String name, String surnames, String email, Date dateOfBirth, String movilnumber) {
		super();
		this.id = id;
		this.userAlias=userAlias;
		this.name = name;
		this.surnames = surnames;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.movilNumber = movilnumber;
	}
	

	public User(UserDTO userDTO) {
		this.userAlias=userDTO.getUserAlias();
		this.name = userDTO.getName();
		this.surnames = userDTO.getSurnames();
		this.email = userDTO.getEmail();
		this.dateOfBirth = userDTO.getDateOfBirth();
		this.movilNumber = userDTO.getMovilNumber();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	public String getMovilNumber() {
		return movilNumber;
	}

	public void setMovilNumber(String movilNumber) {
		this.movilNumber = movilNumber;
	}



}