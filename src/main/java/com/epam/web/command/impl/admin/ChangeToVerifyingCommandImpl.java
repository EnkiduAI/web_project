package com.epam.web.command.impl.admin;
import static com.epam.web.command.ParameterProvider.*;
import static com.epam.web.command.PagePath.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.command.Command;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeToVerifyingCommandImpl implements Command{
private static final Logger logger = LogManager.getLogger();
private AdminServiceImpl service = AdminServiceImpl.getInstance();
@Override
public String execute(HttpServletRequest request) {
	HttpSession session = request.getSession();
	List<ApplicationEntity> applications = new ArrayList<>();
	try {
		applications = service.findUnpostedApplications();
		session.setAttribute(UNPOSTED_APPLICATION_LIST, applications);
	}catch (ServiceException e) {
		logger.error("Error caused by ChangeToVerifyingCommandImpl method",e);
		return MAIN_ADMIN;
	}
	return MAIN_ADMIN;
}
}
