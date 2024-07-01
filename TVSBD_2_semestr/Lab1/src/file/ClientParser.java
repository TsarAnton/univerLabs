package file;

import java.util.ArrayList;

public class ClientParser {
    public static ArrayList<ClientRecord> ClientStringToArray(String text) {
        ArrayList<ClientRecord> array = new ArrayList<ClientRecord>();
        String lineArray[] = text.split("\n");
        for(int i = 0; i < lineArray.length; i++) {
            String line[] = lineArray[i].split(";");
            int num = Integer.parseInt(line[0]);
            double sum = Double.parseDouble(line[1]);
            String type = line[2]; 
            ClientRecord record = new ClientRecord(num, sum, type);
            array.add(record);
        }
        return array;
    }

    public static String ClientArrayToString(ArrayList<ClientRecord> array) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < array.size(); i++) {
            str.append(array.get(i).ToCsvString()).append("\n");
        }
        return str.toString();
    }
}
