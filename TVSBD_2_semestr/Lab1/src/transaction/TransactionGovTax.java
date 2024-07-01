package transaction;

import java.util.ArrayList;

import file.ClientRecord;

public class TransactionGovTax implements ITrasaction, IGovTax {

    private double govTax;

    @Override
    public double govTax(double sum, int percent) {
        govTax = sum * percent / 100;
        return govTax;
    }

    @Override
    public boolean transaction(double sum, int numOut, int numIn, ArrayList<ClientRecord> data) {
        double sumOut = data.get(numOut).getSum();
        if(sumOut < sum + govTax) {
            return false;
        }
        double sumIn = data.get(numIn).getSum();
        data.get(numOut).setSum(sumOut - sum - govTax);
        data.get(numIn).setSum(sumIn + sum);
        return true;
    }
    
}
