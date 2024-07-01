package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;

class CalculateButtonHandler implements ActionListener {

    private JTextField f1, f2, f3;
    public CalculateButtonHandler(JTextField f1, JTextField f2, JTextField f3) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            Date date = new SimpleDateFormat("dd.MM.y").parse(f1.getText());
            Integer days = Integer.parseInt(f2.getText());
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.HOUR_OF_DAY + Calendar.MINUTE, days);
            date = c.getTime();
            //date = Calendar.getTime();

            String result = "Ответ: " + new SimpleDateFormat("dd.MM.y").format(date);
            JOptionPane.showMessageDialog(null, result);

            if(date.compareTo(new Date()) > 0){
                f3.setText("Будущее");
            }else {
                f3.setText("Прошлое");
            }
        } catch (ParseException e) {
            String result = "Ошибка ввода даты";
            JOptionPane.showMessageDialog(null, result);
        } catch (NumberFormatException e){
            String result = "Ошибка ввода числа";
            JOptionPane.showMessageDialog(null, result);
        }
    }
    
}
