package com.epam.web.controller.command.impl.admin;

import static com.epam.web.controller.command.ParameterProvider.*;
import static com.epam.web.controller.command.PagePath.*;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FindByApplicantCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		AdminServiceImpl service = AdminServiceImpl.getInstance();
		HttpSession session = request.getSession();
		String organizationName = request.getParameter(ORGANIZATION_FILTER);
		request.setAttribute(ORGANIZATION_SELECTED, organizationName);
		List<Application> applications = (List<Application>) session.getAttribute(UNPOSTED_APPLICATION_LIST);
		try {
			applications = service.findByOrganizationName(applications, organizationName);
			session.setAttribute(UNPOSTED_APPLICATION_LIST, applications);
		} catch (ServiceException e) {
			logger.error("Error caused by FindByApplicantCommandImpl", e);
		}
		return MAIN_ADMIN;
	}

}
