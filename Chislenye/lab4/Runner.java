import java.util.Scanner;
import java.util.ArrayList;

public class Runner {

    public static int space = 23;

    public static void printLine(String name, ArrayList<Double> array) {
        StringBuilder str = new StringBuilder();
        str.append(new BeatyString<String>().get(name, space));
        for(int i = 0; i < array.size(); i++) {
            if(array.get(i) != null) {
                str.append(new BeatyString<Double>().get(array.get(i), space));
            } else {
                str.append(new BeatyString<String>().get("-", space));
            }
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        double a, b, h;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите a и b");
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        if(a <= 0 || b <= 0 || b < a) {
            System.out.println("Задайте другой промежуток");
            System.exit(1);
        }
        System.out.println(("Введите шаг h"));
        h = scanner.nextDouble();
        if(h > b - a) {
            System.out.println("Слишком большой шаг");
            System.exit(1);
        }
        int n = (int) ((b - a) / h) + 1;

        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            x.add(a + h * i);
            y.add(Math.log(x.get(i)) - 1 / x.get(i));
            //y.add(x.get(i) * x.get(i) * x.get(i));
        }

        ArrayList<Double> yFirstRight = new ArrayList<>();
        ArrayList<Double> yFirstLeft = new ArrayList<>();
        ArrayList<Double> yFirstCenter = new ArrayList<>();
        ArrayList<Double> ySecond = new ArrayList<>();
        ArrayList<Double> yFirstExact = new ArrayList<>();
        ArrayList<Double> ySecondExact = new ArrayList<>();

        //правая
        for(int i = 0; i < n - 1; i++) {
            yFirstRight.add((y.get(i + 1) - y.get(i)) / h);
        }
        yFirstRight.add(null);

        //левая
        yFirstLeft.add(null);
        for(int i = 1; i < n; i++) {
            yFirstLeft.add((y.get(i) - y.get(i - 1)) / h);
        }

        //центральная
        yFirstCenter.add(null);
        for(int i = 1; i < n - 1; i++) {
            yFirstCenter.add((y.get(i + 1) - y.get(i - 1)) / (2 * h));
        }
        yFirstCenter.add(null);

        //точная
        for(int i = 0; i < n; i++) {
            yFirstExact.add(1 / x.get(i) + 1 / (x.get(i) * x.get(i)));
            //yFirstExact.add(3 * x.get(i) * x.get(i));
        }

        //вторая
        ySecond.add(null);
        for(int i = 1; i < n - 1; i++) {
            ySecond.add((y.get(i + 1) - 2 * y.get(i) + y.get(i - 1)) / (h * h));
        }
        ySecond.add(null);

        //точная
        for(int i = 0; i < n; i++) {
            ySecondExact.add(0 - 1 / (x.get(i) * x.get(i)) - 2 / (x.get(i) * x.get(i) * x.get(i)));
            //ySecondExact.add(6 * x.get(i));
        }

        System.out.println("\n\nТаблица значений");
        Runner.printLine("x", x);
        Runner.printLine("y", y);

        System.out.println("\n\nТаблица производных");
        Runner.printLine("x", x);
        Runner.printLine("правая", yFirstRight);
        Runner.printLine("левая", yFirstLeft);
        Runner.printLine("центральная", yFirstCenter);
        Runner.printLine("точная", yFirstExact);
        Runner.printLine("вторая", ySecond);
        Runner.printLine("точная", ySecondExact);
        scanner.close();
    }
}