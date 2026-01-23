package com.practice.rentalcar.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.practice.rentalcar.customvalidations.CarType;
import com.practice.rentalcar.customvalidations.StartEndDateValues;
import com.practice.rentalcar.customvalidations.ValidateDatesForm;
import com.practice.rentalcar.customvalidations.ValidateSelectCar;

@StartEndDateValues( groups = ValidateDatesForm.class ,endDate = "endDate", startDate = "startDate", message="Please insert a valid End Date set after Start Date") 
@Entity(name="cars")
@Table(name="cars")
public class Car {
	
	
	public Car(String licensePlate, String model, String type, int price, int available) {
		this.licensePlate = licensePlate;
		this.model = model;
		this.type = type;
		this.price = price;
		this.available = available;
	}
	
	public Car(int idCar, String licensePlate, String model, String type, int price) {
		this.idCar = idCar;
		this.licensePlate = licensePlate;
		this.model = model;
		this.type = type;
		this.price = price;
	}

	public Car(int idCar) {
		this.idCar = idCar;
	}

	public Car() {
	}
	
	
	@NotNull(groups = ValidateSelectCar.class, message="Please select a car")
	@Positive(groups = ValidateSelectCar.class, message="Please select a car")
	@Id
	@Column(name="id")
	private int idCar;

	@Column(name="license_plate")
	private String licensePlate;
	
	@Column(name="model")
	private String model;
	
	@CarType(groups = ValidateDatesForm.class)
	@Column(name="type")
	private String type;
	
	@Column(name="price")
	private int price;
	
	@Column(name="available")
	private int available;
	
	@NotNull(groups = ValidateDatesForm.class, message="Start Date required")
	@FutureOrPresent(groups = ValidateDatesForm.class, message="Please enter current or future Start Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
	@Transient
	private LocalDate startDate;
	
	@NotNull(groups = ValidateDatesForm.class, message="End Date required")
	@Future(groups = ValidateDatesForm.class, message="Please select a future End Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
	@Transient
	private LocalDate endDate;
	
	@NotNull(groups = ValidateDatesForm.class, message="Select a sort order")
	@Transient
	private String sortCars;

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
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

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getSortCars() {
		return sortCars;
	}

	public void setSortCars(String sortCars) {
		this.sortCars = sortCars;
	}
}
