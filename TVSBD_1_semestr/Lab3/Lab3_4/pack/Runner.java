package pack;

public class Runner {
    public static void main(String[] args) {
        String S = new String("sa");
        System.out.println("Array:");

        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        BubbleSort<String> sorter1 = new BubbleSort<String>();
        QuickSort<String> sorter2 = new QuickSort<String>();

        String array[] = sorter1.sort(args, new StringComparator1(S));

        System.out.println("Array after sort 1:");

        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        System.out.println("Number of compare: " + BubbleSort.compare_number);
        System.out.println("Number of change: " + BubbleSort.change_number);

        array = sorter2.sort(args, new StringComparator2());

        System.out.println("Array after sort 2:");

        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        System.out.println("Number of compare: " + QuickSort.compare_number);
        System.out.println("Number of change: " + QuickSort.change_number);
    }
}