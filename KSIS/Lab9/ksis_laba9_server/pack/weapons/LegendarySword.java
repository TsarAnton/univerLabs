package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class LegendarySword extends Weapon {
    public LegendarySword() {
        super();
        this.name = "Легендарный меч";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.attackBonus = 12;
            this.speedBonus = 2;
            this.quality = "Плохое";
            this.kritChanceBonus = 4;
            this.cost = 55;
        } else if(chance < 80) {
            this.attackBonus = 14;
            this.speedBonus = -1;
            this.quality = "Среднее";
            this.kritChanceBonus = 6;
            this.cost = 60;
        } else if(chance <= 100) {
            this.attackBonus = 16;
            this.speedBonus = -2;
            this.quality = "Хорошее";
            this.kritChanceBonus = 8;
            this.cost = 70;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("yellow")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("yellow")).append("Меч\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("yellow")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Легендарный меч, наносит большой урон с высокой скоростью.");
        return res.toString();
    }
}
