package com.epam.web.controller.command.impl.user;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.service.impl.UserServiceImpl;
import com.epam.web.model.validator.UserValidator;

import static com.epam.web.controller.command.ParameterProvider.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.web.controller.command.PagePath.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UpdateLoginCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session.getAttribute(ROLE) != null && session.getAttribute(ROLE).equals(USER_ROLE)) {
			Applicant applicant = (Applicant) session.getAttribute(CURRENT_USER);
			UserServiceImpl service = UserServiceImpl.getInstance();
			UserValidator validator = UserValidator.getInstance();
			String login = request.getParameter(LOGIN);
			String newLogin = (validator.checkLogin(request.getParameter(NEW_LOGIN)) ? request.getParameter(NEW_LOGIN)
					: "");
			String confirmLogin = request.getParameter(CONFIRM_LOGIN);

			if (login != null && login.equals(applicant.getLogin())) {
				if (confirmLogin != null && newLogin != null && newLogin.equals(confirmLogin)) {
					applicant.setLogin(newLogin);
					session.setAttribute(CURRENT_USER, applicant);
					try {
						service.updateProfileInfo(applicant);
					} catch (ServiceException e) {
						logger.error("Error ocurred by UpdateLoginCommand", e);
						return USER_LOGIN_CHANGE;
					}
				} else {
					return USER_LOGIN_CHANGE;
				}

			} else {
				return USER_LOGIN_CHANGE;
			}
		} else {
			return LOGIN_PAGE;
		}
		return USER_PROFILE;
	}

}
