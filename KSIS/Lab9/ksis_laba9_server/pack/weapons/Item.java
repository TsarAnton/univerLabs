package pack.weapons;

public abstract class Item {
    public String name;
    public String quality;
    public String type;
    public int armorBonus;
    public int speedBonus;
    public int attackBonus;
    public int healthBonus;
    public int staminaBonus;
    public int kritChanceBonus;
    public int cost;

    public String shortName() {
        StringBuilder res = new StringBuilder();
        res.append(name).append("(").append(quality).append(")");
        return res.toString();
    }
}
