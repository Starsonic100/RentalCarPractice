package com.practice.rentalcar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.practice.rentalcar.model.Car;

public interface ICarService {

	List<Car> getFilteredCars(LocalDate startDate, LocalDate endDate, String type, String sortCars);
	Car getSeletedCar(int idCar);
}