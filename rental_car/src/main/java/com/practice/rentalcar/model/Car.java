package com.practice.rentalcar.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.practice.rentalcar.customvalidations.CarType;
import com.practice.rentalcar.customvalidations.StartEndDateValues;
import com.practice.rentalcar.customvalidations.ValidateDatesForm;
import com.practice.rentalcar.customvalidations.ValidateSelectCar;

@StartEndDateValues( groups = ValidateDatesForm.class ,endDate = "endDate", startDate = "startDate", message="Please insert a start date equal or set after current date and an end date set after the start date") 
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
	
	@NotBlank(message="Start Date required")
	@Transient
	private String startDate;
	
	@NotBlank(message="End Date required")
	@Transient
	private String endDate;
	
	@Transient
	private int sortCars;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getSortCars() {
		return sortCars;
	}

	public void setSortCars(int sortCars) {
		this.sortCars = sortCars;
	}
}
