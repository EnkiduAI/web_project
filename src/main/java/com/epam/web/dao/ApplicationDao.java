package com.epam.web.dao;

import java.util.List;

import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.DaoException;

public interface ApplicationDao extends BaseDao<Integer, ApplicationEntity>{
List<ApplicationEntity> findbyStatus(int status) throws DaoException;
List<ApplicationEntity> findbyOrganizationName(int name) throws DaoException;
}
