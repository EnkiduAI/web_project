package com.epam.web.dao;

import java.util.List;

import com.epam.web.entity.ClosedApplicationEntity;
import com.epam.web.exception.DaoException;

public interface ClosedApplicationsDao extends BaseDao<Integer, ClosedApplicationEntity>{
List<ClosedApplicationEntity>findByApplicantId(int applicantId) throws DaoException;
}
