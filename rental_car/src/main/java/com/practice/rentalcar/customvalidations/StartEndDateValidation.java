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
		LocalDate startDateObject = (LocalDate) new BeanWrapperImpl(dateObject).getPropertyValue(startDate);
		LocalDate endDateObject = (LocalDate) new BeanWrapperImpl(dateObject).getPropertyValue(endDate); 
		if(startDateObject!=null&&endDateObject!=null) {
			return (startDateObject.isBefore(endDateObject)); 
		}
		return false;
	}


	
}
