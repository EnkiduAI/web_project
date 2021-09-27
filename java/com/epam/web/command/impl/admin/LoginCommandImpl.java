package com.epam.web.command.impl.admin;

import com.epam.web.command.Command;
import com.epam.web.command.ParameterProvider;
import com.epam.web.validator.LoginValidator;
import com.epam.web.command.PagePath;

import jakarta.servlet.http.HttpServletRequest;

public class LoginCommandImpl implements Command{
private LoginValidator validator = new LoginValidator();

@Override
public String execute(HttpServletRequest request) {
	String page = null;
	String loginValue = request.getParameter(ParameterProvider.LOGIN);
	String passwordValue = request.getParameter(ParameterProvider.PASSWORD);
	if(validator.isValid(loginValue, passwordValue)) {
		page = PagePath.MAIN_ADMIN;
	}else {
		page = PagePath.LOGIN_ADMIN;
	}
	return page;
}


}
