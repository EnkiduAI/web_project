package com.epam.web.command.impl.admin;

import com.epam.web.command.Command;
import static com.epam.web.command.PagePath.*;

import jakarta.servlet.http.HttpServletRequest;

public class GoToAdminLoginPageCommandImpl implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		return LOGIN_ADMIN;
	}

}
