package com.epam.web.model.dao;

import java.util.List;

import com.epam.web.exception.DaoException;
import com.epam.web.model.entity.Update;

public interface UpdateDao extends BaseDao<Integer, Update>{
List<Update>findByApplicationId(int applicationId) throws DaoException;
}
