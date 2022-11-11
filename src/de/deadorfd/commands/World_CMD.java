package de.deadorfd.commands;

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
 * @Time 03:41:33
 */
public class World_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(getMessage("MustPlayer"));
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("Day")) {
			if (!hasPermission(player, "Day")) {
				player.sendMessage(noPermission());
				return true;
			}
			if (args.length != 0) {
				player.sendMessage(wrongCommand("day"));
				return true;
			}
			player.getWorld().setTime(27000);
			player.sendMessage(getString("Prefix") + getMessage("TimeDay"));
		} else if (label.equalsIgnoreCase("Night")) {
			if (!hasPermission(player, "Night")) {
				player.sendMessage(noPermission());
				return true;
			}
			if (args.length != 0) {
				player.sendMessage(wrongCommand("night"));
				return true;
			}
			player.getWorld().setTime(20000);
			player.sendMessage(getString("Prefix") + getMessage("TimeNight"));
		} else if (label.equalsIgnoreCase("Sun")) {
			if (!hasPermission(player, "Sun")) {
				player.sendMessage(noPermission());
				return true;
			}
			if (args.length != 0) {
				player.sendMessage(wrongCommand("sun"));
				return true;
			}
			player.getWorld().setStorm(false);
			player.getWorld().setThundering(false);
			player.sendMessage(getString("Prefix") + getMessage("WeatherSun"));
		} else if (label.equalsIgnoreCase("Rain")) {
			if (!hasPermission(player, "Rain")) {
				player.sendMessage(noPermission());
				return true;
			}
			if (args.length != 0) {
				player.sendMessage(wrongCommand("rain"));
				return true;
			}
			player.getWorld().setStorm(true);
			player.getWorld().setThundering(true);
			player.sendMessage(getString("Prefix") + getMessage("WeatherRain"));
		}
		return false;
	}
}