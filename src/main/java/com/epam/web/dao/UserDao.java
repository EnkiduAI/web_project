package com.epam.web.dao;

import java.util.Optional;

import com.epam.web.entity.UserEntity;
import com.epam.web.exception.DaoException;

public interface UserDao extends BaseDao<Integer, UserEntity>{
Optional<UserEntity> findByLogin(String login) throws DaoException;
UserEntity findByEmail(String email) throws DaoException;
}
