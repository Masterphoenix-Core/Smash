/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.fight.FightManager;
import de.master.smash.lib.gamestates.states.IngameState;
import de.master.smash.lib.scoreboard.ScoreManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLargeFireball;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSmallFireball;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class DamageListener_Ingame implements Listener {
    
    FightManager manager;
    ScoreManager scoreManager;

    public DamageListener_Ingame() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
        manager = Smash.getInstance().getFightManager();
        scoreManager = Smash.getInstance().getScoreManager();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(EntityDamageByEntityEvent e) {

        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof IngameState) {

            if(e.isCancelled()) return;

            if (e.getEntity() instanceof Player) {
                if (e.getDamager() instanceof Player) {

                    Player p = (Player) e.getEntity();
                    Player t = (Player) e.getDamager();

                    Main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                        manager.killer.put(p, t);
                    }, 5);

                    e.setDamage(0);

                    if (t.getItemInHand().getType() == Material.DIAMOND_SWORD || p.getItemInHand().getType() == Material.GOLD_SWORD || p.getItemInHand().getType() == Material.IRON_SWORD) {
                        ItemStack is = p.getItemInHand();
                        p.getInventory().remove(is);

                        manager.swordDamage(p, t, p.getItemInHand());

                    } else {
                        manager.normalDamage(p, t);
                    }

                } else if (e.getDamager() instanceof CraftLargeFireball) {
                    Player p = (Player) e.getEntity();

                    manager.addDmgFromFireball(p);
                    p.setVelocity(manager.fireballVelocity(p, e.getDamager()));

                    System.out.println(e.getDamager().toString());

                } else if (e.getDamager() instanceof CraftSmallFireball) {

                    Player p = (Player) e.getEntity();
                    Random rn = new Random();
                    int i = rn.nextInt(3);
                    p.setFireTicks(3 + i);

                } else {
                    e.setCancelled(true);
                    System.out.println(e.getDamager().toString());
                }

                scoreManager.getScoreboard().reloadScoreboard();

            }
        }
    }

    @EventHandler
    public void on(EntityDamageEvent e) {
        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof IngameState) {

            if (e.getEntity() instanceof Player) {
                Player p = (Player) e.getEntity();

                if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    e.setCancelled(true);

                } else if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {

                    e.setCancelled(true);
                    Smash.getInstance().getFightManager().lavaDmg(p);

                } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {

                    e.setCancelled(true);
                    if (Smash.getInstance().getCooldownManager().getFireCooldown().run.containsKey(p)) {
                        return;
                    }
    
                    Smash.getInstance().getCooldownManager().getFireCooldown().start(p);
                    Smash.getInstance().getFightManager().fireDmg(p);

                }
                Smash.getInstance().getScoreManager().getScoreboard().reloadScoreboard();
            }
        }
    }
}
