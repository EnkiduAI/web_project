package com.epam.web.service;

import java.util.List;

import com.epam.web.entity.ApplicantEntity;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.ServiceException;

public interface AdminService {
	List<ApplicationEntity> findUnpostedApplications() throws ServiceException;
	
	List<ApplicationEntity> findPostedApplications() throws ServiceException;
	
	List<ApplicationEntity> findByOrganizationName(String name) throws ServiceException;
	
	List<ApplicantEntity> findAllApplicants() throws ServiceException;

	ApplicationEntity findById(int id) throws ServiceException;

	void updateApplication(ApplicationEntity application) throws ServiceException;

	int findPostedStatusId() throws ServiceException;

	int findVerifyingStatusId() throws ServiceException;

	ApplicationEntity changeToPosted(ApplicationEntity application) throws ServiceException;
	
	ApplicationEntity changeToVerifying(ApplicationEntity application) throws ServiceException;
	
	boolean deleteApplication (int applicationId) throws ServiceException;

}
