package com.practice.rental_car.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.practice.rental_car.model.Car;
import com.practice.rental_car.model.Person;
import com.practice.rental_car.model.Rental;

@Repository("rentalDAO")
public class RentalDAO implements IRentalDAO {
	@Override
	public int addRental(Rental rental) {
		int idRental;
		int active = 0;
		LocalDate currentDate = LocalDate.now();
		if(currentDate.toString().equals(rental.getStart_date())) {
			active = 1;
		}
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Rental.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		try {
			Rental rental1 = new Rental(rental.getStart_date(), rental.getEnd_date(), rental.getIdCars(), rental.getIdPerson(), active);
			mySession.beginTransaction();
			mySession.persist(rental1);
			mySession.getTransaction().commit();
			idRental = rental1.getIdRent();
		}finally {
			mySession.close();
			myFactory.close();
		}
		return idRental;
	}
	
	@Override
	public Rental getSelectedRental(int idRent) {
		Rental rental1 = new Rental();
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Rental.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		try {
			mySession.beginTransaction();
			rental1 = (Rental) mySession.createQuery("from rent where idRent="+idRent).uniqueResult();
		}finally {
			mySession.close();
			myFactory.close();
		}
		return rental1;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object> getActiveRents(){
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addPackage("rental_car.model").addAnnotatedClass(Rental.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		List<Object> activeRentalsList = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		
		String sql = "select r.idRent, r.start_date, r.end_date, c.license_plate, c.model, p.name, p.surname from\r\n"
				+ "rent r INNER JOIN cars c ON c.idCars = r.idCars\r\n"
				+ "INNER JOIN person p ON p.idPerson = r.idPerson\r\n"
				+ "where r.active = '1'";
		try {
			mySession.beginTransaction();
			List<Object[]> rows = mySession.createSQLQuery(sql).list();
			mySession.getTransaction().commit();
			for(Object[] row : rows) {
				Rental rental = new Rental();
				Car car = new Car();
				Person person = new Person();
				rental.setCar(car);
				rental.setPerson(person);
				rental.setIdRent((Integer)row[0]);
				rental.setStart_date(row[1].toString());
				rental.setEnd_date(row[2].toString());
				rental.getCar().setLicense_plate(row[3].toString());
				rental.getCar().setModel(row[4].toString());
				rental.getPerson().setName(row[5].toString());
				rental.getPerson().setSurname(row[6].toString());
				activeRentalsList.add(rental);
			}

		}finally {
			mySession.close();
			myFactory.close();
		}
		return activeRentalsList;
	}
	
	@Override
	public List<Object> getInactiveRents(){
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addPackage("rental_car.model").addAnnotatedClass(Rental.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		List<Object> inactiveRentalsList = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		
		String sql = "select r.idRent, r.start_date, r.end_date, c.license_plate, c.model, p.name, p.surname from\r\n"
				+ "rent r INNER JOIN cars c ON c.idCars = r.idCars\r\n"
				+ "INNER JOIN person p ON p.idPerson = r.idPerson\r\n"
				+ "where r.active = '0' AND (r.start_date <= '"+currentDate+"' OR r.start_date >= '"+currentDate+"') AND r.end_date > '"+currentDate+"'";
		try {
			mySession.beginTransaction();
			List<Object[]> rows = mySession.createSQLQuery(sql).list();
			mySession.getTransaction().commit();
			for(Object[] row : rows) {
				Rental rental = new Rental();
				Car car = new Car();
				Person person = new Person();
				rental.setCar(car);
				rental.setPerson(person);
				rental.setIdRent((Integer)row[0]);
				rental.setStart_date(row[1].toString());
				rental.setEnd_date(row[2].toString());
				rental.getCar().setLicense_plate(row[3].toString());
				rental.getCar().setModel(row[4].toString());
				rental.getPerson().setName(row[5].toString());
				rental.getPerson().setSurname(row[6].toString());
				inactiveRentalsList.add(rental);
			}

		}finally {
			mySession.close();
			myFactory.close();
		}
		return inactiveRentalsList;
	}
	
	@Override
	public void updateRental(int idRent, String actionRental) {
		LocalDate currentDate = LocalDate.now();
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Rental.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		try {
			mySession.beginTransaction();
			Rental rental1 = mySession.get(Rental.class, idRent);
			switch(actionRental) {
				case "returnCar":
					rental1.setActive(0);
					rental1.setEnd_date(currentDate.toString());
					mySession.getTransaction().commit();
					break;
				case "activeRent":
					rental1.setActive(1);
					rental1.setStart_date(currentDate.toString());
					mySession.getTransaction().commit();
					break;
			}
		}
		finally {
			mySession.close();
			myFactory.close();
		}
	}

}
