package pack;

public class RandomNumber {
    public static double getRandomNumber(int a, int b) {
        return ( Math.random() * (b-a) ) + a;
    }
}
