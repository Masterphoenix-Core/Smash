/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core;

import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.MasterLib;
import de.master.smash.lib.fight.*;
import de.master.smash.lib.gamestates.CooldownManager;
import de.master.smash.lib.gamestates.GameStateManager;
import de.master.smash.lib.item.ItemManager;
import de.master.smash.lib.mysql.MySQL;
import de.master.smash.lib.scoreboard.ScoreManager;
import de.master.smash.lib.util.MessageUtil;
import de.master.smash.lib.voting.Map;
import de.master.smash.lib.voting.Voting;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@Getter
public class Smash {
    
    @Getter
    static Smash instance;
    
    MySQL mySQL;
    
    GameStateManager gameStateManager;
    CooldownManager cooldownManager;
    
    FightManager fightManager;
    LifeManager lifeManager;
    DieManager dieManager;
    CheckManager checkManager;
    ItemManager itemManager;
    
    ScoreManager scoreManager;
    
    MessageUtil messageUtil;
    
    ArrayList<Map> maps;
    Voting voting;
    
    ArrayList<Player> players, spectators;
    ArrayList<String> build;
    
    public Smash() {
        
        mySQL = Main.getPlugin().getMySQL();
        
        init();
    }
    
    void init() {
        
        gameStateManager = new GameStateManager();
        cooldownManager = new CooldownManager();
        
        fightManager = new FightManager();
        lifeManager = new LifeManager();
        dieManager = new DieManager();
        checkManager = new CheckManager();
        itemManager = new ItemManager();
        
        scoreManager = new ScoreManager();
        
        messageUtil = new MessageUtil();
    
        initList();
        loadVoting();
    }
    
    void initList() {
        players = new ArrayList<>();
        spectators = new ArrayList<>();
        build = new ArrayList<>();
    }
    
    void loadVoting() {
        maps = new ArrayList<>();
        for (String current : Main.getPlugin().getConfig().getConfigurationSection("Arenas").getKeys(false)) {
            Map map = new Map(current);
            if (map.playable()) {
                maps.add(map);
                System.out.println("Added " + map.getName());
            } else
                Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§cDie Map §4" + map.getName() + " §cist noch nicht fertig eingerichtet!");
        }
        if (maps.size() >= Voting.MIN_MAPS) {
            voting = new Voting(maps);
            Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§aEs sind genügend Maps vorhanden um das Voting zu starten!");
        } else {
            Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§cEs müssen mindestens " + Voting.MIN_MAPS + " Maps eingerichtet sein, damit das Voting starten kann!");
            voting = null;
        }
    }
    
}
