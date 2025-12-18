package com.practice.rentalcar.service;

import com.practice.rentalcar.model.Person;

public interface IPersonService {

	int addPerson(Person person);
	Person getSelectedPerson(int idPerson);

}