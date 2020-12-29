/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.mysql;

import de.master.smash.core.Smash;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLStats {

    public boolean playerExists(String uuid) {

        try {
            ResultSet rs = Smash.getInstance().getMySQL().query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");

            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void createPlayer(String uuid) {
        if (!(playerExists(uuid))) {
            Smash.getInstance().getMySQL().update("INSERT INTO Stats(UUID, KILLS, DEATHS, DAMAGEGIVEN, DAMAGETAKEN, GAMES, WINS) VALUES('" + uuid + "', '0', '0', '0', '0', '0', '0');");
        }
    }

    public int getPlayerKills(String uuid) {
        int kills = 0;
        if (playerExists(uuid)) {

            try {
                ResultSet rs = Smash.getInstance().getMySQL().query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if (!(rs.next()) || !(rs.getInt("KILLS") == 0)) {
                    kills = rs.getInt("KILLS");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            createPlayer(uuid);
            getPlayerKills(uuid);
        }

        return kills;
    }

    public int getPlayerDeaths(String uuid) {
        int deaths = 0;
        if (playerExists(uuid)) {

            try {
                ResultSet rs = Smash.getInstance().getMySQL().query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if (!(rs.next()) || !(rs.getInt("DEATHS") == 0)) {
                    deaths = rs.getInt("DEATHS");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            createPlayer(uuid);
            getPlayerKills(uuid);
        }

        return deaths;
    }

    public int getPlayerDamageGiven(String uuid) {
        int damage = 0;
        if (playerExists(uuid)) {

            try {
                ResultSet rs = Smash.getInstance().getMySQL().query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if (!(rs.next()) || !(rs.getInt("DAMAGEGIVEN") == 0)) {
                    damage = rs.getInt("DAMAGEGIVEN");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            createPlayer(uuid);
            getPlayerKills(uuid);
        }

        return damage;
    }

    public int getPlayerDamageTaken(String uuid) {
        int damage = 0;
        if (playerExists(uuid)) {

            try {
                ResultSet rs = Smash.getInstance().getMySQL().query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if (!(rs.next()) || !(rs.getInt("DAMAGETAKEN") == 0)) {
                    damage = rs.getInt("DAMAGETAKEN");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            createPlayer(uuid);
            getPlayerDamageTaken(uuid);
        }

        return damage;
    }

    public int getPlayerGames(String uuid) {
        int damage = 0;
        if (playerExists(uuid)) {

            try {
                ResultSet rs = Smash.getInstance().getMySQL().query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if (!(rs.next()) || !(rs.getInt("GAMES") == 0)) {
                    damage = rs.getInt("GAMES");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            createPlayer(uuid);
            getPlayerGames(uuid);
        }

        return damage;
    }

    public int getPlayerWins(String uuid) {
        int wins = 0;
        if (playerExists(uuid)) {

            try {
                ResultSet rs = Smash.getInstance().getMySQL().query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if (!(rs.next()) || !(rs.getInt("WINS") == 0)) {
                    wins = rs.getInt("WINS");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            createPlayer(uuid);
            getPlayerWins(uuid);
        }

        return wins;
    }

    public void setKills(String uuid, int kills) {
        if (playerExists(uuid)) {
            Smash.getInstance().getMySQL().update("UPDATE Stats SET KILLS= '" + kills + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setKills(uuid, kills);
        }
    }

    public void setDeaths(String uuid, int deaths) {
        if (playerExists(uuid)) {
            Smash.getInstance().getMySQL().update("UPDATE Stats SET DEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setDeaths(uuid, deaths);
        }
    }

    public void setDamageGiven(String uuid, int damage) {
        if (playerExists(uuid)) {
            Smash.getInstance().getMySQL().update("UPDATE Stats SET DAMAGEGIVEN= '" + damage + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setDamageGiven(uuid, damage);
        }
    }

    public void setDamageTaken(String uuid, int damage) {
        if (playerExists(uuid)) {
            Smash.getInstance().getMySQL().update("UPDATE Stats SET DAMAGETAKEN= '" + damage + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setDamageTaken(uuid, damage);
        }
    }

    public void setGames(String uuid, int games) {
        if (playerExists(uuid)) {
            Smash.getInstance().getMySQL().update("UPDATE Stats SET GAMES= '" + games + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setKills(uuid, games);
        }
    }

    public void setWins(String uuid, int wins) {
        if (playerExists(uuid)) {
            Smash.getInstance().getMySQL().update("UPDATE Stats SET WINS= '" + wins + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setDeaths(uuid, wins);
        }
    }

    public void addKills(String uuid, int kills) {
        if (playerExists(uuid)) {
            setKills(uuid, getPlayerKills(uuid) + kills);
        } else {
            createPlayer(uuid);
            addKills(uuid, kills);
        }
    }

    public void addDeaths(String uuid, int deaths) {
        if (playerExists(uuid)) {
            setDeaths(uuid, getPlayerDeaths(uuid) + deaths);
        } else {
            createPlayer(uuid);
            addDeaths(uuid, deaths);
        }
    }

    public void addDamageGiven(String uuid, int damage) {
        if (playerExists(uuid)) {
            setDamageGiven(uuid, getPlayerDamageGiven(uuid) + damage);
        } else {
            createPlayer(uuid);
            addDamageGiven(uuid, damage);
        }
    }

    public void addDamageTaken(String uuid, int damage) {
        if (playerExists(uuid)) {
            setDamageTaken(uuid, getPlayerDamageTaken(uuid) + damage);
        } else {
            createPlayer(uuid);
            addDamageTaken(uuid, damage);
        }
    }

    public void addGames(String uuid, int games) {
        if (playerExists(uuid)) {
            setGames(uuid, getPlayerGames(uuid) + games);
        } else {
            createPlayer(uuid);
            addGames(uuid, games);
        }
    }

    public void addWins(String uuid, int wins) {
        if (playerExists(uuid)) {
            setWins(uuid, getPlayerWins(uuid) + wins);
        } else {
            createPlayer(uuid);
            addWins(uuid, wins);
        }
    }

    /*public void removeKills(String uuid, int kills) {
        if (playerExists(uuid)) {
            setKills(uuid, getPlayerKills(uuid) - kills);
        } else {
            createPlayer(uuid);
            removeKills(uuid, kills);
        }
    }

    public void removeDeaths(String uuid, int deaths) {
        if (playerExists(uuid)) {
            setDeaths(uuid, getPlayerDeaths(uuid) - deaths);
        } else {
            createPlayer(uuid);
            removeDeaths(uuid, deaths);
        }
    }*/
}