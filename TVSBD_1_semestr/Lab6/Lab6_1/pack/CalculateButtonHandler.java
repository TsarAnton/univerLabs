package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

class CalculateButtonHandler implements ActionListener {
    private JTextField f1, f2;
    public CalculateButtonHandler(JTextField f1, JTextField f2) {
        this.f1 = f1;
        this.f2 = f2;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            Double x = Double.parseDouble(f1.getText());
            Double f = Double.parseDouble(f2.getText());
            Double res = Functions.expression(x, f);
            String result = "Результат вычисления " + res + "\nРезультат вычисления с помощью встроенной функции " + Math.pow(Math.E, x);
            JOptionPane.showMessageDialog(null, result);
        } catch(NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Неверное число");
        }
    }
}
