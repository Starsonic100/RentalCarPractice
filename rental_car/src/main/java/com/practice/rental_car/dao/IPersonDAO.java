package com.practice.rental_car.dao;

import com.practice.rental_car.model.Person;

public interface IPersonDAO {

	int addPerson(Person person);
	Person getSelectedPerson(int idPerson);

}