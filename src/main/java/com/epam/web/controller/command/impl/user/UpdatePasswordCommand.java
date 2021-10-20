package com.epam.web.controller.command.impl.user;

import static com.epam.web.controller.command.PagePath.*;
import static com.epam.web.controller.command.ParameterProvider.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.controller.command.Command;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.service.impl.UserServiceImpl;
import com.epam.web.model.util.security.PasswordEncryptor;
import com.epam.web.model.validator.UserValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UpdatePasswordCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session.getAttribute(ROLE) != null && session.getAttribute(ROLE).equals(USER_ROLE)) {
			PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
			Applicant applicant = (Applicant) session.getAttribute(CURRENT_USER);
			UserServiceImpl service = UserServiceImpl.getInstance();
			UserValidator validator = UserValidator.getInstance();
			String password = request.getParameter(PASSWORD);
			String newPassword = (validator.checkPassword(request.getParameter(NEW_PASSWORD))
					? request.getParameter(NEW_PASSWORD)
					: "");
			String confirmPassword = request.getParameter(CONFIRM_PASSWORD);

			if (password != null && encryptor.checkPassword(password, applicant.getPassword())) {
				if (confirmPassword != null && newPassword != null && newPassword.equals(confirmPassword)) {
					applicant.setPassword(encryptor.encodePassword(newPassword));
					session.setAttribute(CURRENT_USER, applicant);
					try {
						service.updateProfileInfo(applicant);
					} catch (ServiceException e) {
						logger.error("Error ocurred by UpdatePasswordCommand", e);
						return USER_PASSWORD_CHANGE;
					}
				}

			} else {
				return USER_PASSWORD_CHANGE;
			}
		} else {
			return LOGIN_PAGE;
		}
		return USER_PROFILE;

	}

}
