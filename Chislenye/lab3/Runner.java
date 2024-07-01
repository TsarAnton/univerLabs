import java.text.DecimalFormat;

public class Runner {
    public static String format(Double n) {
        DecimalFormat df = new DecimalFormat("0.0000000000000");
        return df.format(n).replace(",", ".");
    }
    public static void main(String[] args) {

        double n = 7;
        Double[] x = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
        Double[] y = {3.02, 2.81, 2.57, 2.39, 2.18, 1.99, 1.81};

        // double n = 6;
        // Double[] x = {0.0, 2.0, 4.0, 6.0, 8.0, 10.0};
        // Double[] y = {5.0, -1.0, 0.5, 1.5, 4.5, 8.5};

        double xSum = 0, ySum = 0, x2Sum = 0, x3Sum = 0, x4Sum = 0, x5Sum = 0, x6Sum = 0, yxSum = 0, yx2Sum = 0, yx3Sum = 0;

        for(int i = 0; i < n; i++) {
            xSum += x[i];
            ySum += y[i];
            x2Sum += x[i] * x[i];
            x3Sum += Math.pow(x[i], 3);
            x4Sum += Math.pow(x[i], 4);
            x5Sum += Math.pow(x[i], 5);
            x6Sum += Math.pow(x[i], 6);
            yxSum += x[i] * y[i];
            yx2Sum += x[i] * x[i] * y[i];
            yx3Sum += Math.pow(x[i], 3) * y[i];
        }

        Double[][] matrix1 = {{x2Sum, xSum, yxSum}, 
                                {xSum, n, ySum}};
        Double[][] matrix2 = {{x4Sum, x3Sum, x2Sum, yx2Sum}, 
                                {x3Sum, x2Sum, xSum, yxSum},
                                {x2Sum, xSum, n, ySum}};
        Double[][] matrix3 = {{x6Sum, x5Sum, x4Sum, x3Sum, yx3Sum},
                                {x5Sum, x4Sum, x3Sum, x2Sum, yx2Sum},
                                {x4Sum, x3Sum, x2Sum, xSum, yxSum},
                                {x3Sum, x2Sum, xSum, n, ySum}};

        Double[] coef1 = EquationsSystem.findSolution(matrix1, 2);
        Double[] coef2 = EquationsSystem.findSolution(matrix2, 3);
        Double[] coef3 = EquationsSystem.findSolution(matrix3, 4);

        StringBuilder f1 = new StringBuilder(), f2 = new StringBuilder(), f3 = new StringBuilder();
        f1.append("y = (").append(format(coef1[0])).append(") * x + (").append(format(coef1[1])).append(")");
        f2.append("y = (").append(format(coef2[0])).append(") * x^2 + (").append(format(coef2[1])).append(") * x + (").append(format(coef2[2])).append(")");
        f3.append("y = (").append(format(coef3[0])).append(") * x^3 + (").append(format(coef3[1])).append(") * x^2 + (").append(format(coef3[2])).append(") * x + (").append(format(coef3[3])).append(")");

        double r1 = 0, r2 = 0, r3 = 0;
        for(int i = 0; i < n; i++) {
            r1 += Math.pow((coef1[0] * x[i] + coef1[1]) - y[i], 2);
            r2 += Math.pow((coef2[0] * x[i] * x[i] + coef2[1] * x[i] + coef2[2]) - y[i], 2);
            r3 += Math.pow((coef3[0] * Math.pow(x[i], 3) + coef3[1] * x[i] * x[i] + coef3[2] * x[i] + coef3[3]) - y[i], 2);
        }

        System.out.println("Формула для f(x) = ax + b:");
        System.out.println(f1);
        System.out.println("Формула для f(x) = ax^2 + bx + c:");
        System.out.println(f2);
        System.out.println("Формула для f(x) = ax^3 + bx^2 + cx + d:");
        System.out.println(f3);
        System.out.println("Сумма квадратов отклонений для f(x) = ax + b: " + r1);
        System.out.println("Сумма квадратов отклонений для f(x) = ax^2 + bx + c: " + r2);
        System.out.println("Сумма квадратов отклонений для f(x) = ax^3 + bx^2 + cx + d: " + r3);
    }
}