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
	
	public boolean isValid(Object dateObject, ConstraintValidatorContext context) {
		Object startDateObject = new BeanWrapperImpl(dateObject).getPropertyValue(startDate);
		Object endDateObject =  new BeanWrapperImpl(dateObject).getPropertyValue(endDate); 
		LocalDate currentDate = LocalDate.now();
		if((!startDateObject.toString().isEmpty()||startDateObject.toString()!=null)&&(!endDateObject.toString().isEmpty()||endDateObject.toString()!=null)) {
			LocalDate startDateValue = LocalDate.parse(startDateObject.toString());
			LocalDate endDateValue = LocalDate.parse(endDateObject.toString());
			return ((startDateValue.isAfter(currentDate) || startDateValue.equals(currentDate)) && startDateValue.isBefore(endDateValue)); 
		}
		return false;
	}


	
}
