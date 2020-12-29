/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.core.commands;

import de.master.smash.core.Smash;
import de.master.smash.lib.MasterLib;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildCMD implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        
        if (s instanceof Player) {
            Player p = (Player) s;
            if (p.hasPermission("smash.build")) {
                if (args.length == 0) {
                    
                    ArrayList<String> build = Smash.getInstance().getBuild();
                    
                    if (!Smash.getInstance().getBuild().contains(p.getName())) {
                        build.add(p.getName());
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(MasterLib.prefix + "§u kannst nun bauen.");
                    } else {
                        build.remove(p.getName());
                        p.setGameMode(GameMode.SURVIVAL);
                        p.setAllowFlight(true);
                        p.setFlying(false);
                        p.sendMessage(MasterLib.prefix + "Du kannst nun nicht mehr bauen");
                    }
                    
                } else
                    p.sendMessage(MasterLib.error + "§fBenutze nur /build um zu bauen.");
            } else
                p.sendMessage(MasterLib.noPerms);
        }
        return false;
    }
}
