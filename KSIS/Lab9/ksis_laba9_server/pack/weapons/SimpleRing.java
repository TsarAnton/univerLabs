package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class SimpleRing extends Ring {
    public SimpleRing() {
        super();
        this.name = "Медное кольцо";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.staminaBonus = 1;
            this.quality = "Плохое";
            this.cost = 10;
        } else if(chance < 80) {
            this.staminaBonus = 2;
            this.quality = "Среднее";
            this.cost = 20;
        } else if(chance <= 100) {
            this.staminaBonus = 3;
            this.quality = "Хорошее";
            this.cost = 30;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("black")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("black")).append("Кольцо\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("black")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Медное кольцо, немного увеличивает выносливость.");
        return res.toString();
    }
}
