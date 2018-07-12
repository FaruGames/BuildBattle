package net.farugames.buildbattle.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.api.tools.board.TeamsTagsManager;
import net.farugames.buildbattle.GameStatus;
import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.PluginMethods;
import net.farugames.buildbattle.boards.ScoreboardManager;
import net.farugames.buildbattle.runnables.LobbyRunnable;
import net.farugames.buildbattle.utils.TitleManager;

public class PlayerJoinListener implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(p.getUniqueId());
		Player fp = faruPlayer.getPlayer();
		Rank r = faruPlayer.getRank();

		e.setJoinMessage(null);
		
		if (!(GameStatus.isStatus(GameStatus.LOBBY))) {
			fp.kickPlayer(ChatColor.RED + "La partie est déjà en cours.");
			return;
			//soon arraylist spectator
		}
		
		TeamsTagsManager.setNameTag(fp, r.getOrder(), r.getColor() + r.getPrefix() + " ");
		TitleManager.sendActionBar(fp, "§2Developer: §aChocoIG");
		new ScoreboardManager(fp).loadScoreboard();
		
		fp.setGameMode(GameMode.ADVENTURE);
		fp.setMaxHealth(20);
		fp.setHealth(20);
		fp.setFoodLevel(20);
		fp.getInventory().clear();
		fp.playSound(fp.getLocation(), Sound.BLOCK_NOTE_HARP, 2F, 1F);
		fp.teleport(new Location(Bukkit.getWorld("world"), -329.5, 137 + 2, -203.5, 90.0f, 0.0f));
		fp.sendMessage("\n"
				+ Main.centerText("§f[§d?§f] Information about your statistics" + "\n") 
				+ "\n"
				+ Main.centerText("§eYou are level §6" + "0" + "§e.") + "\n"
				+ "    §7Ranking: §bhttps://stats.farugames.net/player/" + fp.getName() +"\n");

		for (Player op : Bukkit.getOnlinePlayers()) {
			op.sendMessage(PluginMethods.getChatPrefix() + r.getColor() + r.getPrefix() + " " + p.getName()
					+ " §ea joined the game §a(" + Bukkit.getOnlinePlayers().size() + "/" + Main.MAXPLAYER + ")");
		}
		if ((Bukkit.getOnlinePlayers().size() >= Main.MINPLAYER) && (!(LobbyRunnable.start))) {
			new LobbyRunnable().runTaskTimer(Main.instance, 0L, 20L);
			LobbyRunnable.start = true;
		}
	}

}