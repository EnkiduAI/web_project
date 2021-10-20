package com.epam.web.controller;
import java.io.IOException;
import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Controller() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Command command = CommandFactory.getInstance().createCommand(request);
		String page = command.execute(request);
		request.getRequestDispatcher(page).forward(request, response);

	}
}
