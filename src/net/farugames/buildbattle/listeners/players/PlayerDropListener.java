package net.farugames.buildbattle.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import net.farugames.buildbattle.GameStatus;

public class PlayerDropListener implements Listener {
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if(!(GameStatus.isStatus(GameStatus.GAME))) {
			event.setCancelled(true);
		}
	}
}