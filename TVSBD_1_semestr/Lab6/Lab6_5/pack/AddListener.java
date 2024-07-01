package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AddListener implements ActionListener {

    private JFrame scroll;
    private JTable table;
    private JTextField id, computer, name, date, start, end;

    public AddListener(JFrame scroll,JTable table, JTextField id, JTextField computer, JTextField name, JTextField date, JTextField start, JTextField end) {
        this.scroll = scroll;
        this.table = table;
        this.id = id;
        this.computer = computer;
        this.name = name;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        DataTableModel model = (DataTableModel) table.getModel();
        try {
            Integer computerBuff = Integer.parseInt(computer.getText());
            String nameBuff = name.getText();
            if(nameBuff.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ошибка: пустое имя");
                return;
            }
            Date dateBuff = Functions.dateFormat.parse(date.getText());
            Date startBuff = Functions.timeFormat.parse(start.getText());
            Date endBuff = Functions.timeFormat.parse(end.getText());

            String idCheck = id.getText();
            if(idCheck.isEmpty()) {
                Functions.ID++;
                int idBuff = Functions.ID;
                model.getData().add(new Record(idBuff, computerBuff, nameBuff, dateBuff, startBuff, endBuff));
                Functions.editFile(model.getData(), Functions.file);
            } else {
                int idBuff = Integer.parseInt(idCheck);
                if(!model.containsId(idBuff)) {
                    JOptionPane.showMessageDialog(null, "Ошибка: такого id нет");
                    return;
                }
                model.getData().set(idBuff, new Record(idBuff, computerBuff, nameBuff, dateBuff, startBuff, endBuff));
                Functions.editFile(model.getData(), Functions.file);
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 0, 770, 400);
            scroll.add(scrollPane);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ошибка: неверный формат даты");
            return;
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ошибка: нужно ввести число");
            return;
        }
    }
}

