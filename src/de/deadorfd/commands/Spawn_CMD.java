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
 * @Time 23:31:20
 */
public class Spawn_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (args.length != 0) {
			player.sendMessage(wrongCommand("Spawn"));
			return true;
		}
		if (!WarpAPI.warpExist("Spawn")) {
			player.sendMessage(getString("Prefix") + getMessage("SpawnWasNotSet"));
			return true;
		}
		WarpAPI.tpWarp(player, "Spawn");
		player.sendMessage(getString("Prefix") + getMessage("SpawnTeleported"));
		return true;
	}
}