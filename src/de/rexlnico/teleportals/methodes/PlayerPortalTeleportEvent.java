package de.rexlnico.teleportals.methodes;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerPortalTeleportEvent extends Event implements Cancellable {

    private static HandlerList handlerList = new HandlerList();

    private Player player;
    private Teleport teleport;
    private Location from;
    private Location to;
    private boolean cancelled = false;

    public PlayerPortalTeleportEvent(Player player, Teleport teleport, Location from, Location to) {
        this.player = player;
        this.teleport = teleport;
        this.from = from;
        this.to = to;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public Teleport getTeleport() {
        return teleport;
    }

    public Location getTo() {
        return to;
    }

    public Location getFrom() {
        return from;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
