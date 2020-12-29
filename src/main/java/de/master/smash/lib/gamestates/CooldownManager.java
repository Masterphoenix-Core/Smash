/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates;

import de.master.smash.lib.gamestates.cooldown.*;
import lombok.Getter;

@Getter
public class CooldownManager {
    
    JumpCooldown jumpCooldown;
    SmashCooldown smashCooldown;

    ItemSpawnCount itemSpawnCount;
    LavaCooldown lavaCooldown;
    FireCooldown fireCooldown;

    public CooldownManager() {
        loadCooldowns();
    }

    public void loadCooldowns() {
        
        jumpCooldown = new JumpCooldown();
        smashCooldown = new SmashCooldown();
        itemSpawnCount = new ItemSpawnCount();
        lavaCooldown = new LavaCooldown();
        fireCooldown = new FireCooldown();

    }
}