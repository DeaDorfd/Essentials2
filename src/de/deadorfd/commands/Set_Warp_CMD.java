package de.deadorfd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.deadorfd.utils.WarpAPI.*;
import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.Utils.*;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.commands
 * @Date 10.11.2022
 * @Time 23:15:48
 */
public class Set_Warp_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "SetWarp")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length != 1) {
			player.sendMessage(wrongCommand("setWarp <Warp-Name>"));
			return true;
		}
		String warpname = args[0];
		setWarp(player, warpname);
		player.sendMessage(getString("Prefix") + getWarpMessage("WarpSet", warpname));
		return true;
	}
}