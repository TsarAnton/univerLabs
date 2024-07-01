package pack;

import java.util.Scanner;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;

public class Runner {

    public static void write(ArrayList<Record> data, Scanner scanner, Integer id) {
        System.out.println("    Номер компьютера:");
        int computerNumber = scanner.nextInt();
        System.out.println("    Ф.И.О:");
        String name = scanner.next();
        System.out.println("    Дата:");
        String buff = scanner.next();
        Date date;
        try {
            date = new SimpleDateFormat("dd.MM.y").parse(buff);
        } catch(ParseException e) {
            System.out.println("Неверный формат даты");
            return;
        }
        System.out.println("    Время начала:");
        buff = scanner.next();
        Date startTime;
        try {
            startTime = new SimpleDateFormat("H:mm").parse(buff);
        } catch(ParseException e) {
            System.out.println("Неверный формат времени");
            return;
        }
        System.out.println("    Время окончания:");
        buff = scanner.next();
        Date endTime;
        try {
            endTime = new SimpleDateFormat("H:mm").parse(buff);
        } catch(ParseException e) {
            System.out.println("Неверный формат времени");
            return;
        }
        Record record = new Record(computerNumber, name, date, startTime, endTime);
        if(id == null) {
            data.add(record);
        } else {
            data.set(id, record);
        }
    }

    public static void editFile(ArrayList<Record> data, String file) {
        StringBuilder CSVString = new StringBuilder();
        for(int i = 0; i < data.size(); i++) {
            CSVString.append(data.get(i).toCSVString()).append("\n");
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
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Record> data = new ArrayList<Record>();

        System.out.println("Имя файла:");
        String file = scanner.next();

        try {
            Reader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);
            String line;
            while((line = buffReader.readLine()) != null) {
                String text[] = line.split(";");
                int computerNumber = Integer.parseInt(text[0]);
                String name = text[1];
                try {
                    Date date = new SimpleDateFormat("dd.MM.y").parse(text[2]);
                    Date startTime = new SimpleDateFormat("H:mm").parse(text[3]);
                    Date endTime = new SimpleDateFormat("H:mm").parse(text[4]);

                    Record record = new Record(computerNumber, name, date, startTime, endTime);
                    data.add(record);
                } catch(ParseException e) {
                    System.out.println("Неверный формат времени");
                }
            }
            buffReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("Файл не найден");
            File newfile = new File("D:/java/laba4_2", "newFile.csv");
            try {
                newfile.createNewFile();
            } catch(IOException error) {
                System.out.println("Не удалось создать новый файл");
            }
            file = "newFile.csv";
        } catch(IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }

        boolean con = true;

        while(con) {
            System.out.println("Операция:");
            String operation = scanner.next();
            switch(operation) {
                case("print"):
                    for(int i = 0; i < data.size(); i++) {
                        System.out.println("ID: " + i);
                        System.out.println(data.get(i));
                    }
                    break;
                case("quit"):
                    con = false;
                    break;
                case("add"):
                    write(data, scanner, null);
                    editFile(data, file);
                    break;
                case("delete"):
                    System.out.println("    Номер записи:");
                    int id = scanner.nextInt();
                    try {
                        data.remove(id);
                        editFile(data, file);
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("Неверный ID");
                        break;
                    }
                    break;
                case("edit"):
                    System.out.println("    Номер записи:");
                    int id1 = scanner.nextInt();
                    try {
                        data.set(id1, data.get(id1));
                        write(data, scanner, id1);
                        editFile(data, file);
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("Неверный ID");
                        break;
                    }
                    break;
                case("request1"):
                    System.out.println("Введите дату: ");
                    String buff = scanner.next();
                    Date compareDate;
                    try {
                        compareDate = new SimpleDateFormat("dd.MM.y").parse(buff);
                    } catch(ParseException e) {
                        System.out.println("Неверный формат даты");
                        break;
                    }
                    HashMap<Integer, String> map = new HashMap<Integer, String>();
                    for(int i = 0; i < data.size(); i++) {
                        if(data.get(i).date.compareTo(compareDate) == 0) {
                            Record temp = data.get(i);
                            StringBuilder str;
                            if(map.keySet().contains(temp.computerNumber)) {
                                str = new StringBuilder(map.get(temp.computerNumber));
                            } else {
                                str = new StringBuilder();
                            }
                            str.append(" ").append(new SimpleDateFormat("H:mm").format(temp.startTime)).append("-").append(new SimpleDateFormat("H:mm").format(temp.endTime)).append(";");
                            map.put(temp.computerNumber, str.toString());
                        }
                    }
                    for(Entry<Integer, String> entry:map.entrySet()) {
                        StringBuilder result = new StringBuilder();
                        result.append("{\n").append("   Компьютер: ").append(entry.getKey()).append(",\n")
                            .append("   Сессии: ").append(entry.getValue()).append("\n}");
                        System.out.println(result);
                    }
                    break;
                case("request2"):
                    System.out.println("Введите дату:");
                    String text = scanner.next();
                    Date currentDate;
                    try {
                        currentDate = new SimpleDateFormat("dd.MM.y").parse(text);
                    } catch(ParseException e) {
                        System.out.println("Неверный формат даты");
                        break;
                    }
                    System.out.println("Введите номер компьютера:");
                    int computerNum = scanner.nextInt();
                    ArrayList<Record> buffArray = new ArrayList<Record>();
                    for(int i = 0; i < data.size(); i++) {
                        if(data.get(i).date.compareTo(currentDate) == 0 && data.get(i).computerNumber == computerNum) {
                            buffArray.add(data.get(i));
                        }
                    }
                    buffArray.sort(new RecordComparator());
                    for(int i = 0; i < buffArray.size(); i++) {
                        StringBuilder result = new StringBuilder();
                        result.append("{\n").append("   Пользователь: ").append(buffArray.get(i).name).append(",\n")
                            .append("   Время пользования: ").append(new SimpleDateFormat("H:mm").format(buffArray.get(i).startTime)).append("-").append(new SimpleDateFormat("H:mm").format(buffArray.get(i).endTime)).append("\n}");
                        System.out.println(result);
                    }
                    break;
                default:
                    System.out.println("Неверная операция");
                    break;
            }
        }

        scanner.close();
    }
}