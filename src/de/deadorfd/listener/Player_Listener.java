package de.deadorfd.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.deadorfd.commands.Vanish_CMD;
import static de.deadorfd.utils.Config.*;
import static de.deadorfd.utils.Utils.*;

/**
 * @Author Flugboy
 * @Project Essentials 2
 * @Package de.deadorfd.listener
 * @Date 10.11.2022
 * @Time 03:35:07
 */
public class Player_Listener implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		if (!getBoolean("JoinMessage")) event.setJoinMessage(null);
		if (getBoolean("JoinMessage"))
			event.setJoinMessage(getString("Prefix") + getMessagePlayer("JoinMessage", event.getPlayer().getName()));
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (Vanish_CMD.vanish.contains(all)) {
				if (hasPermission(event.getPlayer(), "VanishSee")) {
					event.getPlayer().showPlayer(all);
				} else {
					event.getPlayer().hidePlayer(all);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		if (!getBoolean("LeaveMessage")) event.setQuitMessage(null);
		if (getBoolean("LeaveMessage"))
			event.setQuitMessage(getString("Prefix") + getMessagePlayer("LeaveMessage", event.getPlayer().getName()));
	}
}