/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.fight;

import de.master.smash.core.Smash;
import de.master.smash.lib.MasterLib;
import de.master.smash.lib.gamestates.states.LobbyState;
import de.master.smash.lib.mysql.SQLStats;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.Random;

public class DieManager {

    public void playerDied(Player p) {

        SQLStats sqlStats = Smash.getInstance().getMySQL().getSqlStats();
        LifeManager lifeManager = Smash.getInstance().getLifeManager();

        p.setMaxHealth(100);
        p.setHealth(100);

        sqlStats.addDeaths(p.getUniqueId().toString(), 1);

        FightManager manager = Smash.getInstance().getFightManager();
        if (manager.killer.containsKey(p)) {
            sqlStats.addKills(manager.killer.get(p).getUniqueId().toString(), 1);
        }

        lifeManager.removeLife(p);
        p.getInventory().clear();

        if (lifeManager.getLifes(p) > 0) {

            Bukkit.broadcastMessage(MasterLib.prefix + "§7Der Spieler §6" + p.getName() + " §7ist gestorben.");
            p.sendMessage(MasterLib.prefix+ "§7Du hast noch §c" +lifeManager.getLifes(p)+ " Leben§7.");

            //TELEPORT To SPAWN
            Random rn = new Random();

            int number = rn.nextInt(LobbyState.MAX_PLAYERS - 1);
            int spawnNumber = number + 1;

            p.teleport(Smash.getInstance().getVoting().getWinnerMap().getSpawnLocations()[spawnNumber]);
            manager.setDmg(p, 0.0);

        } else {

            Smash.getInstance().getPlayers().remove(p);
            Smash.getInstance().getSpectators().add(p);

            p.teleport(Smash.getInstance().getVoting().getWinnerMap().getSpectatorLocation());
            p.setGameMode(GameMode.SPECTATOR);

            Smash.getInstance().getCheckManager().checkForWin();
        }
        Smash.getInstance().getScoreManager().getScoreboard().reloadScoreboard();
    }
}
