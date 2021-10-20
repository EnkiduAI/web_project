package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.ParameterProvider;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.entity.ApplicationType;
import com.epam.web.model.entity.Status;
import com.epam.web.model.service.impl.AdminServiceImpl;
import com.epam.web.model.service.impl.UserServiceImpl;
import com.epam.web.model.util.security.PasswordEncryptor;

import static com.epam.web.controller.command.PagePath.*;
import static com.epam.web.controller.command.ParameterProvider.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		AdminServiceImpl adminService = AdminServiceImpl.getInstance();
		UserServiceImpl userService = UserServiceImpl.getInstance();
		PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
		Applicant applicant = new Applicant();
		HttpSession session = request.getSession();
		List<ApplicationType> types = new ArrayList<>();
		List<Status> status = new ArrayList<>();
		String page = null;
		String loginValue = request.getParameter(ParameterProvider.LOGIN);
		String passwordValue = request.getParameter(ParameterProvider.PASSWORD);

		try {
			applicant = userService.findOrganizationByLogin(loginValue);
			types = adminService.findAllTypes();
			status = adminService.findAllStatus();
		} catch (ServiceException e) {
			logger.error("Error caused by LoginCommand", e);
		}

		session.setAttribute(STATUS_LIST, status);
		session.setAttribute(TYPES_LIST, types);
		if (applicant.getLogin() != null) {
			if (request.getParameter(LOGIN) != null && applicant.getLogin().equals("Admin")) {
				if (applicant.getPassword() != null
						&& encryptor.checkPassword(passwordValue, applicant.getPassword())) {

					session.setAttribute(ROLE, ADMIN_ROLE);
					session.setAttribute(USER_LOGIN, loginValue);
					session.setAttribute(CURRENT_USER, applicant);
					List<Application> applications = new ArrayList<>();
					List<Applicant> applicants = new ArrayList<>();

					try {

						applications = adminService.findUnpostedApplications();
						applicants = adminService.findAllApplicants();

					} catch (ServiceException e) {

						logger.error("error during GoToMainAdminCommand", e);
					}

					session.setAttribute(UNPOSTED_APPLICATION_LIST, applications);
					session.setAttribute(APPLICANTS_LIST, applicants);
					session.setAttribute(CURRENT_PAGE, MAIN_ADMIN);

					page = MAIN_ADMIN;
				} else {
					page = LOGIN_PAGE;
				}

			} else if ((request.getParameter(LOGIN) != null || !applicant.getLogin().equals("Admin"))
					&& applicant.getLogin().equals(loginValue)) {
				if (applicant.getLogin() != null && encryptor.checkPassword(passwordValue, applicant.getPassword())) {
					session.setAttribute(ROLE, USER_ROLE);
					session.setAttribute(USER_LOGIN, loginValue);
					session.setAttribute(CURRENT_USER, applicant);

					List<Application> applications = new ArrayList<>();
					try {
						applications = userService.findAllUnpostedByApplicant(applicant);
					} catch (ServiceException e) {
						logger.error("Error occured at Login: findAllUnpostedByApplicant", e);
					}

					session.setAttribute(UNPOSTED_APPLICATION_LIST, applications);
					session.setAttribute(CURRENT_PAGE, MAIN_USER);

					page = MAIN_USER;
				} else {
					page = LOGIN_PAGE;
				}
			} else {
				page = LOGIN_PAGE;
			}
		} else {
			return page = LOGIN_PAGE;
		}

		return page;
	}

}
