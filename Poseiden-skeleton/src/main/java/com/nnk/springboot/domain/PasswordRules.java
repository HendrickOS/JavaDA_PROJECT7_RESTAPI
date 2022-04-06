package com.nnk.springboot.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nnk.springboot.validator.PasswordValidator;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordRules {

//	Valider la saisie, en particulier pour les champs numériques (comprenant uniquement des chiffres) et 
//	pour les mots de passe (au moins une lettre majuscule, au moins 8 caractères, au moins un chiffre et un symbole)

	String message() default "Invalid password (>=8 char, at least 1 special char, 1 uppercase and 1 digit)";

	Class<?>[] groups() default {};

	public abstract Class<? extends Payload>[] payload() default {};

}
