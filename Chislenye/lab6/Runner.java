import java.util.Scanner;

public class Runner {

    public static int space = 23;

    public static void printLine(String name, double[] array, int n) {
        StringBuilder str = new StringBuilder();
        str.append(new BeatyString<String>().get(name, space));
        for(int i = 0; i < n; i++) {
            str.append(new BeatyString<Double>().get(array[i], space));
        }
        System.out.println(str);
    }

    public static double f(double x, double y) {
        //return (x * y) / (1 + x * x) - 1 / (1 + x * x); //задание 2
        return x * x - 2 * y; //задание 1.1
    }

    public static double f(double x, double y, double z) {
        return z;
    }

    public static double g(double x, double y, double z) {
        return (2 * x * z) / (x * x + 1);
    }

    public static void main(String[] args) {
        double a, b, h, fa;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите a и b:");
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        System.out.println("Введите h:");
        h = scanner.nextDouble();
        System.out.println("Введите y(" + a + "):");
        fa = scanner.nextDouble();
        scanner.close();

        int n = (int) ((b - a) / h) + 1;

        double[] x = new double[n];
        for(int i = 0; i < n; i++) {
            x[i] = a + h * i;
        }

        double[] yE = new double[n];
        yE[0] = fa;
        double[] yS = new double[n];
        yS[0] = fa;
        double[] yT = new double[n];
        yT[0] = fa;
        double[] yR = new double[n];
        yR[0] = fa;

        //Эйлера
        for(int i = 0; i < n - 1; i++) {
            yE[i + 1] = yE[i] + h * f(x[i], yE[i]);
        }

        //средней точки
        for(int i = 0; i < n - 1; i++) {
            yS[i + 1] = yS[i] + h * f((x[i] + x[i + 1]) / 2, h / 2 * f(x[i], yS[i]) + yS[i]);
        }

        //трапеций
        for(int i = 0; i < n - 1; i++) {
            yT[i + 1] = yT[i] + h / 2 * (f(x[i], yT[i]) + f(x[i + 1], yT[i] + h * f(x[i], yT[i])));
        }

        //Рунге-Кутты
        for(int i = 0; i < n - 1; i++) {
            double k1 = f(x[i], yR[i]);
            double k2 = f(x[i] + (h / 2), yR[i] + (h * k1) / 2);
            double k3 = f(x[i] + (h / 2), yR[i] + (h * k2) / 2);
            double k4 = f(x[i] + h, yR[i] + h * k3);
            yR[i + 1] = yR[i] + (1.0 / 6.0) * h * (k1 + 2 * k2 + 2 * k3 + k4);
        }

        printLine("Эйлера", yE, n);
        printLine("Средней", yS, n);
        printLine("Трапеций", yT, n);
        printLine("Рунге-Кутты", yR, n);

        //задание 1.2
        double[] y = new double[n];
        y[0] = 1;
        double[] z = new double[n];
        z[0] = 3;

        double[] yA = new double[n];
        for(int i = 0; i < n; i++) {
            yA[i] = x[i] * x[i] * x[i] + 3 * x[i] + 1;
        }

        for(int i = 0; i < n - 1; i++) {
            double k1 = f(x[i], y[i], z[i]);
            double l1 = g(x[i], y[i], z[i]);
            double k2 = f(x[i] + h / 2, y[i] + h * k1 / 2, z[i] + h * l1 / 2);
            double l2 = g(x[i] + h / 2, y[i] - h * l1 / 2, z[i] + h * l1 / 2);
            double k3 = f(x[i] + h / 2, y[i] + h * k2 / 2, z[i] + h * l2 / 2);
            double l3 = g(x[i] + h / 2, y[i] + h * k2 / 2, z[i] + h * l2 / 2);
            double k4 = f(x[i] + h, y[i] + h * k3, z[i] + h * l3);
            double l4 = g(x[i] + h, y[i] + h * k3, z[i] + h * l3);
            y[i + 1] = y[i] + 0.166666666667 * h * (k1 + 2 * k2 + 2 * k3 + k4);
            z[i + 1] = z[i] + 0.166666666667 * h * (l1 + 2 * l2 + 2 * l3 + l4);
        }
        System.out.println("\nЗадание 1.2");
        printLine("y", y, n);
        printLine("y точное", yA, n);
        printLine("z", z, n);
    }
}