package pack;

import java.util.Comparator;

public class BubbleSort<Type> implements SortInterface<Type> {

    static int change_number = 0;
    static int compare_number = 0;


    public Type[] sort(Type[] elements, Comparator<Type> comparator){
        change_number = 0;
        compare_number = 0;
        for (int i = elements.length - 1; i >= 1; i--){
            for (int j = 0; j < i; j++){    
                compare_number++;   
                if(comparator.compare(elements[j], elements[j + 1]) > 0) {
                    change_number++;
                    Type buff = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = buff;
                }
            }
        }
        return elements;
    };
}