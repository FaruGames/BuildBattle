package net.farugames.buildbattle.listeners.players;

import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.farugames.buildbattle.GameStatus;
import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.PluginMethods;
import net.farugames.buildbattle.arenas.ArenaManager;
import net.farugames.buildbattle.runnables.votes.RatingRunnable;

public class PlayerInteractListener implements Listener {

	private String rightclick = ChatColor.GRAY + " (Clique droit)";

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(GameStatus.isStatus(GameStatus.GAME)) {
			if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
				Block b = p.getTargetBlock((Set<Material>) null, 200);
				Location loc = b.getLocation();
				if(Main.instance.isCancelled(p, loc) == false) {
					p.getWorld().getBlockAt(loc).setType(Material.AIR);
				}
			}
		}
		if (GameStatus.isStatus(GameStatus.VOTE)) {
			if (e.getItem() != null) {
				if (e.getItem().getType() == null)
					return;
				if ((e.getAction().equals(Action.RIGHT_CLICK_AIR))
						|| (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
					if (e.getItem().getType() != Material.AIR) {
						e.setCancelled(true);
						if (e.getItem().getType().equals(Material.STAINED_CLAY)) {

							UUID last = RatingRunnable.actual;

							/* VOTE POUR LUI MEME */
							if (ArenaManager.names.get(last).equalsIgnoreCase(p.getName())) {
								p.sendMessage(PluginMethods.getChatPrefix() + ChatColor.RED
										+ "Tu ne peux pas voter pour toi même !");
								return;
							}

							short data = e.getItem().getDurability();

							/* SUPER MOCHE */
							if (data == (short) 14) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(p.getUniqueId(), 5);
							}

							/* MOCHE */
							if (data == (short) 6) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(p.getUniqueId(), 10);
							}

							/* OK */
							if (data == (short) 5) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(p.getUniqueId(), 15);
							}

							/* BIEN */
							if (data == (short) 13) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(p.getUniqueId(), 20);
							}

							/* SUPER BIEN */
							if (data == (short) 11) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(p.getUniqueId(), 25);
							}

							/* MAGNIFIQUE */
							if (data == (short) 4) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(p.getUniqueId(), 30);
							}

							String voteName = e.getItem().getItemMeta().getDisplayName().replace(rightclick, "");
							p.sendMessage(PluginMethods.getChatPrefix() + "§eVous venez de voter: " + voteName);
						}
					}
				}
			}
		}
	}

}
