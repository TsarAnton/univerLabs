package view.request2_1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.request2_1.FindButtonClick;
import domain.Driver;
import ioc.IocContainer;

public class Request2_1InitFrame extends JDialog {
	private List<Driver> drivers;
	private JComboBox<String> driverBox;

	private Request2_1InitFrame(List<Driver> drivers, String title, IocContainer container) {
		this.drivers = drivers;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 275);
		setResizable(false);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		JLabel modelLabel = new JLabel("Водитель:");
		modelLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(modelLabel, constraints);
		add(modelLabel);

		String driversArray[] = new String[drivers.size()];
		for(int i = 0; i < drivers.size(); i++) {
			driversArray[i] = drivers.get(i).getId() + "; " + drivers.get(i).getSurname() + "; " + drivers.get(i).getName();
		}

		driverBox = new JComboBox<>(driversArray);
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(driverBox, constraints);
		add(driverBox);

		JButton saveButton = new JButton("Выполнить запрос");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new FindButtonClick(this, container));
		add(saveButton);

		setVisible(true);
	}

	public Request2_1InitFrame(List<Driver> drivers, IocContainer container) {
		this(drivers, "Выбор водителя", container);
	}

	public Long getSelectedDriverId() {
		String buff = (String) driverBox.getSelectedItem();
		if(buff.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Водитель» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		Long driver_id = Long.parseLong(buff.split(";")[0]);
		return driver_id;
	}
}
