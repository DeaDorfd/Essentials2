package de.deadorfd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.Utils.*;

import java.util.ArrayList;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.commands
 * @Date 10.11.2022
 * @Time 17:55:04
 */
public class Fly_CMD implements CommandExecutor {

	private static ArrayList<Player> fly = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "Fly")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length == 0) {
			if (!fly.contains(player)) {
				fly.add(player);
				player.setAllowFlight(true);
				player.sendMessage(getString("Prefix") + getMessage("FlyOn"));
				return true;
			}
			fly.remove(player);
			player.setAllowFlight(false);
			player.sendMessage(getString("Prefix") + getMessage("FlyOff"));
			return true;
		} else if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				player.sendMessage(noPlayerFound(args[0]));
				return true;
			}
			if (!fly.contains(target)) {
				fly.add(target);
				target.setAllowFlight(true);
				target.sendMessage(getString("Prefix") + getMessage("FlyOn"));
				player.sendMessage(getString("Prefix") + getMessagePlayer("FlyOnOther", target.getName()));
				return true;
			}
			fly.remove(target);
			target.setAllowFlight(false);
			target.sendMessage(getString("Prefix") + getMessage("FlyOff"));
			player.sendMessage(getString("Prefix") + getMessagePlayer("FlyOffOther", target.getName()));
			return true;
		} else {
			player.sendMessage(wrongCommand("fly <Player-Name>"));
			return true;
		}
	}
}