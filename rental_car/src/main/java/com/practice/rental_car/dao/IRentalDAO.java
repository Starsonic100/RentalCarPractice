package com.practice.rental_car.dao;

import java.util.List;

import com.practice.rental_car.model.Car;
import com.practice.rental_car.model.Person;
import com.practice.rental_car.model.Rental;

public interface IRentalDAO {

	int addRental(Rental rental);
	
	Rental getSelectedRental(int idRent);

	List<Object> getActiveRents();
	
	List<Object> getInactiveRents();
	
	void updateRental(int idRent, String actionRental);
}