package controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.User;
import ioc.IocContainer;
import view.user.UserEditFrame;
import view.user.UsersListFrame;

public class EditUserButtonClick implements ActionListener {
	private UsersListFrame usersListFrame;
	private IocContainer container;

	public EditUserButtonClick(UsersListFrame usersListFrame, IocContainer container) {
		this.usersListFrame = usersListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		User user = usersListFrame.getSelectedUser();
		if(user != null) {
			new UserEditFrame(usersListFrame, user, container);
		} else {
			JOptionPane.showMessageDialog(usersListFrame, "В таблице не выбран ни один пользователь", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
