package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import static com.epam.web.controller.command.PagePath.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToLoginPageCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_PAGE, LOGIN_PAGE);
		return LOGIN_PAGE;
	}

}
