package com.practice.rentalcar.dao;

import com.practice.rentalcar.model.Person;

public interface IPersonDAO {

	int addPerson(Person person);
	Person getSelectedPerson(int idPerson);

}