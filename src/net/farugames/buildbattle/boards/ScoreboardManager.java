package net.farugames.buildbattle.boards;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.farugames.api.tools.board.ScoreboardSign;
import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.PluginMethods;
import net.farugames.buildbattle.arenas.ArenaTheme;
import net.farugames.buildbattle.runnables.GameRunnable;
import net.farugames.buildbattle.runnables.LobbyRunnable;

public class ScoreboardManager {

	public Player player;
	public ScoreboardSign scoreboardSign;

	public static Map<Player, ScoreboardSign> scoreboardGame = new HashMap<Player, ScoreboardSign>();

	public ScoreboardManager(Player player) {
		this.player = player;
		this.scoreboardSign = new ScoreboardSign(player, player.getName());
		scoreboardGame.put(player, scoreboardSign);
	}

	public void loadScoreboard() {
		this.scoreboardSign.setObjectiveName(PluginMethods.getPrefix());
		this.scoreboardSign.create();
		scoreboardGame.get(player).setLine(1, "§1");
		scoreboardGame.get(player).setLine(2,
				"§7Joueurs: §f" + Bukkit.getOnlinePlayers().size() + "/" + Main.MAXPLAYER);
		scoreboardGame.get(player).setLine(3, "§2");
		scoreboardGame.get(player).setLine(4, "§cEn attente de");
		scoreboardGame.get(player).setLine(5, "§c joueurs...");
		scoreboardGame.get(player).setLine(6, "§3");
		scoreboardGame.get(player).setLine(7,
				"§7Lancement: §f" + new SimpleDateFormat("mm:ss").format(new Date(LobbyRunnable.timer * 1000)));
		scoreboardGame.get(player).setLine(8, "§4");
		scoreboardGame.get(player).setLine(9, "§7Carte: §bstring");
		scoreboardGame.get(player).setLine(10, "§5");
		scoreboardGame.get(player).setLine(11, "§eplay.farugames.net");
		scoreboardGame.get(player).setLine(12, "§8§m+---------------+");
	}

	public static void loadScoreboardGame(Player playerTwo) {
		if (ScoreboardManager.scoreboardGame.containsKey(playerTwo)) {
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(1);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(2);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(3);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(4);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(5);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(6);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(7);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(8);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(9);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(10);
			ScoreboardManager.scoreboardGame.get(playerTwo).removeLine(11);
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(1, "§1");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(2,
					"§7Temps: §b" + new SimpleDateFormat("mm:ss").format(new Date(GameRunnable.timer * 1000)));
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(3, "§2");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(4, "§7Thème: §a" + ArenaTheme.theme);
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(5, "§3");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(6,
					"§7Joueurs: §9" + Bukkit.getOnlinePlayers().size());
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(7, "§4");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(8, "§7Carte: §bstring");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(9, "§5");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(10, "§eplay.farugames.net");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(11, "§8§m+---------------+");
		}
	}

	public static void loadScoreboardVote(Player playerTwo) {
		if (ScoreboardManager.scoreboardGame.containsKey(playerTwo)) {
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(1, "§1");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(2, "§7Créateur: ");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(3, "§2");
			;
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(4, "§7Thème: §a" + ArenaTheme.theme);
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(5, "§3");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(6,
					"§7Joueurs: §9" + Bukkit.getOnlinePlayers().size());
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(7, "§4");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(8, "§7Carte: §bstring");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(9, "§5");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(10, "§eplay.farugames.net");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(11, "§8§m+---------------+");
		}
	}
}