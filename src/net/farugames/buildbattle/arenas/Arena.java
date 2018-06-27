package net.farugames.buildbattle.arenas;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class Arena {

	private String worldname;
	private Vector minimumPoint;
	private Vector maximumPoint;

	public Arena(Location minimum, Location maximum) {
		if (minimum == null || maximum == null) {
			throw new NullArgumentException("location minmum || location maximun is null");
		}
		if (minimum.getWorld() != maximum.getWorld()) {
			throw new IllegalStateException("world1 != world2");
		}
		this.worldname = minimum.getWorld().getName();
		this.minimumPoint = new Vector(Math.min(minimum.getX(), maximum.getX()),
				Math.min(minimum.getY(), maximum.getY()), Math.min(minimum.getZ(), maximum.getZ()));
		this.maximumPoint = new Vector(Math.max(minimum.getX(), maximum.getX()),
				Math.max(minimum.getY(), maximum.getY()), Math.max(minimum.getZ(), maximum.getZ()));
	}

	public boolean IsArena(Location location) {
		return location != null && location.getWorld().getName().equals(this.getWorldname())
				&& location.toVector().isInAABB(this.getMinimumPoint(), this.getMaximumPoint());
	}

	@SuppressWarnings("deprecation")
	public void setTypePerY(Material m, int s, int y) {
		if (!m.isSolid()) {
			throw new IllegalStateException("material:" + m + " is not a bloc.");
		}
		World world = Bukkit.getWorld(getWorldname());
		if (world != null) {
			for (int x = this.getMinimumPoint().getBlockX(); x <= this.getMaximumPoint().getBlockX(); x++) {
				for (int z = this.getMinimumPoint().getBlockZ(); z <= this.getMaximumPoint().getBlockZ(); z++) {
					Location loc = new Location(world, x, y, z);
					Block block = loc.getBlock();
					block.setType(m);
					block.setData((byte) s);
				}
			}
		}
	}

	public List<Location> getShower() {
		List<Location> locations = new ArrayList<>();
		World world = Bukkit.getWorld(getWorldname());
		if (world != null) {
			for (int x = this.getMinimumPoint().getBlockX(); x <= this.getMaximumPoint().getBlockX(); x++) {
				for (int z = this.getMinimumPoint().getBlockZ(); z <= this.getMaximumPoint().getBlockZ(); z++) {
					Location loc = new Location(world, x, 20, z);
					locations.add(loc);
				}
			}
		}
		return locations;
	}

	public double getLowerX() {
		return this.minimumPoint.getX();
	}

	public double getLowerY() {
		return this.minimumPoint.getY();
	}

	public double getLowerZ() {
		return this.minimumPoint.getZ();
	}

	public double getUpperX() {
		return this.maximumPoint.getX();
	}

	public double getUpperY() {
		return this.maximumPoint.getY();
	}

	public double getUpperZ() {
		return this.maximumPoint.getZ();
	}

	public Location getCenter() {
		int x1 = (int) (this.getUpperX() + 1);
		int y1 = (int) (this.getUpperY() + 1);
		int z1 = (int) (this.getUpperZ() + 1);
		return new Location(Bukkit.getWorld(getWorldname()), this.getLowerX() + (x1 - this.getLowerX()) / 2.0,
				this.getLowerY() + (y1 - this.getLowerY()) / 2.0, this.getLowerZ() + (z1 - this.getLowerZ()) / 2.0);
	}

	public String getWorldname() {
		return worldname;
	}

	public Vector getMinimumPoint() {
		return minimumPoint;
	}

	public Vector getMaximumPoint() {
		return maximumPoint;
	}
}