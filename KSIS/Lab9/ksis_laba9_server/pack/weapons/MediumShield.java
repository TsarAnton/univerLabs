package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class MediumShield extends Shield {
    public MediumShield() {
        super();
        this.name = "Средний щит";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.armorBonus = 4;
            this.speedBonus = 2;
            this.quality = "Плохое";
            this.cost = 35;
        } else if(chance < 80) {
            this.armorBonus = 5;
            this.speedBonus = 2;
            this.quality = "Среднее";
            this.cost = 40;
        } else if(chance <= 100) {
            this.armorBonus = 6;
            this.speedBonus = 2;
            this.quality = "Хорошее";
            this.cost = 50;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("green")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("green")).append("Щит\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("green")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Средний щит, увеличивает защиту и уменьшает скорость атаки.");
        return res.toString();
    }
}
