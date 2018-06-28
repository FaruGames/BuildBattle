package net.farugames.buildbattle.runnables.votes;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import net.farugames.buildbattle.GameStatus;
import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.PluginMethods;
import net.farugames.buildbattle.boards.ScoreboardManager;
import net.farugames.buildbattle.utils.ItemBuilder;

public class RatingManager {
	
	public RatingManager() {
		GameStatus.setStatus(GameStatus.VOTE);
		loadRate();
	}
	
	public void loadRate() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setGameMode(GameMode.ADVENTURE);
			player.setAllowFlight(true);
			player.setFlying(true);
			player.getInventory().clear();
			
			player.getInventory().setItem(0, new ItemBuilder().type(Material.STAINED_CLAY).name("§4§lSUPER MOCHE" + PluginMethods.getRightClick()).data((byte) 14).build());
			player.getInventory().setItem(1, new ItemBuilder().type(Material.STAINED_CLAY).name("§c§lMOCHE" + PluginMethods.getRightClick()).data((byte) 6).build());
			player.getInventory().setItem(2, new ItemBuilder().type(Material.STAINED_CLAY).name("§a§lOK" + PluginMethods.getRightClick()).data((byte) 5).build());
			player.getInventory().setItem(3, new ItemBuilder().type(Material.STAINED_CLAY).name("§2§lBIEN" + PluginMethods.getRightClick()).data((byte) 13).build());
			player.getInventory().setItem(4, new ItemBuilder().type(Material.STAINED_CLAY).name("§5§lSUPER BIEN" + PluginMethods.getRightClick()).data((byte) 11).build());
			player.getInventory().setItem(5, new ItemBuilder().type(Material.STAINED_CLAY).name("§6§lMAGNIFIQUE" + PluginMethods.getRightClick()).data((byte) 4).build());
			ScoreboardManager.loadScoreboardVote(player);
		}
		new RatingRunnable().runTaskTimer(Main.instance, 0L, 15 * 20L);
	}

}
