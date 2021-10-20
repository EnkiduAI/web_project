package com.epam.web.controller.listner;

import static com.epam.web.controller.command.ParameterProvider.*;

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


    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(CURRENT_LOCALE, DEFAULT_LOCALE);
        session.setAttribute(CURRENT_BUNDLE, DEFAULT_BUNDLE);
        session.setAttribute(SECOND_LOCALE, SECOND_LOCALE);
        //session.setAttribute(USER_LOGIN, null);
    }
}
