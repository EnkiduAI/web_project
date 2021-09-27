package com.epam.web.command.impl.user;

import com.epam.web.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import static com.epam.web.command.PagePath.*;

public class GoToUserRegistrationPageCommandImpl implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		
		return USER_REGISTRATION;
	}

}
