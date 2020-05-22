package de.rexlnico.teleportals.methodes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Teleport {

    private String name;
    private Location teleportLocation;
    private ArrayList<Region> regions;
    private File file;
    private YamlConfiguration cfg;

    public Teleport(String name) {
        this.name = name;
        file = new File("plugins/Teleportals/portals/" + name + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        this.regions = new ArrayList<>();
        if (file.exists()) {
            this.teleportLocation = (Location) cfg.get("Location");
            loadRegions();
        }
    }

    private void loadRegions() {
        for (String sRegion : cfg.getStringList("Regions")) {
            String[] locsS = sRegion.split("-");
            String[] loc1S = locsS[0].split(";");
            String[] loc2S = locsS[1].split(";");
            Location loc1 = new Location(Bukkit.getWorld(loc1S[0]), Double.valueOf(loc1S[1]), Double.valueOf(loc1S[2]), Double.valueOf(loc1S[3]));
            Location loc2 = new Location(Bukkit.getWorld(loc2S[0]), Double.valueOf(loc2S[1]), Double.valueOf(loc2S[2]), Double.valueOf(loc2S[3]));
            Region region = new Region(loc1, loc2);
            regions.add(region);
        }
    }

    public void addRegion(Region region) {
        regions.add(region);
    }

    public void removeRegion(Region region) {
        if (regions.contains(region)) regions.remove(region);
    }

    public void setTeleportLocation(Location location) {
        teleportLocation = location;
    }

    public void clearRegions() {
        regions.clear();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public boolean isLocationTeleport(Location location) {
        for (Region region : regions) {
            if (region.inRegion(location)) {
                return true;
            }
        }
        return false;
    }

    public Location getTeleportLocation() {
        return teleportLocation;
    }

    public void save() throws IOException {
        cfg.set("Name", name);
        cfg.set("Location", teleportLocation);
        ArrayList<String> regionsList = new ArrayList<>();
        for (Region region : regions) {
            String string = region.getLoc1().getWorld().getName() + ";" + region.getLoc1().getX() + ";" + region.getLoc1().getY() + ";" + region.getLoc1().getZ() + "-" + region.getLoc2().getWorld().getName() + ";" + region.getLoc2().getX() + ";" + region.getLoc2().getY() + ";" + region.getLoc2().getZ();
            regionsList.add(string);
        }
        cfg.set("Regions", regionsList);
        cfg.save(file);
    }

    public void delete() {
        file.delete();
    }

}
