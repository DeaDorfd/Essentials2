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
 * @Time 15:49:28
 */
public class Heal_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "Heal")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length == 0) {
			player.setFoodLevel(20);
			player.setHealth(20);
			player.sendMessage(getString("Prefix") + getMessage("Healed"));
			return true;
		} else if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				player.sendMessage(noPlayerFound(args[0]));
				return true;
			}
			target.setFoodLevel(20);
			target.setHealth(20);
			target.sendMessage(getString("Prefix") + getMessage("Healed"));
			player.sendMessage(getString("Prefix") + getMessagePlayer("HealedOther", target.getName()));
			return true;
		} else {
			player.sendMessage(wrongCommand("heal <Player-Name>"));
			return true;
		}
	}
}