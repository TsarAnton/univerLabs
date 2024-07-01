package pack;

import java.util.Comparator;

public class StringComparator1 implements Comparator<String> {
    String s;
    static int count;

    public StringComparator1(String s) {
        this.s = s;
        StringComparator1.count = 0;
    }

    public int compare(String s1, String s2) {
        StringComparator1.count++;
        int c1 = s1.indexOf(this.s);
        int c2 = s2.indexOf(this.s);
        if(c1 > c2) {
            return 1;
        }

        if(c1 < c2) {
            return -1;
        }

        return 0;
    }
}

