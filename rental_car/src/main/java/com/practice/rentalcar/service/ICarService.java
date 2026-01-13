package com.practice.rentalcar.service;

import java.util.ArrayList;
import java.util.List;

import com.practice.rentalcar.model.Car;

public interface ICarService {

	List<Car> getAllCars();
	List<Car> getFilteredCars(String startDate, String endDate, String type, String sortCars);
	Car getSeletedCar(int idCar);
}