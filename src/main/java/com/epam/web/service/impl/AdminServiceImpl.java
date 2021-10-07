package com.epam.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.dao.impl.ApplicationDaoImpl;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.AdminService;

public class AdminServiceImpl implements AdminService{
	private static final Logger logger = LogManager.getLogger();
	private static AdminServiceImpl instance = new AdminServiceImpl();
	
	private AdminServiceImpl() {
		
	}
	
	public static AdminServiceImpl getInstance() {
		return instance;
	}
	
	ApplicationDaoImpl applicationDao = ApplicationDaoImpl.getInstance();
	
	@Override
	public List<ApplicationEntity> findUnpostedApplications() throws ServiceException {
		List<ApplicationEntity> applications = new ArrayList<>();
		try {
			applications = applicationDao.findUnposted();
		}catch(DaoException e) {
			logger.error("problem at AdminServiceImpl: findUnposted", e);
			throw new ServiceException(e);
		}
		return applications;
	}

	@Override
	public ApplicationEntity findById(int id) throws ServiceException {
		ApplicationEntity application = new ApplicationEntity();
		try {
			application = applicationDao.findById(id);
		}catch (DaoException e) {
			logger.error("problem at AdminServiceImpl: findById", e);
			throw new ServiceException(e);
		}
		return application;
	}

	@Override
	public void updateApplication(ApplicationEntity application) throws ServiceException {
		try {
			if(application != null) {
				 applicationDao.update(application);
			}
		}catch (DaoException e) {
			logger.error("Error at updateApplication: AdminServiceImpl", e);
			throw new ServiceException(e);
		}
	}

}
