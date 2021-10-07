package com.epam.web.controller;

import static com.epam.web.command.PagePath.EDIT_APPLICATION;
import static com.epam.web.command.ParameterProvider.CURRENT_APPLICATION;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.web.entity.ApplicationEntity;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet(value="/upload")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15) // 15 MB
public class UploadServlet extends HttpServlet{
	private static final Logger logger = LogManager.getLogger();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = EDIT_APPLICATION;

		HttpSession session = request.getSession();
		ApplicationEntity application = (ApplicationEntity) session.getAttribute(CURRENT_APPLICATION);
		try {
			Part filePart = request.getPart("fileToUpload");
			InputStream fileInputStream = filePart.getInputStream();
			File fileToSave = new File(DESTINATION + filePart.getSubmittedFileName());
			Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
			application.setPhoto(filePart.getSubmittedFileName());
			logger.info(application.getPhoto());
			session.setAttribute(CURRENT_APPLICATION, application);
		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (ServletException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
