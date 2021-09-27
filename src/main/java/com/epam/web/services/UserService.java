package com.epam.web.services;

import java.util.Optional;

import com.epam.web.entity.UserEntity;
import com.epam.web.exception.ServiceException;


public interface UserService {
Optional<UserEntity> userLogin(String login, String password)throws ServiceException;
boolean userSignUp(UserEntity user) throws ServiceException;
}
