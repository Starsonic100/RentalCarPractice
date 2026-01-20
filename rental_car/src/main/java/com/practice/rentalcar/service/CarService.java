package com.practice.rentalcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.practice.rentalcar.dao.ICarDAO;
import com.practice.rentalcar.model.Car;

@Service
public class CarService implements ICarService {
	
	@Autowired
	private ICarDAO carDAO;
	
	
	@Override
	@Transactional
	public List<Car> getFilteredCars(String startDate, String endDate, String type, String sortCars){
		return carDAO.getFilteredCars(startDate, endDate, type, sortCars);
	}
	
	@Override
	@Transactional
	public Car getSeletedCar(int idCar) {
		return carDAO.getSelectedCar(idCar);
	}

}
