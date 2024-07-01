package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class GoodArmor extends Armor {
    public GoodArmor() {
        super();
        this.name = "Железная броня";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.healthBonus = 20;
            this.quality = "Плохое";
            this.cost = 35;
        } else if(chance < 80) {
            this.healthBonus = 25;
            this.quality = "Среднее";
            this.cost = 40;
        } else if(chance <= 100) {
            this.healthBonus = 30;
            this.quality = "Хорошее";
            this.cost = 50;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("green")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("green")).append("Броня\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("green")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Железная броня, увеличивает здоровье.");
        return res.toString();
    }
}
