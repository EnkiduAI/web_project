package com.epam.web.controller.command.impl.admin;

import static com.epam.web.controller.command.PagePath.*;
import static com.epam.web.controller.command.ParameterProvider.*;
import com.epam.web.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToApplicantCreatePageCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE) != null && session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
			session.setAttribute(CURRENT_PAGE, APPLICANT_CREATE);
			return APPLICANT_CREATE;
		} else {
			return LOGIN_PAGE;
		}
	}

}
