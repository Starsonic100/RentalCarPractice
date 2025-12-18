package com.practice.rentalcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.practice.rentalcar.dao.ICarDAO;
import com.practice.rentalcar.model.Car;

@Service("carService")
public class CarService implements ICarService {
	
	@Autowired
	private ICarDAO carDAO;
	
	@Override
	public List<Car> getAllCars(){
		List<Car> cars = carDAO.getAllCars();
		return cars;
	}
	
	@Override
	public List<Car> getFilteredCars(String startDate, String endDate, String type, int sortCars){
		List<Car> cars = carDAO.getFilteredCars(startDate, endDate, type, sortCars);
		return cars;
	}
	
	@Override
	public Car getSeletedCar(int idCar) {
		Car selectedCar = carDAO.getSelectedCar(idCar);
		return selectedCar;
	}

}
