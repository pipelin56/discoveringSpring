package com.example.spring.boot.practise.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.spring.boot.practise.dto.UserDTO;


@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true, length=12)
	private String userAlias;
	@Column(nullable=false, length=30)
	private String name;
	@Column(nullable=false, length=40)
	private String surnames;
	@Column(nullable=false, length=50)
	private String email;
	@Column(nullable=false)
	private Date dateOfBirth;
	@Column(nullable=true, length=10)
	private String mobilNumber;
	@Column(name="is_active", columnDefinition="boolean default true")//Default value, true.
	private Boolean isActive = Boolean.TRUE;
	
	public User() {}
	
	public User(Long id) {
		super();
		this.id = id;
	}
	
	public User(String userAlias, String name, String surnames, String email, Date dateOfBirth, String movilNumber) {
		super();
		this.userAlias=userAlias;
		this.name = name;
		this.surnames = surnames;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.mobilNumber=movilNumber;
	}	
	
	public User(Long id, String userAlias, String name, String surnames, String email, Date dateOfBirth, String movilnumber) {
		super();
		this.id = id;
		this.userAlias=userAlias;
		this.name = name;
		this.surnames = surnames;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.mobilNumber = movilnumber;
	}
	

	public User(UserDTO userDTO) throws ParseException {
		this.id=userDTO.getId();
		this.userAlias=userDTO.getUserAlias();
		this.name = userDTO.getName();
		this.surnames = userDTO.getSurnames();
		this.email = userDTO.getEmail();
		this.dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(userDTO.getDateOfBirth());
		this.mobilNumber = userDTO.getMobilNumber();
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

	public String getMobilNumber() {
		return mobilNumber;
	}

	public void setMobilNumber(String mobilNumber) {
		this.mobilNumber = mobilNumber;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userAlias=" + userAlias + ", name=" + name + ", surnames=" + surnames + ", email="
				+ email + ", dateOfBirth=" + dateOfBirth + ", movilNumber=" + mobilNumber + ", isActive=" + isActive
				+ "]";
	}

	


}
