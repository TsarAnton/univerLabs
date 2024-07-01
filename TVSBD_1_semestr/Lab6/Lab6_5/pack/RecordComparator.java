package pack;

import java.util.Date;
import java.util.Comparator;

public class RecordComparator implements Comparator<Record> {
    public int compare(Record r1, Record r2) {
        Date date1 = r1.startTime;
        Date date2 = r2.startTime;

        if(date1.compareTo(date2) > 0) {
            return 1;
        }

        if(date1.compareTo(date2) < 0) {
            return -1;
        }
        
        return 0;
    }
}
