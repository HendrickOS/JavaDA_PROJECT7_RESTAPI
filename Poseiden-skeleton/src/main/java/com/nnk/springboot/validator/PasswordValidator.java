package com.nnk.springboot.validator;

import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nnk.springboot.domain.PasswordRules;
import com.nnk.springboot.domain.User;

public class PasswordValidator implements ConstraintValidator<PasswordRules, User> {

	@Override
	public void initialize(PasswordRules passwordRules) {

	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext cxt) {
		boolean majExist = false;
		boolean digitExist = false;
		ArrayList<Character> specialCharacters = new ArrayList<Character>();
		specialCharacters.add('?');
		specialCharacters.add('!');
		specialCharacters.add('/');
		specialCharacters.add('-');
		specialCharacters.add('_');
		boolean specialCharacterExist = false;
		String password = user.getPassword();
		int i;
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
