package pack;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    public int id;
    public int computerNumber;
    public String name;
    public Date date;
    public Date startTime;
    public Date endTime;

    public Record(int id,int computerNumber, String name, Date date, Date startTime, Date endTime) {
        this.id = id;
        this.computerNumber = computerNumber;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toCSVString() {
        StringBuilder result = new StringBuilder();
        result.append(id).append(";");
        result.append(computerNumber).append(";")
            .append(name).append(";")
            .append(new SimpleDateFormat("dd.MM.y").format(date)).append(";")
            .append(new SimpleDateFormat("H:mm").format(startTime)).append(";")
            .append(new SimpleDateFormat("H:mm").format(endTime));
        return result.toString();
    }
}
