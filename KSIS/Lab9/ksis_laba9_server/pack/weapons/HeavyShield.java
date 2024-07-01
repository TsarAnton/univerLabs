package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class HeavyShield extends Shield {
    public HeavyShield() {
        super();
        this.name = "Большой щит";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.armorBonus = 5;
            this.speedBonus = 3;
            this.quality = "Плохое";
            this.cost = 55;
        } else if(chance < 80) {
            this.armorBonus = 7;
            this.speedBonus = 3;
            this.quality = "Среднее";
            this.cost = 60;
        } else if(chance <= 100) {
            this.armorBonus = 10;
            this.speedBonus = 3;
            this.quality = "Хорошее";
            this.cost = 70;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("yellow")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("yellow")).append("Щит\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("yellow")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Тяжелый щит, сильно увеличивает защиту и уменьшает скорость атаки.");
        return res.toString();
    }
}
