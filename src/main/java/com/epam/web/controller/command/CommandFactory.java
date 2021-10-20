package com.epam.web.controller.command;

import static com.epam.web.controller.command.ParameterProvider.*;
import jakarta.servlet.http.HttpServletRequest;

public class CommandFactory {
	private static CommandFactory instance = new CommandFactory();

	private CommandFactory() {
	}

	public static CommandFactory getInstance() {
		return instance;
	}

	public Command createCommand(HttpServletRequest request) {
		String commandName = request.getParameter(COMMAND);
		Command command;
		if (commandName != null) {
			try {
				CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
				command = commandType.getCommand();
			} catch (IllegalArgumentException e) {
				command = CommandType.DEFAULT_COMMAND.getCommand();
			}

		} else {
			command = CommandType.DEFAULT_COMMAND.getCommand();
		}
		return command;
	}
}
