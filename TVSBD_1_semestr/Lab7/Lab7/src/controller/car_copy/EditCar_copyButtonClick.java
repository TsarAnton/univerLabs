package controller.car_copy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Car;
import domain.Car_copy;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import view.car_copy.Car_copyEditFrame;
import view.car_copy.Car_copysListFrame;

public class EditCar_copyButtonClick implements ActionListener {
	private Car_copysListFrame car_copysListFrame;
	private IocContainer container;

	public EditCar_copyButtonClick(Car_copysListFrame car_copysListFrame, IocContainer container) {
		this.car_copysListFrame = car_copysListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Car_copy car_copy = car_copysListFrame.getSelectedCar_copy();
		List<Car> cars = new ArrayList<>();
		try {
			cars = container.getCarService().findAll();
		} catch (ServiceException e1) {
			e1.printStackTrace();
		} catch (ContainerException e1) {
			e1.printStackTrace();
		}
		if(car_copy != null) {
			new Car_copyEditFrame(car_copysListFrame, car_copy, cars, container);
		} else {
			JOptionPane.showMessageDialog(car_copysListFrame, "В таблице не выбрана ни одна единица машины", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
