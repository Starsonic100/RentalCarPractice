package com.practice.rentalcar.service;

import java.time.LocalDate;
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
		int active = 0;
		LocalDate currentDate = LocalDate.now();
		if(currentDate==rental.getStartDate()) {
			active = 1;
		}
		rental.setActive(active);
		return rentalDAO.addRental(rental);
	}
	
	@Override
	@Transactional
	public Rental getSeletedRental(int idRent) {
		return rentalDAO.getSelectedRental(idRent);
	} 
	
	@Override
	@Transactional
	public List<Rental> getActiveRents(){
		return rentalDAO.getActiveRents();
	}
	
	@Override
	@Transactional
	public List<Rental> getInactiveRents(){
		return rentalDAO.getInactiveRents();
	}
	
	
	@Override
	@Transactional
	public void updateRental(Rental rental, int actionRental) {
		LocalDate currentDate = LocalDate.now();
		rental.setActive(actionRental);
		if(actionRental == 1) rental.setStartDate(currentDate); else rental.setEndDate(currentDate);
		rentalDAO.updateRental(rental);
	}

}
