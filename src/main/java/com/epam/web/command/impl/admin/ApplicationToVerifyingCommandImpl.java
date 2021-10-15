package com.epam.web.command.impl.admin;

import static com.epam.web.command.ParameterProvider.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.command.Command;
import com.epam.web.command.impl.GoToMainPageCommandImpl;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

public class ApplicationToVerifyingCommandImpl implements Command {
	private static final Logger logger = LogManager.getLogger();
	private AdminServiceImpl service = AdminServiceImpl.getInstance();
	private GoToAdminMainPageImpl goToMainAdmin = new GoToAdminMainPageImpl();
	private GoToMainPageCommandImpl goToMainPage = new GoToMainPageCommandImpl();

	@Override
	public String execute(HttpServletRequest request) {
		int applicationId = Integer.parseInt(request.getParameter(APPLICATION_ID));
		try {
			ApplicationEntity application = service.findById(applicationId);
			service.changeToVerifying(application);
			return goToMainAdmin.execute(request);
		} catch (ServiceException e) {
			logger.error("Error at ApplicationToVerifyingCommandImpl",e);
			e.printStackTrace();
			return goToMainPage.execute(request);
		}
		
	}
}
