package com.sarkar.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String uname;
	private String email;
	private String pwd;
	private String phno;
	private String pwdUpdated;
	@CreationTimestamp
	@Column(updatable = false) 
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;

	@ManyToOne
	@JoinColumn(name = "countryId")
	private CountryEntity country;

	@ManyToOne
	@JoinColumn(name = "stateId")
	private StateEntity state;

	@ManyToOne
	@JoinColumn(name = "cityId")
	private CityEntity city;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getPwdUpdated() {
		return pwdUpdated;
	}

	public void setPwdUpdated(String pwdUpdated) {
		this.pwdUpdated = pwdUpdated;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public StateEntity getState() {
		return state;
	}

	public void setState(StateEntity state) {
		this.state = state;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

}
