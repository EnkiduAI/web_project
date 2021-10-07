package com.epam.web.dao;

import java.util.List;

import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.DaoException;

public interface ApplicationDao extends BaseDao<Integer, ApplicationEntity>{
List<ApplicationEntity> findbyStatus(String status) throws DaoException;
List<ApplicationEntity> findbyOrganizationName(int name) throws DaoException;
List<ApplicationEntity> findUnposted() throws DaoException;
}
