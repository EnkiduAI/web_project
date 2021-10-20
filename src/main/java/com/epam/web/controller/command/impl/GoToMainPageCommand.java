package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.impl.UserServiceImpl;

import static com.epam.web.controller.command.ParameterProvider.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.web.controller.command.PagePath.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToMainPageCommand implements Command{
private static final Logger logger = LogManager.getLogger();
UserServiceImpl service = UserServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		String page = MAIN_PAGE;
		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_PAGE, MAIN_PAGE);
		try {
			List<Application> applications = service.findAllPosted("POSTED");
			session.setAttribute(POSTED_APPLICATION_LIST, applications);
		} catch (ServiceException e) {
			logger.error("GoToMainPageCommandImpl error", e);
		}
		
		
		return page;
	}

}
