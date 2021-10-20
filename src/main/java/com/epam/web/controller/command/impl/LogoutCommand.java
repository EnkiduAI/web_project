package com.epam.web.controller.command.impl;



import com.epam.web.controller.command.Command;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {
private GoToMainPageCommand returnCommand = new GoToMainPageCommand();
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession seesion = request.getSession();
		request.changeSessionId();
		seesion.invalidate();
		return returnCommand.execute(request);
	}

}
