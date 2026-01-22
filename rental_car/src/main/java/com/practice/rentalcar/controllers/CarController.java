package com.practice.rentalcar.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.practice.rentalcar.customvalidations.StartEndDateValues;
import com.practice.rentalcar.customvalidations.ValidateDatesForm;
import com.practice.rentalcar.customvalidations.ValidateSelectCar;
import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.service.ICarService;


@Controller
@SessionAttributes({"person","car"})

@RequestMapping("/car")

public class CarController {
	
	private static final Logger logger = LogManager.getLogger(CarController.class);	
	private ICarService carService;
	@Autowired
	@Qualifier("carService") 
	public void setCarService(ICarService cs) {
		this.carService = cs;
	}
	
	
	@PostMapping("/submitDateForm")
	public ModelAndView submitDateForm(@ModelAttribute("car") @Validated(ValidateDatesForm.class) Car car, BindingResult result, @ModelAttribute("person") Person person) {
		if(result.hasErrors()) {
			logger.error("Please insert a start date that is equal or after current date and an end date that is after the start date");
			ModelAndView viewMap = new ModelAndView("datesForm");
			return viewMap;
		}
		else {
		logger.trace("Obtaining cars");
		List<Car> cars = this.carService.getFilteredCars(car.getStartDate(), car.getEndDate(), car.getType(), car.getSortCars());
		ModelAndView viewMap = new ModelAndView("carsList");
		viewMap.addObject("cars", cars);
		return viewMap;
		}
	}
	
	
	@PostMapping(value="/selectCar", params="submit")
	public ModelAndView selectedCar(@ModelAttribute("car") @Validated({ValidateDatesForm.class, ValidateSelectCar.class}) Car car, BindingResult result, @ModelAttribute("person") Person person, Model model) {
		if(result.hasErrors()) {
			List<Car> cars = this.carService.getFilteredCars(car.getStartDate(), car.getEndDate(), car.getType(), car.getSortCars());
			ModelAndView viewMap = new ModelAndView("carsList");

			viewMap.addObject("cars", cars);
			return viewMap;		
		}
		else {
			ModelAndView viewMap = new ModelAndView("viewSelectedCar");

			LocalDate endDate = car.getEndDate();
			LocalDate startDate = car.getStartDate();
			car = this.carService.getSeletedCar(car.getIdCar());
			car.setEndDate(endDate);
			car.setStartDate(startDate);
			model.addAttribute("car", car);
			return viewMap;
		}
	}
	
	@GetMapping(value="/selectCar", params="cancel")
	public String returnDateForm(Model model,  @ModelAttribute("person") Person person) {
		Car car = new Car();
		model.addAttribute("car", car);
		return "datesForm";
	}
}
