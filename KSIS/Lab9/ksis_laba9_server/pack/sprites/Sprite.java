package pack.sprites;

import java.util.ArrayList;
import java.util.HashMap;

import pack.weapons.Item;

public abstract class Sprite {

    public String name;

    //parametrs
    public int attack;
    public int speed;
    public int health;
    public int stamina;
    public int armor;
    public int critChance;
    public int mode;

    public int currentTime;

    public int killExp;
    public int exp;

    public String messageString = "";


    public ArrayList<Item> inventory = new ArrayList<Item>();
    public HashMap<String, Item> equipment = new HashMap<String, Item>();

    abstract public String autoAttack(Sprite enemy);
    abstract public String useSpell(String name, Sprite enemy);
    final public String killed(Sprite killer) {
        killer.exp += this.killExp;
        return killer.name + " убивает " + this.name + " и получает опыт " + this.killExp;
    }
}
