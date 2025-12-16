package com.practice.rental_car.service;

import com.practice.rental_car.model.Person;

public interface IPersonService {

	int addPerson(Person person);
	Person getSelectedPerson(int idPerson);

}