/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates.cooldown;

import de.master.smash.core.bootstrap.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class SmashCooldown {
    public HashMap<Player, Integer> run;
    
    public ArrayList<Player> smash;
    public ArrayList<Player> smashed;

    public SmashCooldown() {
        run = new HashMap<>();
        smash = new ArrayList<>();
        smashed = new ArrayList<>();
    }

    public void start(Player p) {

        smashed.add(p);
        run.put(p, Main.getPlugin().getServer().getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(), () -> {

            stop(p);

        }, 20 * 10));
    }

    public void stop(Player p) {
        run.remove(p);
        smashed.remove(p);
        p.playSound(p.getLocation(), Sound.NOTE_SNARE_DRUM, 1, 1);
        p.sendMessage("§cDeine Smash-Attacke ist wieder aufgeladen.");
    }
}
