package pack.map;

import java.util.ArrayList;

import pack.Color;
import pack.RandomNumber;
import pack.sprites.DeadBody;
import pack.sprites.Enemy;
import pack.sprites.Player;

public abstract class Cell {

    public String name;
    public String description;
    
    public Cell west;
    public Cell east;
    public Cell north;
    public Cell south;
    
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public ArrayList<DeadBody> deadBodies = new ArrayList<DeadBody>();

    public String toString(Player player) {
        StringBuilder str = new StringBuilder();
        str.append("Вы находитесь в локации ").append(Color.setColor("dark blue")).append(name).append(Color.setColor("white")).append("\n")
            .append(description).append("\n");
        if(north != null) {
            str.append("На севере находиться ").append(Color.setColor("dark blue")).append(north.name).append(Color.setColor("white")).append("\n");
        }
        if(south != null) {
            str.append("На юге находиться ").append(Color.setColor("dark blue")).append(south.name).append(Color.setColor("white")).append("\n");
        }
        if(west != null) {
            str.append("На востоке находиться ").append(Color.setColor("dark blue")).append(west.name).append(Color.setColor("white")).append("\n");
        }
        if(east != null) {
            str.append("На западе находиться ").append(Color.setColor("dark blue")).append(east.name).append(Color.setColor("white")).append("\n");
        }
        if(players.size() != 1) {
            str.append("Вы видите других игроков: ").append("\n");
            for(int i = 0; i < players.size(); i++) {
                if(!players.get(i).name.equals(player.name)) {
                    str.append(Color.setColor("yellow")).append(players.get(i).name).append(Color.setColor("white")).append("{").append(Color.setColor("yellow")).append(players.get(i).level).append(Color.setColor("white")).append("}\n");
                }
            }
        }
        if(!enemies.isEmpty()) {
            str.append("Вы видите противников: ").append("\n");
            for(int i = 0; i < enemies.size(); i++) {
                str.append(Color.setColor("red")).append(enemies.get(i).name).append(Color.setColor("white")).append("{").append(i).append("}\n");
            }
        }
        if(!deadBodies.isEmpty()) {
            str.append("Вы видите мертвые тела:").append("\n");
            for(int i = 0; i < deadBodies.size(); i++) {
                str.append(Color.setColor("black")).append(deadBodies.get(i).name).append(Color.setColor("white")).append("{").append(i).append("}\n");
            }
        }
        return str.toString();
    };

    public void update() {
        if(enemies.size() <= 4) {
            double chance = RandomNumber.getRandomNumber(0, 1000);
            if(chance <= 10) {
                enemies.add(new Enemy());
            }
        }
    }
}
