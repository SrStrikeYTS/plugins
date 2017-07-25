
package net.eduard.eduardapi.command;

import net.eduard.api.manager.CMD;

public class ConfigCommand extends CMD {

	public ConfigCommand() {
		super("config");
		
		register(new ConfigSaveCommand());
		register(new ConfigReloadCommand());
		register(new ConfigHelpCommand());
	}

}