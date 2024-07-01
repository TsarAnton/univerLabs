package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class MyFile {
    public static void writeFile(String text, String fileName) {
        try {
            Writer writer = new FileWriter(fileName);
            BufferedWriter buffWritter = new BufferedWriter(writer);
            buffWritter.write(text);
            buffWritter.close();
        } catch(FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch(IOException e) {
            System.out.println("Ошибка вводв-вывода");
        }
    }

    public static String readFile(String fileName) {
        StringBuilder text = new StringBuilder();
        try {
            Reader reader = new FileReader(fileName);
            BufferedReader buffReader = new BufferedReader(reader);
            String line;
            while((line = buffReader.readLine()) != null) {
                text.append(line).append("\n");
            }
            buffReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("Файл не существует");
        } catch(IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
        return text.toString();
    }
}
