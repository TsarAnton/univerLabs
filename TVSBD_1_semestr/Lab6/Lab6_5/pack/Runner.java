package pack;

import java.util.Date;
import java.text.ParseException;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {

        List<Record> DATA = new ArrayList<Record>();

        JFrame window = new JFrame("Главное окно");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(10, 10, 800, 850);
        window.setLayout(null);

        Functions.file = "file.csv";

        try {
            Reader reader = new FileReader(Functions.file);
            BufferedReader buffReader = new BufferedReader(reader);
            String line;
            while((line = buffReader.readLine()) != null) {
                String text[] = line.split(";");
                int id = Integer.parseInt(text[0]);
                Functions.ID = id;
                int computerNumber = Integer.parseInt(text[1]);
                String name = text[2];
                try {
                    Date date = Functions.dateFormat.parse(text[3]);
                    Date startTime = Functions.timeFormat.parse(text[4]);
                    Date endTime = Functions.timeFormat.parse(text[5]);

                    Record record = new Record(id ,computerNumber, name, date, startTime, endTime);
                    DATA.add(record);
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
            Functions.file = "newFile.csv";
        } catch(IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }

        DataTableModel model = new DataTableModel(DATA);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 0, 770, 400);
        window.add(scrollPane);
        JLabel idLabel = new JLabel("ID(пустой для добавления):");
        idLabel.setBounds(10, 410, 170, 30);
        window.add(idLabel);
        JTextField idField = new JTextField();
        idField.setBounds(200, 410, 200, 30);
        window.add(idField);
        JLabel computerLabel = new JLabel("Номер компьютера:");
        computerLabel.setBounds(10,450,170,30);
        window.add(computerLabel);
        JTextField computerField = new JTextField();
        computerField.setBounds(200,450,200,30);
        window.add(computerField);
        JLabel nameLabel = new JLabel("Ф.И.О.:");
        nameLabel.setBounds(10,490,170,30);
        window.add(nameLabel);
        JTextField nameField = new JTextField();
        nameField.setBounds(200,490,200,30);
        window.add(nameField);
        JLabel dateLabel = new JLabel("Дата:");
        dateLabel.setBounds(10,530,170,30);
        window.add(dateLabel);
        JTextField dateField = new JTextField();
        dateField.setBounds(200,530,200,30);
        window.add(dateField);
        JLabel startLabel = new JLabel("Время начала:");
        startLabel.setBounds(10,570,170,30);
        window.add(startLabel);
        JTextField startField = new JTextField();
        startField.setBounds(200,570,200,30);
        window.add(startField);
        JLabel endLabel = new JLabel("Время завершения:");
        endLabel.setBounds(10,610,170,30);
        window.add(endLabel);
        JTextField endField = new JTextField();
        endField.setBounds(200,610,200,30);
        window.add(endField);
        AddListener addHandler = new AddListener(window, table, idField, computerField, nameField, dateField, startField, endField);
        JButton addButton = new JButton("Добавить(изменить) запись в таблицу");
        addButton.setBounds(10, 650, 400, 30);
        addButton.addActionListener(addHandler);
        window.add(addButton);
        DeleteListener deleteHandler = new DeleteListener(window, table, idField);
        JButton deleteButton = new JButton("Удалить запись");
        deleteButton.setBounds(10, 690, 400, 30);
        deleteButton.addActionListener(deleteHandler);
        window.add(deleteButton);
        JButton firstRequestButton = new JButton("Запрос 1");
        FirstRequestListener firstRequestListener = new FirstRequestListener(table, dateField);
        firstRequestButton.setBounds(10, 730, 400, 30);
        firstRequestButton.addActionListener(firstRequestListener);
        window.add(firstRequestButton);
        JButton secondRequestButton = new JButton("Запрос 2");
        SecondRequestListener secondRequestListener = new SecondRequestListener(table, dateField, computerField);
        secondRequestButton.setBounds(10, 770, 400, 30);
        secondRequestButton.addActionListener(secondRequestListener);
        window.add(secondRequestButton);

        window.setVisible(true);
        window.validate();
    }
}