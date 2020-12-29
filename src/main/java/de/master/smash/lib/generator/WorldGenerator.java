/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.generator;

import de.master.smash.core.bootstrap.Main;
import org.bukkit.*;

public class WorldGenerator {
    
    WorldCreator worldCreator;
    
    public WorldGenerator(String name) {
        worldCreator = new WorldCreator(name);
    }
    
    public WorldGenerator generateStructures(boolean generateStructures) {
        worldCreator.generateStructures(generateStructures);
        return this;
    }
    
    public WorldGenerator setType(WorldType type) {
        worldCreator.type(type);
        return this;
    }
    
    public WorldGenerator setVoidWorld() {
        worldCreator.generatorSettings("3;minecraft:air;2");
        worldCreator.generateStructures(false);
        worldCreator.type(WorldType.FLAT);
        return this;
    }
    
    public World create() {
        return Main.getPlugin().getServer().createWorld(worldCreator);
    }
    
}
