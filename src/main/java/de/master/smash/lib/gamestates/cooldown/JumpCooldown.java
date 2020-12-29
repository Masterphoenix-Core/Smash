/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates.cooldown;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class JumpCooldown {

    HashMap<Player, Integer> run;
    public ArrayList<Player> jumped;
    
    public JumpCooldown() {
        jumped = new ArrayList<>();
        run = new HashMap<>();
    }

    public void start(Player p) {
        jumped.add(p);
        p.setAllowFlight(false);
        p.setFlying(false);
        p.setExp(0);
    }

    public void stop(Player p) {
        jumped.remove(p);
        run.remove(p);
        p.setAllowFlight(true);
        p.setFlying(false);
        p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1);
        p.setExp(1);
    }
}
