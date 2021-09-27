package com.epam.web.dao;

import java.util.List;

import com.epam.web.entity.UpdateEntity;
import com.epam.web.exception.DaoException;

public interface UpdateDao extends BaseDao<Integer, UpdateEntity>{
List<UpdateEntity>findByApplicationId(int applicationId) throws DaoException;
}
