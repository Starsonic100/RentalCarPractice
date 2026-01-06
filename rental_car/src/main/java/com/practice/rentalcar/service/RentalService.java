package com.practice.rentalcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.rentalcar.dao.IRentalDAO;
import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

@Service("rentalService")
public class RentalService implements IRentalService {
	@Autowired
	private IRentalDAO rentalDAO;
	
	@Override
	@Transactional
	public int addRental(Rental rental) {
		return rentalDAO.addRental(rental);
	}
	
	@Override
	@Transactional
	public Rental getSeletedRental(int idRent) {
		Rental selectedRental = rentalDAO.getSelectedRental(idRent);
		return selectedRental;
	} 
	
	@Override
	@Transactional
	public List<Object> getActiveRents(){
		List<Object> rents = rentalDAO.getActiveRents();
		return rents;
	}
	
	@Override
	@Transactional
	public List<Object> getInactiveRents(){
		List<Object> rents = rentalDAO.getInactiveRents();
		return rents;
	}
	
	
	@Override
	@Transactional
	public void updateRental(int idRent, int actionRental) {
		rentalDAO.updateRental(idRent, actionRental);
	}

}
