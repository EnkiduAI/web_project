package com.epam.web.controller.command.impl.user;
import static com.epam.web.controller.command.ParameterProvider.*;
import static com.epam.web.controller.command.PagePath.*;
import com.epam.web.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToLoginChangeCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute(ROLE) != null || session.getAttribute(ROLE).equals(USER_ROLE)) {
			session.setAttribute(CURRENT_PAGE, USER_LOGIN_CHANGE);
			return USER_LOGIN_CHANGE;
		}else {
			return LOGIN_PAGE;
		}
	}

}
