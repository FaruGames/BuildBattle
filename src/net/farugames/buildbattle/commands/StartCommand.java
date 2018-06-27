package net.farugames.buildbattle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.PluginMethods;
import net.farugames.buildbattle.runnables.LobbyRunnable;

public class StartCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.isOp()) {
			if (Main.MINPLAYER > Bukkit.getOnlinePlayers().size()) {
				new LobbyRunnable().runTaskTimer(Main.instance, 0L, 20L);
				LobbyRunnable.start = true;
			}
			LobbyRunnable.timer = 10;
			LobbyRunnable.startCommand = true;
			Bukkit.broadcastMessage(PluginMethods.getChatPrefix() + "§6§l" + sender.getName() + " §ca forcé le démarrage de la partie !");
		}
		return false;
	}

}
