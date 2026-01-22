package com.practice.rentalcar.service;

import java.util.List;

import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

public interface IRentalService {

	int addRental(Rental rental);
	Rental getSeletedRental(int idRent);
	List<Rental> getActiveRents();
	List<Rental> getInactiveRents();
	void updateRental(Rental rental, int actionRental);
}