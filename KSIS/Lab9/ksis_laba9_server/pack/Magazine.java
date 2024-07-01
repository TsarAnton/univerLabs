package pack;

import java.util.ArrayList;

import pack.weapons.*;

public class Magazine {
    public static ArrayList<Item> items = new ArrayList<Item>();
    public static int size = 5;

    public static void update() {
        for(int i = 0; i < items.size(); i++) {
            items.remove(i);
        }
        for(int i = 0; i < size; i++) {
            generateItem();
        }
    }

    public static void generateItem() {
        double chance2 = RandomNumber.getRandomNumber(0, 101);
        if(chance2 <= 25) {
            double chance3 = RandomNumber.getRandomNumber(0, 101);
            if(chance3 <= 70) {
                items.add(new SmallShield());
            } else if(chance3 <= 90) {
                items.add(new MediumShield());
            } else {
                items.add(new HeavyShield());
            }
        } else if(chance2 <= 50) {
            double chance3 = RandomNumber.getRandomNumber(0, 101);
            if(chance3 <= 70) {
                items.add(new SimpleSword());
            } else if(chance3 <= 90) {
                items.add(new GoodSword());
            } else {
                items.add(new LegendarySword());
            }
        } else if(chance2 <= 75) {
            double chance3 = RandomNumber.getRandomNumber(0, 101);
            if(chance3 <= 70) {
                items.add(new SimpleRing());
            } else if(chance3 <= 90) {
                items.add(new GoodRing());
            } else {
                items.add(new LegendaryRing());
            }
        } else {
            double chance3 = RandomNumber.getRandomNumber(0, 101);
            if(chance3 <= 70) {
                items.add(new SimpleArmor());
            } else if(chance3 <= 90) {
                items.add(new GoodArmor());
            } else {
                items.add(new LegendaryArmor());
            }
        }
    }

    public static String toString1() {
        StringBuilder str = new StringBuilder();
        str.append("Ассортимент\n");
        for(int i = 0; i < items.size(); i++) {
            str.append("Слот ").append(Color.setColor("blue")).append(i).append(Color.setColor("white")).append(": ").append(items.get(i).shortName()).append("\n")
                .append("Цена: ").append(Color.setColor("yellow")).append(items.get(i).cost).append(Color.setColor("white")).append("\n");
        }
        return str.toString();
    }
}
