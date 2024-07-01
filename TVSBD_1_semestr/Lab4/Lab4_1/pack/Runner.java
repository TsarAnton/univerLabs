package pack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Runner {

    public static Date subDates(Date date1, Date date2) {
        String dateStr1 = new SimpleDateFormat("HH:mm").format(date1);
        String dateStr2 = new SimpleDateFormat("HH:mm").format(date2);
        String hStr1 = new StringBuilder().append(dateStr1.charAt(0)).append(dateStr1.charAt(1)).toString();
        String hStr2 = new StringBuilder().append(dateStr2.charAt(0)).append(dateStr2.charAt(1)).toString();
        String mStr1 = new StringBuilder().append(dateStr1.charAt(3)).append(dateStr1.charAt(4)).toString();
        String mStr2 = new StringBuilder().append(dateStr2.charAt(3)).append(dateStr2.charAt(4)).toString();
        int h1 = Integer.parseInt(hStr1);
        int h2 = Integer.parseInt(hStr2);
        int m1 = Integer.parseInt(mStr1);
        int m2 = Integer.parseInt(mStr2);
        Date ret = new Date();
        int minutes;
        int hours;
        if(m1 >= m2) {
            minutes = m1 - m2;
            hours = h1 - h2;
        } else {
            minutes = 60 - m2 + m1;
            hours = h1 - h2 - 1;
        }
        try {
            ret =  new SimpleDateFormat("HH:mm").parse(new StringBuilder().append(hours).append(":").append(minutes).toString());
        } catch (ParseException e) {}
        return ret;
    }
    public static void main(String[] args) {
        Date a = new Date(), b = new Date();
        try {
            a = new SimpleDateFormat("HH:mm").parse("16:20");
        } catch (ParseException e) {}
        try {
            b = new SimpleDateFormat("HH:mm").parse("01:30");
        } catch (ParseException e) {}
        System.out.println(new SimpleDateFormat("H:mm").format(Runner.subDates(a, b)));


        // Queue<Plate> plates = new Queue<Plate>();

        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Введите кол-во тарелок:");
        // int N = scanner.nextInt();
        // for(int i = 0; i < N; i++) {
        //     System.out.println("    Цвет:");
        //     String color = scanner.next();
        //     System.out.println("    Время на обработку:");
        //     int washTime = scanner.nextInt();
        //     System.out.println("    Время поступления:");
        //     int startTime = scanner.nextInt();

        //     Plate plate = new Plate(color, washTime, startTime);
        //     plates.push(plate);
        // }
        // System.out.print("Введите кол-во мойщиков тарелок:");
        // int n = scanner.nextInt();
        // scanner.close();

        // Stack<Plate> dirtyPlates = new Stack<Plate>(N);
        // Stack<Plate> cleanPlates = new Stack<Plate>(N);

        // Washer washers[] = new Washer[n];
        // for(int i = 0; i < n; i++) {
        //     washers[i] = new Washer();
        // }

        // int sec = 1;

        // while(true) {

        //     System.out.println("Секунда " + sec);

        //     //1
        //     if(!plates.isEmpty()) {
        //         System.out.println("    Тарелка в очереди тарелок: " + plates.safePop().color);
        //         plates.safePop().startTime--;
        //         System.out.println("        Время поступления: " + plates.safePop().startTime);

        //     //2
        //         if(plates.safePop().startTime == 0) {
        //             System.out.println("    Тарелка " + plates.safePop().color + " попадает в стек грязных тарелок");
        //             dirtyPlates.push(plates.pop());
        //         }
        //     } else {
        //         System.out.println("    Очередь тарелок пуста");
        //     }


        //     //3
        //     for(int i = 0; i < n && !dirtyPlates.isEmpty(); i++) {
        //         if(!washers[i].isWashing()) {
        //             System.out.println( "    Мойщик №" + (i + 1) + " берет тарелку " + dirtyPlates.safePop().color + " из стека грязных тарелок");
        //             washers[i].plate = dirtyPlates.pop();
        //         }
        //     }

        //     //4
        //     for(int i = 0; i < n; i++) {
        //         if(washers[i].isWashing()) {
        //             if(washers[i].plate.washTime == 0) {
        //                 System.out.println("    Мойщик №" + (i + 1) + " помыл тарелку " + washers[i].plate.color + " и она попадает в стек чистых тарелок");
        //                 cleanPlates.push(washers[i].plate);
        //                 washers[i].washedPlates++;
        //                 washers[i].plate = null;
        //             } else {
        //                 System.out.println("    Мойщик №" + (i + 1) + " моет тарелку " + washers[i].plate.color + "\n    Время на помывку " + washers[i].plate.washTime);
        //                 washers[i].plate.washTime--;
        //             }
        //         } else {
        //             washers[i].wastedTime++;
        //             System.out.println("    Мойщик №" + (i + 1) + " время простоя = " + washers[i].wastedTime);
        //         }
        //     }

        //     //5
        //     if(cleanPlates.isFull()) {
        //         System.out.println("    Все тарелки вымыты");
        //         break;
        //     }

        //     sec++;
        // }

        // System.out.println("Порядок тарелок:");
        // while(!cleanPlates.isEmpty()) {
        //     System.out.println(cleanPlates.pop().color);
        // }
        // System.out.println("Мойщики:");
        // for(int i = 0; i < n; i++) {
        //     System.out.println("    Мойщик №" + (i + 1));
        //     System.out.println("        Торелок помыто: " + washers[i].washedPlates);
        //     System.out.println("        Время простоя: " + washers[i].wastedTime);
        // }
    }

}