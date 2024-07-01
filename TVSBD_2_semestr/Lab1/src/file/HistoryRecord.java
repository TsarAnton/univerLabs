package file;

import java.util.Date;
import java.text.SimpleDateFormat;

public class HistoryRecord implements IRecord {

    private int numOut;
    private int numIn;
    private double sum;
    private double bankComission;
    private double govTax;
    private Date date;

    public HistoryRecord(int numOut, int numIn, double sum, double bankComission, double govTax, Date date) {
        this.numOut = numOut;
        this.numIn = numIn;
        this.sum = sum;
        this.bankComission = bankComission;
        this.govTax = govTax;
        this.date = date;
    }

    @Override
    public String ToCsvString() {
        StringBuilder res = new StringBuilder();
        res.append(numOut).append(";")
            .append(numIn).append(";")
            .append(sum).append(";")
            .append(bankComission).append(";")
            .append(govTax).append(";")
            .append(new SimpleDateFormat("dd.MM.y").format(date)).append(";")
            .append(new SimpleDateFormat("H:mm").format(date));
        return res.toString();
    }
}
