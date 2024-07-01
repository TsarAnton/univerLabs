package pack.skills;

import pack.Color;
import pack.sprites.Player;
import pack.sprites.Sprite;

public class Block extends Skill {

    boolean reduce;

    public Block() {
        name = "Блок";
        cooldown = 20;
        currentTime = 0;
        actionTime = 15;
        currentActionTime = 0;
        reduce = false;
        staminaReduce = 3;
    }

    @Override
    public String useSpell(Player player, Sprite enemy) {
        if(player.stamina >= staminaReduce) {
            if(currentTime == 0) {
                currentTime = cooldown;
                player.armor += 5;
                player.stamina -= staminaReduce;
                currentActionTime = actionTime;
                reduce = true;
                return player.name + " использует "+Color.setColor("purple")+"Блок"+Color.setColor("white")+": защита увеличена";
            } else {
                return player.name + " не может использовать "+Color.setColor("purple")+"Блок"+Color.setColor("white")+": умение в откате";
            }
        } else {
            return player.name + " не может использовать "+Color.setColor("purple")+"Блок"+Color.setColor("white")+": не хватает выносливости";
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
            player.armor -= 5;
            reduce = false;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("purple")).append(name).append(Color.setColor("white")).append("\n")
            .append("Тип: ").append(Color.setColor("purple")).append("Умение").append(Color.setColor("white")).append("\n")
            .append("Временно увеличивает защиту.\n");
        return res.toString();
    }
}
