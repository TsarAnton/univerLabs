package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FirstRequestListener implements ActionListener {

    private JTable table;
    private JTextField dateFrame;

    public FirstRequestListener(JTable table, JTextField dateFrame) {
        this.table = table;
        this.dateFrame = dateFrame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        DataTableModel model = (DataTableModel) table.getModel();
        try {
            Date compareDate = Functions.dateFormat.parse(dateFrame.getText());
            List<Record> data = model.getData();
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
                    str.append(" ").append(Functions.timeFormat.format(temp.startTime)).append("-").append(Functions.timeFormat.format(temp.endTime)).append(";");
                    map.put(temp.computerNumber, str.toString());
                }
            }
            StringBuilder result = new StringBuilder();
            for(Entry<Integer, String> entry:map.entrySet()) {
                result.append("\n").append("   Компьютер: ").append(entry.getKey()).append(",\n")
                        .append("   Сессии: ").append(entry.getValue()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, result.toString());
        } catch(ParseException e) {
            JOptionPane.showMessageDialog(null, "Ошибка: неправмльный формат даты");
            return;
        }
    }
}
