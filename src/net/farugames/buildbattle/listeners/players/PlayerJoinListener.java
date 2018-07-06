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

		/* UN STATUS AUTRE QUE LE LOBBY */
		if (!(GameStatus.isStatus(GameStatus.LOBBY))) {
			p.kickPlayer(ChatColor.RED + "La partie est d�j� en cours.");
			return;
		}

		p.sendMessage("");
		p.sendMessage(Main.centerText("�f[�d?�f] Informations sur vos statistiques"));
		p.sendMessage("");
		p.sendMessage(Main.centerText("�eVous �tes niveau �6" + "0" + "�e."));
		p.sendMessage("    �7Classement: �bhttps://stats.farugames.net/player/" + fp.getName());
		p.sendMessage("");

		TeamsTagsManager.setNameTag(p, r.getOrder(), r.getColor() + r.getPrefix() + " ");

		for (Player op : Bukkit.getOnlinePlayers()) {
			op.sendMessage(PluginMethods.getChatPrefix() + r.getColor() + r.getPrefix() + " " + p.getName()
					+ " �ea rejoint la partie �a(" + Bukkit.getOnlinePlayers().size() + "/" + Main.MAXPLAYER + ")");
		}
		TitleManager.sendActionBar(p, "�2Developer: �aEtor");

		/* PARAMETRES */
		p.setGameMode(GameMode.ADVENTURE);
		p.setMaxHealth(20);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getInventory().clear();
		p.teleport(new Location(Bukkit.getWorld("world"), -329.5, 137 + 2, -203.5, 90.0f, 0.0f));
		new ScoreboardManager(fp).loadScoreboard();

		/* RUNNABLE */
		if ((Bukkit.getOnlinePlayers().size() >= Main.MINPLAYER) && (!(LobbyRunnable.start))) {
			new LobbyRunnable().runTaskTimer(Main.instance, 0L, 20L);
			LobbyRunnable.start = true;
		}
	}

}