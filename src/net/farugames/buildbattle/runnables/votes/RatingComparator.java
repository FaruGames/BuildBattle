package net.farugames.buildbattle.runnables.votes;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

public class RatingComparator implements Comparator<UUID> {
	
	private Map<UUID, Integer> base;
	
	public RatingComparator(Map<UUID, Integer> base) {
		this.base = base;
	}

	@Override
	public int compare(UUID o1, UUID o2) {
		if(base.get(o1) >= base.get(o2)) {
			return -1;
		} else {
			return 1;
		}
	}
	
}
