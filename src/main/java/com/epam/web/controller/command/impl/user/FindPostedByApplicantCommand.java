package com.epam.web.controller.command.impl.user;

import static com.epam.web.controller.command.PagePath.*;
import static com.epam.web.controller.command.ParameterProvider.*;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FindPostedByApplicantCommand implements Command{
private static final Logger logger = LogManager.getLogger();
	@Override
	public String execute(HttpServletRequest request) {
		UserServiceImpl userService = UserServiceImpl.getInstance();
		HttpSession session = request.getSession();
		Applicant applicant = (Applicant) session.getAttribute(CURRENT_USER);
		if(session.getAttribute(ROLE).equals(USER_ROLE)){
			List<Application> applications = new ArrayList<>();
			try {
				applications = userService.findAllPostedByApplicant(applicant);
			}catch (ServiceException e) {
				logger.error("Error occured at Login: findAllUnpostedByApplicant", e);
			}
			session.setAttribute(UNPOSTED_APPLICATION_LIST, applications);
		}else {
			return LOGIN_PAGE;
		}
		return MAIN_USER;
		
	}

}
