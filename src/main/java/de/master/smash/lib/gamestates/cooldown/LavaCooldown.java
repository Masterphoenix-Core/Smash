/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates.cooldown;

import de.master.smash.core.bootstrap.Main;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LavaCooldown {
    
    public HashMap<Player, Integer> run;

    public LavaCooldown() {
        run = new HashMap<>();
    }

    public void start(Player p) {
        if (!run.containsKey(p)) {
            run.put(p, Main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {

                run.remove(p);

            }, 20));
        } else
            System.out.println("Error, Player already in HashMap RUN of LAVACOOLDOWN");
    }
}
