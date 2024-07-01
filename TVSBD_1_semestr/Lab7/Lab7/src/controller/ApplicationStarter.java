package controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ioc.IocContainer;

public class ApplicationStarter {
	private IocContainer container;

	public ApplicationStarter(IocContainer container) {
		this.container = container;
	}

	public void start() {
		JFrame window = new JFrame("Главное окно");
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setBounds(10, 10, 250, 300);
        window.setLayout(null);

		JLabel tableLabel = new JLabel("Выберите таблицу:");
    	tableLabel.setBounds(10, 10, 200, 30);
        window.add(tableLabel);

		String[] tables = {
			"Поломки",
			"Пользователи",
			"Роли",
			"Машины",
			"Единицы машин"
		};

		JComboBox<String> switchTable = new JComboBox<>(tables);
		switchTable.setBounds(10, 50, 200, 30);
		window.add(switchTable);

		SwitchTableButtonClick tableHandler = new SwitchTableButtonClick(switchTable, container);

		JButton tableButton = new JButton("Показать таблицу");
        tableButton.setBounds(10, 90, 200, 30);
        tableButton.addActionListener(tableHandler);
        window.add(tableButton);

		JLabel requestLabel = new JLabel("Выберите запрос:");
    	requestLabel.setBounds(10, 130, 200, 30);
        window.add(requestLabel);

		String[] requests = {
			"Задание 2 (1)",
			"Задание 3 (1)",
			"Задание 4 (1)"
		};

		JComboBox<String> switchRequest = new JComboBox<>(requests);
		switchRequest.setBounds(10, 170, 200, 30);
		window.add(switchRequest);

		SwitchRequestButtonClick requestHandler = new SwitchRequestButtonClick(switchRequest, container);

		JButton requestButton = new JButton("Выполнить запрос");
        requestButton.setBounds(10, 210, 200, 30);
        requestButton.addActionListener(requestHandler);
        window.add(requestButton);

		window.setVisible(true);
        window.validate();
	}
}
