package com.epam.web.command.impl.admin;
import static com.epam.web.command.ParameterProvider.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import static com.epam.web.command.PagePath.*;
public class DeleteApplicationCommandImpl implements Command{
private static final Logger logger = LogManager.getLogger();
private AdminServiceImpl service = AdminServiceImpl.getInstance();
private GoToAdminMainPageImpl goToMain = new GoToAdminMainPageImpl();
@Override
public String execute(HttpServletRequest request) {
	int applicationId = Integer.parseInt(request.getParameter(APPLICATION_ID));
	try {
		
		if(service.deleteApplication(applicationId)) {
			return goToMain.execute(request);
		}else {
			logger.error("Application delete has been failed");
			return goToMain.execute(request);
		}
	}catch(ServiceException e) {
		logger.error("Error occured at DeleteApplicationCommandImpl", e);
	}
	return goToMain.execute(request);
}
}
