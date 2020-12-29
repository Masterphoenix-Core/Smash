/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.config;

import de.master.smash.core.bootstrap.Main;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigLocation {

    final Main plugin;
    private final Location location;
    private final String root;

    public ConfigLocation(Location location, String root) {
        this.plugin = Main.getPlugin();
        this.location = location;
        this.root = root;
    }

    public ConfigLocation(String root) {
        this(null, root);
    }

    public void saveLocation() {
        FileConfiguration cfg = plugin.getConfig();
        cfg.set(root+ ".World", location.getWorld().getName());
        cfg.set(root+ ".X", location.getX());
        cfg.set(root+ ".Y", location.getY());
        cfg.set(root+ ".Z", location.getZ());
        cfg.set(root+ ".Yaw", location.getYaw());
        cfg.set(root+ ".Pitch", location.getPitch());
        plugin.saveConfig();
    }

    public Location loadLocation() {
        
        FileConfiguration cfg = plugin.getConfig();

        if (cfg.contains(root)) {

            World world = Bukkit.getWorld(cfg.getString(root + ".World"));
            double x = cfg.getDouble(root + ".X"),
                    y = cfg.getDouble(root + ".Y"),
                    z = cfg.getDouble(root + ".Z");
            float yaw = (float) cfg.getDouble(root + ".Yaw"),
                    pitch = (float) cfg.getDouble(root + ".Pitch");

            return new Location(world, x, y, z, yaw, pitch);

        } else
            return null;
    }
}
