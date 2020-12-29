/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.gamestates;

import de.master.smash.lib.gamestates.states.*;

public class GameStateManager {
    GameState[] gameStates;
    GameState currentGameState;
    
    public GameStateManager() {
        gameStates = new GameState[3];
        
        gameStates[GameState.LOBBY_STATE] = new LobbyState(this);
        gameStates[GameState.INGAME_STATE] = new IngameState(this);
        gameStates[GameState.ENDING_STATE] = new EndingState(this);
    }
    
    public void setGameState(int gameStateID) {
        if (currentGameState != null) {
            currentGameState.stop();
        }
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }
    
    public void stopCurrentGameState() {
        if (currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;
        }
    }
    
    public GameState getCurrentGameState() {
        return currentGameState;
    }
    
    public GameState[] getGameStates() {
        return gameStates;
    }
    
}
