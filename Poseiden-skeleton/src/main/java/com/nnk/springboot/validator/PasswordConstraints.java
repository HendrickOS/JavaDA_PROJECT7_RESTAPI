package com.nnk.springboot.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.nnk.springboot.domain.User;

@Documented
@Constraint(validatedBy = User.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraints {

	String message() default "Invalid password";

}
