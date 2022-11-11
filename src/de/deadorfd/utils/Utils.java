package de.deadorfd.utils;

import org.bukkit.entity.Player;
import static de.deadorfd.utils.Config.*;

import java.util.ArrayList;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.utils
 * @Date 10.11.2022
 * @Time 02:43:08
 */
public class Utils {

	public static boolean hasPermission(Player player, String permission) {
		return player.hasPermission(Config.getPermission("Admin"))
				|| player.hasPermission(Config.getPermission(permission));
	}

	public static String wrongCommand(String command) {
		return getString("Prefix") + getMessage("WrongCommand").replace("%command%", command);
	}

	public static String noPermission() {
		return getString("Prefix") + getMessage("NoPermission");
	}

	public static String mustPlayer() {
		return getMessage("MustPlayer");
	}

	public static String noPlayerFound(String name) {
		return getString("Prefix") + getMessagePlayer("NoPlayerFound", name);
	}

	public static ArrayList<String> tabComplete(String[] args, ArrayList<String> subcommands) {
		final ArrayList<String> l = new ArrayList<String>();
		if (!args[0].isEmpty()) {
			subcommands.forEach(s -> {
				if (s.toLowerCase().startsWith(args[0].toLowerCase())) l.add(s);
			});
		} else {
			subcommands.forEach(s -> l.add(s));
		}
		return l;
	}
}