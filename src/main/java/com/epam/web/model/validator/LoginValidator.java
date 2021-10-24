package com.epam.web.model.validator;

public class LoginValidator {
private static final String LOGIN = "Admin";
private static final String PASSWORD = "1243";

public boolean isValid(String login, String password) {
	return LOGIN.equals(login) && PASSWORD.equals(password);
}
}
