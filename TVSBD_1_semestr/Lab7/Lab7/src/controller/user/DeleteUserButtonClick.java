package controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.user.UserService;
import service.ServiceException;
import view.user.UserEditFrame;

public class DeleteUserButtonClick implements ActionListener {
	private UserEditFrame userEditFrame;
	private IocContainer container;

	public DeleteUserButtonClick(UserEditFrame userEditFrame, IocContainer container) {
		this.userEditFrame = userEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = userEditFrame.getUserId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(userEditFrame, "Вы действительно хотите удалить запись", "Подтверждение действия", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					UserService service = container.getUserService();
					service.delete(id);
					JOptionPane.showMessageDialog(userEditFrame, "Данные успешно удалены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
					userEditFrame.update(service.findAll());
					userEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(userEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
