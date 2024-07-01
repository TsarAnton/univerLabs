package pack.sprites;

import pack.Color;
import pack.Frame;
import pack.RandomNumber;
import pack.map.Cell;
import pack.map.Map;
import pack.skills.Angry;
import pack.skills.Block;
import pack.skills.StrongAttack;
import pack.weapons.Item;

import java.io.DataOutputStream;
import java.util.Map.Entry;

public class Player extends Sprite {
    public Cell place;
    public int money;

    int needExp;
    public int level;
    public int bonusPoints;

    public Block block;
    public Angry angry;
    public StrongAttack strongAttack;

    public Player(String name, DataOutputStream dos) {
        this.name = name;
        this.money = 100;
        this.mode = 0;

        this.health = 100;
        this.armor = 0;
        this.speed = 10;
        this.critChance = 5;
        this.stamina = 10;
        this.attack = 0;

        equipment.put("Броня", null);//health
        equipment.put("Оружие", null);//attack
        equipment.put("Щит", null);//armor
        equipment.put("Кольцо",null);//stamina

        this.level = 1;
        this.needExp = 100;
        this.bonusPoints = 10;
        this.killExp = 200;

        block = new Block();
        angry = new Angry();
        strongAttack = new StrongAttack();

        this.place = Map.cityCenter;
        Map.cityCenter.players.add(this);
        this.currentTime = 0;
    }

    public String equip(Item item) {
        switch(item.type) {
            case("Оружие"):
                if(equipment.get("Оружие") != null) {
                    Item buff = equipment.get("Оружие");
                    inventory.add(buff);
                    attack -= buff.attackBonus;
                    speed -= buff.speedBonus;
                    critChance -= buff.kritChanceBonus;
                }
                equipment.put("Оружие", item);
                inventory.remove(item);
                attack += item.attackBonus;
                speed += item.speedBonus;
                critChance += item.kritChanceBonus;
                break;
            case("Броня"):
                if(equipment.get("Броня") != null) {
                    Item buff = equipment.get("Броня");
                    inventory.add(buff);
                    health -= buff.healthBonus;
                }
                inventory.remove(item);
                equipment.put("Броня", item);
                health += item.healthBonus;
                break;
            case("Щит"):
                if(equipment.get("Щит") != null) {
                    Item buff = equipment.get("Щит");
                    inventory.add(buff);
                    health -= buff.healthBonus;
                }
                inventory.remove(item);
                equipment.put("Щит", item);
                armor += item.armorBonus;
                break;
            case("Кольцо"):
                if(equipment.get("Кольцо") != null) {
                    Item buff = equipment.get("Кольцо");
                    inventory.add(buff);
                    stamina -= buff.staminaBonus;
                }
                inventory.remove(item);
                equipment.put("Кольцо", item);
                stamina += item.staminaBonus;
                break;
        }
        return "Экипировано: " + item.shortName();
    }

    public String getItems(DeadBody deadBody) {
        StringBuilder res = new StringBuilder();
        res.append("Вы обыскали ").append(deadBody.name).append("\n");
        if(deadBody.items.isEmpty()) {
            res.append("Пусто");
        } else {
            res.append("Получены предметы:\n");
            for(int i = 0; i < deadBody.items.size(); i++) {
                res.append(deadBody.items.get(i).shortName()).append("\n");
                inventory.add(deadBody.items.get(i));
            }
        }
        return res.toString();
    }

