package com.epam.web.controller.command.impl.admin;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.epam.web.controller.command.ParameterProvider.*;
import static com.epam.web.controller.command.PagePath.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToEditApplicationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	AdminServiceImpl service = AdminServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE).equals(ADMIN_ROLE) || session.getAttribute(ROLE).equals(USER_ROLE)) {
			String page = EDIT_APPLICATION;
			int id = Integer.parseInt(request.getParameter(APPLICATION_ID));
			Application application = new Application();
			try {
				application = service.findById(id);
				session.setAttribute(CURRENT_APPLICATION, application);
				session.setAttribute(CURRENT_PAGE, EDIT_APPLICATION);
			} catch (ServiceException e) {
				logger.error(e);
			}
			return page;
		} else {
			return LOGIN_PAGE;
		}

	}

}
