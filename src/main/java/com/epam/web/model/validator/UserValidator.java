package com.epam.web.model.validator;

public class UserValidator {
	private static final String NAME_REGEX = "^(?![\\s.]+$)[a-zA-Z\\s.]*$";
	private static final String SURNAME_REGEX = "^[a-zA-ZА-Яа-я]{5,15}$";
	private static final String LOGIN_REGEX = "^[A-Za-z0-9_]{5,17}$";
	private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	private static final String EMAIL_REGEX = "^(.+)@(.+)$";
	private static final String PHONE_REGEX = "^[0-9\\+]{1,}[0-9\\-]{3,15}$";
	
	private static UserValidator instance = new UserValidator();
	
	private UserValidator() {
		
	}
	
	public static UserValidator getInstance() {
		return instance;
	}

	public boolean checkName(String name) {
		return name.matches(NAME_REGEX);
	}
	
	public boolean checkSurname(String surname) {
		return surname.matches(SURNAME_REGEX);
	}
	
	public boolean checkLogin(String login) {
		return login.matches(LOGIN_REGEX);
	}
	
	public boolean checkPassword(String password) {
		return password.matches(PASSWORD_REGEX);
	}
	
	public boolean checkEmail(String email) {
		return email.matches(EMAIL_REGEX);
	}
	
	public boolean checkPhone(String phone) {
		return phone.matches(PHONE_REGEX);
	}
}
