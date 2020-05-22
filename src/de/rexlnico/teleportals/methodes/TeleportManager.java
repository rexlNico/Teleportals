package de.rexlnico.teleportals.methodes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TeleportManager {

    private ArrayList<Teleport> teleports;
    private File file;
    private YamlConfiguration cfg;

    public TeleportManager() {
        teleports = new ArrayList<>();
        file = new File("plugins/Teleportals/Teleports.list");
        cfg = YamlConfiguration.loadConfiguration(file);
        if (file.exists()) {
            loadTeleports();
        }
    }

    private void loadTeleports() {
        for (String teleport : cfg.getStringList("List")) {
            addTeleport(new Teleport(teleport));
        }
    }

    public void addTeleport(Teleport teleport) {
        teleports.add(teleport);
    }

    public void removeTeleport(String name) {
        if (existTeleport(name)) {
            Teleport teleport = getTeleport(name);
            teleport.delete();
            teleports.remove(teleport);
        }
    }

    public boolean existTeleport(String name) {
        for (Teleport teleport : teleports) {
            if (teleport.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Teleport getTeleport(String name) {
        if (existTeleport(name)) {
            for (Teleport teleport : teleports) {
                if (teleport.getName().equals(name)) {
                    return teleport;
                }
            }
        }
        return null;
    }

    public ArrayList<Teleport> getTeleports() {
        return teleports;
    }

    public boolean isLocationPortal(Location location) {
        return getTeleportFromlocation(location) != null;
    }

    public Teleport getTeleportFromlocation(Location location) {
        for (Teleport teleport : teleports) {
            if (teleport.isLocationTeleport(location)) {
                return teleport;
            }
        }
        return null;
    }

    public void save() throws IOException {
        ArrayList<String> teleportList = new ArrayList<>();
        for (Teleport teleport : teleports) {
            teleport.save();
            teleportList.add(teleport.getName());
        }
        cfg.set("List", teleportList);
        cfg.save(file);
    }

}
