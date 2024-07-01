package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DeleteListener implements ActionListener {

    private JFrame scroll;
    private JTable table;
    private JTextField id;

    public DeleteListener(JFrame scroll, JTable table, JTextField id) {
        this.scroll = scroll;
        this.table = table;
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        DataTableModel model = (DataTableModel) table.getModel();
        try {
            String idCheck = id.getText();
            if(idCheck.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ошибка: пустой id");
                return;
            } else {
                int idBuff = Integer.parseInt(idCheck);
                if(!model.containsId(idBuff)) {
                    JOptionPane.showMessageDialog(null, "Ошибка: такого id нет");
                    return;
                }
                model.getData().remove(model.getById(idBuff));
                Functions.editFile(model.getData(), Functions.file);
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 0, 770, 400);
            scroll.add(scrollPane);

        } catch (NumberFormatException e){
            System.out.println(e);
        }
    }
}
