package com.epam.web.command.impl.admin;

import com.epam.web.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.epam.web.command.PagePath.*;
import static com.epam.web.command.ParameterProvider.ROLE;

public class GoToSettingNewImageCommandImpl implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE).equals("admin")) {
		return IMAGE_EDIT;
		}else {
			return MAIN_PAGE;
		}
	}

}
