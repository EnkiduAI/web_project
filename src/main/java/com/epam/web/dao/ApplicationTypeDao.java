package com.epam.web.dao;

import java.util.List;

import com.epam.web.entity.ApplicationTypeEntity;
import com.epam.web.exception.DaoException;

public interface ApplicationTypeDao extends BaseDao<Integer, ApplicationTypeEntity>{
	
List<ApplicationTypeEntity> findByType(String type) throws DaoException;
}
