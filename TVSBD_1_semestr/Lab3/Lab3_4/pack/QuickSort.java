package pack;

import java.util.Comparator;

public class QuickSort<Type> implements SortInterface<Type> {

    static int change_number = 0;
    static int compare_number = 0;


    private void quickSort(Type[] array, int low, int high, Comparator<Type> comparator) {
        if (array.length == 0)
            return;
 
        if (low >= high)
            return;
 
        int middle = low + (high - low) / 2;
        Type opora = array[middle];
 
        int i = low, j = high;
        while (i <= j) {

            while(comparator.compare(array[i], opora) < 0) {
                compare_number++;
                i++;
            }

            while(comparator.compare(array[j], opora) > 0) {
                compare_number++;
                j--;
            }
 
            if (i <= j) {
                change_number++;
                Type temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
 
        if (low < j)
            quickSort(array, low, j, comparator);
 
        if (high > i)
            quickSort(array, i, high, comparator);
    }

    public Type[] sort(Type[] elements, Comparator<Type> comparator) {
        compare_number = 0;
        change_number = 0;
        this.quickSort(elements, 0, elements.length - 1, comparator);
        return elements;
    }
}
