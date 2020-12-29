/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.item;

import de.master.smash.core.Smash;
import de.master.smash.lib.gamestates.states.LobbyState;
import de.master.smash.lib.voting.Map;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemManager {

    ArrayList<ItemStack> items;

    ItemStack finalItem;
    Random rn;

    public ItemManager() {

        items = new ArrayList<>();
        addItems();
    }

    public void addItems() {

        ItemStack rocketLauncher = new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§cRaketenwerfer").build();
        ItemStack jetpack = new ItemBuilder(Material.FLINT_AND_STEEL).setName("§6Jetpack").build();
        ItemStack enderpearl = new ItemBuilder(Material.ENDER_PEARL).setName("§5Enderperle").build();
        ItemStack fireflower = new ItemBuilder(Material.RED_ROSE).setName("§cFeuerblume").build();

        ItemStack diamondSword = new ItemBuilder(Material.DIAMOND_SWORD).setName("§bDiamantschwert").build();
        ItemStack goldSword = new ItemBuilder(Material.GOLD_SWORD).setName("§6Goldschwert").build();
        ItemStack ironSword = new ItemBuilder(Material.IRON_SWORD).setName("§7Eisenschwert").build();

        items.add(rocketLauncher);
        items.add(jetpack);
        items.add(enderpearl);
        items.add(fireflower);

        items.add(diamondSword);
        items.add(goldSword);
        items.add(ironSword);
    }

    public void spawnRandomItem() {

        Collections.shuffle(items);
        finalItem = items.get(0);

        rn = new Random();

        reRollIfSword();

        int itemNumber = rn.nextInt(LobbyState.MAX_ITEM_SPAWNS);

        Map map = Smash.getInstance().getVoting().getWinnerMap();
        World w = map.getSpawnLocations()[0].getWorld();

        if (map.getItemLocations()[itemNumber] == null) {
            System.out.println("ERROR ItemSpawn NOT EXISTENT");
            return;
        }

        w.dropItemNaturally(map.getItemLocations()[itemNumber], finalItem);
        System.out.println("Spawned Item!!!");
    }

    public void reRollIfSword() {
        if (finalItem.getType() == Material.DIAMOND_SWORD || finalItem.getType() == Material.GOLD_SWORD || finalItem.getType() == Material.IRON_SWORD) {
            int i = rn.nextInt(1);
            if (i == 0) {
                Collections.shuffle(items);
                finalItem = items.get(0);
            } else
                reRollIfSword();
        }
    }
}
