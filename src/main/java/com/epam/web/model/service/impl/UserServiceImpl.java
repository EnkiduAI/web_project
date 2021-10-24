package com.epam.web.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.dao.impl.ApplicantDaoImpl;
import com.epam.web.model.dao.impl.ApplicationDaoImpl;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
public class UserServiceImpl implements UserService {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The instance. */
	private static UserServiceImpl instance = new UserServiceImpl();

	/**
	 * Instantiates a new user service impl.
	 */
	private UserServiceImpl() {

	}

	/**
	 * Gets the single instance of UserServiceImpl.
	 *
	 * @return single instance of UserServiceImpl
	 */
	public static UserServiceImpl getInstance() {
		return instance;
	}


	/**
	 * Find all applications.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Find all posted.
	 *
	 * @param status the status
	 * @return the list
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Find organization by login.
	 *
	 * @param name the name
	 * @return the applicant
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Find all unposted by applicant.
	 *
	 * @param applicant the applicant
	 * @return the list
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Find all posted by applicant.
	 *
	 * @param applicant the applicant
	 * @return the list
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Update profile info.
	 *
	 * @param applicant the applicant
	 * @throws ServiceException the service exception
	 */
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
