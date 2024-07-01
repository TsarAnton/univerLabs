package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class GoodSword extends Weapon {
    public GoodSword() {
        super();
        this.name = "Хороший меч";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.attackBonus = 7;
            this.speedBonus = 2;
            this.quality = "Плохое";
            this.kritChanceBonus = 4;
            this.cost = 35;
        } else if(chance < 80) {
            this.attackBonus = 9;
            this.speedBonus = 1;
            this.quality = "Среднее";
            this.kritChanceBonus = 6;
            this.cost = 40;
        } else if(chance <= 100) {
            this.attackBonus = 11;
            this.speedBonus = 0;
            this.quality = "Хорошее";
            this.kritChanceBonus = 7;
            this.cost = 50;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("red")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("red")).append("Меч\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("red")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Хороший меч, наносит средний урон с нормальной скоростью.");
        return res.toString();
    }
}
