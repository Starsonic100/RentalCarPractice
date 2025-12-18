package com.practice.rentalcar.customvalidations;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class StartEndDateValidation implements ConstraintValidator<StartEndDateValues, Object>{
	
	private String startDate;
	private String endDate;

	public void initialize(StartEndDateValues constraintAnnotation) {
		this.startDate = constraintAnnotation.startDate();
		this.endDate = constraintAnnotation.endDate();
	}
	
	public boolean isValid(Object arg0, ConstraintValidatorContext context) {
		Object startDateObject = new BeanWrapperImpl(arg0).getPropertyValue(startDate);
		Object endDateObject =  new BeanWrapperImpl(arg0).getPropertyValue(endDate); 
		LocalDate currentDate = LocalDate.now();
		if(!startDateObject.toString().isEmpty()&&!endDateObject.toString().isEmpty()) {
			LocalDate startDateValue = LocalDate.parse(startDateObject.toString());
			LocalDate endDateValue = LocalDate.parse(endDateObject.toString());
	
			if((startDateValue.isAfter(currentDate) || startDateValue.equals(currentDate)) && startDateValue.isBefore(endDateValue)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}


	
}
