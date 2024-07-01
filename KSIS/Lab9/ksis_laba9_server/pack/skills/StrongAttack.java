package pack.skills;

import pack.Color;
import pack.sprites.Player;
import pack.sprites.Sprite;

public class StrongAttack extends Skill{
    public StrongAttack() {
        name = "Гнев";
        cooldown = 10;
        currentTime = 0;
        staminaReduce = 4;
    }

    @Override
    public String useSpell(Player player, Sprite enemy) {
        if(player.stamina >= staminaReduce) {
            if(currentTime == 0) {
                currentTime = cooldown;
                enemy.health -= player.attack * 2;
                player.stamina -= staminaReduce;
                return player.name +  " использует "+Color.setColor("purple")+"Гнев: "+Color.setColor("white") + enemy.name + " получает урон " + Color.setColor("red") + (player.attack * 2) + Color.setColor("white")+"{" + Color.setColor("green")+enemy.health + Color.setColor("white")+"}";
            } else {
                return player.name +  " не может использовать "+Color.setColor("purple")+"Гнев: "+Color.setColor("white") + "умение в откате";
            }
        } else {
            return player.name +  " не может использовать "+Color.setColor("purple")+"Гнев: "+Color.setColor("white") + "не хватает выносливости";
        }
    }

    @Override
    public void update(Player player) {
        if(currentTime != 0) {
            currentTime--;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("purple")).append(name).append(Color.setColor("white")).append("\n")
            .append("Тип: ").append(Color.setColor("purple")).append("Умение").append(Color.setColor("white")).append("\n")
            .append("Мнгновенно наносит противнику удвоенный урон.\n");
        return res.toString();
    }
}
