/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates.states;

import de.master.smash.lib.MasterLib;
import de.master.smash.lib.gamestates.GameState;
import de.master.smash.lib.gamestates.GameStateManager;
import de.master.smash.lib.gamestates.countdown.LobbyCountdown;
import lombok.Getter;
import org.bukkit.Bukkit;

public class LobbyState extends GameState {
    
    public static final int MIN_PLAYERS = 2, MAX_PLAYERS = 4, MAX_ITEM_SPAWNS = 4;
    
    @Getter
    LobbyCountdown lobbyCountdown;
    
    public LobbyState(GameStateManager gameStateManager) {
        lobbyCountdown = new LobbyCountdown(gameStateManager);
    }
    
    @Override
    public void start() {
        lobbyCountdown.startIdle();
    }
    
    @Override
    public void stop() {
        Bukkit.broadcastMessage(MasterLib.prefix + "Alle Spieler werden teleportiert.");
    }
    
}
