package com.epam.web.controller.command;

import com.epam.web.controller.command.impl.CreateApplicationCommand;
import com.epam.web.controller.command.impl.DefaultCommand;
import com.epam.web.controller.command.impl.DeleteApplicationCommand;
import com.epam.web.controller.command.impl.GoToCreateApplicationCommand;
import com.epam.web.controller.command.impl.GoToLoginPageCommand;
import com.epam.web.controller.command.impl.GoToMainPageCommand;
import com.epam.web.controller.command.impl.LanguageChangeCommand;
import com.epam.web.controller.command.impl.LoginCommand;
import com.epam.web.controller.command.impl.LogoutCommand;
import com.epam.web.controller.command.impl.UpadateApplicationCommand;
import com.epam.web.controller.command.impl.admin.ApplicationToVerifyingCommand;
import com.epam.web.controller.command.impl.admin.ChangeToVerifyingCommand;
import com.epam.web.controller.command.impl.admin.DeleteApplicantCommand;
import com.epam.web.controller.command.impl.admin.FindByApplicantCommand;
import com.epam.web.controller.command.impl.admin.FindPostedCommand;
import com.epam.web.controller.command.impl.admin.GoToAdminMainPageCommand;
import com.epam.web.controller.command.impl.admin.GoToApplicantCreatePageCommand;
import com.epam.web.controller.command.impl.admin.GoToEditApplicationCommand;
import com.epam.web.controller.command.impl.admin.GoToSettingNewImageCommand;
import com.epam.web.controller.command.impl.admin.GoToUserManagmentCommand;
import com.epam.web.controller.command.impl.admin.PostApplicationCommand;
import com.epam.web.controller.command.impl.admin.RegisterApplicantCommand;
import com.epam.web.controller.command.impl.user.EditProfileCommand;
import com.epam.web.controller.command.impl.user.FindPostedByApplicantCommand;
import com.epam.web.controller.command.impl.user.GoToLoginChangeCommand;
import com.epam.web.controller.command.impl.user.GoToPasswordChangeCommand;
import com.epam.web.controller.command.impl.user.GoToUserInfoCommand;
import com.epam.web.controller.command.impl.user.GoToUserMainCommand;
import com.epam.web.controller.command.impl.user.UpdateLoginCommand;
import com.epam.web.controller.command.impl.user.UpdatePasswordCommand;

public enum CommandType {
	LOGIN(new LoginCommand()), 
	LANGUAGE_CHANGE(new LanguageChangeCommand()),
	GO_TO_ADMIN_MAIN_PAGE(new GoToAdminMainPageCommand()), 
	DEFAULT_COMMAND(new DefaultCommand()),
	PAGE_LOGIN(new GoToLoginPageCommand()), 
	GO_TO_MAIN(new GoToMainPageCommand()),
	EDIT_APPLICATION(new GoToEditApplicationCommand()), 
	SET_NEW_IMAGE(new GoToSettingNewImageCommand()),
	UPDATE_APPLICATION(new UpadateApplicationCommand()), 
	LOGOUT(new LogoutCommand()),
	POST(new PostApplicationCommand()), 
	RETURN_TO_VERIFYING(new ApplicationToVerifyingCommand()),
	CHANGE_TO_VERYFYING(new ChangeToVerifyingCommand()),
	CHANGE_TO_POSTED(new FindPostedCommand()),
	DELETE_APPLICATION(new DeleteApplicationCommand()),
	APPLICANT_FILTERED(new FindByApplicantCommand()),
	USER_MANAGMENT(new GoToUserManagmentCommand()),
	APPLICANT_CREATE_PAGE(new GoToApplicantCreatePageCommand()),
	APPLICANT_CREATE(new RegisterApplicantCommand()),
	DELETE_APPLICANT(new DeleteApplicantCommand()),
	GO_TO_CREATE_APPLICATION(new GoToCreateApplicationCommand()),
	CREATE_APPLICATION(new CreateApplicationCommand()),
	GO_TO_MAIN_USER(new GoToUserMainCommand()),
	FIND_APPLICANT_POSTED(new FindPostedByApplicantCommand()),
	PROFILE(new GoToUserInfoCommand()),
	SAVE_PROFILE_CHANGES(new EditProfileCommand()),
	CHANGE_LOGIN(new GoToLoginChangeCommand()),
	CHANGE_PASSWORD(new GoToPasswordChangeCommand()),	
	UPDATE_LOGIN(new UpdateLoginCommand()),
	UPDATE_PASSWORD(new UpdatePasswordCommand());
	private Command command;

	CommandType(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

}
