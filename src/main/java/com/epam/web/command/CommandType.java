package com.epam.web.command;
import com.epam.web.command.impl.LanguageChangeImpl;
import com.epam.web.command.impl.LoginCommandImpl;
import com.epam.web.command.impl.GoToAdminMainPageImpl;
public enum CommandType {
	LOGIN(new LoginCommandImpl()),
	LANGUAGE_CHANGE(new LanguageChangeImpl()),
	GO_TO_ADMIN_MAIN_PAGE(new GoToAdminMainPageImpl());
	private Command command;
	
	CommandType(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		
		return command;
	}
}
