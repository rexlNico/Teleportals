package de.rexlnico.teleportals.methodes;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Region {

    private Location loc1;
    private Location loc2;

    public Region(Location loc1, Location loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    public void setLoc1(Location loc1) {
        this.loc1 = loc1;
    }

    public void setLoc2(Location loc2) {
        this.loc2 = loc2;
    }

    public Location getLoc1() {
        return loc1;
    }

    public Location getLoc2() {
        return loc2;
    }

    public boolean inRegion(Location location) {
        Location minLoc = new Location(loc1.getWorld(), Math.min(loc1.getX(), loc2.getX()), Math.min(loc1.getY(), loc2.getY()), Math.min(loc1.getZ(), loc2.getZ()));
        Location maxLoc = new Location(loc2.getWorld(), Math.max(loc1.getX(), loc2.getX()), Math.max(loc1.getY(), loc2.getY()), Math.max(loc1.getZ(), loc2.getZ()));
        return (location.getWorld().getName().equals(minLoc.getWorld().getName())
                && minLoc.getX() <= location.getBlock().getLocation().getX()
                && minLoc.getY() <= location.getBlock().getLocation().getY()
                && minLoc.getZ() <= location.getBlock().getLocation().getZ()
                && maxLoc.getX() >= location.getBlock().getLocation().getX()
                && maxLoc.getY() >= location.getBlock().getLocation().getY()
                && maxLoc.getZ() >= location.getBlock().getLocation().getZ());
    }

    public boolean isNotNull(){
        return (loc1 != null && loc2 != null);
    }

}
