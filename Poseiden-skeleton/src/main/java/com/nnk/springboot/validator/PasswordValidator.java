package com.nnk.springboot.validator;

import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nnk.springboot.domain.PasswordRules;
import com.nnk.springboot.domain.User;

public class PasswordValidator implements ConstraintValidator<PasswordRules, User> {

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
		int i; // Parcourir mon password
		int n; // Parcourir ma liste de caractères spéciaux
		boolean passwordSize = false;
		for (i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				majExist = true;
			}
			if (Character.isDigit(password.charAt(i))) {
				digitExist = true;
			}
			for (n = 0; n < specialCharacters.size(); n++) {
				if (password.charAt(i) == specialCharacters.get(n)) {
					specialCharacterExist = true;
				}
			}
		}
		if (password.length() >= 8) {
			passwordSize = true;
		}
		if (!majExist) {
			System.out.print("Votre mot de passe ne contient pas de majuscule\n");
		}
		if (!digitExist) {
			System.out.print("Votre mot de passe ne contient pas de chiffre\n");
		}
		if (!specialCharacterExist) {
			System.out.print("Votre mot de passe ne contient pas de caractères spéciaux\n");
		}
		if (!passwordSize) {
			System.out.print("Votre mot de passe contient moins de 8 caractères\n");
		}
		return majExist && digitExist && specialCharacterExist && passwordSize;
	}

	@Override
	public void initialize(PasswordRules passwordRules) {

	}

}
