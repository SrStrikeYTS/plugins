
package net.eduard.api.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.api.API;
import net.eduard.api.manager.Commands;
import net.eduard.api.util.Extras;

public class StaffChatCommand extends Commands {
	public StaffChatCommand() {
		super("staffchat");
	}
	public String staffTag = "[STAFF] ";
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (API.onlyPlayer(sender)) {
			Player p = (Player) sender;

			StringBuilder builder = new StringBuilder();
			int id = 0;
			for (String arg : args) {
				if (id != 0)
					builder.append(" ");
				else
					id++;

				builder.append(API.toChatMessage(arg));
			}
			API.broadcast(staffTag + p.getDisplayName() + Extras.getArrowRight()
					+ " " + builder.toString(), getCommand().getPermission());
		}
		return true;
	}

}
