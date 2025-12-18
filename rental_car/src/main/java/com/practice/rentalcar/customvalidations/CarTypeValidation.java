package com.practice.rentalcar.customvalidations;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarTypeValidation implements ConstraintValidator<CarType, String>{
	 @Override
	    public void initialize(CarType carType) {
	    }

	    @Override
	    public boolean isValid(String carType,
	      ConstraintValidatorContext cxt) {
	    	final String ECONOMIC = "Economic";
	    	final String STANDARD = "Standard";
	    	final String PREMIUM = "Premium";
	    	
	    	ArrayList<String> types = new ArrayList<>(Arrays.asList(ECONOMIC, STANDARD, PREMIUM));
	    	return types.contains(carType);
	    }

}
