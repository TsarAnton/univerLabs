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
import view.user_vs_role.RoleUsersFrame;
import view.role.RolesListFrame;

public class StartRoleUsersButtonClick implements ActionListener {
	private RolesListFrame rolesListFrame;
	private IocContainer container;

	public StartRoleUsersButtonClick(RolesListFrame rolesListFrame, IocContainer container) {
		this.rolesListFrame = rolesListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Role role = rolesListFrame.getSelectedRole();
		List<User> users = new ArrayList<>();
		try {
			users = container.getUserService().findByRole(role.getId());
		} catch (ServiceException e1) {
			System.out.println("oshibka1");
			e1.printStackTrace();
		} catch (ContainerException e1) {
			e1.printStackTrace();
		}
		if(role != null) {
			RoleUsersFrame roleUsersFrame = new RoleUsersFrame(container);
			roleUsersFrame.setUsers(users);
			roleUsersFrame.setRole(role);
		} else {
			JOptionPane.showMessageDialog(rolesListFrame, "В таблице не выбрана ни одна роль", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
