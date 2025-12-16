package com.practice.rental_car.dao;

import java.util.List;

import com.practice.rental_car.model.Car;


public interface ICarDAO {

	List<Car> getAllCars();

	List<Car> getFilteredCars(String startDate, String endDate, String type, int sortCars);
	
	Car getSelectedCar(int idCars);

}