package pack;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import javax.swing.JTextArea;

class MyForm extends JFrame {
    public MyForm() {
        super("Задание 4");
        setBounds(100, 50, 600, 670);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel firstOperandLabel = new JLabel("Элементы массива:");
        firstOperandLabel.setBounds(10, 10, 350, 30);
        add(firstOperandLabel);

        JTextArea firstOperandTextField = new JTextArea();
        firstOperandTextField.setBounds(10, 50, 560, 300);
        add(firstOperandTextField);

        JLabel secondOperandLabel = new JLabel("Подстрока:");
        secondOperandLabel.setBounds(10, 360, 150, 30);
        add(secondOperandLabel);

        JTextField secondOperandTextField = new JTextField();
        secondOperandTextField.setBounds(10, 400, 200, 30);
        add(secondOperandTextField);

        JRadioButton caseCompator1 = new JRadioButton("Сортировка 1");
        caseCompator1.setBounds(10, 450, 150, 20);
        caseCompator1.setSelected(true);
        JRadioButton caseCompator2 = new JRadioButton("Сортировка 2");
        caseCompator2.setBounds(170, 450, 150, 20);
        caseCompator2.setSelected(false);

        ButtonGroup group = new ButtonGroup();
        group.add(caseCompator1);
        group.add(caseCompator2);
        
        add(caseCompator1);
        add(caseCompator2);

        JLabel compareLabel = new JLabel("Кол-во сравнений:");
        compareLabel.setBounds(10, 490, 150, 30);
        add(compareLabel);

        JTextField compareTextField = new JTextField();
        compareTextField.setBounds(10, 530, 200, 30);
        compareTextField.setEnabled(false);
        add(compareTextField);

        JButton calculateButton = new JButton("Отсортировать");
        calculateButton.setBounds(10, 580, 150, 30);
        calculateButton.addActionListener(
                new CalculateButtonHandler(
                        compareTextField,
                        firstOperandTextField,
                        secondOperandTextField,
                        caseCompator1,
                        caseCompator2
                )
        );
        add(calculateButton);
        validate();
        setVisible(true);
    }
}