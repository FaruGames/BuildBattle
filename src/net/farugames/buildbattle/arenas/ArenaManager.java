package net.farugames.buildbattle.arenas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class ArenaManager {
	
	public static Map<UUID, ArenaManager> arenaPlayer = new HashMap<UUID, ArenaManager>();
	public static Map<UUID, String> names = new HashMap<UUID, String>();
 	
	public static List<Arena> maps = new ArrayList<Arena>();
	
	private World world = Bukkit.getWorld("world");
	private Arena cuboid;
	
	public ArenaManager() {
		if(maps.size() == 0) {
			maps.add(new Arena(new Location(world, -472, 89, -44), new Location(world, -510, 140, -6)));
			maps.add(new Arena(new Location(world, -472, 89, 127), new Location(world, -510, 140, 165)));
			maps.add(new Arena(new Location(world, -472, 89, 298), new Location(world, -510, 140, 336)));
			maps.add(new Arena(new Location(world, -472, 89, 469), new Location(world, -510, 140, 507)));
			maps.add(new Arena(new Location(world, -662, 89, -44), new Location(world, -700, 140, -6)));
			maps.add(new Arena(new Location(world, -662, 89, 127), new Location(world, -700, 140, 165)));
			maps.add(new Arena(new Location(world, -662, 89, 298), new Location(world, -700, 140, 336)));
			maps.add(new Arena(new Location(world, -662, 89, 469), new Location(world, -700, 140, 507)));	
			maps.add(new Arena(new Location(world, -852, 89, -44), new Location(world, -890, 140, -6)));
			maps.add(new Arena(new Location(world, -852, 89, 127), new Location(world, -890, 140, 165)));
			maps.add(new Arena(new Location(world, -852, 89, 298), new Location(world, -890, 140, 336)));
			maps.add(new Arena(new Location(world, -852, 89, 469), new Location(world, -890, 140, 507)));	
			maps.add(new Arena(new Location(world, -1024, 89, -44), new Location(world, -1080, 140, -6)));
			maps.add(new Arena(new Location(world, -1024, 89, 127), new Location(world, -1080, 140, 165)));
			maps.add(new Arena(new Location(world, -1024, 89, 298), new Location(world, -1080, 140, 336)));
			maps.add(new Arena(new Location(world, -1024, 89, 469), new Location(world, -1080, 140, 507)));
		}
		Random random = new Random();
		Arena arena = maps.get(random.nextInt(maps.size()));
		this.cuboid = arena;
		maps.remove(arena);
	}

	public Arena getCuboid() {
		return cuboid;
	}
}
