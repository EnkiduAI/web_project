package com.epam.web.command.impl.admin;

import static com.epam.web.command.PagePath.*;
import static com.epam.web.command.ParameterProvider.*;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.command.Command;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class UpadateApplicationCommandImpl implements Command {
private static final Logger logger = LogManager.getLogger();
AdminServiceImpl service = AdminServiceImpl.getInstance();
	@Override
	public String execute(HttpServletRequest request) {
		String page = MAIN_ADMIN;
		HttpSession session = request.getSession();		
		try {
			ApplicationEntity application = (ApplicationEntity) session.getAttribute(CURRENT_APPLICATION);
			application.setName(request.getParameter(APPLICATION_NAME));
			application.setSurname(request.getParameter(APPLICATION_SURNAME));
			application.setTraits(request.getParameter(APPLICATION_TRAITS));
			application.setHeight(Integer.parseInt(request.getParameter(APPLICATION_HEIGHT)));
			application.setWeight(Integer.parseInt(request.getParameter(APPLICATION_WEIGHT)));
			application.setDescription(request.getParameter(APPLICATION_DESCRIPTION));
			application.setReward(Integer.parseInt(request.getParameter(APPLICATION_REWARD)));
			
			service.updateApplication(application);
			return page;
		} catch (ServiceException e) {
			logger.error("Application not updated",e);
		}
		return page;
	}

}
