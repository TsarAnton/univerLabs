package pack;

import pack.map.Map;

public class Tick extends Thread {

    public static int tick = 0;
    public static int tick1 = 0;

    public Tick() {}
    public void initialize() {
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    public void run() {
        while(true) {
            if(tick % 300000000 == 0) {
                tick1++;
                if(tick1 % 150 == 0) {
                    Magazine.update();
                }
                for(int i = 0; i < Game.players.size(); i++) {
                    Game.players.get(i).update();
                    Game.players.get(i).angry.update(Game.players.get(i));
                    Game.players.get(i).strongAttack.update(Game.players.get(i));
                    Game.players.get(i).block.update(Game.players.get(i));
                }
                Map.building.update();
                Map.cityCenter.update();
                Map.coast.update();
                Map.crypt.update();
                Map.docks.update();
                Map.factory.update();
                Map.forest.update();
                Map.graveyard.update();
                Map.industrialArea.update();
                Map.park.update();
                Map.port.update();
                Map.railwayStation.update();
                Map.residence.update();
            }
            tick++;
        }
    }
}
