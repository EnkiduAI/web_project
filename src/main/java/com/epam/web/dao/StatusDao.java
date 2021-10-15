package com.epam.web.dao;



import com.epam.web.entity.StatusEntity;
import com.epam.web.exception.DaoException;

public interface StatusDao extends BaseDao<Integer, StatusEntity>{
	StatusEntity findByStatus(String status) throws DaoException;

}
