package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.PagePath;
import com.epam.web.command.ParameterProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LanguageChangeImpl implements Command{
	 private static final String RUSSIAN_LOCALE = "ru";
	 private static final String ENGLISH_LOCALE = "en";
	 private static final String ENGLISH_BUNDLE = "prop.pagecontent";
	 private static final String RUSSIAN_BUNDLE = "prop.pagecontent_ru_RU";
	@Override
	public String execute(HttpServletRequest request) {
		String page = PagePath.LOGINSELECT;
		HttpSession session = request.getSession();
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
