package net.farugames.buildbattle.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.farugames.buildbattle.GameStatus;
import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.boards.ScoreboardManager;

public class ScoreboardRunnable extends BukkitRunnable {

	public ScoreboardRunnable() {}
	
	@Override
	public void run() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(ScoreboardManager.scoreboardGame.containsKey(player)) {
				
				/** LOBBY **/
				if(GameStatus.isStatus(GameStatus.LOBBY)) {
					ScoreboardManager.scoreboardGame.get(player).setLine(2, "§7Joueurs: §f" + Bukkit.getOnlinePlayers().size() + "/" + Main.MAXPLAYER);
				}
				
			}
		}
	}

}
