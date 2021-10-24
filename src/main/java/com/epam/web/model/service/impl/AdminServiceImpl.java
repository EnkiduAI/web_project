package com.epam.web.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.model.dao.impl.ApplicantDaoImpl;
import com.epam.web.model.dao.impl.ApplicationDaoImpl;
import com.epam.web.model.dao.impl.ApplicationTypeDaoImpl;
import com.epam.web.model.dao.impl.StatusDaoImpl;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.entity.ApplicationType;
import com.epam.web.model.entity.Status;
import com.epam.web.model.service.AdminService;

import static com.epam.web.controller.command.ParameterProvider.*;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminServiceImpl.
 */
public class AdminServiceImpl implements AdminService {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The instance. */
	private static AdminServiceImpl instance = new AdminServiceImpl();

	/**
	 * Instantiates a new admin service impl.
	 */
	private AdminServiceImpl() {

	}

	/**
	 * Gets the single instance of AdminServiceImpl.
	 *
	 * @return single instance of AdminServiceImpl
	 */
	public static AdminServiceImpl getInstance() {
		return instance;
	}

	/**
	 * Find unposted applications.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Application> findUnpostedApplications() throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		List<Application> applications = new ArrayList<>();
		try {
			applications = applicationDao.findUnposted();
		} catch (DaoException e) {
			logger.error("problem at AdminServiceImpl: findUnposted", e);
			throw new ServiceException(e);
		}
		return applications;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the application
	 * @throws ServiceException the service exception
	 */
	@Override
	public Application findById(int id) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		Application application = new Application();
		if (id != 0) {
			try {
				application = applicationDao.findById(id);
			} catch (DaoException e) {
				logger.error("problem at AdminServiceImpl: findById", e);
				throw new ServiceException(e);
			}
			return application;
		} else {
			logger.error("Entered at AdminServiceImpl: findById id is null");
			return application;
		}
	}

	/**
	 * Update application.
	 *
	 * @param application the application
	 * @throws ServiceException the service exception
	 */
	@Override
	public void updateApplication(Application application) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		try {
			if (application != null) {
				applicationDao.update(application);
			}
		} catch (DaoException e) {
			logger.error("Error at updateApplication: AdminServiceImpl", e);
			throw new ServiceException(e);
		}
	}

	/**
	 * Find posted status id.
	 *
	 * @return the int
	 * @throws ServiceException the service exception
	 */
	@Override
	public int findPostedStatusId() throws ServiceException {
		StatusDaoImpl statusDao = StatusDaoImpl.getInstance();
		Status status = new Status();
		int id;
		try {
			status = statusDao.findByStatus(STATUS_POSTED);
			id = status.getStatusId();
		} catch (DaoException e) {
			logger.error("Error caused by AdminServiceImpl: findPostedStatusId", e);
			throw new ServiceException(e);
		}
		return id;
	}

	/**
	 * Change to posted.
	 *
	 * @param application the application
	 * @return the application
	 * @throws ServiceException the service exception
	 */
	public Application changeToPosted(Application application) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		if (application != null) {
			try {
				int postedId = findPostedStatusId();
				application.setStatusId(postedId);
				applicationDao.update(application);
				return application;
			} catch (DaoException e) {
				logger.error("Error caused by AdminServiceImpl: changeToPosted", e);
				throw new ServiceException(e);
			}
		} else {
			logger.error("Application is null!");
			return application;
		}

	}

	/**
	 * Find verifying status id.
	 *
	 * @return the int
	 * @throws ServiceException the service exception
	 */
	@Override
	public int findVerifyingStatusId() throws ServiceException {
		StatusDaoImpl statusDao = StatusDaoImpl.getInstance();
		Status status = new Status();
		int id;
		try {
			status = statusDao.findByStatus(STATUS_VERIFYING);
			id = status.getStatusId();
		} catch (DaoException e) {
			logger.error("Error caused by AdminServiceImpl: findPostedStatusId", e);
			throw new ServiceException(e);
		}
		return id;
	}

	/**
	 * Change to verifying.
	 *
	 * @param application the application
	 * @return the application
	 * @throws ServiceException the service exception
	 */
	@Override
	public Application changeToVerifying(Application application) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		if (application != null) {
			try {
				int verifyingId = findVerifyingStatusId();
				application.setStatusId(verifyingId);
				applicationDao.update(application);
				return application;
			} catch (DaoException e) {
				logger.error("Error caused by AdminServiceImpl: changeToPosted", e);
				throw new ServiceException(e);
			}
		} else {
			logger.error("Application is null!");
			return application;
		}
	}

	/**
	 * Delete application.
	 *
	 * @param applicationId the application id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean deleteApplication(int applicationId) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		try {
			if (applicationDao.delete(applicationId)) {
				return true;
			} else {
				return false;
			}
		} catch (DaoException e) {
			logger.error("Error occured at AdminServiceImpl: deleteApplication", e);
			throw new ServiceException(e);
		}
	}

	/**
	 * Find posted applications.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Application> findPostedApplications() throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		List<Application> applications = new ArrayList<>();
		try {
			applications = applicationDao.findPosted();
		} catch (DaoException e) {
			logger.error("problem at AdminServiceImpl: findPosted", e);
			throw new ServiceException(e);
		}
		return applications;

	}

	/**
	 * Find all applicants.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Applicant> findAllApplicants() throws ServiceException {
		ApplicantDaoImpl applicantDao = ApplicantDaoImpl.getInstance();
		List<Applicant> applicants = new ArrayList<>();
		try {
			applicants = applicantDao.findAll();
		} catch (DaoException e) {
			logger.error("Error occured by AdminServiceImpl: findAllApplicants", e);
			throw new ServiceException(e);
		}
		return applicants;
	}

	/**
	 * Find by organization name.
	 *
	 * @param applications the applications
	 * @param name the name
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Application> findByOrganizationName(List<Application> applications, String name)
			throws ServiceException {
		ApplicantDaoImpl applicationDao = ApplicantDaoImpl.getInstance();
		Applicant applicant = new Applicant();
		List<Application> newApplicationList = new ArrayList<>();
		try {
			applicant = applicationDao.findByOrganizationName(name);
		} catch (DaoException e) {
			logger.error("Error occured by AdminServiceImpl: findByOrganizationName");
			throw new ServiceException(e);
		}

		for (Application application : applications) {
			if (application.getApplicantId() == applicant.getId()) {
				newApplicationList.add(application);
			}
		}
		return newApplicationList;
	}

	/**
	 * Find all types.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<ApplicationType> findAllTypes() throws ServiceException {
		ApplicationTypeDaoImpl typeDao = ApplicationTypeDaoImpl.getInstance();
		List<ApplicationType> types = new ArrayList<>();
		try {
			types = typeDao.findAll();
		} catch (DaoException e) {
			logger.error("Error occured at AdminServiceImpl: findAllTypes", e);
			throw new ServiceException(e);
		}

		return types;
	}

	/**
	 * Creates the applicant.
	 *
	 * @param applicant the applicant
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean createApplicant(Applicant applicant) throws ServiceException {
		ApplicantDaoImpl applicantDao = ApplicantDaoImpl.getInstance();
		try {
			return applicantDao.create(applicant);
		} catch (DaoException e) {
			logger.error("Error occured by AdminServiceImpl: createApplicant", e);
			throw new ServiceException(e);
		}

	}

	/**
	 * Delete applicant.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean deleteApplicant(int id) throws ServiceException {
		ApplicantDaoImpl applicantDao = ApplicantDaoImpl.getInstance();
		try {
			return applicantDao.delete(id);
		} catch (DaoException e) {
			logger.error("Error occured by AdminServiceImpl: deleteApplicant", e);
			throw new ServiceException(e);
		}

	}

	/**
	 * Delete application by applicant id.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean deleteApplicationByApplicantId(int id) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		try {
			return applicationDao.deleteApplicationByApplicantId(id);
		} catch (DaoException e) {
			logger.error("Error occured by AdminServiceImpl: deleteApplicationByApplicationId", e);
			throw new ServiceException(e);
		}

	}

	/**
	 * Find all status.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Status> findAllStatus() throws ServiceException {
		List<Status> status = new ArrayList<>();
		StatusDaoImpl statusDao = StatusDaoImpl.getInstance();
		try {
			status = statusDao.findAll();
		} catch (DaoException e) {
			logger.error("Error occured by AdminServiceImpl: findAllStatus", e);
			throw new ServiceException(e);
		}
		return status;
	}

	/**
	 * Creates the application.
	 *
	 * @param application the application
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean createApplication(Application application) throws ServiceException {
		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
		try {
			return applicationDao.create(application);
		} catch (DaoException e) {
			logger.error("Error occured at AdminServiceImpl: createApplication", e);
			throw new ServiceException(e);
		}
	}

}
