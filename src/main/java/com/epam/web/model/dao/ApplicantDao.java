package com.epam.web.model.dao;

import java.util.List;

import com.epam.web.exception.DaoException;
import com.epam.web.model.entity.Applicant;

public interface ApplicantDao extends BaseDao<Integer, Applicant> {
	
	Applicant findOrganizationByLogin(String login) throws DaoException;
	
	Applicant findByOrganizationName(String name) throws DaoException;

	List<Applicant> findAll() throws DaoException;
}
