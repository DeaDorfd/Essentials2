package de.deadorfd.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * @Author DeaDorfd
 * @Project Essentials 2
 * @Package de.deadorfd.utils
 * @Date 10.05.2022
 * @Time 14:38:13
 */
public class WarpAPI {

	public static String path = "plugins//Essentials2//Warps";

	public static void setWarp(Player player, String warpname) {
		File folder = new File(path);
		File file = new File(path + "//" + warpname.toLowerCase() + ".yml");
		if (!folder.exists()) folder.mkdir();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {}
		}
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Location loc = player.getLocation();
		cfg.set("X", loc.getX());
		cfg.set("Y", loc.getY());
		cfg.set("Z", loc.getZ());
		cfg.set("Yaw", loc.getYaw());
		cfg.set("Pitch", loc.getPitch());
		cfg.set("WorldName", loc.getWorld().getName());
		try {
			cfg.save(file);
		} catch (IOException e) {}
	}

	public static void tpWarp(Player player, String warpname) {
		File file = new File(path + "//" + warpname.toLowerCase() + ".yml");
		if (!file.exists()) return;
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		World world = Bukkit.getWorld(cfg.getString("WorldName"));
		double yaw = cfg.getDouble("Yaw");
		double pitch = cfg.getDouble("Pitch");
		player.teleport(new Location(world, cfg.getDouble("X"), cfg.getDouble("Y"), cfg.getDouble("Z"), (float) yaw,
				(float) pitch));
	}

	public static void delWarp(String warpname) {
		File file = new File(path + "//" + warpname.toLowerCase() + ".yml");
		if (file.exists()) file.delete();
	}

	public static Location getLocation(String warpname) {
		File file = new File(path + "//" + warpname.toLowerCase() + ".yml");
		if (file.exists()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			World welt = Bukkit.getWorld(cfg.getString("WorldName"));
			double yaw = cfg.getDouble("Yaw");
			double pitch = cfg.getDouble("Pitch");
			return new Location(welt, cfg.getDouble("X"), cfg.getDouble("Y"), cfg.getDouble("Z"), (float) yaw,
					(float) pitch);
		}
		return null;
	}

	public static File getFile(String filename) {
		return new File(path + "//" + filename.toLowerCase() + ".yml");
	}

	public static boolean warpExist(String warpname) {
		return getFile(warpname.toLowerCase()).exists();
	}

	public static ArrayList<String> getWarps() {
		ArrayList<String> results = new ArrayList<String>();
		File[] files = new File(path).listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String name = file.getName();
				name = name.replaceAll(".yml", "");
				results.add(name);
			}
		}
		return results;
	}
}