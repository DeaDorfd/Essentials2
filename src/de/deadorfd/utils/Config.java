package de.deadorfd.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.deadorfd.main.Essentials2;

/**
 * @Author DeaDorfd
 * @Project Essentials 2
 * @Package de.deadorfd.utils
 * @Date 09.11.2022
 * @Time 13:13:38
 */
public class Config {

	public static FileConfiguration cfg = Essentials2.getInstance().getConfig();

	public static String getMessage(String path) {
		return cfg.getString("Messages." + path).replaceAll("&", "§");
	}

	public static String getMessagePlayer(String path, String playername) {
		return getMessage(path).replaceAll("%player%", playername);
	}

	public static String getWarpMessage(String path, String warpname) {
		return getMessage(path).replaceAll("%warpname%", warpname);
	}

	public static String getGamemodeMessage(String gamemode) {
		return getMessage("GamemodeSet").replaceAll("%gamemode%", getString("Gamemodes." + gamemode));
	}

	public static String getGamemodeOtherPlayerMessage(String gamemode, Player target) {
		return getMessage("GamemodeSetOther").replaceAll("%gamemode%", getString("Gamemodes." + gamemode))
				.replaceAll("%player%", target.getName());
	}

	public static String getPermission(String path) {
		return cfg.getString("Permissions." + path);
	}

	public static String getString(String path) {
		return cfg.getString(path).replaceAll("&", "§");
	}

	public static boolean getBoolean(String path) {
		return cfg.getBoolean(path);
	}

}
