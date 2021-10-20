package com.epam.web.controller.command.impl;

import static com.epam.web.controller.command.ParameterProvider.*;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.impl.admin.GoToAdminMainPageCommand;
import com.epam.web.controller.command.impl.user.GoToUserMainCommand;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.epam.web.controller.command.PagePath.*;

public class UpadateApplicationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		AdminServiceImpl service = AdminServiceImpl.getInstance();
		GoToAdminMainPageCommand goToMainAdmin = new GoToAdminMainPageCommand();
		GoToUserMainCommand goToUserMain = new GoToUserMainCommand();
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE).equals(ADMIN_ROLE) || session.getAttribute(ROLE).equals(USER_ROLE)) {
			try {
				Application application = (Application) session.getAttribute(CURRENT_APPLICATION);
				application.setName(request.getParameter(APPLICATION_NAME));
				application.setSurname(request.getParameter(APPLICATION_SURNAME));
				application.setTraits(request.getParameter(APPLICATION_TRAITS));
				application.setHeight(Integer.parseInt(request.getParameter(APPLICATION_HEIGHT)));
				application.setWeight(Integer.parseInt(request.getParameter(APPLICATION_WEIGHT)));
				application.setDescription(request.getParameter(APPLICATION_DESCRIPTION));
				application.setReward(Integer.parseInt(request.getParameter(APPLICATION_REWARD)));
				application.setExpirationDate(Date.valueOf(request.getParameter(APPLICATION_EXPDATE)));
				service.updateApplication(application);

				if (session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
					return goToMainAdmin.execute(request);
				}
				if (session.getAttribute(ROLE).equals(USER_ROLE)) {
					return goToUserMain.execute(request);
				}else {
					return LOGIN_PAGE;
				}

			} catch (ServiceException e) {
				logger.error("Application not updated", e);
				if (session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
					return MAIN_ADMIN;
				}
				if (session.getAttribute(ROLE).equals(USER_ROLE)) {
					return MAIN_USER;
				}
				else {
					return LOGIN_PAGE;
				}
			}
		} else {
			return LOGIN_PAGE;
		}
	}

}
