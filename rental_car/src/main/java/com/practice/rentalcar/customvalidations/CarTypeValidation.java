package com.practice.rentalcar.customvalidations;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import static com.practice.rentalcar.constants.IConstants.*;

public class CarTypeValidation implements ConstraintValidator<CarType, String>{
	 @Override
	    public void initialize(CarType carType) {
	    }

	    @Override
	    public boolean isValid(String carType,
	      ConstraintValidatorContext cxt) {
	    	ArrayList<String> types = new ArrayList<>(Arrays.asList(ECONOMIC, STANDARD, PREMIUM));
	    	return types.contains(carType);
	    }

}
