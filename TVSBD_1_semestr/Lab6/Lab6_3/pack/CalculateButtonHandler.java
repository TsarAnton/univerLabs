package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class CalculateButtonHandler implements ActionListener {
    private JTextField compare;
    private JTextArea array;
    private JTextField s;
    private JRadioButton c1, c2;
    public CalculateButtonHandler(JTextField compare,JTextArea array, JTextField s, JRadioButton c1, JRadioButton c2) {
        this.compare = compare;
        this.array = array;
        this.s = s;
        this.c1 = c1;
        this.c2 = c2;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String[] arr = this.array.getText().split("\n");
        String S = this.s.getText();
        if(this.c1.isSelected()) {
            if(S.equals("")) {
                this.array.setText("Подстрока не задана");
                return;
            } else {
                Arrays.sort(arr, new StringComparator1(S));
                compare.setText(Integer.toString(StringComparator1.count));
            }
        } else if(this.c2.isSelected()) {
            Arrays.sort(arr, new StringComparator2());
            compare.setText(Integer.toString(StringComparator2.count));
        }
        array.setText(SymbolCount.toString(arr));
    }
}
