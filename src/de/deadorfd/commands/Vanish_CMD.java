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
 * @Time 18:13:47
 */
public class Vanish_CMD implements CommandExecutor {

	public static ArrayList<Player> vanish = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "Vanish")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length != 0) {
			player.sendMessage(wrongCommand("Vanish"));
			return true;
		}
		if (!vanish.contains(player)) {
			vanish.add(player);
			player.sendMessage(getString("Prefix") + getMessage("VanishOn"));
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (!hasPermission(all, "VanishSee")) all.hidePlayer(player);
			}
			return true;
		}
		vanish.remove(player);
		player.sendMessage(getString("Prefix") + getMessage("VanishOff"));
		Bukkit.getOnlinePlayers().forEach(all -> all.showPlayer(player));
		return true;
	}
}