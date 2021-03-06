
package net.eduard.essentials.command.admin;

import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.api.lib.core.Mine;
import net.eduard.api.lib.manager.CommandManager;

public class BanCommand extends CommandManager {

	public String message = "�6O jogador �e$target �6foi banido por �a$sender �6motido: �c$reason";
	public String messageTarget = "�6Voce foi banido por �e$target �6motivo: �f$reason";
	public BanCommand() {
		super("ban");
	}
	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length <= 1) {
			return false;
		}
		// /ban edu
		if (Mine.existsPlayer(sender, args[0])) {
			Player target = Bukkit.getPlayer(args[0]);

			StringBuilder builder = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				builder.append(args[i] + " ");
			}
			target.setBanned(true);
			BanList lista = Bukkit.getBanList(Type.NAME);
			lista.addBan(target.getName(), builder.toString(), null, null);
			target.kickPlayer(messageTarget.replace("$target", sender.getName())
					.replace("$sender", sender.getName())
					.replace("$reason", builder.toString()));
			Mine.broadcast(message.replace("$target", target.getDisplayName())
					.replace("$sender", sender.getName())
					.replace("$reason", builder.toString()));
		}

		return true;
	}
}
