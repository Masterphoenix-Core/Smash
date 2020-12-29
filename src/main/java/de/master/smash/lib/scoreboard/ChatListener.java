/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.scoreboard;

import de.master.smash.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void on(AsyncPlayerChatEvent e) {
        e.setFormat(Main.prefix + "§a" +e.getPlayer().getName() +" §8➥ §f" +e.getMessage());
    }
}
