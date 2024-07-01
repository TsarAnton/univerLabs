package pack;

import java.util.ArrayList;

import pack.sprites.Player;

public class Game {
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static ArrayList<Fight> fights = new ArrayList<Fight>();

    public static Fight getFightByPlayerName(String name) {
        for(int i = 0; i < fights.size(); i++) {
            if(fights.get(i).player.name.equals(name)) {
                return fights.get(i);
            }
        }
        return null;
    }
}