    @Override
    public String autoAttack(Sprite enemy) {
        if(currentTime == 0) {
            currentTime = speed;
            int delta = this.attack - enemy.armor;
            int critAttack = 0;
            int damage = 0;
            double crit = RandomNumber.getRandomNumber(0, 101);
            if(this.critChance >= crit) {
                double critBonus = RandomNumber.getRandomNumber(1, 11);
                critAttack = (int) critBonus;
            }
            if(delta > 0) {
                double chance = RandomNumber.getRandomNumber(0, 101);
                double minusAttack = RandomNumber.getRandomNumber(1, 6);
                if(chance < 50) {
                    damage = (int) (this.attack + critAttack - minusAttack - (enemy.armor / 2));
                } else if(chance < 80) {
                    damage = (int) (this.attack + critAttack - (enemy.armor / 2));
                } else {
                    damage = (int) (this.attack + critAttack + minusAttack - (enemy.armor /2));
                }
            } else {
                int p = (int) (Math.pow(Math.E, delta/10) * 100);

                if(p >= RandomNumber.getRandomNumber(0, 101)) {
                    damage = critAttack + 1;
                }
            }
            if(damage < 0) {
                damage = 0;
            }
            enemy.health -= damage;
            return this.name + " наносит " + enemy.name + " урон " + Color.setColor("red") + damage + Color.setColor("white") + " {"  + Color.setColor("green") + enemy.health  + Color.setColor("white") + "}";
        } else {
            currentTime--;
            return null;
        }
    }

    @Override
    public String useSpell(String name, Sprite enemy) {
        switch(name) {
            case("Блок"):
                return block.useSpell(this, enemy);
            case("Ярость"):
                return angry.useSpell(this, enemy);
            case("Гнев"):
               return  strongAttack.useSpell(this, enemy);
        }
        return "";
    }
    
    public void update() {
        if(this.exp >= this.needExp) {
            this.level++;
            this.bonusPoints += 3;
            this.needExp *= 2;
            this.killExp += 50;
            this.messageString += "\n" + new Frame(name + " получил новый уровень: " + Color.setColor("dark blue") + level + Color.setColor("white")).printFrame("dark blue");
        }
    }

    public String toString() {
        StringBuilder inv = new StringBuilder();
        if(inventory.isEmpty()) {
            inv.append("Пусто\n");
        } else {
            for(int i = 0; i < inventory.size(); i++) {
                inv.append(inventory.get(i).shortName()).append("{").append(i).append("}").append("\n");
            }
        }
        StringBuilder eq = new StringBuilder();
        for(Entry<String, Item> entry:equipment.entrySet()) {
            eq.append(entry.getKey()).append(": ");
            if(entry.getValue() == null) {
                eq.append("пусто\n");
            } else {
                eq.append(entry.getValue().shortName()).append("\n");
            }
        }
        StringBuilder str = new StringBuilder();
        str.append("Имя: ").append(Color.setColor("blue")).append(name).append(Color.setColor("white")).append("\n")
            .append("Уровень: ").append(Color.setColor("blue")).append(level).append(Color.setColor("white")).append("\n")
            .append("Свободные очки: ").append(Color.setColor("blue")).append(bonusPoints).append(Color.setColor("white")).append("\n")
            .append("Опыт: ").append(Color.setColor("blue")).append(exp).append(Color.setColor("white")).append("/").append(Color.setColor("blue")).append(needExp).append(Color.setColor("white")).append("\n\n")
            .append("Параметры: ").append("\n")
            .append("Здоровье: ").append(Color.setColor("green")).append(health).append(Color.setColor("white")).append("\n")
            .append("Выносливость: ").append(Color.setColor("green")).append(stamina).append(Color.setColor("white")).append("\n")
            .append("Скорость: ").append(Color.setColor("green")).append(speed).append(Color.setColor("white")).append("\n")
            .append("Урон: ").append(Color.setColor("green")).append(attack).append(Color.setColor("white")).append("\n")
            .append("Защита: ").append(Color.setColor("green")).append(armor).append(Color.setColor("white")).append("\n\n")
            .append("Инвентарь:\n").append(inv.toString()).append("\n")
            .append("Экипировка: \n").append(eq.toString()).append("\n\n")
            .append("Умения: ").append(Color.setColor("purple")).append("Гнев").append(Color.setColor("white")).append(", ").append(Color.setColor("purple")).append("Ярость").append(Color.setColor("white")).append(", ").append(Color.setColor("purple")).append("Блок").append(Color.setColor("whte"));
        return str.toString();
    }
}