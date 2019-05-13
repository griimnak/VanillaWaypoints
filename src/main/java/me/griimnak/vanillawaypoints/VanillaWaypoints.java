package me.griimnak.vanillawaypoints;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class VanillaWaypoints extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[VanillaWaypoints] >> Enabled");
        // Create storage dir
        createWaypointsDir();
        // Register events
        getServer().getPluginManager().registerEvents(this, this);
        // Hook commands on /waypoints
        this.getCommand("waypoints").setExecutor(new CommandsClass());

    }

    private void createWaypointsDir() {
        // New file object
        File dir=new File("waypoints");
        // Create if dir doesn't yet exist
        if(!dir.exists() || !dir.isDirectory()){
            dir.mkdirs();
        }

    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {
        // Delete navigator on drop
        if (e.getItemDrop().getItemStack().getType() == Material.COMPASS) {
            e.getItemDrop().remove();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[VanillaWaypoints] >> Disabled");
    }
}
