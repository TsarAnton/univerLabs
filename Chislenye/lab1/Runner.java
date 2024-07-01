import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Runner {

    public static int space = 23;

    public static double f(double x) {
        //return x + Math.cos(x) - 1;
        //return Math.pow(2, x) + x * x;
        return 2 * x + 3 * Math.log(x + 0.5);
    }

    public static String format(Double n) {
        DecimalFormat df = new DecimalFormat("0.0000000");
        return df.format(n).replace(",", ".");
    }

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

    public static double max(ArrayList<Double> x, int n) {
        double x0 = x.get(0), xn = x.get(x.size() - 1), extr = -1;
        if(n == 1 || n == 3 || n == 5) {
            extr = 0;
        } else if(n == 2 || n == 4) {
            extr = Math.PI / 2;
        }
        while(true) {
            if((extr >= x0 && extr <= xn) || (0 - extr >= x0 && 0 - extr <= xn)) {
                return 1;
            }
            if((xn > 0 && extr > xn) || (x0 < 0 && extr < x0)) {
                double y0 = 0, yn = 0;
                if(n == 1 || n == 3 || n == 5) {
                    y0 = Math.abs(Math.cos(x0));
                    yn = Math.abs(Math.cos(xn));
                } else if(n == 2 || n == 4) {
                    y0 = Math.abs(Math.sin(x0));
                    yn = Math.abs(Math.sin(xn));
                }
                return y0 > yn ? y0 : yn;
            }
            extr += Math.PI;
        }
    }

    public static double dividedDifference(ArrayList<Double> x) {
        if(x.size() == 2) {
            return (f(x.get(1)) - f(x.get(0))) / (x.get(1) - x.get(0));
        }
        ArrayList<Double> x1 = new ArrayList<>();
        ArrayList<Double> x2 = new ArrayList<>();
        for(int i = 0; i < x.size(); i++) {
            if(i != 0) {
                x1.add(x.get(i));
            }
            if(i != x.size() - 1) {
                x2.add(x.get(i));
            }
        }
        return (dividedDifference(x1) - dividedDifference(x2)) / (x.get(x.size() - 1) - x.get(0));
    }
    public static ArrayList<Double> findPoints(ArrayList<Double> array, double x, int n, int n1) {
        if(n == n1 - 1) {
            return array;
        }
        ArrayList<Double> ret = new ArrayList<>();
        if(x >= 0 && x <= array.get(1)) {
            for(int i = 0; i < n1 + 1; i++) {
                ret.add(array.get(i));
            }
        } else if(x <= array.get(n - 1) && x >= array.get(n - 2)) {
            for(int i = 0, k = n - 1; i < n1 + 1; i++, k--) {
                ret.add(0, array.get(k));
            }
        } else {
            int i1 = 0, i2 = 0, now = 0;
            for(int i = 1; i < n - 1; i++) {
                if(x >= array.get(i) && x <= array.get(i + 1)) {
                    i1 = i;
                    i2 = i + 1;
                    ret.add(array.get(i1));
                    ret.add(array.get(i2));
                    now = 2;
                    break;
                }
            }
            for(int i = i2 + 1; i < n && now != n1 + 1; i++) {
                ret.add(array.get(i));
                now++;
            }
            if(now != n1 + 1) {
                for(int i = i1 - 1; i > 0 && now != n1 + 1; i--) {
                    ret.add(0, array.get(i));
                    now++;
                }
            }
        }
        return ret;
    }

    public static int factorial(int x) {
        int ret = 1;
        for(int i = 2; i <= x; i++) {
            ret *= i;
        }
        return ret;
    }

    public static double R(ArrayList<Double> x, int n, double x1) {
        double M = max(x, n);
        double v = 1;
        for(int i = 0; i < x.size(); i++) {
            v *= x1 - x.get(i);
        }
        return (M * Math.abs(v)) / factorial(n + 1);
    }

    public static double lagrange(ArrayList<Double> x, ArrayList<Double> y, double x1) {
        double ret = 0;
        for(int k = 0; k < x.size(); k++) {
            double znam = 1, chisl = 1;
            for(int i = 0; i < x.size(); i++) {
                if(i != k) {
                    chisl *= x1 - x.get(i);
                    znam *= x.get(k) - x.get(i);
                }
            }
            ret += y.get(k) * (chisl / znam);
        }
        return ret;
    }

    public static String lagrangeFormule(ArrayList<Double> x, ArrayList<Double> y) {
        StringBuilder ret = new StringBuilder().append("y = (");
        for(int k = 0; k < x.size(); k++) {
            double znam = 1;
            ret.append(format(y.get(k))).append(") * ((");
            for(int i = 0; i < x.size(); i++) {
                if(i != k) {
                    znam *= x.get(k) - x.get(i);
                    ret.append("(x - (").append(format(x.get(i))).append("))");
                    if(i != x.size() - 1 && !(k == x.size() - 1  && i == x.size() - 2)) {
                        ret.append(" * ");
                    }
                }
            }
            ret.append(") / (");
            ret.append(format(znam)).append("))");
            if(k != x.size() - 1) {
                ret.append(" + (");
            }
        }
        return ret.toString();
    }

    public static double newton(ArrayList<Double> x, double x1) {
        double ret = f(x.get(0));
        for(int i = 1; i < x.size(); i++) {
            double a = 1;
            ArrayList<Double> x2 = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                x2.add(x.get(j));
                if(j != i) {
                    a *= x1 - x.get(j);
                }
            }
            a *= dividedDifference(x2);
            ret += a;
        }
        return ret;
    }

    public static String newtonFormule(ArrayList<Double> x) {
        StringBuilder ret = new StringBuilder().append("y = ").append(f(x.get(0))).append(" + ");
        for(int i = 1; i < x.size(); i++) {
            ArrayList<Double> x2 = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                x2.add(x.get(j));
                if(j != i) {
                    ret.append("(x - (").append(format(x.get(j))).append(")) * ");
                }
            }
            ret.append("(").append(format(dividedDifference(x2))).append(")");
            if(i != x.size() - 1) {
                ret.append(" + ");
            }
        }
        return ret.toString();
    }
    public static void main(String[] args) {
        double x0 = 5, xn = 10, h = 1, x1 = 6.21, x2 = 8.61, x3 = 8.61;
        int n1 = 4, n2 = 4;
        Scanner scanner = new Scanner(System.in);

        // System.out.println("Введите x0 и xn");
        // x0 = scanner.nextDouble();
        // xn = scanner.nextDouble();
        // if(xn < x0) {
        //     System.out.println("Задайте другой промежуток");
        //     System.exit(1);
        // }
        // System.out.println(("Введите шаг h"));
        // h = scanner.nextDouble();
        // if(h > xn - x0) {
        //     System.out.println("Слишком большой шаг");
        //     System.exit(1);
        // }
         int n = (int) ((xn - x0) / h) + 1;
        // System.out.println("Введите n1 и n2");
        // n1 = scanner.nextInt();
        // n2 = scanner.nextInt();
        // if(n1 > n - 1 || n2 > n - 1) {
        //     System.out.println("Задайте другое n1 или n2");
        //     System.exit(1);
        // }
        // System.out.println("Введите точки x1, x2, x3");
        // x1 = scanner.nextDouble();
        // x2 = scanner.nextDouble();
        // x3 = scanner.nextDouble();
        // if(x1 < x0 || x2 < x0 || x3 < x0 || x1 > xn || x2 > xn || x3 > xn) {
        //     System.out.println("Одна из точек не входит в интервал");
        //     System.exit(1);
        // }

        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            x.add(x0 + h * i);
            y.add(f(x.get(i)));
        }

        ArrayList<Double> pX1 = findPoints(x, x1, n, n1);
        ArrayList<Double> pX2 = findPoints(x, x2, n, n1);
        ArrayList<Double> pX3 = findPoints(x, x3, n, n1);
        ArrayList<Double> pY1 = new ArrayList<>();
        ArrayList<Double> pY2 = new ArrayList<>();
        ArrayList<Double> pY3 = new ArrayList<>();
        for(int i = 0; i <= n1; i++) {
            pY1.add(f(pX1.get(i)));
            pY2.add(f(pX2.get(i)));
            pY3.add(f(pX3.get(i)));
        }

        System.out.println("\nТаблица значений");
        Runner.printLine("x", x);
        Runner.printLine("y", y);

        System.out.println("\n\nМетод Лагранжа");
        System.out.println("Значение в точке x1 = " + lagrange(pX1, pY1, x1) + " (реальное = " + f(x1) + ")");
        System.out.println("Значение в точке x2 = " + lagrange(pX2, pY2, x2) + " (реальное = " + f(x2) + ")");
        System.out.println("Значение в точке x3 = " + lagrange(pX3, pY3, x3) + " (реальное = " + f(x3) + ")");
        System.out.println("Оцененая погрешность в точке x1 = " + R(pX1, n1, x1));
        System.out.println("Оцененая погрешность в точке x2 = " + R(pX2, n1, x2));
        System.out.println("Оцененая погрешность в точке x3 = " + R(pX3, n1, x3));
        System.out.println("Погрешность в точке x1 = " + Math.abs(f(x1) - lagrange(pX1, pY1, x1)));
        System.out.println("Погрешность в точке x2 = " + Math.abs(f(x2) - lagrange(pX2, pY2, x2)));
        System.out.println("Погрешность в точке x3 = " + Math.abs(f(x3) - lagrange(pX3, pY3, x3)));
        System.out.println("Формула 1: " + lagrangeFormule(pX1, pY1));
        System.out.println("Формула 2: " + lagrangeFormule(pX2, pY2));
        System.out.println("Формула 3: " + lagrangeFormule(pX3, pY3));

        pX1 = findPoints(x, x1, n, n2);
        pX2 = findPoints(x, x2, n, n2);
        pX3 = findPoints(x, x3, n, n2);
        pY1 = new ArrayList<>();
        pY2 = new ArrayList<>();
        pY3 = new ArrayList<>();

        for(int i = 0; i <= n1; i++) {
            pY1.add(f(pX1.get(i)));
            pY2.add(f(pX2.get(i)));
            pY3.add(f(pX3.get(i)));
        }

        System.out.println("\n\nМетод Ньютона");
        System.out.println("Значение в точке x1 = " + newton(pX1, x1) + " (реальное = " + f(x1) + ")");
        System.out.println("Значение в точке x2 = " + newton(pX2, x2) + " (реальное = " + f(x2) + ")");
        System.out.println("Значение в точке x3 = " + newton(pX3, x3) + " (реальное = " + f(x3) + ")");
        System.out.println("Оцененая погрешность в точке x1 = " + R(pX1, n2, x1));
        System.out.println("Оцененая погрешность в точке x2 = " + R(pX2, n2, x2));
        System.out.println("Оцененая погрешность в точке x3 = " + R(pX3, n2, x3));
        System.out.println("Погрешность в точке x1 = " + Math.abs(f(x1) - newton(pX1, x1)));
        System.out.println("Погрешность в точке x2 = " + Math.abs(f(x2) - newton(pX2, x2)));
        System.out.println("Погрешность в точке x3 = " + Math.abs(f(x3) - newton(pX3, x3)));
        System.out.println("Формула 1: " + newtonFormule(pX1));
        System.out.println("Формула 2: " + newtonFormule(pX2));
        System.out.println("Формула 3: " + newtonFormule(pX3));

        scanner.close();
    }
}