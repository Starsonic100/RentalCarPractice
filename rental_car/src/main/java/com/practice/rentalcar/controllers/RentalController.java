package com.practice.rentalcar.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.practice.rentalcar.customvalidations.ValidateRentalSearch;
import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;
import com.practice.rentalcar.service.ICarService;
import com.practice.rentalcar.service.IPersonService;
import com.practice.rentalcar.service.IRentalService;

@Controller
@SessionAttributes({"person","car","rental"})

@RequestMapping("/rental")
public class RentalController {
	
	private IRentalService rentalService;
	@Autowired
	@Qualifier("rentalService")
	public void setRentalService(IRentalService rs) {
		this.rentalService = rs;
	}
	
	private IPersonService personService;
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(IPersonService ps) {
		this.personService = ps;
	}

	private ICarService carService;
	@Autowired(required=true)
	@Qualifier(value="carService")
	public void setCarService(ICarService cs) {
		this.carService = cs;
	}

	@PostMapping("/createRent")
	public String createRent(@ModelAttribute("person") Person person, @ModelAttribute("car") Car car, Model model) {
		Rental rental = new Rental();
		rental.setStartDate(car.getStartDate());
		rental.setEndDate(car.getEndDate());
		rental.setIdCar(car.getIdCar());
		rental.setIdPerson(person.getId());
		rental.setIdRent(this.rentalService.addRental(rental));
		model.addAttribute("rental", rental);
		return "createdRent";
	}
	
	
	@GetMapping("/searchRentalForm")
	public String searchRentalForm(Model model) {
		Rental rental = new Rental();
		model.addAttribute("rental", rental);
		return "searchRentalForm";
	}
	
	@GetMapping("/searchRent")
	public ModelAndView viewRentalData(@ModelAttribute("rental") @Validated(ValidateRentalSearch.class) Rental rental, BindingResult result, Model model) {
		if(result.hasErrors()) {
			ModelAndView viewMap = new ModelAndView("searchRentalForm");
			return viewMap;
		}
		else {
			ModelAndView viewMap = new ModelAndView("viewRentalData");
			Rental selectedRental = new Rental();
			selectedRental = this.rentalService.getSeletedRental(rental.getIdRent());
			if(selectedRental != null) {
				viewMap.addObject("car", this.carService.getSeletedCar(selectedRental.getIdCar()));
				viewMap.addObject("person", this.personService.getSelectedPerson(selectedRental.getIdPerson()));
			}
			viewMap.addObject("selectedRental",selectedRental);
			return viewMap;
		}
	}
	
	@GetMapping("/activeRents")
	public ModelAndView viewActiveRents(Model model) {
		Rental rental = new Rental();
		model.addAttribute("rental", rental);
		List<Object> rents = this.rentalService.getActiveRents();
		ModelAndView viewMap = new ModelAndView("viewActiveRentsList");
		viewMap.addObject("rents", rents);
		return viewMap;	
	}
	
	@PostMapping(value="/selectRent", params="submit")
	public ModelAndView selectedActiveRent(@ModelAttribute("rental") @Validated(ValidateRentalSearch.class) Rental rental, BindingResult result, Model model) {
		if(result.hasErrors()) {
			Rental rentals = new Rental();
			model.addAttribute("rental", rentals);
			List<Object> rents = this.rentalService.getActiveRents();
			ModelAndView viewMap = new ModelAndView("viewActiveRentsList");
			viewMap.addObject("rents", rents);
			return viewMap;	
		}
		else {
			this.rentalService.updateRental(rental,0);
			ModelAndView viewMap = new ModelAndView("carReturnMessage");
			return viewMap;
		}
	}
	
	@GetMapping(value={"/selectRent","/selectInactiveRent"}, params="cancel")
	public String returnHome(Model model) {
		return "home";
	}
	
	@GetMapping("/inactiveRents")
	public ModelAndView viewInactiveRents(Model model) {
		Rental rental = new Rental();
		model.addAttribute("rental", rental);
		List<Object> rents = this.rentalService.getInactiveRents();
		ModelAndView viewMap = new ModelAndView("viewInactiveRentsList");
		viewMap.addObject("rents", rents);
		return viewMap;	
	}
	
	@PostMapping(value="/selectInactiveRent", params="submit")
	public ModelAndView selectedInactiveRent(@ModelAttribute("rental") @Validated(ValidateRentalSearch.class) Rental rental, BindingResult result, Model model) {
		if(result.hasErrors()) {
			Rental rentals = new Rental();
			model.addAttribute("rental", rentals);
			List<Object> rents = this.rentalService.getInactiveRents();
			ModelAndView viewMap = new ModelAndView("viewInactiveRentsList");
			viewMap.addObject("rents", rents);
			return viewMap;	
		}
		else {
			this.rentalService.updateRental(rental,1);
			ModelAndView viewMap = new ModelAndView("rentInactiveMessage");
			return viewMap;
		}
	}
	
}
