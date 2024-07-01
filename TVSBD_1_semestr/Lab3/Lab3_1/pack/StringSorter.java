package pack;

import java.util.Arrays;

public class StringSorter {
    public static void main(String[] args) {
        String S = new String("sa");

        System.out.println("Array:");
        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        Arrays.sort(args, new StringComparator1(S));

        System.out.println("Array after sort 1:");

        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        System.out.println("Number of comparisons: " + StringComparator1.count);

        Arrays.sort(args, new StringComparator2());

        System.out.println("Array after sort 2:");

        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        System.out.println("Number of comparisons: " + StringComparator2.count);
    }
}
