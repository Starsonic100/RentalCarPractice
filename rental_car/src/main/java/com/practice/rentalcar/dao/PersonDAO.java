package com.practice.rentalcar.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

@Repository("personDAO")
public class PersonDAO implements IPersonDAO {

	private static final Logger logger = LogManager.getLogger("Person");
	private SessionFactory sessionFactory;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }//setSessionFactory

	@Override
	public int addPerson(Person person) {
		logger.debug("Adding person");
		Session session = sessionFactory.getCurrentSession();
		session.save(person);
		return person.getId();
	}
	
	@Override
	public Person getSelectedPerson(int idPerson) {
		logger.debug("Selecting person");
		Session session = sessionFactory.getCurrentSession();
		return session.get(Person.class, idPerson);
	}
}
