package de.deadorfd.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import static de.deadorfd.utils.WarpAPI.*;
import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.Utils.*;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.commands
 * @Date 10.11.2022
 * @Time 23:05:11
 */
public class Delete_Warp_CMD implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "DeleteWarp")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length != 1) {
			player.sendMessage(wrongCommand("delWarp <Warp-Name>"));
			return true;
		}
		String warpname = args[0];
		if (!warpExist(warpname)) {
			player.sendMessage(getString("Prefix") + getWarpMessage("WarpNotExists", warpname));
			return true;
		}
		delWarp(warpname);
		player.sendMessage(getString("Prefix") + getWarpMessage("WarpDeleted", warpname));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (hasPermission(player, "DeleteWarp")) if (args.length == 1) {
			return tabComplete(args, getWarps());
		}
		return new ArrayList<>();
	}
}