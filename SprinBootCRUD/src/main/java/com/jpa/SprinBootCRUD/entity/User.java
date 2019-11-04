package com.jpa.SprinBootCRUD.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="User")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private long id;
	 
	 @Column(name="name")
	 private String name;
	 
	 @Column(name="address")
	 private String address;
	 
	 @Column(name="country")
	 private String country;
	 
	 @Column(name="email")
	 private String email;
	 
	 @Column(name="sex")
	 private String sex;

	 
	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	 
	 
	 
}
