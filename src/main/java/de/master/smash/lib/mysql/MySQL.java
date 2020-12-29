/*
 * Copyright (c) 2020. All rights preserved.
 * Creator Masterphoenix
 * Contact: Discord: Masterphoenix#8969
 */

package de.master.smash.lib.mysql;

import de.master.smash.lib.MasterLib;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    String host = "localhost";
    String database = "smash";
    String user = "smash";
    String password = "A*C`9YEV;uCnavVMxqZ7";

    Connection con;

    @Getter
    SQLStats sqlStats;

    public MySQL() {
        sqlStats = new SQLStats();
    }
    
    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", user, password);
            Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§8[§b§lMySQL§8] §aVerbindung hergestellt!");
        } catch (SQLException throwables) {
            Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§8[§b§lMySQL§8] §cVerbindung konnte nicht hergestellt werden!");
            Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§8[§b§lMySQL§8] §cError:" + throwables.getMessage());
        }
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
                Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§8[§b§lMySQL§8] §cVerbindung unterbrochen!");
            }
        } catch (SQLException throwables) {
            Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§8[§b§lMySQL§8] §cVerbindung konnte nicht unterbrochen werden!");
            Bukkit.getConsoleSender().sendMessage(MasterLib.prefix + "§8[§b§lMySQL§8] §cError:" + throwables.getMessage());
        }
    }

    public void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
}
