package transaction;

import java.util.ArrayList;

import file.ClientRecord;

public class TransactionGovTaxBankComission implements ITrasaction, IGovTax, IBankComission {

    private double govTax;
    private double bankComission;

    @Override
    public double bankComission(double sum, int percent) {
        bankComission = sum * percent / 100;
        return bankComission;
    }

    @Override
    public double govTax(double sum, int percent) {
        govTax = sum * percent / 100;
        return govTax;
    }

    @Override
    public boolean transaction(double sum, int numOut, int numIn, ArrayList<ClientRecord> data) {
        double sumOut = data.get(numOut).getSum();
        if(sumOut < sum + bankComission + govTax) {
            return false;
        }
        double sumIn = data.get(numIn).getSum();
        data.get(numOut).setSum(sumOut - sum - bankComission - govTax);
        data.get(numIn).setSum(sumIn + sum);
        return true;
    }
    
}
