package com.practice.rentalcar.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.service.ICarService;
import com.practice.rentalcar.service.IPersonService;

import javax.validation.Valid;


@Controller
@SessionAttributes({"person","car"})

@RequestMapping("/person")
public class PersonController {
	private static final Logger logger = LogManager.getLogger("Person");	

	private IPersonService personService;
	@Autowired
	@Qualifier("personService")
	public void setPersonService(IPersonService ps) {
		this.personService = ps;
	}

	
	@GetMapping("/viewPersonForm")
	public String viewPersonForm(Model model) {
		logger.info("Opening Person Form");
		Person person = new Person();
		model.addAttribute("person", person);
		return "personForm";
	}
	
	@PostMapping("/submitForm")
	public String submitForm(@ModelAttribute("person") @Valid Person person,  BindingResult personValidation, Model model) {
		if(personValidation.hasErrors()) {
			logger.error(personValidation.getAllErrors());
			return "personForm";
		}
		else {
			logger.info("Submitting new person");
			person.setCategory(2);
			person.setId(this.personService.addPerson(person));
			Car car = new Car();
			model.addAttribute("car", car);
			return "datesForm";
		}
	}
	
}
