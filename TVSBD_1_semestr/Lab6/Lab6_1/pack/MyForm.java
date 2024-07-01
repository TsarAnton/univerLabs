package pack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class MyForm extends JFrame {
    public MyForm() {
        super("Первое Swing-приложение");
        setBounds(100, 50, 380, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel firstOperandLabel = new JLabel("Аргумент функции:");
        firstOperandLabel.setBounds(10, 10, 350, 30);
        add(firstOperandLabel);

        JTextField firstOperandTextField = new JTextField();
        firstOperandTextField.setBounds(10, 50, 350, 30);
        add(firstOperandTextField);

        JLabel secondOperandLabel = new JLabel("Точность:");
        secondOperandLabel.setBounds(10, 90, 350, 30);
        add(secondOperandLabel);

        JTextField secondOperandTextField = new JTextField();
        secondOperandTextField.setBounds(10, 130, 350, 30);
        add(secondOperandTextField);

        JButton calculateButton = new JButton("Вычислить результат");
        calculateButton.setBounds(60, 170, 250, 30);
        calculateButton.addActionListener(
            new CalculateButtonHandler(
                firstOperandTextField,
                secondOperandTextField
            )
        );
        add(calculateButton);

        validate();
        setVisible(true);
    }
}