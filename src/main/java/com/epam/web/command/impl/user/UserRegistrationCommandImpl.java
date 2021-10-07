package com.epam.web.command.impl.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.command.Command;
import com.epam.web.entity.UserEntity;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.util.security.PasswordEncryptor;
import com.epam.web.validator.UserValidator;
import static com.epam.web.command.ParameterProvider.*;
import static com.epam.web.command.PagePath.*;
import static com.epam.web.command.PagePath.CURRENT_PAGE;

import jakarta.servlet.http.HttpServletRequest;


public class UserRegistrationCommandImpl implements Command{
	
private static final Logger logger = LogManager.getLogger();
private UserServiceImpl service = UserServiceImpl.getInstance();
private UserEntity user = new UserEntity();
private boolean veryfied = true;
	@Override
	public String execute(HttpServletRequest request) {
		String page = request.getParameter(CURRENT_PAGE).substring(34);
		String email = request.getParameter(USER_EMAIL);
		String name = request.getParameter(USER_NAME);
		String surname = request.getParameter(USER_SURNAME);
		String login = request.getParameter(USER_LOGIN);
		String password = request.getParameter(USER_PASSWORD);
		String phone = request.getParameter(USER_PHONE);
		
		if(UserValidator.checkEmail(email)) {
			user.setEmail(email);
			if(UserValidator.checkName(name)) {
				user.setName(name);
				if(UserValidator.checkSurname(surname)) {
					user.setSurname(surname);
					if(UserValidator.checkLogin(login)) {
						user.setLogin(login);
						if(UserValidator.checkPassword(password)) {
							user.setPassword(PasswordEncryptor.encodePassword(password));
							if(UserValidator.checkPhone(phone)) {
								user.setPhone(phone);
							}else {
								veryfied = false;
								logger.error("Wrong registration data due to User Registration");
							}
						}
					}
				}
			}
		}
		
		if(veryfied) {
			try {
				if(service.userSignUp(user)) {
					 page = USER_CREATION_SUCCESS;
					return page;
				}
			} catch (ServiceException e) {
				logger.error("User creation failed", e);
				return page;
			}
		}
		return page;
	}

}
