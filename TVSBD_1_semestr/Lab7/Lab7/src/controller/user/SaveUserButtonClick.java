package controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.User;
import ioc.ContainerException;
import ioc.IocContainer;
import service.user.UserService;
import service.ServiceException;
import view.user.UserEditFrame;

public class SaveUserButtonClick implements ActionListener {
	private UserEditFrame userEditFrame;
	private IocContainer container;

	public SaveUserButtonClick(UserEditFrame userEditFrame, IocContainer container) {
		this.userEditFrame = userEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		User user = userEditFrame.getUser();
		if(user != null) {
			try {
				UserService service = container.getUserService();
				service.save(user);
				JOptionPane.showMessageDialog(userEditFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				userEditFrame.update(service.findAll());
				userEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(userEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
