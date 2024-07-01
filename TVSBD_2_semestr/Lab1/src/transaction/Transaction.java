package transaction;

import java.util.ArrayList;

import file.ClientRecord;

public class Transaction implements ITrasaction {

    @Override
    public boolean transaction(double sum, int numOut, int numIn, ArrayList<ClientRecord> data) {
        double sumOut = data.get(numOut).getSum();
        if(sumOut < sum) {
            return false;
        }
        double sumIn = data.get(numIn).getSum();
        data.get(numOut).setSum(sumOut - sum);
        data.get(numIn).setSum(sumIn + sum);
        return true;
    }
    
}
