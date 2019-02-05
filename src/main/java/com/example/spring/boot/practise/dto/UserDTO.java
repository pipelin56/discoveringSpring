package com.example.spring.boot.practise.dto;

import java.text.SimpleDateFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.spring.boot.practise.entities.User;

public class UserDTO {
	
	private Long id;
	@NotNull
	@Size(min=3, max=12, message="{validation.user.alias}")
	private String userAlias;
	@NotNull
	@Size(min=3, max=30, message="{validation.user.name}")
	private String name;
	@NotNull
	@Size(min=3, max=40, message="{validation.user.surnames}")
	private String surnames;
	@Email
	@Size(min=5, max=50, message="{validation.user.email}")
	private String email;
	private Boolean isActive = Boolean.TRUE;
	@NotEmpty
	@Size(min=9, max=10, message="{validation.user.mobilNumber}")
	private String mobilNumber;
	@NotEmpty(message="{validation.user.date.of.birth}")
	private String dateOfBirth;
	
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.userAlias= user.getUserAlias();
		this.name = user.getName();
		this.surnames = user.getSurnames();
		this.email = user.getEmail();
		this.mobilNumber = user.getMobilNumber();
		this.dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").format(user.getDateOfBirth());
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
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


	public String getMobilNumber() {
		return mobilNumber;
	}

	public void setMobilNumber(String mobilNumber) {
		this.mobilNumber = mobilNumber;
	}

	@Override
	public String toString() {
		return "UserDTO [userAlias=" + userAlias + ", name=" + name + ", surnames=" + surnames + ", email=" + email
				+ ", isActive=" + isActive + ", movilNumber=" + mobilNumber + ", dateOfBirth=" + dateOfBirth + "]";
	}

}
