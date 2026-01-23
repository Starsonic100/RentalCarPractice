package com.practice.rentalcar.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.rentalcar.dao.IPersonDAO;
import com.practice.rentalcar.model.Person;

@Service("personService")
public class PersonService implements IPersonService {
	private static final Logger logger = LogManager.getLogger("Person");
	@Autowired
	private IPersonDAO personDAO;

	@Override
	@Transactional
	public int addPerson(Person person) {
		logger.info("Service: Adding person");
		return personDAO.addPerson(person);
	}
	
	@Override
	@Transactional
	public Person getSelectedPerson(int idPerson) {
		logger.info("Service: Getting selected person");
		return personDAO.getSelectedPerson(idPerson);
	}
}
