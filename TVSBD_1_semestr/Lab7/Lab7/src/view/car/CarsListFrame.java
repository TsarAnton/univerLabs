package view.car;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.car.AddCarButtonClick;
import controller.car.EditCarButtonClick;
import domain.Car;
import ioc.IocContainer;

public class CarsListFrame extends JFrame {
	private CarsListTableModel model;
	private JTable carsListTable;

	public CarsListFrame(IocContainer container) throws HeadlessException {
		super("Список пользователей");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new CarsListTableModel();
		carsListTable = new JTable(model);
		carsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(carsListTable);
		add(jScrollPane);
		FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
		JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
		JButton addCarButton = new JButton("Добавить");
		addCarButton.setPreferredSize(new Dimension(100, 30));
		addCarButton.addActionListener(new AddCarButtonClick(this, container));
		buttonsPanel.add(addCarButton);
		JButton editCarButton = new JButton("Изменить");
		editCarButton.setPreferredSize(new Dimension(100, 30));
		editCarButton.addActionListener(new EditCarButtonClick(this, container));
		buttonsPanel.add(editCarButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setCars(List<Car> cars) {
		model.setCars(cars);
	}

	public Car getSelectedCar() {
		int index = carsListTable.getSelectedRow();
		if(index != -1) {
			return model.getCar(index);
		}
		return null;
	}
}
