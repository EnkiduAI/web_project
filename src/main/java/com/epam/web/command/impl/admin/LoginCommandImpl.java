package com.epam.web.command.impl.admin;

import com.epam.web.command.Command;
import com.epam.web.command.ParameterProvider;
import com.epam.web.entity.ApplicantEntity;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.entity.ApplicationTypeEntity;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;
import com.epam.web.validator.LoginValidator;

import com.epam.web.command.PagePath;
import static com.epam.web.command.ParameterProvider.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommandImpl implements Command {
	private static final Logger logger = LogManager.getLogger();
	private LoginValidator validator = new LoginValidator();
	AdminServiceImpl adminService = AdminServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String page = null;
		String loginValue = request.getParameter(ParameterProvider.LOGIN);

		String passwordValue = request.getParameter(ParameterProvider.PASSWORD);
		if (validator.isValid(loginValue, passwordValue)) {

			session.setAttribute(ROLE, ADMIN_ROLE);
			session.setAttribute(USER_LOGIN, loginValue);

			List<ApplicationEntity> applications = new ArrayList<>();
			List<ApplicantEntity> applicants = new ArrayList<>();
			List<ApplicationTypeEntity> types = new ArrayList<>();
			try {

				applications = adminService.findUnpostedApplications();
				applicants = adminService.findAllApplicants();

			} catch (ServiceException e) {

				logger.error("error during GoToMainAdminCommand", e);
			}

			session.setAttribute(UNPOSTED_APPLICATION_LIST, applications);
			session.setAttribute(APPLICANTS_LIST, applicants);
			session.setAttribute(TYPES_LIST, types);
			page = PagePath.MAIN_ADMIN;

		} else {
			page = PagePath.LOGIN_ADMIN;
		}
		return page;
	}

}
