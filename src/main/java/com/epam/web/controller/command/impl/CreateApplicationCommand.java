package com.epam.web.controller.command.impl;

import static com.epam.web.controller.command.ParameterProvider.*;
import java.sql.Date;
import java.util.List;

import static com.epam.web.controller.command.PagePath.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.impl.admin.GoToAdminMainPageCommand;
import com.epam.web.controller.command.impl.user.GoToUserMainCommand;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CreateApplicationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute(ROLE).equals(ADMIN_ROLE) || session.getAttribute(ROLE).equals(USER_ROLE)) {
			
		AdminServiceImpl service = AdminServiceImpl.getInstance();
		Applicant applicant = (Applicant) session.getAttribute(CURRENT_USER);
		Application application = (Application) session.getAttribute(CURRENT_APPLICATION);
		GoToAdminMainPageCommand goToMainAdmin = new GoToAdminMainPageCommand();
		GoToUserMainCommand goToMainUser = new GoToUserMainCommand();
		request.setAttribute(TYPE_SELECTED, TYPE_FILTER);
		int applicantId = applicant.getId();

		application = buildApplication(request, session);
		application.setApplicantId(applicantId);
		try {

			service.changeToVerifying(application);
			if (service.createApplication(application)) {
				if (session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
					return goToMainAdmin.execute(request);
				}else if(session.getAttribute(ROLE).equals(USER_ROLE)) {
					return goToMainUser.execute(request);
				}else {
					return LOGIN_PAGE;
				}
			} else {
				logger.error("User has been not created!");
				return APPLICANT_CREATE;
			}

		} catch (ServiceException e) {
			logger.error("Error occured at CreateApplicationCommandImpl");
			return APPLICANT_CREATE;
		}
		
		}else {
			return LOGIN_PAGE;
		}

	}

	private Application buildApplication(HttpServletRequest request, HttpSession session) {
		return new Application.ApplicationBuilder()
				.setTypeId(Integer.parseInt(request.getParameter(TYPE_FILTER)))
				.setName(request.getParameter(APPLICATION_NAME)).setSurname(request.getParameter(APPLICATION_SURNAME))
				.setTraits(request.getParameter(APPLICATION_TRAITS)).setWeight(Integer.parseInt(request.getParameter(APPLICATION_WEIGHT)))
				.setHeight(Integer.parseInt(request.getParameter(APPLICATION_HEIGHT)))
				.setDescription(request.getParameter(APPLICATION_DESCRIPTION))
				.setReward(Integer.parseInt(request.getParameter(APPLICATION_REWARD)))
				.setExpirationDate(Date.valueOf(request.getParameter(APPLICATION_EXPDATE))).build();
		
	}

}
