/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates.cooldown;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;

public class ItemSpawnCount {

    int taskID;

    public void start() {
        taskID = Main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
    
            Smash.getInstance().getItemManager().spawnRandomItem();
            restart();

        }, 20 * 15);
    }

    public void stop() {
        Main.getPlugin().getServer().getScheduler().cancelTask(taskID);
    }

    public void restart() {
        stop();
        start();
    }
}
