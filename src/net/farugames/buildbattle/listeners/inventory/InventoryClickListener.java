package net.farugames.buildbattle.listeners.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.farugames.buildbattle.PluginMethods;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null)
				return;
			switch (e.getCurrentItem().getType()) {
			case NETHER_STAR:
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bOptions" + PluginMethods.getRightClick())) {
					e.setCancelled(true);
					p.sendMessage("options");
				}
				break;
			default:
				break;
			}
		}
	}

}
