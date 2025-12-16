package com.practice.rental_car.service;

import java.util.List;

import com.practice.rental_car.model.Car;
import com.practice.rental_car.model.Person;
import com.practice.rental_car.model.Rental;

public interface IRentalService {

	int addRental(Rental rental);
	Rental getSeletedRental(int idRent);
	List<Object> getActiveRents();
	List<Object> getInactiveRents();
	void updateRental(int idRent, String actionRental);
}