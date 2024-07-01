package transaction;

import java.util.ArrayList;

import file.ClientRecord;

public interface ITrasaction {
    public boolean transaction(double sum, int numOut, int numIn, ArrayList<ClientRecord> data);
}
