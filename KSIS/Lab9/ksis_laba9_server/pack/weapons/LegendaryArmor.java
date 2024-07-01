package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class LegendaryArmor extends Armor {
    public LegendaryArmor() {
        super();
        this.name = "Мифриловая броня";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.healthBonus = 35;
            this.quality = "Плохое";
            this.cost = 55;
        } else if(chance < 80) {
            this.healthBonus = 40;
            this.quality = "Среднее";
            this.cost = 60;
        } else if(chance <= 100) {
            this.healthBonus = 45;
            this.quality = "Хорошее";
            this.cost = 70;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("yellow")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("yellow")).append("Броня\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("yellow")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Мифриловая броня, сильно увеличивает здоровье.");
        return res.toString();
    }
}

