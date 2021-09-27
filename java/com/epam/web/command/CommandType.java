package com.epam.web.command;
import com.epam.web.command.impl.LanguageChangeImpl;
import com.epam.web.command.impl.admin.LoginCommandImpl;
import com.epam.web.command.impl.user.GoToUserRegistrationPageCommandImpl;
import com.epam.web.command.impl.DefaultCommandImpl;
import com.epam.web.command.impl.admin.GoToAdminLoginPageCommandImpl;
import com.epam.web.command.impl.admin.GoToAdminMainPageImpl;
public enum CommandType {
	LOGIN(new LoginCommandImpl()),
	LANGUAGE_CHANGE(new LanguageChangeImpl()),
	GO_TO_ADMIN_MAIN_PAGE(new GoToAdminMainPageImpl()),
	DEFAULT_COMMAND(new DefaultCommandImpl()),
	ADMIN_LOGIN(new GoToAdminLoginPageCommandImpl()),
	USER_REGISTRATION(new GoToUserRegistrationPageCommandImpl());
	private Command command;
	
	CommandType(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return command;
	}

}
