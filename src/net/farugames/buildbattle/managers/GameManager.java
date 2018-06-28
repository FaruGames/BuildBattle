package net.farugames.buildbattle.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import net.farugames.buildbattle.GameStatus;
import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.PluginMethods;
import net.farugames.buildbattle.arenas.Arena;
import net.farugames.buildbattle.arenas.ArenaManager;
import net.farugames.buildbattle.arenas.ArenaTheme;
import net.farugames.buildbattle.boards.ScoreboardManager;
import net.farugames.buildbattle.runnables.GameRunnable;
import net.farugames.buildbattle.utils.ItemBuilder;
import net.farugames.buildbattle.utils.TitleManager;

public class GameManager {

	public GameManager() {
		GameStatus.setStatus(GameStatus.GAME);
		new ArenaTheme();
		new GameRunnable().runTaskTimer(Main.instance, 0L, 20L);
	}

	public void loadGame() {
		for (Player op : Bukkit.getOnlinePlayers()) {
			ScoreboardManager.loadScoreboardGame(op);
			ArenaManager.names.put(op.getUniqueId(), op.getName());
			if (!(ArenaManager.arenaPlayer.containsKey(op.getUniqueId()))) {
				ArenaManager.arenaPlayer.put(op.getUniqueId(), new ArenaManager());
			}
			Arena arena = ArenaManager.arenaPlayer.get(op.getUniqueId()).getCuboid();

			op.setLevel(0);
			op.setGameMode(GameMode.CREATIVE);
			op.teleport(arena.getCenter());
			TitleManager.sendTitle(op, "", "§7Le thème de la partie: §a" + ArenaTheme.theme, 20);
			op.sendMessage("");
			op.sendMessage("§f[§d?§f] §fLa partie vient de commencer ! Construisez un(e) §a" + ArenaTheme.theme
					+ " §fet gagnez grâce à vos talents d'artiste !");
			op.sendMessage("");
			op.getInventory().setItem(8, new ItemBuilder().type(Material.NETHER_STAR).name("§bOptions" + PluginMethods.getRightClick()).build());
		}
	}
}
