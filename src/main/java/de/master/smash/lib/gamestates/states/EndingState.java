/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates.states;

import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.gamestates.GameState;
import de.master.smash.lib.gamestates.GameStateManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class EndingState extends GameState {
    
    EndingCountdown endingCountdown;
    
    public EndingState(GameStateManager gameStateManager) {
        
        endingCountdown = new EndingCountdown(gameStateManager);
    }
    @Override
    public void start() {
        
        Main plugin = Main.getPlugin();
        
        endingCountdown.start();
        plugin.getLifeManager().delete();
        
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setGameMode(GameMode.SURVIVAL);
            
            ConfigLocation loc = new ConfigLocation(plugin, "lobby");
            if (loc.loadLocation() != null) {
                all.teleport(loc.loadLocation());
            } else
                System.out.println(Main.error + "Die Lobby wurde noch nicht gesetzt!");
        }
    }
    
    @Override
    public void stop() {
    
    }
    
}
