package com.epam.web.command.impl;

import com.epam.web.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import static com.epam.web.command.PagePath.*;
public class GoToAdminMainPageImpl implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		return MAIN_ADMIN;
	}

}
