package me.griimnak.vanillawaypoints.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Add {
    public static boolean addWaypoint(CommandSender commandSender, String[] strings) {

        if(commandSender instanceof Player){
            Player player= (Player) commandSender;

            if(!commandSender.hasPermission("vanillawaypoints.waypoints")){
                commandSender.sendMessage(""+ ChatColor.RED+"You don't have the permission to do this!!!");
                return true;
            }

            if(strings.length<2){
                player.sendMessage("usage: /waypoints add <name>");
                return true;
            }

            System.out.println("waypoints/"+player.getName()+"-"+
                    player.getWorld().getName()+".properties");

            //load file
            File waypointFile=new File("waypoints/"+player.getName()+"-"+
                    player.getWorld().getName()+".properties");

            Properties properties = new Properties();

            if (loadWaypointFile(waypointFile, properties)) return true;

            //add new waypoint
            properties.setProperty(strings[1],""+player.getLocation().getX()+" "+player.getLocation().getY()+
                    " "+player.getLocation().getZ());

            //save file
            try {
                properties.store(new FileOutputStream(waypointFile),"");
            } catch (IOException e) {
                e.printStackTrace();
            }


            player.sendMessage("Waypoint saved. Type /waypoints list to list all waypoints.");
        }

        return true;
    }

    public static boolean loadWaypointFile(File waypointFile, Properties properties) {
        if(!waypointFile.exists() || !waypointFile.isFile()){
            properties=new Properties();
        }
        else {
            try {
                properties.load(new FileInputStream(waypointFile));
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }
}
