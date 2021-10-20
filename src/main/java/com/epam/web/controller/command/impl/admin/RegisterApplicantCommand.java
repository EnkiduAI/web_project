package com.epam.web.controller.command.impl.admin;
import static com.epam.web.controller.command.PagePath.*;
import static com.epam.web.controller.command.ParameterProvider.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.service.impl.AdminServiceImpl;
import com.epam.web.model.util.security.PasswordEncryptor;
import com.epam.web.model.validator.UserValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RegisterApplicantCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(ROLE) != null && session.getAttribute(ROLE).equals(ADMIN_ROLE)) {
			
		List<Applicant> applicants = new ArrayList<>();
		GoToUserManagmentCommand goToUserManagment = new GoToUserManagmentCommand();
		PasswordEncryptor enryptor = PasswordEncryptor.getInstance();
		UserValidator validator = UserValidator.getInstance();
		Applicant applicant = new Applicant();
		AdminServiceImpl service = AdminServiceImpl.getInstance();

		boolean flag = false;
		String organizationName = request.getParameter(APPLICANT_NAME);
		String login = request.getParameter(APPLICANT_LOGIN);
		String password = request.getParameter(APPLICANT_PASSWORD);
		String email = request.getParameter(APPLICANT_EMAIL);
		String phone = request.getParameter(APPLICANT_PHONE);

		if (validator.checkName(organizationName)) {
			applicant.setOrganizationName(organizationName);
			if (validator.checkLogin(login)) {
				applicant.setLogin(login);
				if (validator.checkPassword(password)) {
					password = enryptor.encodePassword(password);
					applicant.setPassword(password);
					if (validator.checkEmail(email)) {
						applicant.setEmail(email);
						if (validator.checkPhone(phone)) {
							applicant.setPhone(phone);
							flag = true;
						}
					}
				}
			}
		}
		if (flag) {
			try {
				if(service.createApplicant(applicant)) {
					applicants = (List<Applicant>) session.getAttribute(APPLICANTS_LIST);
					applicants.add(applicant);
					session.setAttribute(APPLICANTS_LIST, applicants);
					return goToUserManagment.execute(request);
				}
			} catch (ServiceException e) {
				logger.error("Error occured by RegisterApplicantCommandImpl", e);
				return APPLICANT_CREATE;
			}
		}else {
			return APPLICANT_CREATE;
		}
		return APPLICANT_CREATE;
		}
		else {
			return LOGIN_PAGE;
		}
		
	}

}
