package com.epam.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.dao.impl.ApplicationDaoImpl;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.entity.UserEntity;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.util.security.PasswordEncryptor;


public class UserServiceImpl implements UserService{
	private static final Logger logger = LogManager.getLogger();
	private static UserServiceImpl instance = new UserServiceImpl();
	UserDaoImpl userDao = UserDaoImpl.getInstance();
	ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
	
	
	private UserServiceImpl() {
		
	}
	
	public static UserServiceImpl getInstance() {
		return instance;
	}

	@Override
	public Optional<UserEntity> userLogin(String login, String password)throws ServiceException {
		try {
			Optional<UserEntity> user = userDao.findByLogin(login);
			if(user.isPresent()) {
				String userPassword = user.get().getPassword();
				return(PasswordEncryptor.checkPassword(password, userPassword) ? user : Optional.empty());
			}
			else {
				return Optional.empty();
			}
		} catch (DaoException e) {
			logger.error("Cannot find user with such login", e);
			throw new ServiceException("Cannot find user with such login", e);
		}
		
	}
	
	public boolean userSignUp(UserEntity user) throws ServiceException{
		try {
			return (userDao.create(user) ? true : false);
		}catch(DaoException e) {
			logger.error("Something wrong: userSignIn - UserServiceImpl" , e);
			throw new ServiceException("Something wrong: userSignIn - UserServiceImpl" ,e);
		}
		
	}
	
	public List<ApplicationEntity> findAllApplications() throws ServiceException{
		List<ApplicationEntity> applications = new ArrayList<>();
		try {
			applications = applicationDao.findAll();
		}catch(DaoException e) {
			throw new ServiceException("findAllApplications at UserServiceImpl error", e);
		}
		return applications;
	}

	@Override
	public List<ApplicationEntity> findAllPosted(String status) throws ServiceException {
		List<ApplicationEntity> applications = new ArrayList<>();
		try {
			applications = applicationDao.findbyStatus(status);
		}catch(DaoException e) {
			logger.error("error at findAllPosted method at UserServiceImpl");
			throw new ServiceException("error at findAllPosted method at UserServiceImpl", e);		
		}
		return applications;
	}

}
