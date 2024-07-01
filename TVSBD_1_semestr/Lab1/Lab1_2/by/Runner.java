package by;

public class Runner {

    public static double factorial(int x){
        double result = 1;
        for(int i = 1 ; i <= x ; i++){
            result *= i;
        }
        return result;
    }

    public static double expression(double x, double f) {
        double sum = 0;
        for(int n = 0 ; true ; n++){
            double num = Math.pow(x, n) / factorial(n);
            if(Math.abs(num) >= f){
                sum += num;
            }
            else{
                break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        double f = Double.parseDouble(args[1]);
        System.out.println(Math.pow(Math.E, x));
        System.out.println(expression(x, f));
    }
}