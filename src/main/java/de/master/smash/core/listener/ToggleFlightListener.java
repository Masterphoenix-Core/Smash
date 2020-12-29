/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.gamestates.cooldown.JumpCooldown;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class ToggleFlightListener implements Listener {
    
    JumpCooldown jumpCooldown;

    public ToggleFlightListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
        jumpCooldown = Smash.getInstance().getCooldownManager().getJumpCooldown();
    }

    @EventHandler
    public void on(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();

        if (e.isFlying() && p.getGameMode() != GameMode.CREATIVE) {

            e.setCancelled(true);
            if (jumpCooldown.jumped.contains(p)) {
                return;
            }

            p.setVelocity(p.getLocation().getDirection().multiply(1.1).setY(1.1));
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);

            jumpCooldown.start(p);
        }
    }
}
