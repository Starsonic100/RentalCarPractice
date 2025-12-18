package com.practice.rentalcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.rentalcar.dao.IPersonDAO;
import com.practice.rentalcar.model.Person;

@Service("personService")
public class PersonService implements IPersonService {
	
	@Autowired
	private IPersonDAO personDAO;

	@Override
	public int addPerson(Person person) {
		return personDAO.addPerson(person);
	}
	
	@Override
	public Person getSelectedPerson(int idPerson) {
		return personDAO.getSelectedPerson(idPerson);
	}
}
