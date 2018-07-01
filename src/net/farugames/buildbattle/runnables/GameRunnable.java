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
		for(Player op : Bukkit.getOnlinePlayers()) {
			TitleManager.sendActionBar(op, "§9§lLancement des votes dans: §d§l" + new SimpleDateFormat("mm:ss").format(new Date(timer * 1000)));
			if(ScoreboardManager.scoreboardGame.containsKey(op)) {
				ScoreboardManager.scoreboardGame.get(op).setLine(2, "§7Temps: §b" + new SimpleDateFormat("mm:ss").format(new Date(GameRunnable.timer * 1000)));
				ScoreboardManager.scoreboardGame.get(op).setLine(6, "§7Joueurs: §9" + Bukkit.getOnlinePlayers().size());
				if((timer == 60) || (timer == 120) || (timer == 180) || (timer == 240) || (timer == 300) || (timer == 360) || (timer == 420)){
					TitleManager.sendTitle(op, "§9" + new SimpleDateFormat("m").format(new Date(GameRunnable.timer * 1000)) + " §9minutes" , "§bremaining !", 20);
				}
			}
		}
		
		
		
		timer--;
	}

}
