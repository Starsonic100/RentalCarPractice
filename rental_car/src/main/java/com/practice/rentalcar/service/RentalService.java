package com.practice.rentalcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.rentalcar.dao.IRentalDAO;
import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

@Service("rentalService")
public class RentalService implements IRentalService {
	@Autowired
	private IRentalDAO rentalDAO;
	
	@Override
	public int addRental(Rental rental) {
		return rentalDAO.addRental(rental);
	}
	
	@Override
	public Rental getSeletedRental(int idRent) {
		Rental selectedRental = rentalDAO.getSelectedRental(idRent);
		return selectedRental;
	} 
	
	@Override
	public List<Object> getActiveRents(){
		List<Object> rents = rentalDAO.getActiveRents();
		return rents;
	}
	
	@Override
	public List<Object> getInactiveRents(){
		List<Object> rents = rentalDAO.getInactiveRents();
		return rents;
	}
	
	
	@Override
	public void updateRental(int idRent, String actionRental) {
		rentalDAO.updateRental(idRent, actionRental);
	}

}
