package com.epam.web.command.impl.admin;

import com.epam.web.command.Command;
import com.epam.web.entity.ApplicationEntity;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.AdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.epam.web.command.ParameterProvider.*;
import static com.epam.web.command.PagePath.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class GoToEditApplicationCommandImpl implements Command{
	private static final Logger logger = LogManager.getLogger();
AdminServiceImpl service = AdminServiceImpl.getInstance();
	@Override
	public String execute(HttpServletRequest request) {
		String page = EDIT_APPLICATION;
		HttpSession session = request.getSession();
		
		  int id = Integer.parseInt(request.getParameter(APPLICATION_ID));
		  ApplicationEntity application = new ApplicationEntity(); 
		  try { 
			  application = service.findById(id); 
			  session.setAttribute(CURRENT_APPLICATION, application);
		  }catch (ServiceException e) { 
			  logger.error(e); 
			  }
		 
		
		return page;
	}

}
