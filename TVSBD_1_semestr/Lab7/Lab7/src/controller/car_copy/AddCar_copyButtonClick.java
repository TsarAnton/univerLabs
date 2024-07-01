package controller.car_copy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import domain.Car;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import view.car_copy.Car_copyEditFrame;
import view.car_copy.Car_copysListFrame;

public class AddCar_copyButtonClick implements ActionListener {
	private Car_copysListFrame car_copysListFrame;
	private IocContainer container;

	public AddCar_copyButtonClick(Car_copysListFrame car_copysListFrame, IocContainer container) {
		this.car_copysListFrame = car_copysListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			List<Car> cars = container.getCarService().findAll();
			new Car_copyEditFrame(car_copysListFrame, cars, container);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		} catch (ContainerException e1) {
			e1.printStackTrace();
		}
	}
}
