package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class SimpleSword extends Weapon {
    public SimpleSword() {
        super();
        this.name = "Обычный меч";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.attackBonus = 5;
            this.speedBonus = 3;
            this.quality = "Плохое";
            this.kritChanceBonus = 3;
            this.cost = 10;
        } else if(chance < 80) {
            this.attackBonus = 7;
            this.speedBonus = 2;
            this.quality = "Среднее";
            this.kritChanceBonus = 4;
            this.cost = 20;
        } else if(chance <= 100) {
            this.attackBonus = 9;
            this.speedBonus = 1;
            this.quality = "Хорошее";
            this.kritChanceBonus = 5;
            this.cost = 30;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("black")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("black")).append("Меч\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("black")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Обычный меч, наносит малый урон с низкой скоростью.");
        return res.toString();
    }
}
