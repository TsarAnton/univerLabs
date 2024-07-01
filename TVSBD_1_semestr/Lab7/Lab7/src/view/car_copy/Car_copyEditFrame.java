package view.car_copy;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.car_copy.DeleteCar_copyButtonClick;
import controller.car_copy.SaveCar_copyButtonClick;
import domain.Car;
import domain.Car_copy;
import domain.Car_copyGetAll;
import ioc.IocContainer;

public class Car_copyEditFrame extends JDialog {
	private Car_copy car_copy;
	private List<Car> cars;
	private JTextField state_numTextField;
	private JComboBox<String> carBox;

	private Car_copyEditFrame(JFrame owner, Car_copy car_copy, List<Car> cars, String title, IocContainer container) {
		super(owner, title);
		this.car_copy = car_copy;
		this.cars = cars;
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

		JLabel modelLabel = new JLabel("Машина:");
		modelLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(modelLabel, constraints);
		add(modelLabel);

		String carsArray[] = new String[cars.size()];
		for(int i = 0; i < cars.size(); i++) {
			carsArray[i] = cars.get(i).getId() + "; " + cars.get(i).getMark() + "; " + cars.get(i).getModel();
		}

		carBox = new JComboBox<>(carsArray);
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(carBox, constraints);
		add(carBox);

		JLabel state_numLabel = new JLabel("Госномер:");
		state_numLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(state_numLabel, constraints);
		add(state_numLabel);

		state_numTextField = new JTextField(car_copy.getState_num());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(state_numTextField, constraints);
		add(state_numTextField);

		JButton saveButton = new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveCar_copyButtonClick(this, container));
		add(saveButton);

		if(car_copy.getId() != null) {
			JButton deleteButton = new JButton("Удалить");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteCar_copyButtonClick(this, container));
			add(deleteButton);
		} else {
			JLabel emptyLabel = new JLabel();
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(emptyLabel, constraints);
			add(emptyLabel);
		}

		setVisible(true);
	}

	public Car_copyEditFrame(JFrame owner, Car_copy car_copy, List<Car> cars, IocContainer container) {
		this(owner, car_copy, cars, String.format("Редактирование единицы машины %s", car_copy.getState_num()), container);
	}

	public Car_copyEditFrame(JFrame owner, List<Car> cars, IocContainer container) {
		this(owner, new Car_copy(), cars, "Добавление единицы машины", container);
	}

	public Car_copy getCar_copy() {
		String state_num = state_numTextField.getText();
		if(state_num.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Госномер» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		car_copy.setState_num(state_num);
		String buff = (String) carBox.getSelectedItem();
		if(buff.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Машина» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		Long car_id = Long.parseLong(buff.split(";")[0]);
		car_copy.setCar_id(car_id);
		return car_copy;
	}

	public Long getCar_copyId() {
		return car_copy != null ? car_copy.getId() : null;
	}

	public void update(List<Car_copyGetAll> car_copys) {
		((Car_copysListFrame)getOwner()).setCar_copys(car_copys);
	}
}
