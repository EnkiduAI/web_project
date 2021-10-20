package com.epam.web.model.dao;



import com.epam.web.exception.DaoException;
import com.epam.web.model.entity.Status;

public interface StatusDao extends BaseDao<Integer, Status>{
	Status findByStatus(String status) throws DaoException;

}
