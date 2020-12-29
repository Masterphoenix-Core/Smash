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
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakListener_Ingame implements Listener {
    

    public SneakListener_Ingame() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    @EventHandler
    public void on(PlayerToggleSneakEvent e) {

        Player p = e.getPlayer();

        if (!p.isOnGround()) {

            if (!Smash.getInstance().getCooldownManager().getSmashCooldown().smash.contains(p))
                Smash.getInstance().getCooldownManager().getSmashCooldown().smash.add(p);
            
            else
                Smash.getInstance().getCooldownManager().getSmashCooldown().smash.remove(p);
        }
    }
}
