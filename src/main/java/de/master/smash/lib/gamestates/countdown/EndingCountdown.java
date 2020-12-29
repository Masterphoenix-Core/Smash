/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates.countdown;

import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.gamestates.GameStateManager;
import de.master.smash.lib.MasterLib;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EndingCountdown extends Countdown {

    GameStateManager gameStateManager;

    int seconds = 10;
    boolean isRunning;

    public EndingCountdown(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {

            switch (seconds) {
                case 10: case 9: case 8: case 7: case 6: case 5: case 4: case 3: case 2:

                    String hot = "§7Das Spiel endet in §c" + seconds + " Sekunden§7.";
                    IChatBaseComponent hotbar = IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"" + Main.prefix + hot + "\",\"bold\":true,\"" +
                            "italic\":true,\"color\":\"gold\"}]");
                    PacketPlayOutChat actionbar = new PacketPlayOutChat(hotbar, (byte) 2);

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(actionbar);
                    }

                    if (seconds == 3) {
                        String endend = "§7Alle Spieler werden auf die Lobby gesendet!";
                        IChatBaseComponent ending = IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"" + endend + "\",\"bold\":true,\"" +
                                "italic\":true,\"color\":\"gold\"}]");
                        PacketPlayOutTitle end = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, ending);

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            ((CraftPlayer) all).getHandle().playerConnection.sendPacket(end);
                        }
                    }
                    break;
                case 1:

                    IChatBaseComponent hotbar2 = IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"" + Main.prefix + "Das Spiel endet in einer Sekunde.\",\"bold\":true,\"" +
                            "italic\":true,\"color\":\"gold\"}]");
                    PacketPlayOutChat actionbar2 = new PacketPlayOutChat(hotbar2, (byte) 2);

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(actionbar2);
                    }

                    break;
                case 0:

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.kickPlayer(MasterLib.prefix+ "§fDas Spiel startet neu.");
                    }

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");

                    stop();
                    break;

                default:
                    break;
            }

            seconds--;
        }, 0, 20);
    }

    @Override
    public void stop() {
        isRunning = false;
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
