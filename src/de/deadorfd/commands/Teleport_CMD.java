package de.deadorfd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.deadorfd.utils.Utils.*;
import static de.deadorfd.utils.Config.*;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.commands
 * @Date 10.11.2022
 * @Time 23:41:14
 */
public class Teleport_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "Teleport")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				player.sendMessage(noPlayerFound(args[0]));
				return true;
			}
			player.teleport(target);
			player.sendMessage(getString("Prefix") + getMessagePlayer("TeleportToPlayer", target.getName()));
			return true;
		} else if (args.length == 2) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				player.sendMessage(noPlayerFound(args[0]));
				return true;
			}
			Player target2 = Bukkit.getPlayer(args[1]);
			if (target2 == null) {
				player.sendMessage(noPlayerFound(args[1]));
				return true;
			}
			String message = getMessagePlayer("TeleportPlayerToPlayer", target.getName()).replaceAll("%player1%",
					target2.getName());
			target.teleport(target2);
			player.sendMessage(getString("Prefix") + message);
			return true;
		} else {
			player.sendMessage(wrongCommand("tp <Player-Name> (<Player-Name>)"));
			return true;
		}
	}
}