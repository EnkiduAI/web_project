package com.epam.web.controller.command;

public final class ParameterProvider {
public static final String COMMAND = "command";
public static final String LOGIN = "login";
public static final String PASSWORD = "password";
public static final String LANGUAGE = "language";
public static final String CURRENT_LOCALE = "currentLocale";
public static final String CURRENT_BUNDLE = "currentBundle";
public static final String SECOND_LOCALE = "secondLocale";
public static final String ROLE = "role";

//user data
public static final String USER_EMAIL = "email";
public static final String USER_LOGIN = "login";
public static final String USER_PASSWORD = "password";
public static final String USER_NAME = "name";
public static final String USER_SURNAME = "surname";
public static final String USER_PHONE = "phone";
public static final String CURRENT_USER = "currentUser";
public static final String NEW_LOGIN = "newLogin";
public static final String CONFIRM_LOGIN = "confirmLogin";
public static final String NEW_PASSWORD = "newPassword";
public static final String CONFIRM_PASSWORD = "confirmPassword";

//roles
public static final String ADMIN_ROLE = "admin";
public static final String USER_ROLE = "user";

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

//status
public static final String STATUS_POSTED = "POSTED";
public static final String STATUS_VERIFYING = "VERIFYING";
public static final String STATUS_LIST = "statusList";

//applicants
public static final String APPLICANTS_LIST = "applicantsList";
public static final String APPLICANT_LIST_NAMES = "applicantNames";
public static final String APPLICANT_ID = "applicantId";
public static final String APPLICANT_NAME = "organization";
public static final String APPLICANT_LOGIN = "login";
public static final String APPLICANT_PASSWORD = "password";
public static final String APPLICANT_EMAIL = "email";
public static final String APPLICANT_PHONE = "phone";

//types
public static final String TYPES_LIST = "typesList";
public static final String TYPE_FILTER = "typeSelect";
public static final String TYPE_SELECTED = "selectedType";

//miscellaneous
public static final String ORGANIZATION_FILTER = "organizationSelect";
public static final String ORGANIZATION_SELECTED = "selectedName";
private ParameterProvider() {
	
}
}
