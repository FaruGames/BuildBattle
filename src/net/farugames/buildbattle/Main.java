package net.farugames.buildbattle;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.farugames.buildbattle.arenas.Arena;
import net.farugames.buildbattle.arenas.ArenaManager;
import net.farugames.buildbattle.commands.StartCommand;
import net.farugames.buildbattle.listeners.ListenerManager;
import net.farugames.buildbattle.runnables.ScoreboardRunnable;
import net.farugames.buildbattle.utils.ServerSendManager;
import net.farugames.buildbattle.utils.WorldManager;

public class Main extends JavaPlugin {
	public static int MINPLAYER = 4;
	public static int MAXPLAYER = 16;
	public static Main instance;

	public void onLoad() {
		instance = this;
		GameStatus.setStatus(GameStatus.LOBBY);
		super.onLoad();
	}

	public void onEnable() {
		System.out.println();
		new ListenerManager(this).registerListener();
		new ScoreboardRunnable().runTaskTimer(instance, 0L, 20L);
		getCommand("gamestart").setExecutor(new StartCommand());
		super.onEnable();
	}

	public void onDisable() {
		WorldManager.deleteWorld("world");
		File from = new File("mapreset");
		File to = new File("world");
		try {
			WorldManager.copyFolder(from, to);
		} catch (IOException e) {
			System.err.println("Erreur lors de la copie de la map!");
		}
		super.onDisable();
	}

	public boolean isCancelled(Player player, Location location) {
		Arena cuboid = ((ArenaManager) ArenaManager.arenaPlayer.get(player.getUniqueId())).getCuboid();
		if (cuboid.IsArena(location)) {
			return false;
		}
		return true;
	}

	public void finish() {
		Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
			public void run() {
				for (Player op : Bukkit.getOnlinePlayers()) {
					ServerSendManager.sendToServer(op, "hub1");
				}
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
			}
		}, 600L);
	}

	public static String centerText(String text) {
		int space = (int) Math.round((80.0D - 1.4D * text.length()) / 2.0D);
		return repeat(" ", space) + text;
	}

	private static String repeat(String text, int times) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < times; i++) {
			stringBuilder.append(text);
		}
		return stringBuilder.toString();
	}
}
