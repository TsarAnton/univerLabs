package controller.car_copy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Car_copy;
import ioc.ContainerException;
import ioc.IocContainer;
import service.car_copy.Car_copyService;
import service.ServiceException;
import view.car_copy.Car_copyEditFrame;

public class SaveCar_copyButtonClick implements ActionListener {
	private Car_copyEditFrame car_copyEditFrame;
	private IocContainer container;

	public SaveCar_copyButtonClick(Car_copyEditFrame car_copyEditFrame, IocContainer container) {
		this.car_copyEditFrame = car_copyEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Car_copy car_copy = car_copyEditFrame.getCar_copy();
		if(car_copy != null) {
			try {
				Car_copyService service = container.getCar_copyService();
				service.save(car_copy);
				JOptionPane.showMessageDialog(car_copyEditFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				car_copyEditFrame.update(service.findAll());
				car_copyEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(car_copyEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
