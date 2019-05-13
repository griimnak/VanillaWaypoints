package me.griimnak.vanillawaypoints;

import me.griimnak.vanillawaypoints.commands.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsClass implements CommandExecutor {

    private boolean helpDialog(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "VanillaWaypoints 0.9.9 by griimnak");
        commandSender.sendMessage(ChatColor.GOLD + "/waypoints" + ChatColor.WHITE + "- Lists all waypoints in your collection.");
        commandSender.sendMessage(ChatColor.GOLD + "/waypoints list" + ChatColor.WHITE + "- Same functionality as the command above");
        commandSender.sendMessage(ChatColor.GOLD + "/waypoints add <name>" + ChatColor.WHITE + "- Add a waypoint of your current coords to your collection.");
        commandSender.sendMessage(ChatColor.GOLD + "/waypoints remove <name>" + ChatColor.WHITE + "- Remove a waypoint from your collection.");
        commandSender.sendMessage(ChatColor.GOLD + "/waypoints navigate <name>" + ChatColor.WHITE + "- Navigate to your waypoint with a temporary compass.");
        return true;
    }

    //
    // Queries command args
    //
    private boolean queryCommands(String[] args, CommandSender commandSender) {
        // Make sure commandSender is a player to avoid errors
        if(commandSender instanceof Player) {
            // /waypoints list
            if (args[0].equalsIgnoreCase("list")) {
                List.listWaypoints(commandSender, args);
            // /waypoints add <name>
            } else if(args[0].equalsIgnoreCase("add")) {
                Add.addWaypoint(commandSender, args);
            // /waypoints remove <name>
            } else if(args[0]. equalsIgnoreCase("remove")) {
                Remove.removeWaypoint(commandSender, args);
            // /waypoints navigate <name>
            } else if(args[0].equalsIgnoreCase("navigate")) {
                Navigate.navigateToWaypoint(commandSender, args);
            // /waypoints help
            } else if(args[0].equalsIgnoreCase("help")) {
                helpDialog(commandSender);
            }
        } else {
            // Servers can't use sub-commands
            System.out.println("Sub-commands can only be used in-game.");
        }
        return true;
    }

    // Initial
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("waypoints")) {
            if(sender instanceof Player) {
                if(args.length < 1) {
                    List.listWaypoints(sender, args);
                } else {
                    queryCommands(args, sender);
                }
            } else {
                sender.sendMessage("VanillaWaypoints 0.9.9 by griimnak");
                sender.sendMessage("There are currently no server-side commands available.");
            }
        }
        return true;
    }

}
