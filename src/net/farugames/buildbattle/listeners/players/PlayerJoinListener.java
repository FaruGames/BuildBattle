package net.farugames.buildbattle.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.farugames.buildbattle.GameStatus;
import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.PluginMethods;
import net.farugames.buildbattle.boards.ScoreboardManager;
import net.farugames.buildbattle.runnables.LobbyRunnable;
import net.farugames.buildbattle.utils.TitleManager;

public class PlayerJoinListener implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player p = event.getPlayer();

		/* UN STATUS AUTRE QUE LE LOBBY */
		if (!(GameStatus.isStatus(GameStatus.LOBBY))) {
			event.setJoinMessage(null);
			p.kickPlayer(ChatColor.RED + "La partie est déjà  en cours.");
			return;
		}

		p.sendMessage("");
		p.sendMessage(Main.centerText("§f[§d?§f] Informations sur vos statistiques"));
		p.sendMessage("");
		p.sendMessage(Main.centerText("§eVous êtes niveau §6" + "0" + "§e."));
		p.sendMessage("    §7Classement: §bhttps://stats.farugames.net/player/" + p.getName());
		p.sendMessage("");

		event.setJoinMessage(PluginMethods.getChatPrefix() + "§7" + p.getName() + " §ea rejoint la partie §a("
				+ Bukkit.getOnlinePlayers().size() + "/" + Main.MAXPLAYER + ")");
		TitleManager.sendActionBar(p, "§2Developer: §aEtor");

		/* PARAMETRES */
		p.setGameMode(GameMode.ADVENTURE);
		p.setMaxHealth(20);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getInventory().clear();
		p.teleport(new Location(Bukkit.getWorld("world"), -329.5, 137 + 2, -203.5, 90.0f, 0.0f));
		new ScoreboardManager(p).loadScoreboard();

		/* RUNNABLE */
		if ((Bukkit.getOnlinePlayers().size() >= Main.MINPLAYER) && (!(LobbyRunnable.start))) {
			new LobbyRunnable().runTaskTimer(Main.instance, 0L, 20L);
			LobbyRunnable.start = true;
		}
	}

}