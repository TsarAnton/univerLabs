package controller.car_copy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.car_copy.Car_copyService;
import service.ServiceException;
import view.car_copy.Car_copyEditFrame;

public class DeleteCar_copyButtonClick implements ActionListener {
	private Car_copyEditFrame car_copyEditFrame;
	private IocContainer container;

	public DeleteCar_copyButtonClick(Car_copyEditFrame car_copyEditFrame, IocContainer container) {
		this.car_copyEditFrame = car_copyEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = car_copyEditFrame.getCar_copyId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(car_copyEditFrame, "Вы действительно хотите удалить запись", "Подтверждение действия", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					Car_copyService service = container.getCar_copyService();
					service.delete(id);
					JOptionPane.showMessageDialog(car_copyEditFrame, "Данные успешно удалены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
					car_copyEditFrame.update(service.findAll());
					car_copyEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(car_copyEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
