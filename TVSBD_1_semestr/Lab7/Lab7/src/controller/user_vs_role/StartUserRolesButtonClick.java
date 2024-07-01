package controller.user_vs_role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Role;
import domain.User;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import view.user_vs_role.UserRolesFrame;
import view.user.UsersListFrame;

public class StartUserRolesButtonClick implements ActionListener {
	private UsersListFrame usersListFrame;
	private IocContainer container;

	public StartUserRolesButtonClick(UsersListFrame usersListFrame, IocContainer container) {
		this.usersListFrame = usersListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		User user = usersListFrame.getSelectedUser();
		List<Role> roles = new ArrayList<>();
		try {
			roles = container.getRoleService().findByUser(user.getId());
		} catch (ServiceException e1) {
			e1.printStackTrace();
		} catch (ContainerException e1) {
			e1.printStackTrace();
		}
		if(user != null) {
			UserRolesFrame userRolesFrame = new UserRolesFrame(container);
			userRolesFrame.setRoles(roles);
			userRolesFrame.setUser(user);
		} else {
			JOptionPane.showMessageDialog(usersListFrame, "В таблице не выбрана ни одна роль", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
