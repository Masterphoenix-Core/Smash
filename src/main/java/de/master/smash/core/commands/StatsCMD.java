/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.commands;

import de.master.smash.core.Smash;
import de.master.smash.lib.MasterLib;
import de.master.smash.lib.mysql.SQLStats;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class StatsCMD implements CommandExecutor {
    
    SQLStats sqlStats;
    
    public StatsCMD() {
        sqlStats = Smash.getInstance().getMySQL().getSqlStats();
    }
    
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        
        if (s instanceof Player) {
            Player p = (Player) s;
            if (args.length == 0) {
                
                String uuid = p.getUniqueId().toString();
                
                p.sendMessage(MasterLib.prefix + "§eDeine Stats:");
                p.sendMessage("");
                p.sendMessage("§7Spiele: §a" + sqlStats.getPlayerGames(uuid));
                p.sendMessage("§7Wins: §c" + sqlStats.getPlayerWins(uuid));
                p.sendMessage("");
                p.sendMessage("§7Kills: §a" + sqlStats.getPlayerKills(uuid));
                p.sendMessage("§7Tode: §c" + sqlStats.getPlayerDeaths(uuid));
                
                if (sqlStats.getPlayerKills(uuid) != 0 || sqlStats.getPlayerDeaths(uuid) != 0) {
                    
                    double kd = round((double) sqlStats.getPlayerKills(uuid) / sqlStats.getPlayerDeaths(uuid), 2);
                    p.sendMessage("§7K/D: §5" + kd);
                    
                } else
                    p.sendMessage("§7K/D: §5" + sqlStats.getPlayerKills(uuid));
                
                p.sendMessage("");
                p.sendMessage("§7Schaden ausgeteilt: §a" + sqlStats.getPlayerDamageGiven(uuid) + "%");
                p.sendMessage("§7Schaden erhalten: §c" + sqlStats.getPlayerDamageTaken(uuid) + "%");
                
            } else if (args.length == 1) {
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                String uuid = t.getUniqueId().toString();
                
                p.sendMessage(MasterLib.prefix + "§eStats von §6" + t.getName() + ":");
                p.sendMessage("");
                p.sendMessage("§7Spiele: §a" + sqlStats.getPlayerGames(uuid));
                p.sendMessage("§7Wins: §c" + sqlStats.getPlayerWins(uuid));
                p.sendMessage("");
                p.sendMessage("§7Kills: §a" + sqlStats.getPlayerKills(uuid));
                p.sendMessage("§7Tode: §c" + sqlStats.getPlayerDeaths(uuid));
                
                if (sqlStats.getPlayerKills(uuid) != 0 || sqlStats.getPlayerDeaths(uuid) != 0) {
                    
                    double kd = round((double) sqlStats.getPlayerKills(uuid) / sqlStats.getPlayerDeaths(uuid), 2);
                    p.sendMessage("§7K/D: §5" + kd);
                    
                } else
                    p.sendMessage("§7K/D: §5" + sqlStats.getPlayerKills(uuid));
                
                p.sendMessage("");
                p.sendMessage("§7Schaden ausgeteilt: §a" + sqlStats.getPlayerDamageGiven(uuid) + "%");
                p.sendMessage("§7Schaden erhalten: §c" + sqlStats.getPlayerDamageTaken(uuid) + "%");
            }
        } else
            s.sendMessage(MasterLib.prefix + "§cDieses Kommando ist nur für Spieler!");
        return false;
    }
    
    public static double round(double value, int places) {
        
        if (places < 0)
            throw new IllegalArgumentException();
        
        long factor = (long) Math.pow(10, places);
        long tmp = Math.round(value * factor);
        
        return (double) tmp / factor;
    }
}
