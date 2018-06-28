package net.farugames.buildbattle.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import net.farugames.buildbattle.listeners.entity.EntityDamageListener;
import net.farugames.buildbattle.listeners.entity.FoodLevelChangeListener;
import net.farugames.buildbattle.listeners.inventory.InventoryClickListener;
import net.farugames.buildbattle.listeners.players.PlayerChatListener;
import net.farugames.buildbattle.listeners.players.PlayerDropListener;
import net.farugames.buildbattle.listeners.players.PlayerInteractListener;
import net.farugames.buildbattle.listeners.players.PlayerJoinListener;
import net.farugames.buildbattle.listeners.players.PlayerMoveListener;
import net.farugames.buildbattle.listeners.players.PlayerQuitListener;
import net.farugames.buildbattle.listeners.server.ServerPingListener;
import net.farugames.buildbattle.listeners.world.BlockListener;
import net.farugames.buildbattle.listeners.world.WeatherChangeListener;

public class ListenerManager {

	public Plugin plugin;
	public PluginManager pluginManager;

	public ListenerManager(Plugin plugin) {
		this.plugin = plugin;
		this.pluginManager = Bukkit.getPluginManager();
	}

	public void registerListener() {

		pluginManager.registerEvents(new EntityDamageListener(), plugin);
		pluginManager.registerEvents(new FoodLevelChangeListener(), plugin);
		
		pluginManager.registerEvents(new InventoryClickListener(), plugin);

		pluginManager.registerEvents(new PlayerJoinListener(), plugin);
		pluginManager.registerEvents(new PlayerQuitListener(), plugin);
		pluginManager.registerEvents(new PlayerInteractListener(), plugin);
		pluginManager.registerEvents(new PlayerMoveListener(), plugin);
		pluginManager.registerEvents(new PlayerDropListener(), plugin);
		pluginManager.registerEvents(new PlayerChatListener(), plugin);

		pluginManager.registerEvents(new BlockListener(), plugin);
		pluginManager.registerEvents(new WeatherChangeListener(), plugin);

		pluginManager.registerEvents(new ServerPingListener(), plugin);
	}
}