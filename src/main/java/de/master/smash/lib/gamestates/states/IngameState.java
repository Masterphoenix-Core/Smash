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

import java.util.ArrayList;
import java.util.Collections;

public class IngameState extends GameState {
    
    Main plugin;
    Map map;
    Voting voting;
    
    ItemSpawnCount itemSpawnCount;
    ScoreManager scoreManager;
    
    ArrayList<Player> players;
    
    public IngameState(GameStateManager gameStateManager) {
        this.plugin = Main.getPlugin();
        voting = plugin.getVoting();
        
        scoreManager = new ScoreManager(gameStateManager.getPlugin());
    }
    
    @Override
    public void start() {
        
        GameStateManager gameStateManager = plugin.getGameStateManager();
        LifeManager lifeManager = new LifeManager(gameStateManager.getPlugin());
        itemSpawnCount = plugin.getCooldownManager().getItemSpawnCount();
        
        Collections.shuffle(plugin.getPlayers());
        players = plugin.getPlayers();
        
        Voting voting = gameStateManager.getPlugin().getVoting();
        Map winningMap;
        if (voting != null) {
            winningMap = voting.getWinnerMap();
        } else {
            ArrayList<Map> maps = gameStateManager.getPlugin().getMaps();
            Collections.shuffle(maps);
            
            winningMap = maps.get(0);
        }
        
        map = winningMap;
        map.load();
        map.getSpawnLocations()[0].getWorld().getEntities().clear();
        
        plugin.getVoting().removeAllVotes();
        
        for (int i = 0; i < players.size(); i++) {
            players.get(i).teleport(map.getSpawnLocations()[i]);
        }
        
        for (Player current : players) {
            current.getInventory().clear();
            
            lifeManager.setLifes(current, 2);
            plugin.getMySQL().getSqlStats().addGames(current.getUniqueId().toString(), 1);
            
            plugin.getManager().registerPlayer(current);
            System.out.println("Registered Players");
            
            current.setAllowFlight(true);
            current.setFlying(false);
            
            current.setMaxHealth(100);
            current.setHealth(100);
            current.setHealthScale(10);
        }
        
        itemSpawnCount.start();
        plugin.getScoreManager().getScoreboard().initScoreboard();
    }
    
    @Override
    public void stop() {
        scoreManager.getScoreboards().clear();
        itemSpawnCount.stop();
        
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setGameMode(GameMode.SURVIVAL);
            all.getInventory().clear();
        }
    }
    
}
