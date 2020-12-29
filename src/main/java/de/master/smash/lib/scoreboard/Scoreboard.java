/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.scoreboard;

import de.master.smash.core.Smash;
import de.master.smash.lib.fight.FightManager;
import de.master.smash.lib.fight.LifeManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

public class Scoreboard {

    org.bukkit.scoreboard.Scoreboard sb;
    Objective o;

    ScoreManager scoreManager;
    LifeManager lifeManager;
    FightManager manager;

    public Scoreboard(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        lifeManager = Smash.getInstance().getLifeManager();
        manager = Smash.getInstance().getFightManager();
        
        sb = scoreManager.getSb();
    }

    public void initScoreboard() {
        if (sb.getObjective("test") != null) {
            sb.getObjective("test").unregister();
        }
        o = sb.registerNewObjective("test", "dummy");

        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§4Smash");

        o.getScore("§a ").setScore(15);
        o.getScore("§7Spieler:").setScore(14);
        o.getScore("§b").setScore(13);

        scoreManager.getTablist().registerScoreboard();
        scoreManager.getTablist().setTeamAll();
        reloadScoreboard();

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(sb);
        }
    }

    public void setScoreBoard(Player p) {
        scoreManager.getTablist().setTeamAll();
        reloadScoreboard();

        p.setScoreboard(sb);
    }

    public void reloadScoreboard() {

        for (Player current : Smash.getInstance().getPlayers()) {
            if (lifeManager.getLifes(current) == 0) {

                reset(current);
                o.getScore("§8➥ §c" + current.getDisplayName()).setScore(lifeManager.getLifes(current));

            } else {
                if (manager.getDmg(current) <= 33) {

                    reset(current);
                    o.getScore("§8➥ §a" + current.getDisplayName() + " §8[§c" + Math.round(manager.getDmg(current)) + "%§8]").setScore(lifeManager.getLifes(current));

                } else if (manager.getDmg(current ) <= 66) {

                    reset(current);
                    o.getScore("§8➥ §6" + current.getDisplayName() + " §8[§c" + Math.round(manager.getDmg(current)) + "%§8]").setScore(lifeManager.getLifes(current));

                } else {

                    reset(current);
                    o.getScore("§8➥ §c" + current.getDisplayName() + " §8[§c" + Math.round(manager.getDmg(current)) + "%§8]").setScore(lifeManager.getLifes(current));
                }
            }
        }
    }

    void reset(Player current) {
        for (int i = 0; i <= 999; i++) {
            sb.resetScores("§8➥ §a" + current.getDisplayName() + " §8[§c" + i + "%§8]");
            sb.resetScores("§8➥ §6" + current.getDisplayName() + " §8[§c" + i + "%§8]");
            sb.resetScores("§8➥ §c" + current.getDisplayName() + " §8[§c" + i + "%§8]");
        }
    }
}