package me.gmx.epicutil.handler;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PingHandler {
    public static ArrayList<Player> pings;
    static{
        pings = new ArrayList<Player>();
    }

    public static void addPlayer(Player p){
        if (!pings.contains(p)){
            pings.add(p);
        }
    }

    public static void removePlayer(Player p){
        if (pings.contains(p)){
            pings.remove(p);
        }
    }

    public static boolean isPing(Player p){
        return pings.contains(p);
    }



}
