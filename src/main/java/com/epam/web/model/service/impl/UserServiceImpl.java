package com.epam.web.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.dao.impl.ApplicantDaoImpl;
import com.epam.web.model.dao.impl.ApplicationDaoImpl;
import com.epam.web.model.dao.impl.UserDaoImpl;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.entity.User;
import com.epam.web.model.service.UserService;
import com.epam.web.model.util.security.PasswordEncryptor;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private static UserServiceImpl instance = new UserServiceImpl();

	private UserServiceImpl() {

	}

	public static UserServiceImpl getInstance() {
		return instance;
	}

	@Override
	public Optional<User> userLogin(String login, String password) throws ServiceException {
		PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
		UserDaoImpl userDao = UserDaoImpl.getInstance();
		try {
			Optional<User> user = userDao.findByLogin(login);
			if (user.isPresent()) {
				String userPassword = user.get().getPassword();
				return (encryptor.checkPassword(password, userPassword) ? user : Optional.empty());
			} else {
				return Optional.empty();
			}
		} catch (DaoException e) {
			logger.error("Cannot find user with such login", e);
			throw new ServiceException("Cannot find user with such login", e);
		}

	}

	public boolean userSignUp(User user) throws ServiceException {
		UserDaoImpl userDao = UserDaoImpl.getInstance();
		try {
			return (userDao.create(user) ? true : false);
		} catch (DaoException e) {
			logger.error("Something wrong: userSignIn - UserServiceImpl", e);
			throw new ServiceException("Something wrong: userSignIn - UserServiceImpl", e);
		}

	}

	public List<Application> findAllApplications() throws ServiceException {
		List<Application> applications = new ArrayList<>();
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		try {
			applications = applicationDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("findAllApplications at UserServiceImpl error", e);
		}
		return applications;
	}

	@Override
	public List<Application> findAllPosted(String status) throws ServiceException {
		List<Application> applications = new ArrayList<>();
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		try {
			applications = applicationDao.findbyStatus(status);
		} catch (DaoException e) {
			logger.error("error at findAllPosted method at UserServiceImpl");
			throw new ServiceException("error at findAllPosted method at UserServiceImpl", e);
		}
		return applications;
	}

	@Override
	public Applicant findOrganizationByLogin(String name) throws ServiceException {
		ApplicantDaoImpl applicantDao = ApplicantDaoImpl.getInstance();
		Applicant applicant = new Applicant();
		try {
			applicant = applicantDao.findOrganizationByLogin(name);
		} catch (DaoException e) {
			logger.error("Error occured by UserServiceImpl: findOrganizationByLogin", e);
			throw new ServiceException(e);
		}
		return applicant;
	}

	@Override
	public List<Application> findAllUnpostedByApplicant(Applicant applicant) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		List<Application> applications = new ArrayList<>();
		try {
			applications = applicationDao.findUnpostedByOrganizationName(applicant);	
		}catch(DaoException e) {
			logger.error("Error ocurred by UserServiceImpl: findAllUnpostedByApplicant");
			throw new ServiceException(e);
		}
		return applications;
	}

	@Override
	public List<Application> findAllPostedByApplicant(Applicant applicant) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		List<Application> applications = new ArrayList<>();
		try {
			applications = applicationDao.findPostedByOrganizationName(applicant);	
		}catch(DaoException e) {
			logger.error("Error ocurred by UserServiceImpl: findAllPostedByApplicant");
			throw new ServiceException(e);
		}
		return applications;
	}

	@Override
	public void updateProfileInfo(Applicant applicant) throws ServiceException {
		ApplicantDaoImpl applicantDao = ApplicantDaoImpl.getInstance();
		try {
			applicantDao.update(applicant);
		}catch(DaoException e) {
			logger.error("Error occured by UserServiceImpl: updateProfileInfo", e);
			throw new ServiceException(e);
		}
		
	}

}
