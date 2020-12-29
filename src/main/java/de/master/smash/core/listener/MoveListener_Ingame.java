/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.fight.FightManager;
import de.master.smash.lib.gamestates.cooldown.JumpCooldown;
import de.master.smash.lib.gamestates.cooldown.SmashCooldown;
import de.master.smash.lib.gamestates.states.IngameState;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener_Ingame implements Listener {
    
    public MoveListener_Ingame() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    @EventHandler
    public void on(PlayerMoveEvent e) {

        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof IngameState) {
            Player p = e.getPlayer();

            if (p.getLocation().getY() < 0) p.setHealth(1);

            for (World w : Main.getPlugin().getServer().getWorlds()) {
                if (w.getTime() <= 10500 || w.getTime() >= 10900) {
                    w.setTime(10999);
                }
            }

            if (p.isOnGround()) {

                JumpCooldown jumpCooldown = Smash.getInstance().getCooldownManager().getJumpCooldown();
                SmashCooldown smashCooldown = Smash.getInstance().getCooldownManager().getSmashCooldown();
                
                FightManager manager = Smash.getInstance().getFightManager();
                
                if (manager.killer.containsKey(p))
                    manager.killer.remove(p);

                if (jumpCooldown.jumped.contains(p))
                    jumpCooldown.stop(p);
                
                if (smashCooldown.smash.contains(p)) {
                    if (!smashCooldown.smashed.contains(p)) {

                        smashCooldown.start(p);
                        Smash.getInstance().getFightManager().smashAttack(p);

                    } else
                        p.sendMessage("§cDeine Smash-Attacke lädt noch auf.");
                }

                smashCooldown.smash.remove(p);
            }
        }
    }
}
