package net.eduard.api.command.staff;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.api.API;
import net.eduard.api.config.ConfigSection;
import net.eduard.api.manager.CMD;

public class GamemodeCommand extends CMD {

	public GamemodeCommand() {
		super("gamemode","gm");
	}
	public String message = "�6Seu gamemode agora �: $gamemode";
	public String messageTarget = "�6O gamemode do $player agora �: $gamemode";

	public String getGamemode(Player player) {
		return ConfigSection.toTitle(player.getGameMode().name());
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.getGameMode() == GameMode.CREATIVE) {
					p.setGameMode(GameMode.SURVIVAL);
				} else {
					p.setGameMode(GameMode.CREATIVE);
				}
				ConfigSection.chat(sender,
						message.replace("$gamemode", getGamemode(p)));

			} else
				return false;

		} else {
			String arg = args[0];
			GameMode gm = null;
			try {
				gm = GameMode.getByValue(ConfigSection.toInt(arg));
			} catch (Exception ex) {
				try {
					gm = GameMode.valueOf(arg.toUpperCase());
				} catch (Exception ex2) {
					return false;
				}
			}
			Player p = null;
			if (sender instanceof Player) {
				p = (Player) sender;
			}
			if (args.length >= 2) {
				if (API.existsPlayer(sender, args[1])) {
					p = API.getPlayer(args[1]);
					ConfigSection.chat(sender,
							messageTarget.replace("$gamemode", getGamemode(p))
									.replace("$player", p.getDisplayName()));
				} else
					return true;;

			}
			if (p == null) {
				return false;
			}
			p.setGameMode(gm);
			ConfigSection.chat(p,message.replace("$gamemode", getGamemode(p)));

		}
		return true;
	}
}