/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.gamestates.GameStateManager;
import de.master.smash.lib.gamestates.states.EndingState;
import de.master.smash.lib.gamestates.states.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener_Lobby implements Listener {

    GameStateManager gameStateManager;

    public DamageListener_Lobby() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
        gameStateManager = Smash.getInstance().getGameStateManager();
    }

    @EventHandler
    public void on(EntityDamageEvent e) {

        if (gameStateManager.getCurrentGameState() instanceof LobbyState) {
            if (!(e.getEntity() instanceof Player)) return;

            e.setCancelled(true);

        } else if (gameStateManager.getCurrentGameState() instanceof EndingState) {
            if (!(e.getEntity() instanceof Player)) return;

            e.setCancelled(true);
        }
    }
}
