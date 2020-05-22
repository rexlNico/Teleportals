package de.rexlnico.teleportals.listeners;

import de.rexlnico.teleportals.methodes.Region;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class UseWand implements Listener {

    public static HashMap<Player, Region> regions = new HashMap<>();
    private static ArrayList<Player> used = new ArrayList<>();

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getItemInHand().getType().equals(Material.GOLDEN_AXE)) {
                if (!used.contains(player)) {
                    if (regions.containsKey(player)) {
                        regions.get(player).setLoc2(e.getClickedBlock().getLocation());
                    } else {
                        regions.put(player, new Region(null, e.getClickedBlock().getLocation()));
                    }
                    player.sendMessage("§cLocation 2 wurde gesetzt!");
                    used.add(player);
                } else {
                    used.remove(player);
                }
            }
        } else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (player.getItemInHand().getType().equals(Material.GOLDEN_AXE)) {
                e.setCancelled(true);
                if (regions.containsKey(player)) {
                    regions.get(player).setLoc1(e.getClickedBlock().getLocation());
                } else {
                    regions.put(player, new Region(e.getClickedBlock().getLocation(), null));
                }
                player.sendMessage("§cLocation 1 wurde gesetzt!");
            }
        }
    }

}
