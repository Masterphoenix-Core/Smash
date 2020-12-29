/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates.countdown;

import de.master.smash.lib.gamestates.GameState;
import de.master.smash.lib.gamestates.GameStateManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyCountdown extends Countdown {
    
    private final GameStateManager gameStateManager;
    
    private static final int countdownTime = 30, idleTime = 15;
    
    public boolean isRunning;
    
    private int seconds;
    
    private int idleID;
    private boolean isIdling;
    
    public LobbyCountdown(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        seconds = countdownTime;
    }
    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), () -> {
            switch (seconds) {
                case 30: case 29: case 28: case 27: case 26: case 25: case 24: case 23: case 22: case 21: case 20:
                case 19: case 18: case 17: case 16: case 15: case 14: case 13: case 12: case 11: case 10:
                case 9: case 8: case 7: case 6: case 5: case 4: case 3: case 2:
                    
                    String message = "§7Das Spiel beginnt in §c" + seconds + " Sekunden§7.";
                    IChatBaseComponent hotbar = IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"" + Main.prefix + message + "\",\"bold\":true,\"" +
                            "italic\":true,\"color\":\"gold\"}]");
                    PacketPlayOutChat actionbar = new PacketPlayOutChat(hotbar, (byte) 2);
                    
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(actionbar);
                    }
                    
                    if (seconds == 5) {
                        
                        Voting voting = Smash.getInstance().getVoting();
                        Map winningMap;
                        
                        winningMap = voting.getWinnerMap();
                        
                        Bukkit.broadcastMessage(Main.prefix + "§7Die Map §a" + winningMap.getName() + " §7hat das Voting gewonnen!");
                        Bukkit.broadcastMessage(Main.prefix + "§7Erbauer der Map: §c" + winningMap.getBuilder());
                        
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.getInventory().setItem(4, null);
                        }
                    }
                    break;
                case 1:
                    
                    String message2 = "§7Das Spiel beginnt in §ceiner Sekunde§7.";
                    IChatBaseComponent hotbar2 = IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"" + Main.prefix + message2 + "\",\"bold\":true,\"" +
                            "italic\":true,\"color\":\"gold\"}]");
                    PacketPlayOutChat actionbar2 = new PacketPlayOutChat(hotbar2, (byte) 2);
                    
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(actionbar2);
                    }
                    break;
                case 0:
                    gameStateManager.setGameState(GameState.INGAME_STATE);
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
        if (isRunning) {
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = countdownTime;
        }
    }
    
    public void startIdle() {
        
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(gameStateManager.getPlugin(), () -> {
            
            int nonePlayer = LobbyState.MIN_PLAYERS - gameStateManager.getPlugin().getPlayers().size();
            if (nonePlayer == 1) {
                Bukkit.broadcastMessage(Main.prefix + "§7Es wird noch §cein Spieler §7benötigt.");
            } else if (nonePlayer > 1) {
                Bukkit.broadcastMessage(Main.prefix + "§7Es werden noch §c" + nonePlayer + " Spieler §7benötigt.");
            }
        }, 0, 20 * idleTime);
    }
    public void stopIdle() {
        if (isIdling) {
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
            
            start();
        }
    }
    
    public int getSeconds() {
        return seconds;
    }
    
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
