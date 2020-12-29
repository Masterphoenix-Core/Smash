/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.bootstrap.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener_Anytime implements Listener {
    
    public PlayerInteractListener_Anytime() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }


    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType() == Material.ANVIL || e.getClickedBlock().getType() == Material.TRAP_DOOR) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (e.getRightClicked() instanceof ItemFrame) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (e.getEntity() instanceof ItemFrame) {
                e.setCancelled(true);
            }
        }
    }
}
