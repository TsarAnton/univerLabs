package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SecondRequestListener implements ActionListener {

    private JTable table;
    private JTextField dateFrame, computerField;

    public SecondRequestListener(JTable table, JTextField dateFrame, JTextField computerField) {
        this.table = table;
        this.dateFrame = dateFrame;
        this.computerField = computerField;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        DataTableModel model = (DataTableModel) table.getModel();
        try {
            Date currentDate = Functions.dateFormat.parse(dateFrame.getText());
            int computerNum = Integer.parseInt(computerField.getText());
            List<Record> data = model.getData();
            ArrayList<Record> buffArray = new ArrayList<Record>();
            for(int i = 0; i < data.size(); i++) {
                if(data.get(i).date.compareTo(currentDate) == 0 && data.get(i).computerNumber == computerNum) {
                    buffArray.add(data.get(i));
                }
            }
            buffArray.sort(new RecordComparator());
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < buffArray.size(); i++) {
                result.append("\n").append("   Пользователь: ").append(buffArray.get(i).name).append(",\n")
                        .append("   Время пользования: ").append(Functions.timeFormat.format(buffArray.get(i).startTime)).append("-").append(Functions.timeFormat.format(buffArray.get(i).endTime)).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, result.toString());
        } catch(ParseException e) {
            JOptionPane.showMessageDialog(null, "Ошибка: неправмльный формат даты");
            return;
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ошибка: нужно ввести число");
            return;
        }
    }
}

