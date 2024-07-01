package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class GoodRing extends Ring {
    public GoodRing() {
        super();
        this.name = "Железное кольцо";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.staminaBonus = 4;
            this.quality = "Плохое";
            this.cost = 35;
        } else if(chance < 80) {
            this.staminaBonus = 5;
            this.quality = "Среднее";
            this.cost = 40;
        } else if(chance <= 100) {
            this.staminaBonus = 6;
            this.quality = "Хорошее";
            this.cost = 50;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("green")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("green")).append("Кольцо\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("green")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Железное кольцо, увеличивает выносливость.");
        return res.toString();
    }
}
