package com.epam.web.command;

public class ParameterProvider {
public static final String COMMAND = "command";
public static final String LOGIN = "login";
public static final String PASSWORD = "password";
public static final String LANGUAGE = "language";
public static final String CURRENT_LOCALE = "currentLocale";
public static final String CURRENT_BUNDLE = "currentBundle";
public static final String SECOND_LOCALE = "secondLocale";
public static final String CURRENT_PAGE = "currentPage";
public static final String ROLE = "role";

//user data
public static final String USER_EMAIL = "email";
public static final String USER_LOGIN = "login";
public static final String USER_PASSWORD = "password";
public static final String USER_NAME = "name";
public static final String USER_SURNAME = "surname";
public static final String USER_PHONE = "phone";
public static final String USER_ROLE = "user";

//roles
public static final String ADMIN_ROLE = "admin";

//type of applicationLists
public static final String POSTED_APPLICATION_LIST = "applicationList";
public static final String UNPOSTED_APPLICATION_LIST = "unpostedList";

//application
public static final String CURRENT_APPLICATION = "currentApplication";
public static final String APPLICATION_ID = "applicationId";
public static final String APPLICATION_NAME = "name";
public static final String APPLICATION_SURNAME = "surname";
public static final String APPLICATION_TRAITS = "traits";
public static final String APPLICATION_WEIGHT = "weight";
public static final String APPLICATION_HEIGHT = "height";
public static final String APPLICATION_DESCRIPTION = "description";
public static final String APPLICATION_REWARD = "reward";
public static final String APPLICATION_EXPDATE = "expdate";
private ParameterProvider() {
	
}
}
