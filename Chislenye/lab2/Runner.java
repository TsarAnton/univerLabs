import java.util.Scanner;
import java.text.DecimalFormat;

public class Runner {

    public static int space = 23;

    public static double f(double x) {
        return x + Math.cos(x) - 1;
        //return x * x + x - Math.pow(3, x - 1);
    }

    public static String format(Double n) {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(n).replace(",", ".");
    }

    public static double s(double a, double b, double c, double d, double x, double xi) {
        return a + b * (x - xi) + (c / 2) * Math.pow(x - xi, 2) + (d / 6) * Math.pow(x - xi, 3);
    }
    
    public static void main(String[] args) {
        double x0 = 0, xn = Math.PI, h = Math.PI / 5, x1, x2, x3;
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
        x1 = x0 + 0.3;
        x2 = x0 + 0.5 * h;
        x3 = xn - 0.5 * h;

        Double[] x = new Double[n];
        Double[] y = new Double[n];
        Double[] a = new Double[n];

        for(int i = 0; i < n; i++) {
            x[i] = x0 + h * i;
            y[i] = f(x[i]);
            //a
            a[i] = y[i];
        }

        //c
        Double[] c = new Double[n];
        c[0] = 0.0;
        c[n - 1] = 0.0;
        Double[][] matrix = new Double[n][n + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n + 1; j++) {
                matrix[i][j] = 0.0;
            }
        }
        for(int i = 1; i < n - 1; i++) {
            matrix[i][i - 1] = h;
            matrix[i][i] = 4 * h;
            matrix[i][i + 1] = h;
            matrix[i][n] = 6 * ((y[i + 1] - y[i]) / h - (y[i] - y[i - 1]) / h);
        }
        Double[][] newMatrix = new Double[n - 2][n - 1];
        for(int i1 = 1, i2 = 0; i2 < n - 2; i1++, i2++) {
            for(int j1 = 1, j2 = 0; j2 < n - 2; j1++, j2++) {
                newMatrix[i2][j2] = matrix[i1][j1];
            }
            newMatrix[i2][n - 2] = matrix[i1][n];
        }
        Double[] res = EquationsSystem.findSolution(newMatrix, n - 2);
        for(int i = 0, j = 1; i < n - 2; i++, j++) {
            c[j] = res[i];
        }

        //d
        Double[] d = new Double[n];
        for(int i = 1; i < n; i++) {
            d[i] = (c[i] - c[i - 1]) / h;
        }

        //b
        Double[] b = new Double[n];
        for(int i = 1; i < n; i++) {
            b[i] = c[i] * (h / 2) - d[i] * ((h * h) / 6) + (y[i] - y[i - 1]) / h;
        }

        int num = 0;
        for(int i = 0; i < n - 1; i++) {
            if(x1 >= x[i] && x1 <= x[i + 1]) {
                num = i + 1;
                break;
            }
        }
        double s1 = s(a[num], b[num], c[num], d[num], x1, x[num]);
        double s2 = s(a[1], b[1], c[1], d[1], x2, x[1]);
        double s3 = s(a[n - 1], b[n - 1], c[n - 1], d[n - 1], x3, x[n - 1]);
        System.out.println("Значение в точке x1 = " + x1 + ": " + s1 + " (реальное: " + f(x1) + ")");
        System.out.println("Значение в точке x2 = " + x2 + ": " + s2 + " (реальное: " + f(x2) + ")");
        System.out.println("Значение в точке x3 = " + x3 + ": " + s3 + " (реальное: " + f(x3) + ")");
        System.out.println("\nИнтерпаляционные сплайны:");
        for(int i = 1; i < n; i++) {
            StringBuilder formula = new StringBuilder().append("S").append(i).append(" = (").append(format(a[i])).append(") + (")
                                    .append(format(b[i])).append(") * (x - (").append(format(x[i])).append(")) + (")
                                    .append(format(c[i] / 2)).append(") * (x - (").append(format(x[i])).append(")) ^ 2 + (")
                                    .append(format(d[i] / 6)).append(") * (x - (").append(format(x[i])).append(")) ^ 3");
            System.out.println(formula);
        }

        scanner.close();
    }
}