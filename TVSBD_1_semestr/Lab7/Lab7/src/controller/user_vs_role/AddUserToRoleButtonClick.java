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
import view.user_vs_role.AddUserToRoleFrame;
import view.user_vs_role.RoleUsersFrame;

public class AddUserToRoleButtonClick implements ActionListener {
	private RoleUsersFrame roleUsersFrame;
	private IocContainer container;

	public AddUserToRoleButtonClick(RoleUsersFrame roleUsersFrame, IocContainer container) {
		this.roleUsersFrame = roleUsersFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Role role = roleUsersFrame.getRole();
		List<User> users = new ArrayList<>();
		try {
			users = container.getUserService().findWithoutRole(role.getId());
		} catch (ServiceException e1) {
			e1.printStackTrace();
		} catch (ContainerException e1) {
			e1.printStackTrace();
		}
		if(role != null) {
			new AddUserToRoleFrame(roleUsersFrame, users, role, container);
		} else {
			JOptionPane.showMessageDialog(roleUsersFrame, "В таблице не выбран ни один пользователь", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
