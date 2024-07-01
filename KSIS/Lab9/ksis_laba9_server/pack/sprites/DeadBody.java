package pack.sprites;

import java.util.ArrayList;
import java.util.Map.Entry;

import pack.weapons.Item;

public class DeadBody {

    public String name;

    ArrayList<Item> items = new ArrayList<Item>();

    public DeadBody(Sprite deadMan) {
        name = "Мертвое тело: " + deadMan.name;
        for(int i = 0; i < deadMan.inventory.size(); i++) {
            items.add(deadMan.inventory.get(i));
        }
        for(Entry<String, Item> entry:deadMan.equipment.entrySet()) {
            if(entry.getValue() != null) {
                items.add(entry.getValue());
            }
        }
    }
}
