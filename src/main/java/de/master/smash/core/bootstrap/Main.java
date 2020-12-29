/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.bootstrap;

import de.master.smash.core.Smash;
import de.master.smash.core.commands.*;
import de.master.smash.core.listener.*;
import de.master.smash.lib.mysql.MySQL;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    @Getter
    static Main plugin;
    
    @Getter
    MySQL mySQL;
    
    public static void main(String[] args) {
        Main.getPlugin().onEnable();
    }
    
    @Override
    public void onEnable() {
    
        plugin = this;
    
        mySQL = new MySQL();
        mySQL.connect();
        
        new Smash();        // Calls Bootstrap to Instance
        register();
        
    }
    
    @Override
    public void onDisable() {
        mySQL.close();
    }
    
    void register() {
    
        new CancelListener_Anytime();
        new DamageListener_Ingame();
        new DamageListener_Lobby();
        new DeathListener_Ingame();
        new InteractListener_Ingame();
        new InventoryClickListener_Ingame();
        new ItemPickupListener_Ingame();
        new JoinListener();
        new MobSpawningListener_Anytime();
        new MoveListener_Ingame();
        new PlayerInteractListener_Anytime();
        new QuitListener();
        new SneakListener_Ingame();
        new ToggleFlightListener();
        new VotingListener();
        
        getCommand("build").setExecutor(new BuildCMD());
        getCommand("smash").setExecutor(new SmashCMD());
        getCommand("start").setExecutor(new StartCMD());
        getCommand("stats").setExecutor(new StatsCMD());
    }
}
