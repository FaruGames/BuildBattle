package net.farugames.buildbattle.runnables.votes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RatingPlayer {
	
	public static List<UUID> joueurs = new ArrayList<UUID>();
	
	public UUID uuid;
	public int vote;
	
	public Map<UUID, Integer> actualRate = new HashMap<UUID, Integer>();
	
	public RatingPlayer(UUID uuid) {
		this.uuid = uuid;
		joueurs.add(uuid);
	}
	
	public void addVote(int vote) {
		this.vote += vote;
	}
	
	public int getVote() {
		return vote;
	}
	
	public UUID getUUID() {
		return uuid;
	}
}