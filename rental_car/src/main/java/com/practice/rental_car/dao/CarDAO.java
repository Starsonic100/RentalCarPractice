package com.practice.rental_car.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.practice.rental_car.model.Car;
import com.practice.rental_car.model.Person;
import com.practice.rental_car.model.Rental;

@Repository("carDAO")
public class CarDAO implements ICarDAO {


	class CarMapper implements RowMapper<Car>{
		@Override
		public Car mapRow(ResultSet rs, int rowNum) throws SQLException{
				// TODO Auto-generated method stub
				Car car = new Car();
				car.setIdCars(rs.getInt("idCars"));
				car.setLicense_plate(rs.getString("license_plate"));
				car.setModel(rs.getString("model"));
				car.setType(rs.getString("type"));
				car.setPrice(rs.getInt("price"));
				car.setAvailable(rs.getInt("available"));
				return car;
		}
	}

	
	@Override
	public List<Car> getAllCars(){
		List<Car> carList = new ArrayList<>();
		
		String sql = "select c.idCars, c.license_plate, c.model, c.type, c.price from cars c";
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Car.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		try {
			mySession.beginTransaction();
			carList = mySession.createQuery(sql).list();
			mySession.getTransaction().commit();
		}finally {
			mySession.close();
			myFactory.close();
		}
		return carList;

	}
	
	@Override
	public Car getSelectedCar(int idCars) {
		Car car1 = new Car();
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Car.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		try {
			mySession.beginTransaction();
			car1 = (Car) mySession.createQuery("from cars where idCars="+idCars).uniqueResult();
		}finally {
			mySession.close();
			myFactory.close();
		}
		return car1;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Car> getFilteredCars(String startDate, String endDate, String type, int sortCars){
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addPackage("rental_car.model")
				.addAnnotatedClass(Car.class).addAnnotatedClass(Rental.class).addAnnotatedClass(Person.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
		List<Car> carList = new ArrayList<>();
		String order = null;
		switch(sortCars) {
			case 1:
				order = "ASC";
				break;
			case 0:
				order = "DESC";
				break;
		}
		
		String sql = "select c.idCars, c.license_plate, c.model, c.type, c.price from cars c"
				+ " LEFT JOIN rent r ON c.idCars = r.idCars"
				+ " where ((r.start_date NOT BETWEEN CAST('"+startDate+"' AS DATE) AND CAST('"+endDate+"' AS DATE))"
				+ " AND (r.end_date NOT BETWEEN CAST('"+startDate+"' AS DATE) AND CAST('"+endDate+"' AS DATE))) AND (r.idCars IS NULL OR r.active = 0) "
				+ " AND type = '"+type+"'"
				+" ORDER BY c.price "+order;
		try {
			mySession.beginTransaction();
			List<Object[]> rows = mySession.createSQLQuery(sql).list();
			mySession.getTransaction().commit();
			for(Object[] row : rows) {
				Car car = new Car();
				car.setIdCars((Integer)row[0]);
				car.setLicense_plate(row[1].toString());
				car.setModel(row[2].toString());
				car.setType(row[3].toString());
				car.setPrice((Integer)row[4]);
				carList.add(car);
			}

		}finally {
			mySession.close();
			myFactory.close();
		}
		return carList;
	}
}
