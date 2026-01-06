package com.practice.rentalcar.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

@Repository("rentalDAO")
public class RentalDAO implements IRentalDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }//setSessionFactory

	@Override
	public int addRental(Rental rental) {
		int idRental;
		int active = 0;
		LocalDate currentDate = LocalDate.now();
		if(currentDate.toString().equals(rental.getStartDate())) {
			active = 1;
		}
		Session mySession = sessionFactory.getCurrentSession();
		mySession.createCriteria(Rental.class);
			Rental newRent = new Rental(rental.getStartDate(), rental.getEndDate(), rental.getIdCar(), rental.getIdPerson(), active);
			mySession.save(newRent);
			idRental = newRent.getIdRent();
		
		return idRental;
	}
	
	@Override
	public Rental getSelectedRental(int idRent) {
		Rental selectedRental = new Rental();
		Session mySession = sessionFactory.getCurrentSession();
		mySession.createCriteria(Rental.class);	
		System.out.println(idRent);		
		selectedRental = (Rental) mySession.createQuery("from rent where idRent="+idRent).uniqueResult();

		return selectedRental;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object> getActiveRents(){
		Session mySession = sessionFactory.getCurrentSession();
		mySession.createCriteria(Rental.class);		List<Object> activeRentalsList = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		
		String sql = "select r.idRent, r.start_date, r.end_date, c.license_plate, c.model, p.name, p.surname from\r\n"
				+ "rent r INNER JOIN cars c ON c.idCars = r.idCars\r\n"
				+ "INNER JOIN person p ON p.idPerson = r.idPerson\r\n"
				+ "where r.active = '1'";
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
				rental.setStartDate(row[1].toString());
				rental.setEndDate(row[2].toString());
				rental.getCar().setLicensePlate(row[3].toString());
				rental.getCar().setModel(row[4].toString());
				rental.getPerson().setName(row[5].toString());
				rental.getPerson().setSurname(row[6].toString());
				activeRentalsList.add(rental);
			}


		return activeRentalsList;
	}
	
	@Override
	public List<Object> getInactiveRents(){
		Session mySession = sessionFactory.getCurrentSession();
		mySession.createCriteria(Rental.class);
		List<Object> inactiveRentalsList = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		
		String sql = "select r.idRent, r.start_date, r.end_date, c.license_plate, c.model, p.name, p.surname from\r\n"
				+ "rent r INNER JOIN cars c ON c.idCars = r.idCars\r\n"
				+ "INNER JOIN person p ON p.idPerson = r.idPerson\r\n"
				+ "where r.active = '0' AND (r.start_date <= '"+currentDate+"' OR r.start_date >= '"+currentDate+"') AND r.end_date > '"+currentDate+"'";
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
				rental.setStartDate(row[1].toString());
				rental.setEndDate(row[2].toString());
				rental.getCar().setLicensePlate(row[3].toString());
				rental.getCar().setModel(row[4].toString());
				rental.getPerson().setName(row[5].toString());
				rental.getPerson().setSurname(row[6].toString());
				inactiveRentalsList.add(rental);
			}
		return inactiveRentalsList;
	}
	
	@Override
	public void updateRental(int idRent, int actionRental) {
		LocalDate currentDate = LocalDate.now();
		Session mySession = sessionFactory.getCurrentSession();
		mySession.createCriteria(Rental.class);	
			mySession.beginTransaction();
			Rental selectedRental = mySession.get(Rental.class, idRent);
			selectedRental.setActive(actionRental);
			if(actionRental == 1) selectedRental.setStartDate(currentDate.toString()); else selectedRental.setEndDate(currentDate.toString());
			mySession.getTransaction().commit();
	}

}
