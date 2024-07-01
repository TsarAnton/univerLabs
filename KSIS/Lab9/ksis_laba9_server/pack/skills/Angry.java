package pack.skills;

import pack.Color;
import pack.sprites.Player;
import pack.sprites.Sprite;

public class Angry extends Skill {

    boolean reduce;
    int reduceNum;

    public Angry() {
        name = "Ярость";
        cooldown = 25;
        currentTime = 0;
        actionTime = 10;
        currentActionTime = 0;
        reduce = false;
        reduceNum = 0;
        staminaReduce = 5;
    }

    @Override
    public String useSpell(Player player, Sprite enemy) {
        if(player.stamina >= staminaReduce) {
            if(currentTime == 0) {
                currentTime = cooldown;
                if(player.speed != 0) {
                    if(player.speed == 1) {
                        player.speed -= 1;
                        reduceNum = 1;
                    } else if (player.speed == 2) {
                        player.speed -= 2;
                        reduceNum = 2;
                    } else {
                        player.speed -= 3;
                        reduceNum = 3;
                    }
                    player.stamina -= staminaReduce;
                    currentActionTime = actionTime;
                    reduce = true;
                    return player.name + " использует "+Color.setColor("purple")+"Ярость"+Color.setColor("white")+": скорость атаки увеличена";
                } else {
                    return player.name + " не может использовать "+Color.setColor("purple")+"Ярость"+Color.setColor("white")+": скорость атаки максимальна";
                }
            } else {
                return player.name + " не может использовать "+Color.setColor("purple")+"Ярость"+Color.setColor("white")+": умение в откате";
            }
        } else {
            return player.name + " не может использовать "+Color.setColor("purple")+"Ярость"+Color.setColor("white")+": не хватает выносливости";
        }
    }

    @Override
    public void update(Player player) {
        if(currentTime != 0) {
            currentTime--;
        }
        if(currentActionTime != 0) {
            currentActionTime--;
        } else if(reduce) {
            player.speed += reduceNum;
            reduce = false;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("purple")).append(name).append(Color.setColor("white")).append("\n")
            .append("Тип: ").append(Color.setColor("purple")).append("Умение").append(Color.setColor("white")).append("\n")
            .append("Временно увеличивает скорость атаки.\n");
        return res.toString();
    }
}
