package com.epam.web.command.impl.admin;


import static com.epam.web.command.ParameterProvider.*;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.command.Command;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.epam.web.command.PagePath.*;

public class UpadateApplicationCommandImpl implements Command {
private static final Logger logger = LogManager.getLogger();
private AdminServiceImpl service = AdminServiceImpl.getInstance();
private GoToAdminMainPageImpl goToMain = new GoToAdminMainPageImpl();
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE).equals("admin")) {
		try {
			ApplicationEntity application = (ApplicationEntity) session.getAttribute(CURRENT_APPLICATION);
			application.setName(request.getParameter(APPLICATION_NAME));
			application.setSurname(request.getParameter(APPLICATION_SURNAME));
			application.setTraits(request.getParameter(APPLICATION_TRAITS));
			application.setHeight(Integer.parseInt(request.getParameter(APPLICATION_HEIGHT)));
			application.setWeight(Integer.parseInt(request.getParameter(APPLICATION_WEIGHT)));
			application.setDescription(request.getParameter(APPLICATION_DESCRIPTION));
			application.setReward(Integer.parseInt(request.getParameter(APPLICATION_REWARD)));
			application.setExpirationDate(Date.valueOf(request.getParameter(APPLICATION_EXPDATE)));
			service.updateApplication(application);
			return goToMain.execute(request);
		} catch (ServiceException e) {
			logger.error("Application not updated",e);
		}
		return goToMain.execute(request);
		}else {
			return MAIN_PAGE;
		}
	}

}
