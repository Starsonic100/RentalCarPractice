package com.practice.rentalcar.dao;

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

	class CarMapper implements RowMapper<Car>{
		@Override
		public Car mapRow(ResultSet rs, int rowNum) throws SQLException{
				// TODO Auto-generated method stub
				Car car = new Car();
				car.setIdCar(rs.getInt("idCars"));
				car.setLicensePlate(rs.getString("license_plate"));
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
		Session mySession = sessionFactory.getCurrentSession();
		mySession.createCriteria(Car.class);
			mySession.beginTransaction();
			carList = mySession.createQuery(sql).list();
			mySession.getTransaction().commit();

		return carList;

	}
	
	@Override
	public Car getSelectedCar(int idCar) {
		Car selectedCar = new Car();
		Session mySession = sessionFactory.getCurrentSession();
		mySession.createCriteria(Car.class);
		selectedCar = (Car) mySession.createQuery("from cars where id="+idCar).uniqueResult();
		
		return selectedCar;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Car> getFilteredCars(String startDate, String endDate, String type, int sortCars){
		Session mySession = sessionFactory.getCurrentSession();
		mySession.createCriteria(Car.class);
		mySession.createCriteria(Rental.class);
		mySession.createCriteria(Person.class);
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
			mySession.beginTransaction();
			List<Object[]> rows = mySession.createSQLQuery(sql).list();
			mySession.getTransaction().commit();
			for(Object[] row : rows) {
				Car car = new Car();
				car.setIdCar((Integer)row[0]);
				car.setLicensePlate(row[1].toString());
				car.setModel(row[2].toString());
				car.setType(row[3].toString());
				car.setPrice((Integer)row[4]);
				carList.add(car);
			}

		return carList;
	}
}
