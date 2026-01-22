package com.practice.rentalcar.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//import org.hibernate.qu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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
		Session session = sessionFactory.getCurrentSession();
		session.save(rental);
		return rental.getIdRent();		
	}
	
	@Override
	public Rental getSelectedRental(int idRent) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Rental.class, idRent);
	}
	

	public List<Rental> getActiveRents(){
		Session session = sessionFactory.getCurrentSession();
		Query<Rental> query = session.createNamedQuery("GET_ACTIVE_RENTS",Rental.class);
		return query.getResultList();
	}
	
	@Override
	public List<Rental> getInactiveRents(){
		Session session = sessionFactory.getCurrentSession();
		LocalDate currentDate = LocalDate.now();
		Query<Rental> query = session.createNamedQuery("GET_INACTIVE_RENTS",Rental.class);
		query.setParameter("currentDate", currentDate);
		return query.getResultList();
	}
	
	@Override
	public void updateRental(Rental rental) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(rental);
	}

}
