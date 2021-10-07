package com.epam.web.service;

import java.util.List;

import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.ServiceException;

public interface AdminService {
	List<ApplicationEntity> findUnpostedApplications() throws ServiceException;
 ApplicationEntity findById(int id) throws ServiceException;
 void updateApplication(ApplicationEntity application) throws ServiceException;
}
