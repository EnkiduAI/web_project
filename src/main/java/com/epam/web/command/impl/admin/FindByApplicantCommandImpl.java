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

public class FindByApplicantCommandImpl implements Command{
private static final Logger logger = LogManager.getLogger();
private AdminServiceImpl service = AdminServiceImpl.getInstance();
private GoToAdminMainPageImpl goToMain = new GoToAdminMainPageImpl();
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String organizationName = request.getParameter(APPLICANT_NAME);
		request.setAttribute(ORGANIZATION_SELECTED, organizationName);
		List<ApplicationEntity> applications = new ArrayList<>();
		try {
			applications = service.findByOrganizationName(organizationName);
			session.setAttribute(UNPOSTED_APPLICATION_LIST, applications);
		}catch(ServiceException e) {
			logger.error("Error caused by FindByApplicantCommandImpl", e);
		}
		return MAIN_ADMIN;
	}

}
