package de.deadorfd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.deadorfd.utils.WarpAPI;

import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.Utils.*;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.commands
 * @Date 10.11.2022
 * @Time 19:01:14
 */
public class Warps_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (getBoolean("WarpsPermission")) {
			if (!hasPermission(player, "Warps")) {
				player.sendMessage(noPermission());
				return true;
			}
		}
		if (args.length != 0) {
			player.sendMessage(wrongCommand("Warps"));
			return true;
		}
		player.sendMessage(getString("Prefix") + getMessage("ListWarps"));
		WarpAPI.getWarps().forEach(warpname -> player.sendMessage(getString("Prefix") + "§6" + warpname));
		return true;
	}
}