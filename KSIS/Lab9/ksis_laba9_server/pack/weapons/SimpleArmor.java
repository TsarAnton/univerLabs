package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class SimpleArmor extends Armor {
    public SimpleArmor() {
        super();
        this.name = "Кожаная броня";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.healthBonus = 5;
            this.quality = "Плохое";
            this.cost = 10;
        } else if(chance < 80) {
            this.healthBonus = 10;
            this.quality = "Среднее";
            this.cost = 20;
        } else if(chance <= 100) {
            this.healthBonus = 15;
            this.quality = "Хорошее";
            this.cost = 30;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("black")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("black")).append("Броня\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("black")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Кожаная броня, немного увеличивает здоровье.");
        return res.toString();
    }
}
