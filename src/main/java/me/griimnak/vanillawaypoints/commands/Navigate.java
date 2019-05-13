package me.griimnak.vanillawaypoints.commands;

import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Properties;

public class Navigate {
    private static ItemStack compassy = new ItemStack(Material.COMPASS);
    private static ItemMeta metadatas = compassy.getItemMeta();

    public static boolean navigateToWaypoint(CommandSender commandSender, String[] strings) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if(!commandSender.hasPermission("vanillawaypoints.navigation")){
                commandSender.sendMessage(""+ ChatColor.RED+"You don't have the permission to do this!!!");
                return true;
            }

            if (strings.length < 2) {
                player.sendMessage("usage: /waypoints navigate <name>");
                return true;
            }

            System.out.println("waypoints/" + player.getName() + "-" +
                    player.getWorld().getName() + ".properties");

            //load file
            File waypointFile = new File("waypoints/" + player.getName() + "-" +
                    player.getWorld().getName() + ".properties");

            Properties properties = new Properties();

            if (Add.loadWaypointFile(waypointFile, properties)) return true;

            if(properties.getProperty(strings[1])==null){
                player.sendMessage("Waypoint does not exists");
                return true;
            }

            String waypointString[]=properties.getProperty(strings[1]).split(" ");
            metadatas.setDisplayName(ChatColor.YELLOW + "Navigator");
            compassy.setItemMeta(metadatas);

            double x,y,z;

            x=Double.parseDouble(waypointString[0]);
            y=Double.parseDouble(waypointString[1]);
            z=Double.parseDouble(waypointString[2]);

            Location location=new Location(player.getWorld(),x,y,z);

            if (player.getInventory().getItemInOffHand().getType() != Material.AIR)
            {
                player.sendMessage("Please remove the item from your off-hand and try again.");
            }
            else if (!player.getInventory().contains(compassy))
            {
                addCompass(player);
                player.setCompassTarget(location);
                player.sendMessage("Navigating to target");
            }

        }

        return true;
    }

    public static void addCompass(Player p)
    {
        p.getInventory().setItemInOffHand(compassy);
    }
}
