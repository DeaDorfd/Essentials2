package de.deadorfd.commands;

import static de.deadorfd.utils.Utils.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.deadorfd.utils.Config.*;
import de.deadorfd.utils.WarpAPI;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.commands
 * @Date 10.11.2022
 * @Time 23:34:03
 */
public class Set_Spawn_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (!hasPermission(player, "SetSpawn")) {
			player.sendMessage(noPermission());
			return true;
		}
		if (args.length != 0) {
			player.sendMessage(wrongCommand("setSpawn"));
			return true;
		}
		WarpAPI.setWarp(player, "Spawn");
		player.sendMessage(getString("Prefix") + getMessage("SpawnSet"));
		return false;
	}
}