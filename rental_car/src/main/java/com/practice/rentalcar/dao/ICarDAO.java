package com.practice.rentalcar.dao;

import java.util.List;

import com.practice.rentalcar.model.Car;


public interface ICarDAO {

	List<Car> getAllCars();

	List<Car> getFilteredCars(String startDate, String endDate, String type, int sortCars);
	
	Car getSelectedCar(int idCar);

}