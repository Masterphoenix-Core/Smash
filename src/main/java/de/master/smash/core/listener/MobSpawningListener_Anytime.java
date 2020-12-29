/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.bootstrap.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawningListener_Anytime implements Listener {
    
    public MobSpawningListener_Anytime() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    @EventHandler
    public void on(EntitySpawnEvent e) {

        Entity en = e.getEntity();
        if (en instanceof Zombie || en instanceof Skeleton || en instanceof Creeper || en instanceof Spider || en instanceof Enderman
                || en instanceof Witch || en instanceof Slime) {
            e.setCancelled(true);
        }
    }
}
