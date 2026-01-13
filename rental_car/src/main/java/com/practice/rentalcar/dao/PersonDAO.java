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
		int idPerson;
		Session mySession = sessionFactory.getCurrentSession();
			Person newPerson = new Person(person.getName(),person.getSurname(),person.getEmail(),person.getAddress(),2);
			mySession.save(newPerson);
			idPerson = newPerson.getId();
		return idPerson;
	}
	
	@Override
	public Person getSelectedPerson(int idPerson) {
			Person selectedPerson = new Person();
			Session mySession = sessionFactory.getCurrentSession();
			selectedPerson = (Person) mySession.createCriteria(Person.class).add(Restrictions.like("id", idPerson)).uniqueResult();

			return selectedPerson;
	}
}
