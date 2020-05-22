package de.rexlnico.teleportals.commands;

import de.rexlnico.teleportals.main.Main;
import de.rexlnico.teleportals.methodes.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteTeleportal implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("Teleportals.deleteTeleportal")) {
                if (args.length == 1) {
                    String name = args[0];
                    Teleport teleport = Main.getPlugin().getTeleportManager().getTeleport(name);
                    if (teleport == null) {
                        player.sendMessage("§cEs konnten kein Teleportal gelöscht werden!");
                        return false;
                    }
                    Main.getPlugin().getTeleportManager().removeTeleport(name);
                    player.sendMessage("§aDas Teleportal §6" + name + "§a wurde gelöscht!");
                } else {
                    sendHelp(player);
                }
            }
        }
        return false;
    }

    private void sendHelp(Player player) {
        player.sendMessage("§c/deleteTeleport <Name>");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if (args.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            for(Teleport teleport : Main.getPlugin().getTeleportManager().getTeleports()){
                list.add(teleport.getName());
            }
            return list;
        }
        return new ArrayList<>();
    }

}
