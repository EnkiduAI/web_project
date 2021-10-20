package com.epam.web.controller.command.impl;



import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.ParameterProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.epam.web.controller.command.PagePath.*;

public class LanguageChangeCommand implements Command{
	 private static final String RUSSIAN_LOCALE = "ru";
	 private static final String ENGLISH_LOCALE = "en";
	 private static final String ENGLISH_BUNDLE = "prop.pagecontent";
	 private static final String RUSSIAN_BUNDLE = "prop.pagecontent_ru_RU";
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(CURRENT_PAGE);//.substring(34);
		
		 String language = request.getParameter(ParameterProvider.LANGUAGE);
		 
		 if(language.equals(ENGLISH_LOCALE)) {
			 session.setAttribute(ParameterProvider.CURRENT_LOCALE, ENGLISH_LOCALE);
			 session.setAttribute(ParameterProvider.CURRENT_BUNDLE, ENGLISH_BUNDLE);
			 session.setAttribute(ParameterProvider.SECOND_LOCALE, RUSSIAN_LOCALE);
		 }else {
			 session.setAttribute(ParameterProvider.CURRENT_LOCALE, RUSSIAN_LOCALE);
		 session.setAttribute(ParameterProvider.CURRENT_BUNDLE, RUSSIAN_BUNDLE);
		 session.setAttribute(ParameterProvider.SECOND_LOCALE, ENGLISH_LOCALE);
		 }
		return page;
	}

}
