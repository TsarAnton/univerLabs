package file;

public class ClientRecord implements IRecord {

    private int num;
    private double sum;
    private String type;

    public ClientRecord(int num, double sum, String type) {
        this.num = num;
        this.sum = sum;
        this.type = type;
    }

    public int getNum() {
        return this.num;
    }

    public double getSum() {
        return this.sum;
    }

    public String getType() {
        return this.type;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String ToCsvString() {
        StringBuilder res = new StringBuilder();
        res.append(num).append(";")
            .append(sum).append(";")
            .append(type);
        return res.toString();
    }
    
}
