/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.util;

import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.MasterLib;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MessageUtil {
    
    public void sendHelpMessage(Player p) {
        p.sendMessage("§7┌───────── " + MasterLib.prefix + " §7─────────┐");
        p.sendMessage("§e/smash help §7➥ §fZeigt dir diese Nachricht.");
        p.sendMessage("§e/smash set lobby §7➥ §fSetzt die Lobby.");
        p.sendMessage("§e/smash create <Mapname> <Erbauer> §7➥ §fErstellt eine neue Map");
        p.sendMessage("§e/smash set spawn <Map> <Nummer> §7➥ §fSetzt die Spawns einer Map.");
        p.sendMessage("§e/smash set spectator <Map> §7➥ §fSetzt den Spectator Spawn einer Map.");
        p.sendMessage("§e/smash set item <Map> <Nummer> §7➥ §fSetzt die Item Spawns einer Map.");
        p.sendMessage("§7└───────── " + MasterLib.prefix + " §7─────────┘");
    }
    
    public String usage(String command) {
        FileConfiguration cfg = Main.getPlugin().getConfig();
        
        return MasterLib.prefix + cfg.getString("message.usage").replaceAll("&", "§").replace("%command%", "/" + command);
    }
}
