package com.epam.web.controller.command.impl;
import static com.epam.web.controller.command.PagePath.*;
import static com.epam.web.controller.command.ParameterProvider.*;
import com.epam.web.controller.command.Command;
import com.epam.web.model.entity.Application;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToCreateApplicationCommand implements Command{
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute(ROLE).equals(ADMIN_ROLE) || session.getAttribute(ROLE).equals(USER_ROLE)) {
		Application application = new Application();
		session.setAttribute(CURRENT_APPLICATION, application);
		session.setAttribute(CURRENT_PAGE, APPLICATION_CREATE);
		return APPLICATION_CREATE;
		}else {
			return LOGIN_PAGE;
		}
	}

}
