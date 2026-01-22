package com.practice.rentalcar.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.practice.rentalcar.customvalidations.ValidateRentalSearch;


@Entity(name="rent")
@Table(name="rent")
public class Rental {
	public Rental() {
		
	}

	public Rental(LocalDate startDate, LocalDate endDate, int idCar, int idPerson) {
		this.startDate = startDate;
		this.endDate = endDate;
	/*	this.idCar = idCar;
		this.idPerson = idPerson;*/
	}
	
	public Rental(LocalDate startDate, LocalDate endDate, int idCar, int idPerson, int active) {
		this.startDate = startDate;
		this.endDate = endDate;
		/*this.idCar = idCar;
		this.idPerson = idPerson;*/
		this.active = active;
	}
	
	public Rental(LocalDate startDate, LocalDate endDate, Car car, Person person) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.car = car;
		this.person = person;
	}
	

	@NotNull(message="Please insert an id for Rent", groups=ValidateRentalSearch.class)
	@Positive(message="Please insert a valid id for Rent", groups=ValidateRentalSearch.class)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRent")
	private int idRent;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	

	
	@Column(name="active")
	private int active;
	
	
	//@Transient
	@OneToOne
	@JoinColumn(name="idPerson")
	private Person person;
	
	//@Transient
	@OneToOne
	@JoinColumn(name="idCars")
	private Car car;

	public int getIdRent() {
		return idRent;
	}

	public void setIdRent(int idRent) {
		this.idRent = idRent;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
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

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/*public int getIdCar() {
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
	*/
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
