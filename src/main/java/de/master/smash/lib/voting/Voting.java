/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.voting;

import de.master.smash.lib.MasterLib;
import de.master.smash.lib.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter
public class Voting {
    
    public static final int MIN_MAPS = 2;
    public static final int MAX_MAPS = 2;
    
    public static String VOTING_INV_TITLE = "§4Smash §f- §cVoting";
    
    ArrayList<Map> maps;
    Map[] votingMaps;
    int[] votingInvOrder;
    HashMap<String, Integer> playerVotes;
    
    Inventory votingInv;
    
    public Voting(ArrayList<Map> maps) {
        this.maps = maps;
        
        votingMaps = new Map[MAX_MAPS];
        //votingInvOrder = new int[]{11, 13, 15};
        votingInvOrder = new int[]{12, 14};
        playerVotes = new HashMap<>();
        
        chooseRandomMaps();
        loadVotingInv();
    }
    
    private void chooseRandomMaps() {
        for (int i = 0; i < MAX_MAPS; i++) {
    
    
            Collections.shuffle(maps);
            votingMaps[i] = maps.remove(0);
    
            System.out.println("Choose Maps complete");
        }
    }
    
    public void loadVotingInv() {
        votingInv = Bukkit.createInventory(null, 9 * 3, VOTING_INV_TITLE);
        
        ItemStack placeholderItem = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 7).setName("§4").build();
        
        for (int i = 0; i < votingInv.getSize(); i++) {
            votingInv.setItem(i, placeholderItem);
        }
        
        for (int i = 0; i < votingMaps.length; i++) {
            Map currentMap = votingMaps[i];
            
            if (currentMap == null) {
                System.out.println("ERROR, CURRENTMAP = NULL");
                return;
            }
            
            votingInv.setItem(votingInvOrder[i], new ItemBuilder(Material.PAPER).setName("§a" + currentMap.getName() + "§f - §6§l" + currentMap.getVotes() + " Votes").setLore(" ", "§7Erbauer: §c" + currentMap.getBuilder(), " ").build());
            
        }
    }
    
    public Map getWinnerMap() {
        
        Map winnerMap = votingMaps[0];
        for (int i = 1; i < votingMaps.length; i++) {
            if (votingMaps[i].getVotes() > winnerMap.getVotes()) {
                winnerMap = votingMaps[i];
            }
        }
        return winnerMap;
    }
    
    public void vote(Player p, int votingMap) {
        if (!playerVotes.containsKey(p.getName())) {
            votingMaps[votingMap].addVote();
            playerVotes.put(p.getName(), votingMap);
    
            p.closeInventory();
            p.sendMessage(MasterLib.prefix + "§7Du hast für die Map §e" + votingMaps[votingMap].getName() + " §7abgestimmt.");
    
            loadVotingInv();
        } else      // EVTL VOTE ÄNDERN
            p.sendMessage(MasterLib.prefix + "§cDu hast bereits gevotet.");
    }
    
    public void removeAllVotes() {
        playerVotes.clear();
    }
    
}

