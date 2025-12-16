package com.practice.rental_car.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.practice.rental_car.model.Car;
import com.practice.rental_car.model.Person;
import com.practice.rental_car.service.ICarService;
import com.practice.rental_car.service.IPersonService;

import javax.validation.Valid;


@Controller
@SessionAttributes({"person","car"})

@RequestMapping("/person")
public class PersonController {
	
	private IPersonService personService;
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(IPersonService ps) {
		this.personService = ps;
	}

	
	@RequestMapping("/viewPersonForm")
	public String viewPersonForm(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "personForm";
	}
	
	@RequestMapping("/submitForm")
	public String submitForm(@ModelAttribute("person") @Valid Person person,  BindingResult personValidation, Model model) {
		if(personValidation.hasErrors()==true) {
			return "personForm";
		}
		else {
			person.setId(this.personService.addPerson(person));
			Car car = new Car();
			model.addAttribute("car", car);
			return "datesForm";
		}
	}
	
}
