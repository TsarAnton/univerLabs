package pack.skills;

import pack.sprites.Player;
import pack.sprites.Sprite;

public abstract class Skill {
    public String name;
    public int cooldown;
    public int currentTime;
    public int currentActionTime;
    public int actionTime;
    public int staminaReduce;

    abstract public String useSpell(Player player, Sprite enemy);
    abstract public void update(Player player);
}
