package de.rexlnico.teleportals.main;

import de.rexlnico.teleportals.commands.*;
import de.rexlnico.teleportals.listeners.MoveEvent;
import de.rexlnico.teleportals.listeners.UseWand;
import de.rexlnico.teleportals.methodes.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main plugin;
    private PluginManager pm;
    private TeleportManager teleportManager;

    @Override
    public void onEnable() {
        plugin = this;
        pm = Bukkit.getPluginManager();
        teleportManager = new TeleportManager();

        getCommand("addRegion").setExecutor(new AddRegion());
        getCommand("setTeleport").setExecutor(new SetTeleportLocation());
        getCommand("clearRegions").setExecutor(new ClearRegions());
        getCommand("deleteTeleportal").setExecutor(new DeleteTeleportal());
        getCommand("listTeleportals").setExecutor(new ListTeleportals());

        pm.registerEvents(new UseWand(), this);
        pm.registerEvents(new MoveEvent(), this);

        Bukkit.getConsoleSender().sendMessage("§aTeleportals aktiviert");
    }

    @Override
    public void onDisable() {
        try {
            teleportManager.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Main getPlugin() {
        return plugin;
    }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }
}
