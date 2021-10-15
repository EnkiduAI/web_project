package com.epam.web.command;

import com.epam.web.command.impl.LanguageChangeImpl;
import com.epam.web.command.impl.LogoutCommandImpl;
import com.epam.web.command.impl.admin.LoginCommandImpl;
import com.epam.web.command.impl.admin.PostApplicationCommandImpl;
import com.epam.web.command.impl.admin.UpadateApplicationCommandImpl;
import com.epam.web.command.impl.user.GoToUserRegistrationPageCommandImpl;
import com.epam.web.command.impl.user.UserRegistrationCommandImpl;
import com.epam.web.command.impl.DefaultCommandImpl;
import com.epam.web.command.impl.GoToMainPageCommandImpl;
import com.epam.web.command.impl.admin.ApplicationToVerifyingCommandImpl;
import com.epam.web.command.impl.admin.ChangeToVerifyingCommandImpl;
import com.epam.web.command.impl.admin.DeleteApplicationCommandImpl;
import com.epam.web.command.impl.admin.FindByApplicantCommandImpl;
import com.epam.web.command.impl.admin.FindPostedCommandImpl;
import com.epam.web.command.impl.admin.GoToAdminLoginPageCommandImpl;
import com.epam.web.command.impl.admin.GoToAdminMainPageImpl;
import com.epam.web.command.impl.admin.GoToEditApplicationCommandImpl;
import com.epam.web.command.impl.admin.GoToSettingNewImageCommandImpl;

public enum CommandType {
	LOGIN(new LoginCommandImpl()), 
	LANGUAGE_CHANGE(new LanguageChangeImpl()),
	GO_TO_ADMIN_MAIN_PAGE(new GoToAdminMainPageImpl()), 
	DEFAULT_COMMAND(new DefaultCommandImpl()),
	ADMIN_LOGIN(new GoToAdminLoginPageCommandImpl()), 
	USER_REGISTRATION(new GoToUserRegistrationPageCommandImpl()),
	REGISTER_USER(new UserRegistrationCommandImpl()), 
	GO_TO_MAIN(new GoToMainPageCommandImpl()),
	EDIT_APPLICATION(new GoToEditApplicationCommandImpl()), 
	SET_NEW_IMAGE(new GoToSettingNewImageCommandImpl()),
	UPDATE_APPLICATION(new UpadateApplicationCommandImpl()), 
	LOGOUT(new LogoutCommandImpl()),
	POST(new PostApplicationCommandImpl()), 
	RETURN_TO_VERIFYING(new ApplicationToVerifyingCommandImpl()),
	CHANGE_TO_VERYFYING(new ChangeToVerifyingCommandImpl()),
	CHANGE_TO_POSTED(new FindPostedCommandImpl()),
	DELETE_APPLICATION(new DeleteApplicationCommandImpl()),
	APPLICANT_FILTERED(new FindByApplicantCommandImpl());

	private Command command;

	CommandType(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

}
