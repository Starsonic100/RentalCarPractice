package com.practice.rental_car.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.practice.rental_car.dao.ICarDAO;
import com.practice.rental_car.model.Car;

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
	public Car getSeletedCar(int idCars) {
		Car car1 = carDAO.getSelectedCar(idCars);
		return car1;
	}

}
