package com.epam.web.model.service;

import java.util.List;
import java.util.Optional;

import com.epam.web.exception.ServiceException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;
import com.epam.web.model.entity.User;

public interface UserService {

	List<Application> findAllApplications() throws ServiceException;

	List<Application> findAllPosted(String status) throws ServiceException;

	List<Application> findAllUnpostedByApplicant(Applicant applicant) throws ServiceException;
	
	List<Application> findAllPostedByApplicant(Applicant applicant) throws ServiceException;
	
	void updateProfileInfo(Applicant applicant) throws ServiceException;

	Applicant findOrganizationByLogin(String name) throws ServiceException;
}
