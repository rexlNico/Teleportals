package de.rexlnico.teleportals.commands;

import de.rexlnico.teleportals.listeners.UseWand;
import de.rexlnico.teleportals.main.Main;
import de.rexlnico.teleportals.methodes.Region;
import de.rexlnico.teleportals.methodes.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AddRegion implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("teleportals.addRegion")) {
                if (args.length == 1) {
                    String name = args[0];
                    Teleport teleport = Main.getPlugin().getTeleportManager().getTeleport(name);
                    if (!UseWand.regions.containsKey(player) || !UseWand.regions.get(player).isNotNull()) {
                        player.sendMessage("§cDu hast keine Region markiert!");
                        return false;
                    }
                    Region region = new Region(UseWand.regions.get(player).getLoc1(), UseWand.regions.get(player).getLoc2());
                    if (teleport != null) {
                        teleport.addRegion(region);
                    } else {
                        teleport = new Teleport(name);
                        teleport.addRegion(region);
                        Main.getPlugin().getTeleportManager().addTeleport(teleport);
                        player.sendMessage("§aTeleportal §6" + name + "§a wurde erstellt!");
                    }
                    player.sendMessage("§aDie Region wurde zu §6" + name + "§a hinzugefügt!");
                } else {
                    sendHelp(player);
                }
            }
        }
        return false;
    }

    private void sendHelp(Player player) {
        player.sendMessage("§c/addRegion <Name>");
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
