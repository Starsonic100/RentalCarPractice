package com.practice.rentalcar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

@Repository("personDAO")
public class PersonDAO implements IPersonDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }//setSessionFactory

	@Override
	public int addPerson(Person person) {
		Session session = sessionFactory.getCurrentSession();
		Person newPerson = new Person(person.getName(),person.getSurname(),person.getEmail(),person.getAddress(),2);
		session.save(newPerson);
		return newPerson.getId();
	}
	
	@Override
	public Person getSelectedPerson(int idPerson) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Person.class, idPerson);
	}
}
