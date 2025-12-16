package com.practice.rental_car.customValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarTypeValidation implements ConstraintValidator<CarType, String>{
	 @Override
	    public void initialize(CarType carType) {
	    }

	    @Override
	    public boolean isValid(String carType,
	      ConstraintValidatorContext cxt) {
	    	if(carType.equals("Economic")||carType.equals("Standard")||carType.equals("Premium")) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	        
	    }

}
