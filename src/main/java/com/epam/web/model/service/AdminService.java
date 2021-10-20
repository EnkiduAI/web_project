package com.epam.web.model.service;

import java.util.List;

import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.entity.ApplicationType;
import com.epam.web.model.entity.Status;

public interface AdminService {
	List<Application> findUnpostedApplications() throws ServiceException;
	
	List<Application> findPostedApplications() throws ServiceException;
	
	List<Application> findByOrganizationName(List<Application> applications, String name) throws ServiceException;
	
	List<ApplicationType> findAllTypes() throws ServiceException;
	
	List<Applicant> findAllApplicants() throws ServiceException;
	
	List<Status> findAllStatus() throws ServiceException;
	
	boolean createApplication(Application application) throws ServiceException;
	
	boolean createApplicant(Applicant applicant) throws ServiceException;
	
	boolean deleteApplicant(int id) throws ServiceException;
	
	boolean deleteApplicationByApplicantId(int id) throws ServiceException;

	Application findById(int id) throws ServiceException;

	void updateApplication(Application application) throws ServiceException;

	int findPostedStatusId() throws ServiceException;

	int findVerifyingStatusId() throws ServiceException;

	Application changeToPosted(Application application) throws ServiceException;
	
	Application changeToVerifying(Application application) throws ServiceException;
	
	boolean deleteApplication (int applicationId) throws ServiceException;

}
