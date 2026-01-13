package com.practice.rentalcar.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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
		selectedRental = (Rental) mySession.createCriteria(Rental.class).add(Restrictions.like("idRent", idRent)).uniqueResult();

		return selectedRental;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object> getActiveRents(){
		Session mySession = sessionFactory.getCurrentSession();
		List<Object> activeRentalsList = new ArrayList<>();
		Query query = mySession.createQuery("from rent r, cars c, person p where r.active = '1' AND r.idCar = c.id AND r.idPerson = p.id");
			List<Object[]> rows = query.list();
			for(Object[] row : rows) {
				Rental rental = (Rental) row[0];
				Car car = (Car) row[1];
				Person person = (Person) row[2];
				rental.setCar(car);
				rental.setPerson(person);
				rental.getCar().setLicensePlate(car.getLicensePlate());
				rental.getCar().setModel(car.getModel());
				rental.getPerson().setName(person.getName());
				rental.getPerson().setSurname(person.getSurname());
				activeRentalsList.add(rental);
			}
		return activeRentalsList;
	}
	
	@Override
	public List<Object> getInactiveRents(){
		Session mySession = sessionFactory.getCurrentSession();
		List<Object> inactiveRentalsList = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		Query query = mySession.createQuery("from rent r, cars c, person p where r.active = '0' AND (r.startDate <= :currentDate OR r.startDate >= :currentDate) AND r.endDate > :currentDate  AND r.idCar = c.id AND r.idPerson = p.id");
		query.setString("currentDate", currentDate.toString());
			List<Object[]> rows = query.list();
			for(Object[] row : rows) {
				Rental rental = (Rental) row[0];
				Car car = (Car) row[1];
				Person person = (Person) row[2];
				rental.setCar(car);
				rental.setPerson(person);
				rental.getCar().setLicensePlate(car.getLicensePlate());
				rental.getCar().setModel(car.getModel());
				rental.getPerson().setName(person.getName());
				rental.getPerson().setSurname(person.getSurname());
				inactiveRentalsList.add(rental);
			}
		return inactiveRentalsList;
	}
	
	@Override
	public void updateRental(int idRent, int actionRental) {
		LocalDate currentDate = LocalDate.now();
		Session mySession = sessionFactory.getCurrentSession();
		Rental selectedRental = mySession.get(Rental.class, idRent);
		selectedRental.setActive(actionRental);
		if(actionRental == 1) selectedRental.setStartDate(currentDate.toString()); else selectedRental.setEndDate(currentDate.toString());
		mySession.saveOrUpdate(selectedRental);
	}

}
