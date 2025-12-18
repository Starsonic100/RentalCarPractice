package com.practice.rentalcar.customvalidations;

import java.lang.annotation.*;

import javax.validation.*;

@Constraint(validatedBy = CarTypeValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CarType {
	String message() default "Please select any option";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
