package com.practice.rentalcar.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;

@Repository("personDAO")
public class PersonDAO implements IPersonDAO {

	@Override
	public int addPerson(Person person) {
		int idPerson;
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		try {
			Person newPerson = new Person(person.getName(),person.getSurname(),person.getEmail(),person.getAddress(),2);
			mySession.beginTransaction();
			mySession.persist(newPerson);
			mySession.getTransaction().commit();
			idPerson = newPerson.getId();
		}finally {
			mySession.close();
			myFactory.close();
		}
		return idPerson;
	}
	
	@Override
	public Person getSelectedPerson(int idPerson) {
			Person selectedPerson = new Person();
			SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).buildSessionFactory();
			Session mySession = myFactory.openSession();
			try {
				mySession.beginTransaction();
				selectedPerson = (Person) mySession.createQuery("from person where idPerson="+idPerson).uniqueResult();
			}finally {
				mySession.close();
				myFactory.close();
			}
			return selectedPerson;
	}
}
