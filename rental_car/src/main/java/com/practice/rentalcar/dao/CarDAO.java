package com.practice.rentalcar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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
	public Car getSelectedCar(int idCar) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Car.class, idCar);
	}
	
	@Override
	public List<Car> getFilteredCars(String startDate, String endDate, String type, String sortCars){
		Session session = sessionFactory.getCurrentSession();
		String querySelect = "select distinct c from cars c LEFT JOIN rent r ON c.id = r.idCar where ((r.startDate NOT BETWEEN :startDate AND :endDate ) AND (r.endDate NOT BETWEEN :startDate AND :endDate )) AND (r.idCar IS NULL OR r.active = 0) AND c.type = :type ORDER BY c.price ";
		Query query = session.createQuery(querySelect+sortCars);
		query.setString("startDate", startDate.toString());
		query.setString("endDate", endDate.toString());
		query.setString("type", type);
		return query.getResultList();
	}
}
