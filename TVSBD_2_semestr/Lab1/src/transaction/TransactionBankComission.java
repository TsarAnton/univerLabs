package transaction;

import java.util.ArrayList;

import file.ClientRecord;

public class TransactionBankComission implements ITrasaction, IBankComission {

    private double bankComission;

    @Override
    public double bankComission(double sum, int percent) {
        bankComission = sum * percent / 100;
        return bankComission;
    }

    @Override
    public boolean transaction(double sum, int numOut, int numIn, ArrayList<ClientRecord> data) {
        double sumOut = data.get(numOut).getSum();
        if(sumOut < sum + bankComission) {
            return false;
        }
        double sumIn = data.get(numIn).getSum();
        data.get(numOut).setSum(sumOut - sum - bankComission);
        data.get(numIn).setSum(sumIn + sum);
        return true;
    }
    
}
