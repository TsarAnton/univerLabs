import java.util.Date;

import file.HistoryRecord;
import file.MyFile;

public class HistoryReport {
    public static void doHistoryReport(int numOut, int numIn, double sum, double govTax, double bankComission) {
        String historyData = MyFile.readFile("history.csv");
        Date date = new Date();
        HistoryRecord historyRecord = new HistoryRecord(numOut, numIn, sum, bankComission, govTax, date);
        StringBuilder str = new StringBuilder();
        str.append(historyData).append("\n").append(historyRecord.ToCsvString());
        MyFile.writeFile(str.toString(), "history.csv");
    }
}
