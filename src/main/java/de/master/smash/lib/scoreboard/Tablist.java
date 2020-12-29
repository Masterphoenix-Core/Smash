/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.scoreboard;

import de.master.smash.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Tablist {

    Main plugin;
    ScoreManager scoreManager;
    Scoreboard sb;

    public Tablist(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        this.plugin = scoreManager.getPlugin();

        sb = scoreManager.getSb();
    }

    public void registerScoreboard() {

        sb.registerNewTeam("default");
        sb.registerNewTeam("spectator");
        sb.registerNewTeam("error");

        sb.getTeam("default").setPrefix("§a");
        sb.getTeam("spectator").setPrefix("§8");
        sb.getTeam("error").setPrefix("§7");

        System.out.println("REGISTERED SCOREBOARD");
    }

    public void setTeam(Player p) {

        String team = "";

        if (plugin.getPlayers().contains(p)) {
            team = "default";
        } else if (plugin.getSpectators().contains(p)) {
            team = "spectators";
        } else
            team = "error";

        Team t = sb.getTeam(team);
        if (t == null) {
            System.out.println("ERROR 1");
            return;
        }

        t.addEntry(p.getDisplayName());
        System.out.println("REGISTERED TEAMS");
    }

    public void setTeamAll() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            setTeam(all);
        }
    }
}