package controller.car;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.car.CarEditFrame;
import view.car.CarsListFrame;

public class AddCarButtonClick implements ActionListener {
	private CarsListFrame carsListFrame;
	private IocContainer container;

	public AddCarButtonClick(CarsListFrame carsListFrame, IocContainer container) {
		this.carsListFrame = carsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new CarEditFrame(carsListFrame, container);
	}
}
