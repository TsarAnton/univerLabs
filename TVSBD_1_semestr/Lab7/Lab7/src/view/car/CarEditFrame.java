package view.car;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.car.DeleteCarButtonClick;
import controller.car.SaveCarButtonClick;
import domain.Car;
import ioc.IocContainer;

public class CarEditFrame extends JDialog {
	private Car car;
	private JTextField markTextField;
	private JTextField modelTextField;

	private CarEditFrame(JFrame owner, Car car, String title, IocContainer container) {
		super(owner, title);
		this.car = car;
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

		JLabel modelLabel = new JLabel("Модель:");
		modelLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(modelLabel, constraints);
		add(modelLabel);

		modelTextField = new JTextField(car.getModel());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(modelTextField, constraints);
		add(modelTextField);

		JLabel markLabel = new JLabel("Марка:");
		markLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(markLabel, constraints);
		add(markLabel);

		markTextField = new JTextField(car.getMark());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(markTextField, constraints);
		add(markTextField);

		JButton saveButton = new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveCarButtonClick(this, container));
		add(saveButton);

		if(car.getId() != null) {
			JButton deleteButton = new JButton("Удалить");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteCarButtonClick(this, container));
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

	public CarEditFrame(JFrame owner, Car car, IocContainer container) {
		this(owner, car, String.format("Редактирование пользователя %s", car.getMark()), container);
	}

	public CarEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Car(), "Добавление пользователя", container);
	}

	public Car getCar() {
		String mark = markTextField.getText();
		if(mark.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Логин» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		car.setMark(mark);
		String model = modelTextField.getText();
		if(model.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Пароль» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		car.setModel(model);
		return car;
	}

	public Long getCarId() {
		return car != null ? car.getId() : null;
	}

	public void update(List<Car> cars) {
		((CarsListFrame)getOwner()).setCars(cars);
	}
}
