/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.fight;

import de.master.smash.core.Smash;
import de.master.smash.lib.MasterLib;
import de.master.smash.lib.gamestates.GameState;
import de.master.smash.lib.gamestates.GameStateManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CheckManager {
    
    public void checkForWin() {
        GameStateManager gameStateManager = Smash.getInstance().getGameStateManager();
        ArrayList<Player> players = Smash.getInstance().getPlayers();
        
        Smash.getInstance().getScoreManager().getScoreboard().reloadScoreboard();
        
        if (players.size() > 1) {
            Bukkit.broadcastMessage(MasterLib.prefix + "§7Es sind noch §e" + Smash.getInstance().getPlayers().size() + " Spieler §7übrig.");
            
        } else if (players.size() == 1) {
            Player winner = players.get(0);
            Smash.getInstance().getMySQL().getSqlStats().addWins(winner.getUniqueId().toString(), 1);
            
            Bukkit.broadcastMessage(MasterLib.prefix + "§a" + winner.getName() + " §7hat das Spiel gewonnen!");
            Smash.getInstance().getGameStateManager().setGameState(GameState.ENDING_STATE);
            
            gameStateManager.setGameState(GameState.ENDING_STATE);
        } else {
            Bukkit.broadcastMessage(MasterLib.prefix + "§7Das Spiel endete in einem Unentschieden!");
            gameStateManager.setGameState(GameState.ENDING_STATE);
        }
    }
}
