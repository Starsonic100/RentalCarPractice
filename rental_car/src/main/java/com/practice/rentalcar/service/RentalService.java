package com.practice.rentalcar.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.rentalcar.dao.IRentalDAO;
import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

@Service("rentalService")
public class RentalService implements IRentalService {
	private static final Logger logger = LogManager.getLogger("Rental");
	@Autowired
	private IRentalDAO rentalDAO;
	
	@Override
	@Transactional
	public int addRental(Rental rental, LocalDate startDate) {
		logger.info("Service: Adding rent");
		int active = 0;
		LocalDate currentDate = LocalDate.now();
		if(currentDate.isEqual(startDate)) {
			active = 1;
		}
		rental.setActive(active);
		return rentalDAO.addRental(rental);
	}
	
	@Override
	@Transactional
	public Rental getSeletedRental(int idRent) {
		logger.info("Service: Getting selected rent");
		return rentalDAO.getSelectedRental(idRent);
	} 
	
	@Override
	@Transactional
	public List<Rental> getActiveRents(){
		logger.info("Service: Getting active rents");
		return rentalDAO.getActiveRents();
	}
	
	@Override
	@Transactional
	public List<Rental> getInactiveRents(){
		logger.info("Service: Getting inactive rents");
		return rentalDAO.getInactiveRents();
	}
	
	
	@Override
	@Transactional
	public void updateRental(Rental rental, int actionRental) {
		logger.info("Service: Updating rent");
		LocalDate currentDate = LocalDate.now();
		rental.setActive(actionRental);
		logger.debug("Rental will be updated with currentDate {} based on action for rent: {}", currentDate.toString(), actionRental);
		if(actionRental == 1) rental.setStartDate(currentDate); else rental.setEndDate(currentDate);
		rentalDAO.updateRental(rental);
	}

}
