package de.rexlnico.teleportals.listeners;

import de.rexlnico.teleportals.main.Main;
import de.rexlnico.teleportals.methodes.PlayerPortalTeleportEvent;
import de.rexlnico.teleportals.methodes.Teleport;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent e) {
        if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getY() != e.getTo().getY() || e.getFrom().getZ() != e.getTo().getZ()) {
            if (Main.getPlugin().getTeleportManager().isLocationPortal(e.getTo())) {
                Teleport teleport = Main.getPlugin().getTeleportManager().getTeleportFromlocation(e.getTo());
                Player player = e.getPlayer();
                if (teleport.getTeleportLocation() != null) {
                    PlayerPortalTeleportEvent event = new PlayerPortalTeleportEvent(player, teleport, e.getTo(), teleport.getTeleportLocation());
                    Bukkit.getPluginManager().callEvent(event);
                    if (!event.isCancelled()) {
                        player.teleport(teleport.getTeleportLocation());
                        player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1, 1);
                    }
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);
                }
            }
        }
    }

}
