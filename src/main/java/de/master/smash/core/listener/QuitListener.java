/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.listener;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.MasterLib;
import de.master.smash.lib.fight.LifeManager;
import de.master.smash.lib.gamestates.GameStateManager;
import de.master.smash.lib.gamestates.countdown.LobbyCountdown;
import de.master.smash.lib.gamestates.states.*;
import de.master.smash.lib.voting.Voting;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    GameStateManager gameStateManager;
    LifeManager lifeManager;

    public QuitListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
        gameStateManager = Smash.getInstance().getGameStateManager();
        lifeManager = Smash.getInstance().getLifeManager();
    }

    @EventHandler
    public void on(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {

            LobbyState lobbyState = (LobbyState) Smash.getInstance().getGameStateManager().getCurrentGameState();
            LobbyCountdown lobbyCountdown = lobbyState.getLobbyCountdown();

            Smash.getInstance().getPlayers().remove(p);

            Smash.getInstance().getScoreManager().getTablist().setTeamAll();

            e.setQuitMessage(MasterLib.prefix + "§c" +p.getDisplayName()+ " §7hat das Spiel verlassen. " +
                    "§8[§e" + Smash.getInstance().getPlayers().size()+ "§8/§7" +LobbyState.MAX_PLAYERS+"§8]§7.");

            if (Smash.getInstance().getPlayers().size() < LobbyState.MIN_PLAYERS) {
                lobbyCountdown.stop();
                lobbyCountdown.startIdle();
            }

            Voting voting = Smash.getInstance().getVoting();
            if (voting.getPlayerVotes().containsKey(p.getName())) {
                voting.getVotingMaps()[voting.getPlayerVotes().get(p.getName())].removeVote();
                voting.getPlayerVotes().remove(p.getName());
                voting.loadVotingInv();
            }

        } else if (gameStateManager.getCurrentGameState() instanceof IngameState) {

            if (Smash.getInstance().getPlayers().contains(p)) {

                e.setQuitMessage(MasterLib.prefix + "§c" + p.getName() + " §7hat das Spiel verlassen.");

                Smash.getInstance().getPlayers().remove(p);
                lifeManager.removePlayer(p);

                Smash.getInstance().getCheckManager().checkForWin();

            } else if (Smash.getInstance().getSpectators().contains(p)) {
                Smash.getInstance().getSpectators().remove(p);
                p.setGameMode(GameMode.SURVIVAL);
                e.setQuitMessage(MasterLib.prefix + "§6" + p.getName() + " §7hat aufgehört das Spiel zu beobachten.");
            }
            Smash.getInstance().getScoreManager().getTablist().setTeamAll();
            
        } else if (gameStateManager.getCurrentGameState() instanceof EndingState) {
            e.setQuitMessage("");
        }
    }
}
