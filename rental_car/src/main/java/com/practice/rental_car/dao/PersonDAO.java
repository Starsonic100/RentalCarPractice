package com.practice.rental_car.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.practice.rental_car.model.Car;
import com.practice.rental_car.model.Person;

@Repository("personDAO")
public class PersonDAO implements IPersonDAO {

	@Override
	public int addPerson(Person person) {
		int idPerson;
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		try {
			Person person1 = new Person(person.getName(),person.getSurname(),person.getEmail(),person.getAddress(),2);
			mySession.beginTransaction();
			mySession.persist(person1);
			mySession.getTransaction().commit();
			idPerson = person1.getId();
		}finally {
			mySession.close();
			myFactory.close();
		}
		return idPerson;
	}
	
	@Override
	public Person getSelectedPerson(int idPerson) {
			Person person1 = new Person();
			SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class).buildSessionFactory();
			Session mySession = myFactory.openSession();
			try {
				mySession.beginTransaction();
				person1 = (Person) mySession.createQuery("from person where idPerson="+idPerson).uniqueResult();
			}finally {
				mySession.close();
				myFactory.close();
			}
			return person1;
	}
}
