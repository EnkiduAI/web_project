package com.epam.web.controller.command.impl.admin;

import static com.epam.web.controller.command.ParameterProvider.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.impl.GoToMainPageCommand;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

public class ApplicationToVerifyingCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private AdminServiceImpl service = AdminServiceImpl.getInstance();
	private GoToAdminMainPageCommand goToMainAdmin = new GoToAdminMainPageCommand();
	private GoToMainPageCommand goToMainPage = new GoToMainPageCommand();

	@Override
	public String execute(HttpServletRequest request) {
		int applicationId = Integer.parseInt(request.getParameter(APPLICATION_ID));
		try {
			Application application = service.findById(applicationId);
			service.changeToVerifying(application);
			return goToMainAdmin.execute(request);
		} catch (ServiceException e) {
			logger.error("Error at ApplicationToVerifyingCommandImpl",e);
			e.printStackTrace();
			return goToMainPage.execute(request);
		}
		
	}
}
