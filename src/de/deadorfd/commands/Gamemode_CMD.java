package de.deadorfd.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.Utils.*;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.commands
 * @Date 10.11.2022
 * @Time 02:38:48
 */
public class Gamemode_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(getMessage("MustPlayer"));
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "Gamemode")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length == 1) {
			String gamemode = args[0];
			if (gamemode.equalsIgnoreCase("0") || gamemode.equalsIgnoreCase("survival")) {
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(getString("Prefix") + getGamemodeMessage("Survival"));
			} else if (gamemode.equalsIgnoreCase("1") || gamemode.equalsIgnoreCase("Creative")) {
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(getString("Prefix") + getGamemodeMessage("Creative"));
			} else if (gamemode.equalsIgnoreCase("2") || gamemode.equalsIgnoreCase("Adventure")) {
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(getString("Prefix") + getGamemodeMessage("Adventure"));
			} else if (gamemode.equalsIgnoreCase("3") || gamemode.equalsIgnoreCase("spectator")) {
				player.setGameMode(GameMode.SPECTATOR);
				player.sendMessage(getString("Prefix") + getGamemodeMessage("Spectator"));
			} else {
				player.sendMessage(wrongCommand("Gamemode <gamemode> <Player-Name>"));
				return true;
			}
		} else if (args.length == 2) {
			Player target = Bukkit.getPlayer(args[1]);
			if (target == null) {
				player.sendMessage(noPlayerFound(args[1]));
				return true;
			}
			String gamemode = args[0];
			if (gamemode.equalsIgnoreCase("0") || gamemode.equalsIgnoreCase("survival")) {
				target.setGameMode(GameMode.SURVIVAL);
				target.sendMessage(getString("Prefix") + getGamemodeMessage("survival"));
				player.sendMessage(getString("Prefix") + getGamemodeOtherPlayerMessage("survival", target));
			} else if (gamemode.equalsIgnoreCase("1") || gamemode.equalsIgnoreCase("creative")) {
				target.setGameMode(GameMode.CREATIVE);
				target.sendMessage(getString("Prefix") + getGamemodeMessage("creative"));
				player.sendMessage(getString("Prefix") + getGamemodeOtherPlayerMessage("creative", target));
			} else if (gamemode.equalsIgnoreCase("2") || gamemode.equalsIgnoreCase("adventure")) {
				target.setGameMode(GameMode.ADVENTURE);
				target.sendMessage(getString("Prefix") + getGamemodeMessage("adventure"));
				player.sendMessage(getString("Prefix") + getGamemodeOtherPlayerMessage("adventure", target));
			} else if (gamemode.equalsIgnoreCase("3") || gamemode.equalsIgnoreCase("spectator")) {
				target.setGameMode(GameMode.SPECTATOR);
				target.sendMessage(getString("Prefix") + getGamemodeMessage("spectator"));
				player.sendMessage(getString("Prefix") + getGamemodeOtherPlayerMessage("spectator", target));
			} else {
				player.sendMessage(wrongCommand("Gamemode <gamemode> <Player-Name>"));
				return true;
			}
		} else {
			player.sendMessage(wrongCommand("Gamemode <gamemode> <Player-Name>"));
			return true;
		}
		return true;
	}
}