package controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.user.UserEditFrame;
import view.user.UsersListFrame;

public class AddUserButtonClick implements ActionListener {
	private UsersListFrame usersListFrame;
	private IocContainer container;

	public AddUserButtonClick(UsersListFrame usersListFrame, IocContainer container) {
		this.usersListFrame = usersListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new UserEditFrame(usersListFrame, container);
	}
}
