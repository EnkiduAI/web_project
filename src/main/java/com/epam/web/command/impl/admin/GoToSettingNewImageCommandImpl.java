package com.epam.web.command.impl.admin;

import com.epam.web.command.Command;
import com.epam.web.entity.ApplicationEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.epam.web.command.PagePath.*;
import static com.epam.web.command.ParameterProvider.*;

public class GoToSettingNewImageCommandImpl implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		return IMAGE_EDIT;
	}

}
