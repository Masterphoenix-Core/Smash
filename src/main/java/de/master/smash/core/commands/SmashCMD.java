/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.commands;

import de.master.smash.core.Smash;
import de.master.smash.lib.MasterLib;
import de.master.smash.lib.config.ConfigLocation;
import de.master.smash.lib.gamestates.states.LobbyState;
import de.master.smash.lib.voting.Map;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SmashCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (s instanceof Player) {
            Player p = (Player) s;
            if (p.hasPermission("smash.setup")) {
                if (args.length == 1) {
    
                    Smash.getInstance().getMessageUtil().sendHelpMessage(p);

                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args[1].equalsIgnoreCase("lobby")) { //   /smash set lobby

                            new ConfigLocation(p.getLocation(), "lobby").saveLocation();
                            p.sendMessage(MasterLib.prefix + "§7Du hast die Lobby §aneu gesetzt§7!");

                        } else
                            Smash.getInstance().getMessageUtil().sendHelpMessage(p);
                    } else
                        Smash.getInstance().getMessageUtil().sendHelpMessage(p);
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("create")) { ///smash create <Name> <Builder>  /smash set spectator <Map>

                        Map map = new Map(args[1]);
                        if (!map.exists()) {

                            map.create(args[2]);
                            p.sendMessage(MasterLib.prefix + "§7Du hast die Map §e" +args[1]+ " §7erstellt");
                            p.sendMessage(MasterLib.prefix + "§7Erbauer: §6" + args[2]);

                        } else
                            p.sendMessage(MasterLib.prefix+ "§cDiese Map existiert bereits.");
                    } else if (args[0].equalsIgnoreCase("set")) { //smash set spectator <Map>
                        if (args[1].equalsIgnoreCase("spectator")) {
                            Map map = new Map(args[2]);
                            if (map.exists()) {
                                map.setSpectatorLocation(p.getLocation());
                                p.sendMessage(MasterLib.prefix+ "§7Du hast den §6Spectator Spawn §7für die Map §e" +args[2]+ " §7gesetzt.");
                            } else
                                p.sendMessage(MasterLib.prefix+ "§cDiese Map existiert nicht.");
                        } else
                            Smash.getInstance().getMessageUtil().sendHelpMessage(p);
                    } else
                        Smash.getInstance().getMessageUtil().sendHelpMessage(p);
                } else if (args.length == 4) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args[1].equalsIgnoreCase("spawn")) { //smash set spawn <Map> <Zahl>

                            Map map = new Map(args[2]);
                            if (map.exists()) {
                                try {
                                    int spawnNumber = Integer.parseInt(args[3]);
                                    if (spawnNumber > 0 && spawnNumber <= LobbyState.MAX_PLAYERS) {
                                        map.setSpawnLocation(spawnNumber, p.getLocation());
                                        p.sendMessage(MasterLib.prefix+ "§7Du hast den Spawn Nummer §6" +spawnNumber+ " §7für die Map §e" +args[2]+ " §7gesetzt.");
                                    } else
                                        p.sendMessage(MasterLib.prefix+ "§cBenutze nur die Zahlen §e1-" +LobbyState.MAX_PLAYERS+ "§c!");
                                } catch (NumberFormatException e) {
                                    Smash.getInstance().getMessageUtil().sendHelpMessage(p);
                                }

                            } else
                                p.sendMessage(MasterLib.prefix+ "§cDiese Map existiert nicht.");
                        } else if (args[1].equalsIgnoreCase("item")) { // smash set item >Map< <Zahl>
                            Map map = new Map(args[2]);
                            if (map.exists()) {

                                try {
                                    int itemNumber = Integer.parseInt(args[3]);
                                    if (itemNumber > 0 && itemNumber <= LobbyState.MAX_ITEM_SPAWNS) {
                                        map.setItemLocation(itemNumber, p.getLocation());
                                        p.sendMessage(MasterLib.prefix + "§7Du hast den Item Spawn §6" + itemNumber + " §7für die Map §e" + args[2] + " §7gesetzt.");
                                    }
                                } catch (NumberFormatException e) {
                                    Smash.getInstance().getMessageUtil().sendHelpMessage(p);
                                }

                            } else
                                p.sendMessage(MasterLib.prefix+ "§cDiese Map existiert nicht.");
                        } else
                            Smash.getInstance().getMessageUtil().sendHelpMessage(p);
                    } else
                        Smash.getInstance().getMessageUtil().sendHelpMessage(p);
                } else
                    Smash.getInstance().getMessageUtil().sendHelpMessage(p);
            } else
                p.sendMessage(MasterLib.noPerms);
        } else
            s.sendMessage(MasterLib.prefix+ "§cDieses Kommando ist nur für Spieler!");
        return false;
    }
}
