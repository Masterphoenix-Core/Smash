/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class CancelListener_Anytime implements Listener {
    
    public CancelListener_Anytime() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    @EventHandler
    public void on(BlockBreakEvent e) {
        if(Smash.getInstance().getBuild().contains(e.getPlayer().getName())) return;
            e.setCancelled(true);
    }

    @EventHandler
    public void on(BlockPlaceEvent e) {
        if(Smash.getInstance().getBuild().contains(e.getPlayer().getName())) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void on(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setFoodLevel(20);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}
