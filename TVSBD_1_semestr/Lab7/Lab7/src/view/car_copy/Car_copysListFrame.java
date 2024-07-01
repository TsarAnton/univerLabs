package view.car_copy;

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

import controller.car_copy.AddCar_copyButtonClick;
import controller.car_copy.EditCar_copyButtonClick;
import domain.Car_copy;
import domain.Car_copyGetAll;
import ioc.IocContainer;

public class Car_copysListFrame extends JFrame {
	private Car_copysListTableModel model;
	private JTable car_copysListTable;

	public Car_copysListFrame(IocContainer container) throws HeadlessException {
		super("Список единиц машин");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Car_copysListTableModel();
		car_copysListTable = new JTable(model);
		car_copysListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(car_copysListTable);
		add(jScrollPane);
		FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
		JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
		JButton addCar_copyButton = new JButton("Добавить");
		addCar_copyButton.setPreferredSize(new Dimension(100, 30));
		addCar_copyButton.addActionListener(new AddCar_copyButtonClick(this, container));
		buttonsPanel.add(addCar_copyButton);
		JButton editCar_copyButton = new JButton("Изменить");
		editCar_copyButton.setPreferredSize(new Dimension(100, 30));
		editCar_copyButton.addActionListener(new EditCar_copyButtonClick(this, container));
		buttonsPanel.add(editCar_copyButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setCar_copys(List<Car_copyGetAll> car_copys) {
		model.setCar_copys(car_copys);
	}

	public Car_copy getSelectedCar_copy() {
		int index = car_copysListTable.getSelectedRow();
		if(index != -1) {
			return model.getCar_copy(index);
		}
		return null;
	}
}
