package com.practice.rental_car.service;

import java.util.ArrayList;
import java.util.List;

import com.practice.rental_car.model.Car;

public interface ICarService {

	List<Car> getAllCars();
	List<Car> getFilteredCars(String startDate, String endDate, String type, int sortCars);
	Car getSeletedCar(int idCars);
}