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
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener_Ingame implements Listener {
    
    public InventoryClickListener_Ingame() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    @EventHandler
    public void on(InventoryClickEvent e) {
        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof IngameState) {
            Player p = (Player) e.getWhoClicked();
            if (p.getGameMode() != GameMode.CREATIVE) {
                e.setCancelled(true);
            }
        }
    }
}
