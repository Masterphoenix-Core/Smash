/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.commands;

import de.master.smash.core.Smash;
import de.master.smash.lib.MasterLib;
import de.master.smash.lib.gamestates.states.LobbyState;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class StartCMD implements CommandExecutor {

    private static final int startSeconds = 10;

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (s instanceof Player) {
             Player p = (Player) s;
            if (p.hasPermission("smash.start")) {
                if (args.length == 0) {

                    if (Smash.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
                        LobbyState lobbyState = (LobbyState) Smash.getInstance().getGameStateManager().getCurrentGameState();

                        if (lobbyState.getLobbyCountdown().isRunning && lobbyState.getLobbyCountdown().getSeconds() > startSeconds) {

                            lobbyState.getLobbyCountdown().setSeconds(startSeconds);
                            p.sendMessage(MasterLib.prefix+ "§7Du hast das Spiel §agestartet§7!");

                        } else
                            p.sendMessage(MasterLib.prefix+ "§cDas Spiel hat bereits gestartet!");
                    } else
                        p.sendMessage(MasterLib.prefix+ "§cDas Spiel hat bereits gestartet!");
                } else
                    p.sendMessage(Smash.getInstance().getMessageUtil().usage("start"));
            } else
                p.sendMessage(MasterLib.noPerms);
        } else
            s.sendMessage(MasterLib.prefix+ "§cDieses Kommando ist nur für Spieler!");
        return false;
    }
}
