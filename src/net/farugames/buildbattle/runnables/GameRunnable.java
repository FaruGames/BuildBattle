package net.farugames.buildbattle.runnables;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.farugames.buildbattle.boards.ScoreboardManager;
import net.farugames.buildbattle.runnables.votes.RatingManager;
import net.farugames.buildbattle.utils.TitleManager;

public class GameRunnable extends BukkitRunnable {

	public static int timer = 480;
	
	@Override
	public void run() {
		
		/* TIMER 0 */
		if(timer == 0) {
			new RatingManager();
			this.cancel();
			return;
		}
		
		/* SETTINGS */
		for(Player player : Bukkit.getOnlinePlayers()) {
			TitleManager.sendActionBar(player, "§d§lLancement des votes dans: §e§l" + new SimpleDateFormat("mm:ss").format(new Date(timer * 1000)));
			if(ScoreboardManager.scoreboardGame.containsKey(player)) {
				ScoreboardManager.scoreboardGame.get(player).setLine(2, "§7Temps: §b" + new SimpleDateFormat("mm:ss").format(new Date(GameRunnable.timer * 1000)));
				ScoreboardManager.scoreboardGame.get(player).setLine(6, "§7Joueurs: §9" + Bukkit.getOnlinePlayers().size());
			}
		}
		
		timer--;
	}

}
