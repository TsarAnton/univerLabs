package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class LegendaryRing extends Ring {
    public LegendaryRing() {
        super();
        this.name = "Золотое кольцо";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.staminaBonus = 7;
            this.quality = "Плохое";
            this.cost = 55;
        } else if(chance < 80) {
            this.staminaBonus = 8;
            this.quality = "Среднее";
            this.cost = 60;
        } else if(chance <= 100) {
            this.staminaBonus = 9;
            this.quality = "Хорошее";
            this.cost = 70;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("yellow")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("yellow")).append("Кольцо\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("yellow")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Золотое кольцо, сильно увеличивает выносливость.");
        return res.toString();
    }
}
