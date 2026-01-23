package com.practice.rentalcar.customvalidations;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = StartEndDateValidation.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface StartEndDateValues {
	String message() default "Please insert a valid End Date set after Start Date";
	String startDate();
	String endDate();
	
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
