package com.epam.web.controller.command.impl.admin;

import static com.epam.web.controller.command.ParameterProvider.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.epam.web.controller.command.PagePath.*;

public class PostApplicationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private AdminServiceImpl service = AdminServiceImpl.getInstance();
	private GoToAdminMainPageCommand goToMain = new GoToAdminMainPageCommand();

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE).equals("admin")) {
			int applicationId = Integer.parseInt(request.getParameter(APPLICATION_ID));
			try {
				Application application = service.findById(applicationId);
				service.changeToPosted(application);
				return goToMain.execute(request);
			} catch (ServiceException e) {
				logger.error("PostApplicationCommand error", e);
				e.printStackTrace();
				return goToMain.execute(request);
			}
		} else {
			return MAIN_PAGE;
		}
	}

}
