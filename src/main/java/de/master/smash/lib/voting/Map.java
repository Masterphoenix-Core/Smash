/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.voting;

import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.config.ConfigLocation;
import de.master.smash.lib.gamestates.states.LobbyState;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class Map {

    String name, builder;
    Location[] spawnLocations = new Location[LobbyState.MAX_PLAYERS],
            itemLocations = new Location[LobbyState.MAX_ITEM_SPAWNS];
    Location spectatorLocation;

    int votes;

    public Map(String name) {
        this.name = name.toUpperCase();

        if (exists()) {
            builder = Main.getPlugin().getConfig().getString("Arenas." + name + ".Builder");
        }
    }

    public void create(String builder) {
        this.builder = builder;
        Main.getPlugin().getConfig().set("Arenas." +name+ ".Builder", builder);
        Main.getPlugin().saveConfig();
    }

    public boolean playable() {
        ConfigurationSection cfgSection = Main.getPlugin().getConfig().getConfigurationSection("Arenas." + name);
        if (cfgSection.contains("Spectator")) {
            if (cfgSection.contains("Builder")) {
                for (int i = 1; i <= LobbyState.MAX_PLAYERS; i++) {
                    if (!cfgSection.contains(Integer.toString(i))) return false;
                }
                for (int i = 1; i <= LobbyState.MAX_ITEM_SPAWNS; i++) {
                    if(!cfgSection.contains("Item." + i)) return false;
                }
                return true;

            } else
                return false;
        } else
            return false;
    }

    public void load() {
        for (int i = 0; i < spawnLocations.length; i++) {
            spawnLocations[i] = new ConfigLocation("Arenas." + name + "." + (i + 1)).loadLocation();
        }
        for (int i = 0; i < itemLocations.length; i++) {
            itemLocations[i] = new ConfigLocation("Arenas." + name + ".Item." + (i + 1)).loadLocation();
        }
        spectatorLocation = new ConfigLocation("Arenas." + name + ".Spectator").loadLocation();
    }

    public void setSpawnLocation(int spawnNumber, Location loc) {
        spawnLocations[spawnNumber - 1] = loc;
        new ConfigLocation(loc, "Arenas." + name + "." + spawnNumber).saveLocation();
    }

    public void setSpectatorLocation(Location location) {
        spectatorLocation = location;
        new ConfigLocation(location, "Arenas." + name + ".Spectator").saveLocation();
    }

    public void setItemLocation(int itemNumber, Location loc) {
        itemLocations[itemNumber - 1] = loc;
        new ConfigLocation(loc, "Arenas." + name + ".Item." + itemNumber).saveLocation();
    }

    public void addVote() {
        votes++;
    }

    public void removeVote() {
        votes--;
    }

    public boolean exists() {
        return Main.getPlugin().getConfig().getString("Arenas." + name + ".Builder") != null;
    }
}
