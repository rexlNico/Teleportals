package de.rexlnico.teleportals.commands;

import de.rexlnico.teleportals.main.Main;
import de.rexlnico.teleportals.methodes.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListTeleportals implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("Teleportals.list")) {
                if (args.length == 0) {
                    if (Main.getPlugin().getTeleportManager().getTeleports().isEmpty()) {
                        player.sendMessage("§aEs existieren keine Teleportals");
                        return false;
                    }
                    player.sendMessage("§aListe aller Teleportals");
                    for (Teleport teleport : Main.getPlugin().getTeleportManager().getTeleports()) {
                        player.sendMessage("§e- "+teleport.getName());
                    }
                } else {
                    sendHelp(player);
                }
            }
        }
        return false;
    }

    private void sendHelp(Player player) {
        player.sendMessage("§c/listTeleports");
    }

}
