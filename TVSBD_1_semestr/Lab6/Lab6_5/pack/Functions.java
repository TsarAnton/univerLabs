package pack;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;

public class Functions {

    static String file;
    static int ID = 0;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.y");
    static SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
    
    public static void editFile(List<Record> list, String file) {
        StringBuilder CSVString = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            CSVString.append(list.get(i).toCSVString()).append("\n");
        }

        try {
            Writer writer = new FileWriter(file);
            BufferedWriter buffWriter = new BufferedWriter(writer);
            buffWriter.write(CSVString.toString());
            buffWriter.close();
        } catch(FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch(IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }
}
