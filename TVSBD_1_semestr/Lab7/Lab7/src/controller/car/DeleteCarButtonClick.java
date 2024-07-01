package controller.car;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.car.CarService;
import service.ServiceException;
import view.car.CarEditFrame;

public class DeleteCarButtonClick implements ActionListener {
	private CarEditFrame carEditFrame;
	private IocContainer container;

	public DeleteCarButtonClick(CarEditFrame carEditFrame, IocContainer container) {
		this.carEditFrame = carEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = carEditFrame.getCarId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(carEditFrame, "Вы действительно хотите удалить запись", "Подтверждение действия", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					CarService service = container.getCarService();
					service.delete(id);
					JOptionPane.showMessageDialog(carEditFrame, "Данные успешно удалены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
					carEditFrame.update(service.findAll());
					carEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(carEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
