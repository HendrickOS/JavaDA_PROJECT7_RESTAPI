package com.nnk.springboot.validator;

import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraints, String> {

	@Override
	public void initialize(PasswordConstraints passwordConstraints) {

	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext cxt) {
		boolean majExist = false;
		boolean digitExist = false;
		ArrayList<Character> specialCharacters = new ArrayList<Character>();
		specialCharacters.add('?');
		specialCharacters.add('!');
		specialCharacters.add('/');
		specialCharacters.add('-');
		specialCharacters.add('_');
		boolean specialCharacterExist = false;
		for (char c : password) {
			if (Character.isUpperCase(c)) {
				majExist = true;
			} else {
				System.out.print("Votre mot de passe ne contient pas de majuscule");
			}
			if (Character.isDigit(c)) {
				digitExist = true;
			} else {
				System.out.print("Votre mot de passe ne contient pas de chiffre");
			}
			for (Character i : specialCharacters) {
				if (i.equals(c)) {
					specialCharacterExist = true;
				}
			}
			return majExist && digitExist && specialCharacterExist;
		}
	}

}
