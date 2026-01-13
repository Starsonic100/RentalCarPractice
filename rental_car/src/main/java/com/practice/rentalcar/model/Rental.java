package com.practice.rentalcar.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.practice.rentalcar.customvalidations.ValidateRentalSearch;

@Entity(name="rent")
@Table(name="rent")
public class Rental {
	public Rental() {
		
	}

	public Rental(String startDate, String endDate, int idCar, int idPerson) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.idCar = idCar;
		this.idPerson = idPerson;
	}
	
	public Rental(String startDate, String endDate, int idCar, int idPerson, int active) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.idCar = idCar;
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
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="idCars")
	private int idCar;
	
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
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

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
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
