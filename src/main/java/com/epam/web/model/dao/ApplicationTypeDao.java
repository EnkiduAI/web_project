package com.epam.web.model.dao;

import java.util.List;

import com.epam.web.exception.DaoException;
import com.epam.web.model.entity.ApplicationType;

public interface ApplicationTypeDao extends BaseDao<Integer, ApplicationType>{
	
List<ApplicationType> findByType(String type) throws DaoException;
}
