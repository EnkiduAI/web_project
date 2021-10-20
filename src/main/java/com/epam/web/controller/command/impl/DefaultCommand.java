package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import static com.epam.web.controller.command.PagePath.*;

public class DefaultCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		
		return ERROR_PAGE;
	}

}
