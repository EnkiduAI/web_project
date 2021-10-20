package com.epam.web.controller.command.impl.user;
import static com.epam.web.controller.command.PagePath.*;
import static com.epam.web.controller.command.ParameterProvider.*;
import com.epam.web.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToPasswordChangeCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute(ROLE)!=null && session.getAttribute(ROLE).equals(USER_ROLE)) {
			session.setAttribute(CURRENT_PAGE, USER_PASSWORD_CHANGE);
			return USER_PASSWORD_CHANGE;
		}else {
			return LOGIN_PAGE;
		}
	}

}
