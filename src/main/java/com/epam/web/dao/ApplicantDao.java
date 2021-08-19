package com.epam.web.dao;

import java.util.List;

import com.epam.web.entity.ApplicantEntity;
import com.epam.web.exception.DaoException;

public interface ApplicantDao extends BaseDao<Integer, ApplicantEntity>{
List<ApplicantEntity>findAllByOrganizationName(String name) throws DaoException;

List<ApplicantEntity> findAll() throws DaoException;
}
