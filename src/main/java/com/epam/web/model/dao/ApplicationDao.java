package com.epam.web.model.dao;

import java.util.List;

import com.epam.web.exception.DaoException;
import com.epam.web.model.entity.Applicant;
import com.epam.web.model.entity.Application;

public interface ApplicationDao extends BaseDao<Integer, Application>{
List<Application> findbyStatus(String status) throws DaoException;
List<Application> findbyOrganizationName(String name) throws DaoException;
List<Application> findUnposted() throws DaoException;
List<Application> findPosted() throws DaoException;
List<Application> findUnpostedByOrganizationName(Applicant applicant) throws DaoException;
List<Application> findPostedByOrganizationName(Applicant applicant) throws DaoException;
boolean deleteApplicationByApplicantId(int id) throws DaoException;
}
