import java.util.Scanner;
import java.util.ArrayList;

public class Runner {
    public static Double f(double x) {
        //return x / (Math.sin(3 * x) * Math.sin(3 * x));
        //return 1 / (Math.cos(x) * Math.cos(x));
        return x / (3 * x - 5);
    }

    public static Double fP(double x) {
        return - (x / 3) * (1 / Math.tan(3 * x)) + (1 / 9) * Math.log(Math.sin(3 * x));
        //return Math.tan(x);
    }

    public static void main(String[] args) {
        double x0 = 2, xn = 3;
        int n = 5;
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Введите х0 и хn");
        // x0 = scanner.nextDouble();
        // xn = scanner.nextDouble();
        // if(xn < x0) {
        //     System.out.println("Неподходящий интервал");
        //     System.exit(1);
        // }
        // System.out.println("Введите n");
        // n = scanner.nextInt();

        double h = (xn - x0) / n;
        n += 1;
        int n2 = 2 * n - 1;

        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> xD = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            x.add((x0 + h * i));
        }
        for(int i = 0; i < n2; i++) {
            xD.add(x0 + (h / 2) * i);
        }
        for(int i = 0; i < n; i++) {
            System.out.print(x.get(i) + " : " + f(x.get(i)) + "   ");
        }
        System.out.println();


        double sRight = 0, sLeft = 0, sCenter = 0, sTrapeze = 0, sParabole = 0, sGauss = 0;

        //левых прямоугольников
        for(int i = 0; i < n - 1; i++) {
            sLeft += f(x.get(i));
        }
        sLeft *= h;

        //правых прямоугольников
        for(int i = 1; i < n ; i++) {
            sRight += f(x.get(i));
        }
        sRight *= h;

        // //центральных прямоугольников
        for(int i = 0; i < n - 1; i++) {
            sCenter += f(x.get(i) + h / 2);
        }
        sCenter *= h;

        //трапеций
        for(int i = 1; i < n - 1; i++) {
            sTrapeze += 2 * f(x.get(i));
        }
        sTrapeze += f(x.get(0)) + f(x.get(n - 1));
        sTrapeze *= h / 2;

        //парабол
        for(int i = 1; i < n2; i++) {
            int a;
            if(i % 2 == 0) {
                a = 2;
            } else {
                a = 4;
            }
            sParabole += a * f(xD.get(i));
        }
        sParabole += f(xD.get(0)) + f(xD.get(n - 1));
        sParabole *= h / 6;

        //Гаусса
        int nGauss = 6;
        System.out.println("Введите n (n <= 6) для метода Гаусса");
        //nGauss = scanner.nextInt();

        for(int k = 0; k < nGauss; k++) {
            double tK = ((xn - x0) / 2) * GaussTable.xK(nGauss, k) + ((xn + x0) / 2);
            sGauss += f(tK) * GaussTable.cK(nGauss, k);
        }
        sGauss *= (xn - x0) / 2;

        System.out.println(new StringBuilder("Левых прямоугольников       ").append(sLeft));
        System.out.println(new StringBuilder("Правых прямоугольников      ").append(sRight));
        System.out.println(new StringBuilder("Центральных прямоугольников ").append(sCenter));
        System.out.println(new StringBuilder("Трапеций                    ").append(sTrapeze));
        System.out.println(new StringBuilder("Парабол                     ").append(sParabole));
        System.out.println(new StringBuilder("Гаусса                      ").append(sGauss));
        System.out.println(new StringBuilder("Точный                      ").append(Runner.fP(xn) - Runner.fP(x0)));
       // scanner.close();
    }
}