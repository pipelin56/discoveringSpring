package com.example.spring.boot.practise.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.spring.boot.practise.entities.User;

public class UserDTO {
	
	@NotNull
	private String userAlias;
	@NotNull
	@Size(min=3, max=30)
	private String name;
	@NotNull
	@Size(min=3, max=40)
	private String surnames;
	@Email
	private String email;
	private Boolean isActive = Boolean.TRUE;
	
	private String movilNumber;
	
	
	public UserDTO(User user) {
		super();
		this.userAlias= user.getUserAlias();
		this.name = user.getName();
		this.surnames = user.getName();
		this.email = user.getEmail();
		this.dateOfBirth = user.getDateOfBirth();
		this.movilNumber = user.getMovilNumber();
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

	private Date dateOfBirth;
	
	public UserDTO() {}

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

	@Override
	public String toString() {
		return "UserDTO [userAlias=" + userAlias + ", name=" + name + ", surnames=" + surnames + ", email=" + email
				+ ", isActive=" + isActive + ", dateOfBirth=" + dateOfBirth + "]";
	}

	public String getMovilNumber() {
		return movilNumber;
	}

	public void setMovilNumber(String movilNumber) {
		this.movilNumber = movilNumber;
	}

}
