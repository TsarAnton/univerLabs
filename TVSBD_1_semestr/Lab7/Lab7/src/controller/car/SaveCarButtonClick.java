package controller.car;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Car;
import ioc.ContainerException;
import ioc.IocContainer;
import service.car.CarService;
import service.ServiceException;
import view.car.CarEditFrame;

public class SaveCarButtonClick implements ActionListener {
	private CarEditFrame carEditFrame;
	private IocContainer container;

	public SaveCarButtonClick(CarEditFrame carEditFrame, IocContainer container) {
		this.carEditFrame = carEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Car car = carEditFrame.getCar();
		if(car != null) {
			try {
				CarService service = container.getCarService();
				service.save(car);
				JOptionPane.showMessageDialog(carEditFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				carEditFrame.update(service.findAll());
				carEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(carEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
