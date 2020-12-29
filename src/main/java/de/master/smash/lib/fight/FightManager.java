/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.fight;

import de.master.smash.core.Smash;
import de.master.smash.core.bootstrap.Main;
import de.master.smash.lib.mysql.SQLStats;
import de.master.smash.lib.scoreboard.ScoreManager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;

public class FightManager {
    
    public HashMap<Player, Player> killer;
    
    HashMap<Player, Double> damage;
    
    Main plugin;
    LifeManager lifeManager;
    ScoreManager scoreManager;
    CheckManager checkManager;
    DieManager dieManager;
    
    ArrayList<Player> players;
    
    Random rn;
    int randomNumber;
    
    SQLStats sqlStats;
    
    public FightManager() {
        lifeManager = Smash.getInstance().getLifeManager();
        
        killer = new HashMap<>();
        damage = new HashMap<>();
        players = new ArrayList<>();
        
        rn = new Random();
        
        sqlStats = Main.getPlugin().getMySQL().getSqlStats();
    }
    
    public void registerPlayer(Player p) {
        damage.put(p, 0.0);
    }
    
    public double getDmg(Player p) {
        if (!damage.containsKey(p)) {
            return 0;
        }
        return damage.get(p);
    }
    
    public void normalDamage(Player p, Player t) {
        randomNumber = rn.nextInt(4);
        
        if (damage.get(p) == 0.0) {
            damage.put(p, 1.0);
        }
        double oldValue = damage.get(p);
        double end = oldValue * 1.3 + 2 +randomNumber;
        if (end > 999) {
            end = 999;
        }
        
        sqlStats.addDamageTaken(p.getUniqueId().toString(), (int) (end - oldValue));
        sqlStats.addDamageGiven(t.getUniqueId().toString(), (int) (end - oldValue));
        
        damage.put(p, end);
        p.setVelocity(calcVelocity(p, t));
    }
    
    public void setDmg(Player p, Double amount) {
        damage.put(p, amount);
    }
    
    public void addDmgFromFireball(Player p) {
        randomNumber = rn.nextInt(10);
        
        if (damage.get(p) == 0.0) {
            damage.put(p, 1.0);
        }
        double oldValue = damage.get(p);
        double end = oldValue * 1.7 + 20 + randomNumber;
        if (end > 999) {
            end = 999;
        }
        sqlStats.addDamageTaken(p.getUniqueId().toString(), (int) (end - oldValue));
        
        damage.put(p, end);
    }
    
    public void addDmgFromSmash(Player p, Player damager) {
        randomNumber = rn.nextInt(10);
        
        if (damage.get(p) == 0.0) {
            damage.put(p, 1.0);
        }
        double oldValue = damage.get(p);
        double end = oldValue * 1.7 + 10 + randomNumber;
        if (end > 999) {
            end = 999;
        }
        sqlStats.addDamageTaken(p.getUniqueId().toString(), (int) (end - oldValue));
        sqlStats.addDamageGiven(damager.getUniqueId().toString(), (int) (end - oldValue));
        damage.put(p, end);
    }
    
    public void swordDamage(Player p, Entity damager, ItemStack sword) {
        randomNumber = rn.nextInt(7);
        
        if (damage.get(p) == 0.0) {
            damage.put(p, 1.0);
        }
        Double oldValue = damage.get(p);
        
        if (sword.getType() == Material.DIAMOND_SWORD) {
            double end = oldValue * 2.1 + 15 + randomNumber;
            if (end > 999) {
                end = 999;
            }
            sqlStats.addDamageTaken(p.getUniqueId().toString(), (int) (end - oldValue));
            sqlStats.addDamageGiven(damager.getUniqueId().toString(), (int) (end - oldValue));
            damage.put(p, end);
            
        } else if (sword.getType() == Material.GOLD_SWORD) {
            double end = oldValue * 1.4 + 10 + randomNumber;
            if (end > 999) {
                end = 999;
            }
            sqlStats.addDamageTaken(p.getUniqueId().toString(), (int) (end - oldValue));
            sqlStats.addDamageGiven(damager.getUniqueId().toString(), (int) (end - oldValue));
            damage.put(p, end);
            
        } else if (sword.getType() == Material.IRON_SWORD) {
            double end = oldValue * 1.7 + 12 + randomNumber;
            if (end > 999) {
                end = 999;
            }
            sqlStats.addDamageTaken(p.getUniqueId().toString(), (int) (end - oldValue));
            sqlStats.addDamageGiven(damager.getUniqueId().toString(), (int) (end - oldValue));
            damage.put(p, end);
            
        }
        p.setVelocity(calcVelocity(p, damager));
    }
    
