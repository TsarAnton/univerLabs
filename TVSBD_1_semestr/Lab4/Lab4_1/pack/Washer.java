package pack;

public class Washer {
    public int washedPlates;
    public int wastedTime;
    public Plate plate;

    public Washer() {
        washedPlates = 0;
        wastedTime = 0;
        plate = null;
    }

    public boolean isWashing() {
        if(plate == null) {
            return false;
        }
        return true;
    }

}
