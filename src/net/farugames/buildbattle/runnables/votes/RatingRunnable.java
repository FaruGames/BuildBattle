package net.farugames.buildbattle.runnables.votes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.farugames.buildbattle.GameStatus;
import net.farugames.buildbattle.Main;
import net.farugames.buildbattle.PluginMethods;
import net.farugames.buildbattle.arenas.Arena;
import net.farugames.buildbattle.arenas.ArenaManager;
import net.farugames.buildbattle.boards.ScoreboardManager;
import net.farugames.buildbattle.utils.TitleManager;

public class RatingRunnable extends BukkitRunnable {

	public static List<RatingPlayer> rates = new ArrayList<RatingPlayer>();
	public static UUID actual;
	public static String win = null;
	
	private int id;
	
	public RatingRunnable() {
		for(UUID uuid : ArenaManager.arenaPlayer.keySet()) {
			rates.add(new RatingPlayer(uuid));
		}
	}
	
	@Override
	public void run() {
		
		//ID != 0
		if(id != 0) {
			RatingPlayer rating = getRatePointByUUID(actual);
			for(Entry<UUID, Integer> points : rating.actualRate.entrySet()) {
				rating.addVote(points.getValue());
			}
			int finalRating = rating.getVote();
			rating.vote = finalRating;
		}
		
		//FIN DE PARTIE
		if(RatingPlayer.joueurs.size() == 0) {
			
			actual = null;
			GameStatus.setStatus(GameStatus.FINISH);
			
			Map<UUID, Integer> votes = new HashMap<UUID, Integer>();
			for(RatingPlayer rpp : rates) {
				votes.put(rpp.getUUID(), rpp.getVote() / Bukkit.getOnlinePlayers().size());
			}
			
			RatingComparator comparator = new RatingComparator(votes);
			TreeMap<UUID, Integer> filtre = new TreeMap<UUID, Integer>(comparator);
			filtre.putAll(votes);
			
			int position = 1;
			for(Entry<UUID, Integer> winner : filtre.entrySet()) {
				UUID winUUID = winner.getKey();
				String winName = ArenaManager.names.get(winUUID);
				
				if(position == 1) {
					RatingRunnable.win = winName;
					Arena winCuboid = ArenaManager.arenaPlayer.get(winUUID).getCuboid();
					for(Player allPlayers : Bukkit.getOnlinePlayers()) {
						allPlayers.teleport(winCuboid.getCenter());
						TitleManager.sendTitle(allPlayers, "§e§lBuildBattle", "§b§lGagnant de la partie: §c§l" + winName, 40);
						ScoreboardManager.scoreboardGame.get(allPlayers).setLine(2, "§6Gagnant: §d§l" + winName);
					}
					Bukkit.broadcastMessage("§6§m+------§e§m----------§f§m--------------------§e§m----------§6§m-----+");
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("      §7➜ §e§lRésultat:");
					Bukkit.broadcastMessage("          §8■ §6§lGagnant: §d§l" + winName + " §8- §e§lPoints: §b" + winner.getValue());
				} else if(position <= 3) {
					Bukkit.broadcastMessage("          §8■ §6" + position + "ème: §a" + winName + " §8- §e§lPoints: §b" + winner.getValue());
				} else {
					Player player = Bukkit.getPlayer(winUUID);
					if(player != null) {
						player.sendMessage("");
						player.sendMessage("      §6Votre position: §a" + position + "ème §8- §e§lPoints: §b" + winner.getValue());
					}
				}
				position++;
			}
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("    §7§oCliquez ici pour partager le résultat sur §b§lTwitter§7§o.");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§6§m+------§e§m----------§f§m--------------------§e§m----------§6§m-----+");
			
			for (Player op : Bukkit.getOnlinePlayers()) {
				op.sendMessage("");
				op.sendMessage("      §7➜ §e§lGains:");
				op.sendMessage("          §8■ §7Coins: §e" + "0" + " ⛂");
				op.sendMessage("          §8■ §7Crédits: §d" + "0" + " ⛃");
				op.sendMessage("          §8■ §7Expérience: §b" + "0" + " ✯" + "§8[" + "§a§m+++++§f§m+++++" + "§8]");
				op.sendMessage("");
				op.sendMessage("§6§m+------§e§m----------§f§m--------------------§e§m----------§6§m-----+");
			}
			
			Main.instance.finish();
			this.cancel();
			return;
		}
		
		//TP RANDOM
		Random random = new Random();
		UUID uuid = RatingPlayer.joueurs.get(random.nextInt(RatingPlayer.joueurs.size()));
		String playerName = ArenaManager.names.get(uuid);
		Arena arena = ArenaManager.arenaPlayer.get(uuid).getCuboid();
		actual = uuid;
		RatingPlayer.joueurs.remove(uuid);
		for(Player playerOnline : Bukkit.getOnlinePlayers()) {
			ScoreboardManager.scoreboardGame.get(playerOnline).setLine(2, "§7Créateur: §d" + playerName);
			playerOnline.teleport(arena.getCenter());
			TitleManager.sendTitle(playerOnline, PluginMethods.getPrefix(), "§aCréateur de cette construction: §c§l" + playerName, 20);
			TitleManager.sendActionBar(playerOnline, "§7§lVOUS AVEZ §e§l15 SECONDES §7§lPOUR VOTER !");
		}
		
		// ID
		id++;
	}
	
	public static RatingPlayer getRatePointByUUID(UUID uuid) {
		for(RatingPlayer player : rates) {
			if(player.getUUID() == uuid) {
				return player;
			}
		}
		return null;
	}

}
