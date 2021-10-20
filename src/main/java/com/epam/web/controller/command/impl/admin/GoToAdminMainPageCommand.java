package com.epam.web.controller.command.impl.admin;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.entity.ApplicationType;
import com.epam.web.model.service.impl.AdminServiceImpl;

import static com.epam.web.controller.command.ParameterProvider.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.epam.web.controller.command.PagePath.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToAdminMainPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
			session.setAttribute(CURRENT_PAGE, MAIN_ADMIN);
			AdminServiceImpl adminService = AdminServiceImpl.getInstance();

			List<Application> applications = new ArrayList<>();
			List<Applicant> applicants = new ArrayList<>();
			List<ApplicationType> types = new ArrayList<>();
			try {
				applications = adminService.findUnpostedApplications();
				applicants = adminService.findAllApplicants();
				types = adminService.findAllTypes();
			} catch (ServiceException e) {
				logger.error("error during GoToMainAdminCommand", e);
			}
			session.setAttribute(UNPOSTED_APPLICATION_LIST, applications);
			session.setAttribute(APPLICANTS_LIST, applicants);
			session.setAttribute(TYPES_LIST, types);
			return MAIN_ADMIN;
		} else {
			session.setAttribute(CURRENT_PAGE, MAIN_PAGE);
			return LOGIN_PAGE;
		}
	}

}
