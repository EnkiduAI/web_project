package com.epam.web.dao;

public class TableColumns {
//foreign keys
	// applicants
	public static final String APPLICANT_ID = "applicantId";
	public static final String ORGANIZATION_NAME = "organizationName";
	public static final String APPLICANT_LOGIN = "login";
	public static final String APPLICANT_PASSWORD = "password";
	public static final String APPLICANT_EMAIL = "email";
	public static final String APPLICANT_PHONE = "phone";
	
	// applications
	public static final String APPLICATION_ID = "applications.applicationId";
	public static final String FK_STATUS_ID = "applications.statusId";
	public static final String FK_APPLICANT_ID = "applications.applicantId";
	public static final String FK_TYPE_ID = "applications.typeId";
	public static final String PHOTO = "applications.photo";
	public static final String NAME = "applications.name";
	public static final String SURNAME = "applications.surname";
	public static final String TRAITS = "applications.traits";
	public static final String WEIGHT = "applications.weight";
	public static final String HEIGHT = "applications.height";
	public static final String DESCRIPTION = "applications.description";
	public static final String REWARD = "applications.reward";
	public static final String EXPIRATION_DATE = "applications.expirationDate";
	
	// users
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "users.name";
	public static final String USER_SURNAME = "users.surname";
	public static final String USER_LOGIN = "users.login;";
	public static final String USER_PASSWORD = "users.password";
	public static final String USER_EMAIL = "users.email";
	public static final String USER_PHONE = "users.phone";
	
	// closed applications
	public static final String DATE = "closed_applications.date";
	public static final String CLOSED_APPLICATIONS_APPLICANT_ID = "closed_applications.applicantId";
	public static final String FK_APPLICATION_ID = "closed_applications.applicationId";
	public static final String CLOSED_APPLICATION_ID = "closed_application.closedApplicationId";
	
	// status
	public static final String STATUS_ID = "status.statusId";
	public static final String STATUS = "status.status";
	
	// types
	public static final String TYPE_ID = "application_types.typeId";
	public static final String TYPE = "application_type.type";
	
	// updates
	public static final String MESSAGE_ID = "updates.messageId";
	public static final String FK_USER_ID = "updates.userId";
	public static final String UPDATES_APPLICATION_ID = "updates.applicationId";
	public static final String MESSAGE = "updates.message";
	
	private TableColumns() {
		
	}
	}
