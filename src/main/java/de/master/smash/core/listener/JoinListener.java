/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.MasterLib;
import de.master.smash.lib.config.ConfigLocation;
import de.master.smash.lib.gamestates.GameStateManager;
import de.master.smash.lib.gamestates.countdown.LobbyCountdown;
import de.master.smash.lib.gamestates.states.IngameState;
import de.master.smash.lib.gamestates.states.LobbyState;
import de.master.smash.lib.item.ItemBuilder;
import de.master.smash.lib.voting.Map;
import de.master.smash.lib.voting.Voting;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {
    
    GameStateManager gameStateManager;
    ItemStack voteItem;
    
    public static final String VOTING_ITEM_NAME = Voting.VOTING_INV_TITLE;
    
    public JoinListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
        gameStateManager = Smash.getInstance().getGameStateManager();
        voteItem = new ItemBuilder(Material.NETHER_STAR).setName(VOTING_ITEM_NAME).build();
    }
    
    @EventHandler
    public void on(PlayerJoinEvent e) {
        
        Player p = e.getPlayer();
        
        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
            
            LobbyState lobbyState = (LobbyState) Smash.getInstance().getGameStateManager().getCurrentGameState();
            LobbyCountdown lobbyCountdown = lobbyState.getLobbyCountdown();
            
            Smash.getInstance().getPlayers().add(p);
            e.setJoinMessage(MasterLib.prefix + "§a" + p.getDisplayName() + " §7ist dem Spiel beigetreten. " +
                    "§8[§e" + Smash.getInstance().getPlayers().size() + "§8/§7" + LobbyState.MAX_PLAYERS + "§8]§7.");
            
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setItem(4, voteItem);
            
            p.setGameMode(GameMode.SURVIVAL);
            
            ConfigLocation loc = new ConfigLocation("lobby");
            if (loc.loadLocation() != null) {
                p.teleport(loc.loadLocation());
            } else
                p.sendMessage(MasterLib.error + "§cDie Lobby wurde noch nicht gesetzt!");
            
            if (Smash.getInstance().getPlayers().size() >= LobbyState.MIN_PLAYERS) {
                lobbyCountdown.stopIdle();
            }
        } else if (gameStateManager.getCurrentGameState() instanceof IngameState) {
            Smash.getInstance().getSpectators().add(p);
            
            e.setJoinMessage(MasterLib.prefix + "§6" + p.getName() + " §7beobachtet nun das laufende Spiel.");
            p.setGameMode(GameMode.SPECTATOR);
            
            Map map = Smash.getInstance().getVoting().getWinnerMap();
            p.teleport(map.getSpectatorLocation());
            
            Smash.getInstance().getScoreManager().getScoreboard().setScoreBoard(p);
        }
    }
}