    public void lavaDmg(Player p) {
        
        if (Smash.getInstance().getCooldownManager().getLavaCooldown().run.containsKey(p)) {
            return;
        }
        
        Smash.getInstance().getCooldownManager().getLavaCooldown().start(p);
        
        randomNumber = rn.nextInt(11);
        
        if (damage.get(p) == 0.0) {
            damage.put(p, 1.0);
        }
        Double oldValue = damage.get(p);
        double end = oldValue + 15 + randomNumber;
        
        if (end > 999) {
            end = 999;
        }
        damage.put(p, end);
        sqlStats.addDamageTaken(p.getUniqueId().toString(), (int) (end - oldValue));
        
        if (damage.get(p) >= 300) {
            end = 0;
            p.setHealth(20);
            p.setFireTicks(0);
            
            dieManager.playerDied(p);
        }
        damage.put(p, end);
    }
    
    public void fireDmg(Player p) {
        randomNumber = rn.nextInt(5);
        
        if (damage.get(p) == 0.0) {
            damage.put(p, 1.0);
        }
        Double oldValue = damage.get(p);
        double end = oldValue + 10 + randomNumber;
        
        if (end > 999) {
            end = 999;
        }
        
        sqlStats.addDamageTaken(p.getUniqueId().toString(), (int) (end - oldValue));
        damage.put(p, end);
    }
    
    public org.bukkit.util.Vector calcVelocity(Player p, Entity damager) {
        org.bukkit.util.Vector v = damager.getLocation().getDirection().multiply(getDmg(p));
        
        return p.getVelocity().add(v.multiply(0.1).setY(Math.round(getDmg(p))));
    }
    
    public org.bukkit.util.Vector fireballVelocity(Player p, Entity damager) {
        org.bukkit.util.Vector v = damager.getLocation().getDirection().multiply(getDmg(p));
        
        return p.getVelocity().add(v.multiply(0.5).setY(Math.round(getDmg(p) * 0.3)));
    }
    
    public org.bukkit.util.Vector smashAttackVelocity(Player p, Entity damager) {
        Vector v = damager.getLocation().getDirection().multiply(getDmg(p));
        
        return p.getVelocity().add(v.multiply(0.5).setY(getDmg(p) / 32));
    }
    
    public void smashAttack(Player p) {
        Location loc = p.getLocation();
        World w = loc.getWorld();
        
        players.clear();
        players.addAll(Smash.getInstance().getPlayers());
        
        players.remove(p);
        
        for (double x = loc.getX() - 1; x <= loc.getX() + 1; x++) {
            for (double y = loc.getY() - 1; y <= loc.getY() + 1; y++) {
                for (double z = loc.getX() - 1; z <= loc.getX() + 1; z++) {
                    
                    Location l = new Location(w, x, y, z);
                    
                    for (int i = 0; i < players.size(); i++) {
                        
                        Player current = players.get(i);
                        
                        if (       current.getLocation().getX() - p.getLocation().getX() <= 3
                                && current.getLocation().getY() - p.getLocation().getY() <= 3
                                && current.getLocation().getZ() - p.getLocation().getZ() <= 3) {
                            
                            killer.put(current, p);
                            addDmgFromSmash(current, p);
                            current.setVelocity(smashAttackVelocity(current, p));
                            
                            Smash.getInstance().getScoreManager().getScoreboard().reloadScoreboard();
                            
                            players.remove(current);
                            
                            if(players.size() == 0) return;
                            
                        } else {
                            players.remove(current);
                            if(players.size() == 0) return;
                        }
                    }
                }
            }
        }
    }
    
}
