package pack;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

public class TestMethods {
 
    @Test
    public void testRecordComparator1() {
        Date date1 = new Date(), date2 = new Date();
        try {
            date1 = new SimpleDateFormat("H:mm").parse("18:00");
            date2 = new SimpleDateFormat("H:mm").parse("19:00");
        } catch(ParseException e) {}

        Record record1 = new Record(1, "a", date1, date1, date1);
        Record record2 = new Record(1, "a", date2, date2, date2);
        Assert.assertEquals(
            -1,
            new RecordComparator().compare(record1, record2)
        );
    }

    @Test
    public void testRecordComparator2() {
        Date date1 = new Date(), date2 = new Date();
        try {
            date1 = new SimpleDateFormat("H:mm").parse("20:00");
            date2 = new SimpleDateFormat("H:mm").parse("19:00");
        } catch(ParseException e) {}

        Record record1 = new Record(1, "a", date1, date1, date1);
        Record record2 = new Record(1, "a", date2, date2, date2);
        Assert.assertEquals(
            1,
            new RecordComparator().compare(record1, record2)
        );
    }

    @Test
    public void testRecordComparator() {
        Date date1 = new Date(), date2 = new Date();
        try {
            date1 = new SimpleDateFormat("H:mm").parse("19:00");
            date2 = new SimpleDateFormat("H:mm").parse("19:00");
        } catch(ParseException e) {}

        Record record1 = new Record(1, "a", date1, date1, date1);
        Record record2 = new Record(1, "a", date2, date2, date2);
        Assert.assertEquals(
            0,
            new RecordComparator().compare(record1, record2)
        );
    }

    @Test
    public void testToCSVString() {
        Date date1 = new Date();
        try {
            date1 = new SimpleDateFormat("H:mm").parse("18:00");
        } catch(ParseException e) {}

        Record record1 = new Record(1, "a", date1, date1, date1);

        String str = "1;a;01.01.1970;18:00;18:00";
        Assert.assertEquals(
            true,
            record1.toCSVString().equals(str)
        );
    }
}



