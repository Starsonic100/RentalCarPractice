package com.practice.rental_car.customValidations;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = StartEndDateValidation.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface StartEndDateValues {
	String message() default "Please insert a start date that is equal or after current date and an end date that is after the start date";
	String startDate();
	String endDate();
	
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
