package me.griimnak.vanillawaypoints.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Remove {
    public static boolean removeWaypoint(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof Player){
            Player player= (Player) commandSender;

            if(!commandSender.hasPermission("vanillawaypoints.waypoints")){
                commandSender.sendMessage(""+ ChatColor.RED+"You don't have the permission to do this!!!");
                return true;
            }

            if(strings.length<2){
                player.sendMessage("usage: /waypoints remove <name>");
                return true;
            }

            System.out.println("waypoints/"+player.getName()+"-"+
                    player.getWorld().getName()+".properties");

            //load file
            File waypointFile=new File("waypoints/"+player.getName()+"-"+
                    player.getWorld().getName()+".properties");

            Properties properties = new Properties();

            if (Add.loadWaypointFile(waypointFile, properties)) return true;

            //check if the waypoint exists
            if(properties.getProperty(strings[1])!=null){
                properties.remove(strings[1]);
            }
            else {
                player.sendMessage("waypoint \""+strings[1]+"\" do not exists");
                return true;
            }


            //save file
            try {
                properties.store(new FileOutputStream(waypointFile),"");
            } catch (IOException e) {
                e.printStackTrace();
            }


            player.sendMessage("Waypoint deleted");
        }

        return true;
    }
}