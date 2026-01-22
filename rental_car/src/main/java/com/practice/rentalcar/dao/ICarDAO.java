package com.practice.rentalcar.dao;

import java.time.LocalDate;
import java.util.List;

import com.practice.rentalcar.model.Car;


public interface ICarDAO {

	List<Car> getFilteredCars(LocalDate startDate, LocalDate endDate, String type, String namedQuery);
	
	Car getSelectedCar(int idCar);

}