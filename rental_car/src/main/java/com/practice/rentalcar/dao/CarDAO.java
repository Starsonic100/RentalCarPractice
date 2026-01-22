package com.practice.rentalcar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.practice.rentalcar.model.Car;
import com.practice.rentalcar.model.Person;
import com.practice.rentalcar.model.Rental;

@Repository("carDAO")
public class CarDAO implements ICarDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }//setSessionFactory
	
	@Override
	public Car getSelectedCar(int idCar) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Car.class, idCar);
	}
	
	@Override
	public List<Car> getFilteredCars(LocalDate startDate, LocalDate endDate, String type, String namedQuery){
		Session session = sessionFactory.getCurrentSession();
		Query <Car> query = session.createNamedQuery(namedQuery, Car.class);
		query.setParameter("startDate", startDate.toString());
		query.setParameter("endDate", endDate.toString());
		query.setParameter("type", type);
		return query.getResultList();
	}
}
