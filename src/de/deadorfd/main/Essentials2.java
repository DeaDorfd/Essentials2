package de.deadorfd.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.deadorfd.commands.Delete_Warp_CMD;
import de.deadorfd.commands.Fly_CMD;
import de.deadorfd.commands.Gamemode_CMD;
import de.deadorfd.commands.Heal_CMD;
import de.deadorfd.commands.Set_Spawn_CMD;
import de.deadorfd.commands.Set_Warp_CMD;
import de.deadorfd.commands.Spawn_CMD;
import de.deadorfd.commands.Teleport_CMD;
import de.deadorfd.commands.Vanish_CMD;
import de.deadorfd.commands.Warp_CMD;
import de.deadorfd.commands.Warps_CMD;
import de.deadorfd.commands.World_CMD;
import de.deadorfd.listener.Player_Listener;
import de.deadorfd.utils.Config;
import de.deadorfd.utils.Metrics;
import de.deadorfd.utils.WarpAPI;

/**
 * @Author DeaDorfd
 * @Project Essentials 2
 * @Package de.deadorfd.main
 * @Date 09.11.2022
 * @Time 12:35:57
 */
public class Essentials2 extends JavaPlugin {

	private static Essentials2 instance;

	@Override
	public void onEnable() {
		instance = this;
		getDataFolder().mkdir();
		saveDefaultConfig();
		new File(WarpAPI.path).mkdir();
		PluginManager plu = Bukkit.getPluginManager();
		plu.registerEvents(new Player_Listener(), instance);
		getCommand("Gamemode").setExecutor(new Gamemode_CMD());
		getCommand("Day").setExecutor(new World_CMD());
		getCommand("Night").setExecutor(new World_CMD());
		getCommand("Sun").setExecutor(new World_CMD());
		getCommand("Rain").setExecutor(new World_CMD());
		getCommand("Heal").setExecutor(new Heal_CMD());
		getCommand("Fly").setExecutor(new Fly_CMD());
		getCommand("Vanish").setExecutor(new Vanish_CMD());
		getCommand("Warps").setExecutor(new Warps_CMD());
		getCommand("Warp").setExecutor(new Warp_CMD());
		getCommand("Warp").setTabCompleter(new Warp_CMD());
		getCommand("delWarp").setExecutor(new Delete_Warp_CMD());
		getCommand("delWarp").setTabCompleter(new Delete_Warp_CMD());
		getCommand("setWarp").setExecutor(new Set_Warp_CMD());
		getCommand("Spawn").setExecutor(new Spawn_CMD());
		getCommand("setSpawn").setExecutor(new Set_Spawn_CMD());
		getCommand("Teleport").setExecutor(new Teleport_CMD());
		Metrics metrics = new Metrics(instance, 18678);
		metrics.addCustomChart(new Metrics.SimplePie("checkforupdates", () -> {
			return Config.getBoolean("CheckforUpdates") + "";
		}));
	}

	public static Essentials2 getInstance() {
		return instance;
	}
}