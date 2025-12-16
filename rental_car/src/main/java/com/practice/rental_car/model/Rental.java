package com.practice.rental_car.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.practice.rental_car.customValidations.ValidateRentalSearch;

@Entity(name="rent")
@Table(name="rent")
public class Rental {
	public Rental() {
		
	}

	public Rental(String start_date, String end_date, int idCars, int idPerson) {
		this.start_date = start_date;
		this.end_date = end_date;
		this.idCars = idCars;
		this.idPerson = idPerson;
	}
	
	public Rental(String start_date, String end_date, int idCars, int idPerson, int active) {
		this.start_date = start_date;
		this.end_date = end_date;
		this.idCars = idCars;
		this.idPerson = idPerson;
		this.active = active;
	}

	@NotNull(message="Please insert an id for Rent", groups=ValidateRentalSearch.class)
	@Positive(message="Please insert a valid id for Rent", groups=ValidateRentalSearch.class)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRent")
	private int idRent;
	
	@Column(name="start_date")
	private String start_date;
	
	@Column(name="end_date")
	private String end_date;
	
	@Column(name="idCars")
	private int idCars;
	
	@Column(name="idPerson")
	private int idPerson;
	
	@Column(name="active")
	private int active;
	
	@Transient
	private Person person;
	
	@Transient
	private Car car;

	public int getIdRent() {
		return idRent;
	}

	public void setIdRent(int idRent) {
		this.idRent = idRent;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}
	
	public int getActive() {
		return active;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public Car getCar() {
		return car;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public int getIdCars() {
		return idCars;
	}

	public void setIdCars(int idCars) {
		this.idCars = idCars;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	
	public void setActive(int active) {
		this.active = active;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
}
