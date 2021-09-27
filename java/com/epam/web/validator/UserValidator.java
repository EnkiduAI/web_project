package com.epam.web.validator;

public class UserValidator {
	private static final String NAME_REGEX = "^[a-zA-ZА-Яа-я]{5,15}$";
	private static final String SURNAME_REGEX = "^[a-zA-ZА-Яа-я]{5,15}$";
	private static final String LOGIN_REGEX = "^[A-Za-z0-9_]{5,17}$";
	private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	private static final String EMAIL_REGEX = "^(?=.{3,30}$)[^\\\\s]+@[^\\\\s]+\\\\.[^\\\\s]+$";
	private static final String PHONE_REGEX = "^\\\\+\\\\d{12}$";
	
	private static UserValidator instance = new UserValidator();
	
	private UserValidator() {
		
	}
	
	public static UserValidator getInstance() {
		return instance;
	}

	public static boolean checkName(String name) {
		return name.matches(NAME_REGEX);
	}
	
	public static boolean checkSurname(String surname) {
		return surname.matches(SURNAME_REGEX);
	}
	
	public static boolean checkLogin(String login) {
		return login.matches(LOGIN_REGEX);
	}
	
	public static boolean checkPassword(String password) {
		return password.matches(PASSWORD_REGEX);
	}
	
	public static boolean checkEmail(String email) {
		return email.matches(EMAIL_REGEX);
	}
	
	public static boolean checkPhone(String phone) {
		return phone.matches(PHONE_REGEX);
	}
}
