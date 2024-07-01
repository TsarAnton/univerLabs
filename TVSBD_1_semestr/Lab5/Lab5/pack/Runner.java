package pack;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Runner {
    public static String subDates(String date1, String date2) {
        String hStr1 = new StringBuilder().append(date1.charAt(0)).append(date1.charAt(1)).toString();
        String hStr2 = new StringBuilder().append(date2.charAt(0)).append(date2.charAt(1)).toString();
        String mStr1 = new StringBuilder().append(date1.charAt(3)).append(date1.charAt(4)).toString();
        String mStr2 = new StringBuilder().append(date2.charAt(3)).append(date2.charAt(4)).toString();
        int h1 = Integer.parseInt(hStr1);
        int h2 = Integer.parseInt(hStr2);
        int m1 = Integer.parseInt(mStr1);
        int m2 = Integer.parseInt(mStr2);
        StringBuilder ret = new StringBuilder();
        int minutes;
        int hours;
        if(m1 >= m2) {
            minutes = m1 - m2;
            hours = h1 - h2;
        } else {
            minutes = 60 - m2 + m1;
            hours = h1 - h2 - 1;
        }
        if(hours < 10) {
            ret.append("0");
        }
        ret.append(hours).append(":");
        if(minutes < 10) {
            ret.append("0");
        }
        ret.append(minutes);
        return ret.toString();
    }

    public static String addDates(String date1, String date2) {
        String hStr1 = new StringBuilder().append(date1.charAt(0)).append(date1.charAt(1)).toString();
        String hStr2 = new StringBuilder().append(date2.charAt(0)).append(date2.charAt(1)).toString();
        String mStr1 = new StringBuilder().append(date1.charAt(3)).append(date1.charAt(4)).toString();
        String mStr2 = new StringBuilder().append(date2.charAt(3)).append(date2.charAt(4)).toString();
        int h1 = Integer.parseInt(hStr1);
        int h2 = Integer.parseInt(hStr2);
        int m1 = Integer.parseInt(mStr1);
        int m2 = Integer.parseInt(mStr2);
        StringBuilder ret = new StringBuilder();
        int minutes;
        int hours = 0;
        if(m1 + m2 == 60) {
            minutes = 0;
            hours = h1 + h2 + 1;
        } else if(m1 + m2 > 60) {
            minutes = m2 + m1 - 60;
            hours = h1 + h2 + 1;
        } else {
            minutes = m1 + m2;
            hours = h1 + h2;
        }
        if(hours < 10) {
            ret.append("0");
        }
        ret.append(hours).append(":");
        if(minutes < 10) {
            ret.append("0");
        }
        ret.append(minutes);
        return ret.toString();
    }

    public static Integer DateCompare(String date1, String date2) {
        String hStr1 = new StringBuilder().append(date1.charAt(0)).append(date1.charAt(1)).toString();
        String hStr2 = new StringBuilder().append(date2.charAt(0)).append(date2.charAt(1)).toString();
        String mStr1 = new StringBuilder().append(date1.charAt(3)).append(date1.charAt(4)).toString();
        String mStr2 = new StringBuilder().append(date2.charAt(3)).append(date2.charAt(4)).toString();
        int h1 = Integer.parseInt(hStr1);
        int h2 = Integer.parseInt(hStr2);
        int m1 = Integer.parseInt(mStr1);
        int m2 = Integer.parseInt(mStr2);
        if(h1 > h2) {
            return 1;
        } else if(h1 < h2) {
            return -1;
        } else {
            if(m1 > m2) {
                return 1;
            } else if(m1 < m2) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static String percentDate(String date1, int percent) {
        String hStr1 = new StringBuilder().append(date1.charAt(0)).append(date1.charAt(1)).toString();
        String mStr1 = new StringBuilder().append(date1.charAt(3)).append(date1.charAt(4)).toString();
        int h1 = Integer.parseInt(hStr1);
        int m1 = Integer.parseInt(mStr1);
        StringBuilder ret = new StringBuilder();
        int minutes = 0;
        int hours = 0;
        int resMinutes = (m1 + h1 * 60) * percent / 100;
        boolean con = true;
        while(con) {
            if(resMinutes < 60) {
                if(resMinutes < 10) {
                    resMinutes = 10;
                }
                minutes = resMinutes;
                con = false;
            } else {
                resMinutes -= 60;
                hours++;
            }
        }
        if(hours < 10) {
            ret.append("0");
        }
        ret.append(hours).append(":");
        if(minutes < 10) {
            ret.append("0");
        }
        ret.append(minutes);
        return ret.toString();
    }
    public static void main(String[] args) {
        System.out.println(Runner.percentDate(Runner.subDates("20:00", "12:00"), 80));
        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Кол-во вершин графа:");
        // int N = scanner.nextInt();

        // Matrix matrix = new Matrix(N);

        // System.out.print("Кол-во ребер графа:");
        // int M = scanner.nextInt();

        // for(int i = 0; i < M; i++) {
        //     System.out.println("Ребро: " + (i + 1));
        //     System.out.print("Из вершины:");
        //     int v1 = scanner.nextInt();
        //     System.out.print("В вершину:");
        //     int v2 = scanner.nextInt();
        //     matrix.addEdge(v1 - 1, v2 - 1);
        // }

        // scanner.close();

        // Algorithm search = new Algorithm(matrix, 0, N - 1);
        // search.fillArray(0);
        // ArrayList<Integer> result = search.getElements();
        // for(int i = 0; i < result.size(); i++) {
        //     System.out.println(result.get(i));
        // }
    }
}