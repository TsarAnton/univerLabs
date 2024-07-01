package pack;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    int computerNumber;
    String name;
    public Date date;
    public Date startTime;
    public Date endTime;

    public Record(int computerNumber, String name, Date date, Date startTime, Date endTime) {
        this.computerNumber = computerNumber;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toCSVString() {
        StringBuilder result = new StringBuilder();
        result.append(computerNumber).append(";")
            .append(name).append(";")
            .append(new SimpleDateFormat("dd.MM.y").format(date)).append(";")
            .append(new SimpleDateFormat("H:mm").format(startTime)).append(";")
            .append(new SimpleDateFormat("H:mm").format(endTime));
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{\n   Номер компьютера: ").append(computerNumber).append(",\n")
            .append("   Ф.И.О: ").append(name).append(",\n")
            .append("   Дата: ").append(new SimpleDateFormat("dd.MM.y").format(date)).append(",\n")
            .append("   Время начала: ").append(new SimpleDateFormat("H:mm").format(startTime)).append(",\n")
            .append("   Время окончания: ").append(new SimpleDateFormat("H:mm").format(endTime)).append("\n}");
        return result.toString();
    }
}
