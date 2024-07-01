package pack.weapons;

import pack.Color;
import pack.RandomNumber;

public class SmallShield extends Shield {
    public SmallShield() {
        super();
        this.name = "Малый щит";
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance <= 50) {
            this.armorBonus = 2;
            this.speedBonus = 1;
            this.quality = "Плохое";
            this.cost = 10;
        } else if(chance < 80) {
            this.armorBonus = 3;
            this.speedBonus = 1;
            this.quality = "Среднее";
            this.cost = 20;
        } else if(chance <= 100) {
            this.armorBonus = 4;
            this.speedBonus = 1;
            this.quality = "Хорошее";
            this.cost = 30;
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Название: ").append(Color.setColor("black")).append(name).append("\n")
            .append(Color.setColor("white")).append("Тип: ").append(Color.setColor("black")).append("Щит\n")
            .append(Color.setColor("white")).append("Качество: ").append(Color.setColor("black")).append(quality).append("\n")
            .append(Color.setColor("white")).append("Малый щит, немного увеличивает защиту и уменьшает скорость атаки.");
        return res.toString();
    }
}


