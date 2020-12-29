/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.gamestates.states.LobbyState;
import de.master.smash.lib.voting.Voting;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class VotingListener implements Listener {

    Voting voting;

    public VotingListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }
    
    @EventHandler
    public void on(PlayerInteractEvent e) {

        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                Player p = e.getPlayer();
                ItemStack is = e.getItem();

                if(is == null) return;
                if(is.getItemMeta() == null) return;
                if (!is.getItemMeta().getDisplayName().equalsIgnoreCase(Voting.VOTING_INV_TITLE)) return;

                voting = Smash.getInstance().getVoting();

                if (voting.getVotingInv() == null) {
                    System.out.println("ERROR, INVENTORY == NULL");
                }
                p.openInventory(voting.getVotingInv());
            }
        }
    }

    @EventHandler
    public void on(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (e.getInventory().getName().equalsIgnoreCase(Voting.VOTING_INV_TITLE)) {
                e.setCancelled(true);

                for (int i = 0; i < voting.getVotingInvOrder().length; i++) {
                    if (voting.getVotingInvOrder()[i] == e.getSlot()) {
                        voting.vote(p, i);
                        return;
                    }
                }
            }
        }
    }
}
