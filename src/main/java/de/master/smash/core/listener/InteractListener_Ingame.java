/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener_Ingame implements Listener {
    
    public InteractListener_Ingame() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(e.getItem() == null) return;

        if (e.getItem().getType() == Material.DIAMOND_PICKAXE) {

            Fireball f = e.getPlayer().launchProjectile(Fireball.class);

            for (Player player : Smash.getInstance().getPlayers()) {
                player.playSound(p.getLocation(), Sound.WITHER_SHOOT, 1, 1);
            }
            f.setVelocity(f.getVelocity().add(f.getVelocity().add(f.getDirection())).multiply(30));
            f.setIsIncendiary(false);

            p.getInventory().remove(p.getItemInHand());

        } else if (e.getItem().getType() == Material.FLINT_AND_STEEL) {
            ItemStack item = p.getItemInHand();

            double maxDurability = item.getDurability();
            double finalDurability = maxDurability + 10;

            if (finalDurability >= item.getType().getMaxDurability()) {
                p.getInventory().remove(item);
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
            }

            for (Player all : Bukkit.getOnlinePlayers()) {
                all.playEffect(p.getLocation(), Effect.STEP_SOUND, 51);
            }

            item.setDurability((short) finalDurability);

            p.setVelocity(p.getLocation().getDirection().setY(0.7));
            e.setCancelled(true);
        } else if (e.getItem().getType() == Material.RED_ROSE) {

            e.setCancelled(true);
            p.launchProjectile(SmallFireball.class, p.getLocation().getDirection().multiply(2));
            p.getInventory().remove(e.getItem());

        }
    }
}
