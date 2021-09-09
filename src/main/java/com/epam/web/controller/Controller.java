package com.epam.web.controller;

import java.io.IOException;

import com.epam.web.command.Command;
import com.epam.web.command.CommandType;
import com.epam.web.command.ParameterProvider;
import com.epam.web.exception.CommandException;
import com.epam.web.exception.DaoException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(value = "/controller")
public class Controller extends HttpServlet{

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		processRequest(req, resp);
	}

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		processRequest(req, resp);
	}

private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	String commandName = request.getParameter(ParameterProvider.COMMAND);
	String page;
	Command command = CommandType.valueOf(commandName).getCommand();
	page = command.execute(request);
	request.getRequestDispatcher(page).forward(request, response);
	
}
	
}
