//В ПАПКУ LIB ЗАКИНУТЬ АРХИВ strings(ЛЕЖИТ НА SDO)
import by.vsu.mf.ai.ssd.strings.Manager;

public class Runner {
    public static void main(String[] args) {
        Updater sc = new Updater();
        StringBuilder strb = new StringBuilder();

        sc.perform(strb);

        Manager.createWindow(sc);
    }
}
