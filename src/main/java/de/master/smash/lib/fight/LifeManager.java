/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.fight;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LifeManager {

    public LifeManager() {
        File file = new File("plugins/Smash", "Lifes.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLifes(Player p, int amount) {

        File file = new File("plugins/Smash", "Lifes.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set(p.getName() + ".Lifes", amount);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeLife(Player p) {
        File file = new File("plugins/Smash", "Lifes.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        int oldLifes = cfg.getInt(p.getName() + ".Lifes");
        int newLifes = oldLifes - 1;
        cfg.set(p.getName() + ".Lifes", newLifes);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removePlayer(Player p) {
        File file = new File("plugins/Smash", "Lifes.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set(p.getName() + ".Lifes", null);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        File file = new File("plugins/Smash", "Lifes.yml");
        file.delete();
    }

    public int getLifes(Player p) {
        File file = new File("plugins/Smash", "Lifes.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getInt(p.getName() + ".Lifes") + 1;
    }
}
