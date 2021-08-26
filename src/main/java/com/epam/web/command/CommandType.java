package com.epam.web.command;
import com.epam.web.command.impl.LoginCommandImpl;
public enum CommandType {
	LOGIN(new LoginCommandImpl());

	private Command command;
	
	CommandType(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return command;
	}
}
