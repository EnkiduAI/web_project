package com.epam.web.controller.command.impl.user;

import static com.epam.web.controller.command.PagePath.*;
import static com.epam.web.controller.command.ParameterProvider.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.service.impl.UserServiceImpl;
import com.epam.web.model.validator.UserValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class EditProfileCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ROLE) != null && session.getAttribute(ROLE).equals(USER_ROLE)) {
			
			UserValidator validator = UserValidator.getInstance();
			GoToUserMainCommand goToUserMain = new GoToUserMainCommand();
			UserServiceImpl service = UserServiceImpl.getInstance();
			Applicant applicant = (Applicant) session.getAttribute(CURRENT_USER);
			
			String email = (validator.checkEmail(request.getParameter(USER_EMAIL)) ? request.getParameter(USER_EMAIL) : "");
			String phone = (validator.checkPhone(request.getParameter(USER_PHONE)) ? request.getParameter(USER_PHONE) : "");
			
			if(email.equals("") || phone.equals("")) {
				return USER_PROFILE;
			}
			
			applicant.setEmail(email);
			applicant.setPhone(phone);
			
			session.setAttribute(CURRENT_USER, applicant);
			
			try {
				service.updateProfileInfo(applicant);
			}catch (ServiceException e) {
				logger.error("Error ocurred by EditProfileCommand", e);
				return USER_PROFILE;
			}
			
			return goToUserMain.execute(request);
			
		} else {
			return LOGIN_PAGE;
		}
	}

}
