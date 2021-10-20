package com.epam.web.model.dao;

import java.util.Optional;

import com.epam.web.exception.DaoException;
import com.epam.web.model.entity.User;

public interface UserDao extends BaseDao<Integer, User>{
Optional<User> findByLogin(String login) throws DaoException;
User findByEmail(String email) throws DaoException;
}
