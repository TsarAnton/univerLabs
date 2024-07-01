package controller.car;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Car;
import ioc.IocContainer;
import view.car.CarEditFrame;
import view.car.CarsListFrame;

public class EditCarButtonClick implements ActionListener {
	private CarsListFrame carsListFrame;
	private IocContainer container;

	public EditCarButtonClick(CarsListFrame carsListFrame, IocContainer container) {
		this.carsListFrame = carsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Car car = carsListFrame.getSelectedCar();
		if(car != null) {
			new CarEditFrame(carsListFrame, car, container);
		} else {
			JOptionPane.showMessageDialog(carsListFrame, "В таблице не выбрана ни одна машина", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
