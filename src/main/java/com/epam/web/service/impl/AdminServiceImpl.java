package com.epam.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.dao.impl.ApplicantDaoImpl;
import com.epam.web.dao.impl.ApplicationDaoImpl;
import com.epam.web.dao.impl.StatusDaoImpl;
import com.epam.web.entity.ApplicantEntity;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.entity.StatusEntity;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.AdminService;
import static com.epam.web.command.ParameterProvider.*;

public class AdminServiceImpl implements AdminService {
	private static final Logger logger = LogManager.getLogger();
	private static AdminServiceImpl instance = new AdminServiceImpl();
	private ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
	private StatusDaoImpl statusDao = StatusDaoImpl.getInstance();
	private ApplicantDaoImpl applicantDao = ApplicantDaoImpl.getInstance();

	private AdminServiceImpl() {

	}

	public static AdminServiceImpl getInstance() {
		return instance;
	}

	@Override
	public List<ApplicationEntity> findUnpostedApplications() throws ServiceException {
		List<ApplicationEntity> applications = new ArrayList<>();
		try {
			applications = applicationDao.findUnposted();
		} catch (DaoException e) {
			logger.error("problem at AdminServiceImpl: findUnposted", e);
			throw new ServiceException(e);
		}
		return applications;
	}

	@Override
	public ApplicationEntity findById(int id) throws ServiceException {
		ApplicationEntity application = new ApplicationEntity();
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

	@Override
	public void updateApplication(ApplicationEntity application) throws ServiceException {
		try {
			if (application != null) {
				applicationDao.update(application);
			}
		} catch (DaoException e) {
			logger.error("Error at updateApplication: AdminServiceImpl", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int findPostedStatusId() throws ServiceException {
		StatusEntity status = new StatusEntity();
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

	public ApplicationEntity changeToPosted(ApplicationEntity application) throws ServiceException {
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

	@Override
	public int findVerifyingStatusId() throws ServiceException {
		StatusEntity status = new StatusEntity();
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

	@Override
	public ApplicationEntity changeToVerifying(ApplicationEntity application) throws ServiceException {
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

	@Override
	public boolean deleteApplication(int applicationId) throws ServiceException {
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

	@Override
	public List<ApplicationEntity> findPostedApplications() throws ServiceException {
		List<ApplicationEntity> applications = new ArrayList<>();
		try {
			applications = applicationDao.findPosted();
		} catch (DaoException e) {
			logger.error("problem at AdminServiceImpl: findPosted", e);
			throw new ServiceException(e);
		}
		return applications;

	}

	@Override
	public List<ApplicantEntity> findAllApplicants() throws ServiceException {
		List<ApplicantEntity> applicants = new ArrayList<>();
		try {
			applicants = applicantDao.findAll();
		} catch (DaoException e) {
			logger.error("Error occured by AdminServiceImpl: findAllApplicants", e);
			throw new ServiceException(e);
		}
		return applicants;
	}

	@Override
	public List<ApplicationEntity> findByOrganizationName(String name) throws ServiceException {
		List<ApplicationEntity> applications = new ArrayList<>();
		try {
			applications = applicationDao.findbyOrganizationName(name);
		}catch(DaoException e) {
			logger.error("Error occured by AdminServiceImpl: findByOrganizationName");
			throw new ServiceException(e);
		}
		return applications;
	}

}
