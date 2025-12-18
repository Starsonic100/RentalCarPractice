package com.practice.rentalcar.dao;

import java.util.List;

import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

public interface IRentalDAO {

	int addRental(Rental rental);
	
	Rental getSelectedRental(int idRent);

	List<Object> getActiveRents();
	
	List<Object> getInactiveRents();
	
	void updateRental(int idRent, String actionRental);
}