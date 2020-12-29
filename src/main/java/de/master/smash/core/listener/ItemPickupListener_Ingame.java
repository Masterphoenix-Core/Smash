/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.gamestates.states.IngameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ItemPickupListener_Ingame implements Listener {
    
    public ItemPickupListener_Ingame() {
        Bukkit.getPluginManager().registerEvents( this, Main.getPlugin());
    }

    @EventHandler
    public void on(PlayerPickupItemEvent e) {

        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof IngameState) {
            Player p = e.getPlayer();

            for (int i = 0; i <= p.getInventory().getSize(); i++) {
                if (p.getInventory().getItem(i) != null) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }
}