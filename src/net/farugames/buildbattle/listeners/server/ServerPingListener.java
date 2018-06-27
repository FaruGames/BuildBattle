package net.farugames.buildbattle.listeners.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.farugames.buildbattle.GameStatus;
import net.farugames.buildbattle.PluginMethods;

public class ServerPingListener implements Listener {
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent event) {
		if(GameStatus.isStatus(GameStatus.LOBBY)) {
			event.setMotd(PluginMethods.getChatPrefix() + "§eLa partie va bientôt commencer.");
		} else {
			event.setMotd(PluginMethods.getChatPrefix() + "§cLa partie est déjà  en cours.");
		}
	}	
}