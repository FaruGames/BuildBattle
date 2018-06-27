package net.farugames.buildbattle.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if(e.getPlayer().isOp()) {
			e.setFormat("§4Owner " + e.getPlayer().getName() + " §f» §f" +  e.getMessage());
		} else {
			e.setFormat("§7" + e.getPlayer().getName() + " §f» §7" +  e.getMessage());
		}
	}

}
