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
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener_Ingame implements Listener {
    
    public DeathListener_Ingame() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    @EventHandler
    public void on(PlayerDeathEvent e) {
        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof IngameState) {
            Player p = e.getEntity();

            e.setDeathMessage("");
            Smash.getInstance().getDieManager().playerDied(p);
        }
    }
}
