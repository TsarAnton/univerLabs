package pack;

public class Runner {
	public static void main(String[] args) {
        System.out.println("String:");
        System.out.println(args[0]);

        Updater buff = new Updater(args[0]);
        StringBuilder str = buff.new_string();

        System.out.println("String after update:");
        System.out.println(str);
    }
}
