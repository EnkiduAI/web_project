package com.epam.web.service;

import java.util.List;
import java.util.Optional;

import com.epam.web.entity.ApplicationEntity;
import com.epam.web.entity.UserEntity;
import com.epam.web.exception.ServiceException;


public interface UserService {
Optional<UserEntity> userLogin(String login, String password)throws ServiceException;
boolean userSignUp(UserEntity user) throws ServiceException;
List<ApplicationEntity> findAllApplications() throws ServiceException;
List<ApplicationEntity> findAllPosted(String status) throws ServiceException;
}
