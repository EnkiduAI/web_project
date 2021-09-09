package com.epam.web.controller.listner;

import com.epam.web.command.ParameterProvider;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListnerImpl
 *
 */
public class SessionListnerImpl implements HttpSessionListener {
	private static final String DEFAULT_LOCALE = "en";
    private static final String SECOND_LOCALE = "ru_RU";
    private static final String DEFAULT_BUNDLE = "prop.pagecontent";
    private static final String DEFAULT_PREVIOUS_QUERY = "/pages/login.jsp";


    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(ParameterProvider.CURRENT_LOCALE, DEFAULT_LOCALE);
        session.setAttribute(ParameterProvider.CURRENT_BUNDLE, DEFAULT_BUNDLE);
        session.setAttribute(ParameterProvider.SECOND_LOCALE, SECOND_LOCALE);
    }
}