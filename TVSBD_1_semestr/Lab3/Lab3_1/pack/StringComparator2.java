package pack;

import java.util.Comparator;

public class StringComparator2 implements Comparator<String> {

    static int count;

    public StringComparator2() {
        StringComparator2.count = 0;
    }

    public int compare(String s1, String s2) {
        StringComparator2.count++;
        int c1 = new SymbolCount().countOfSmallChars(s1);
        int c2 = new SymbolCount().countOfSmallChars(s2);
        
        if(c1 < c2) {
            return -1;
        }

        if(c1 > c2) {
            return 1;
        }

        return 0;
    }
}
