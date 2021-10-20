package com.epam.web.controller.command.impl.admin;
import static com.epam.web.controller.command.ParameterProvider.*;
import static com.epam.web.controller.command.PagePath.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DeleteApplicantCommand implements Command{
private static final Logger logger = LogManager.getLogger();
	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute(ROLE).equals(ADMIN_ROLE)){
			
		List<Applicant> applicants = new ArrayList<>();
		AdminServiceImpl service = AdminServiceImpl.getInstance();
		int id = Integer.parseInt(request.getParameter(APPLICANT_ID));
		try {
			service.deleteApplicationByApplicantId(id);
			service.deleteApplicant(id);
			applicants = service.findAllApplicants();
		}catch(ServiceException e) {
			logger.error("Error occured by DeleteApplicantCommandImpl", e);
			return USER_MANAGMENT;
		}
		
		session.setAttribute(APPLICANTS_LIST, applicants);
		return USER_MANAGMENT;
		}else {
			return LOGIN_PAGE;
		}
	}

}
