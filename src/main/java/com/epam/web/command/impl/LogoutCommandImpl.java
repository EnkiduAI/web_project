package com.epam.web.command.impl;

import com.epam.web.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutCommandImpl implements Command {
private GoToMainPageCommandImpl returnCommand = new GoToMainPageCommandImpl();
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession seesion = request.getSession();
		seesion.invalidate();
		return returnCommand.execute(request);
	}

}
