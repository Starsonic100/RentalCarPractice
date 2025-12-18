package com.practice.rentalcar.model;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity(name="person")
@Table(name="person")
public class Person {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPerson")
	private int idPerson;
	

	@Column(name="name")
	@NotBlank(message="Name required")
	private String name;

	@NotBlank(message="Surname required")
	@Column(name="surname")
	private String surname;
	
	@NotBlank(message="Email required")
	@Email(message="Valid email is required")
	@Column(name="email")
	private String email;
	
	@NotBlank(message="Address required")
	@Column(name="address")
	private String address;
	
	@Column(name="person_category")
	private int personCategory;
	
	public Person() {
	}
	
	public Person(String name, String surname, String email, String address, int personCategory) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.personCategory = personCategory;
	}

	public int getId() {
		return idPerson;
	}
	public void setId(int id) {
		this.idPerson = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCategory() {
		return personCategory;
	}
	public void setCategory(int category) {
		this.personCategory = category;
	}
}
