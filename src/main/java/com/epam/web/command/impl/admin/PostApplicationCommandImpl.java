package com.epam.web.command.impl.admin;

import static com.epam.web.command.ParameterProvider.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.command.Command;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.epam.web.command.PagePath.*;

public class PostApplicationCommandImpl implements Command {
	private static final Logger logger = LogManager.getLogger();
	private AdminServiceImpl service = AdminServiceImpl.getInstance();
	private GoToAdminMainPageImpl goToMain = new GoToAdminMainPageImpl();

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE).equals("admin")) {
			int applicationId = Integer.parseInt(request.getParameter(APPLICATION_ID));
			try {
				ApplicationEntity application = service.findById(applicationId);
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
