package com.epam.web.dao;

import java.util.List;

import com.epam.web.entity.StatusEntity;
import com.epam.web.exception.DaoException;

public interface StatusDao extends BaseDao<Integer, StatusEntity>{
	List<StatusEntity> findByStatus(String status) throws DaoException;

}
