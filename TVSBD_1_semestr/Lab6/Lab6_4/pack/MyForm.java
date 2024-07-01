package pack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class MyForm extends JFrame {
    public MyForm() {
        super("Задание 3");
        setBounds(100, 50, 380, 450);
        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel firstOperandLabel = new JLabel("Дата:");
        firstOperandLabel.setBounds(10, 10, 350, 30);
        add(firstOperandLabel);

        JTextField firstOperandTextField = new JTextField();
        firstOperandTextField.setBounds(10, 50, 350, 30);
        add(firstOperandTextField);

        JLabel secondOperandLabel = new JLabel("Число дней:");
        secondOperandLabel.setBounds(10, 90, 350, 30);
        add(secondOperandLabel);

        JTextField secondOperandTextField = new JTextField();
        secondOperandTextField.setBounds(10, 130, 350, 30);
        add(secondOperandTextField);

        JTextField thirdOperandTextField = new JTextField();
        thirdOperandTextField.setBounds(10, 370, 350, 30);
        thirdOperandTextField.setEnabled(false);
        add(thirdOperandTextField);

        JButton calculateButton = new JButton("Вычислить новую дату");
        calculateButton.setBounds(10, 270, 350, 30);
        calculateButton.addActionListener(
                new CalculateButtonHandler(
                        firstOperandTextField,
                        secondOperandTextField,
                        thirdOperandTextField
                )
        );
        add(calculateButton);




        validate();
        setVisible(true);
    }
}
