package com.epam.web.controller.command.impl.admin;

import com.epam.web.controller.command.Command;
import static com.epam.web.controller.command.ParameterProvider.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.epam.web.controller.command.PagePath.*;

public class GoToSettingNewImageCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute(ROLE).equals(ADMIN_ROLE) && session.getAttribute(ROLE).equals(USER_ROLE)) {
			return IMAGE_EDIT;
		}else {
			return LOGIN_PAGE;
		}
		

	}
}
