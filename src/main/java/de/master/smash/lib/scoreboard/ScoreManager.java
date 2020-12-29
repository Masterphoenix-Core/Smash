/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.scoreboard;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;

@Getter
public class ScoreManager {
    
    Scoreboard scoreboard;
    Tablist tablist;

    org.bukkit.scoreboard.Scoreboard sb;
    ScoreboardManager sm;

    ArrayList<org.bukkit.scoreboard.Scoreboard> scoreboards;

    public ScoreManager() {
        scoreboards = new ArrayList<>();

        sm = Bukkit.getScoreboardManager();
        sb = sm.getNewScoreboard();

        scoreboard = new Scoreboard(this);
        tablist = new Tablist(this);
    }
}
