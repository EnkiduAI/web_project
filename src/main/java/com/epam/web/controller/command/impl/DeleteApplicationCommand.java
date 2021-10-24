package com.epam.web.controller.command.impl;

import static com.epam.web.controller.command.ParameterProvider.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.impl.admin.GoToAdminMainPageCommand;
import com.epam.web.controller.command.impl.user.GoToUserMainCommand;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.epam.web.controller.command.PagePath.*;

public class DeleteApplicationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	@Override
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session.getAttribute(ROLE).equals(ADMIN_ROLE) || session.getAttribute(ROLE).equals(USER_ROLE)) {
			AdminServiceImpl service = AdminServiceImpl.getInstance();
			GoToAdminMainPageCommand goToMainAdmin = new GoToAdminMainPageCommand();
			GoToUserMainCommand goToUserMain = new GoToUserMainCommand();
			int applicationId = Integer.parseInt(request.getParameter(APPLICATION_ID));
			try {

				if (service.deleteApplication(applicationId)) {
					if(session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
						return goToMainAdmin.execute(request);
					}
					else if(session.getAttribute(ROLE).equals(USER_ROLE)) {
						return goToUserMain.execute(request);
					}else {
						return LOGIN_PAGE;
					}
					
				} else {
					logger.error("Application delete has been failed");
					if(session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
						return goToMainAdmin.execute(request);
					}
					else if(session.getAttribute(ROLE).equals(USER_ROLE)) {
						return goToUserMain.execute(request);
					}else {
						return LOGIN_PAGE;
					}
				}
			} catch (ServiceException e) {
				logger.error("Error occured at DeleteApplicationCommandImpl", e);
				if(session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
					return goToMainAdmin.execute(request);
				}
				else if(session.getAttribute(ROLE).equals(USER_ROLE)) {
					return goToUserMain.execute(request);
				}else {
					return LOGIN_PAGE;
				}
			}
		} else {
			return LOGIN_PAGE;
		}
	}
}
