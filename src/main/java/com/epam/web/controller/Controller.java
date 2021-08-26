package com.epam.web.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

public class Controller extends HttpServlet{
public static final String COMMAND = "command";

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPost(req, resp);
	}

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
}
	
}
