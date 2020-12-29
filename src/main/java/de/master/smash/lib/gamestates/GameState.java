/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates;

public abstract class GameState {
    
    public static final int
            LOBBY_STATE = 0,
            INGAME_STATE = 1,
            ENDING_STATE = 2;
    
    public abstract void start();
    public abstract void stop();
    
}
