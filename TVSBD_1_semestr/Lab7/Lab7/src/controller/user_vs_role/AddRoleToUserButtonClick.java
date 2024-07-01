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
import view.user_vs_role.AddRoleToUserFrame;
import view.user_vs_role.UserRolesFrame;

public class AddRoleToUserButtonClick implements ActionListener {
	private UserRolesFrame userRolesFrame;
	private IocContainer container;

	public AddRoleToUserButtonClick(UserRolesFrame userRolesFrame, IocContainer container) {
		this.userRolesFrame = userRolesFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		User user = userRolesFrame.getUser();
		List<Role> roles = new ArrayList<>();
		try {
			roles = container.getRoleService().findWithoutUser(user.getId());
		} catch (ServiceException e1) {
			e1.printStackTrace();
		} catch (ContainerException e1) {
			e1.printStackTrace();
		}
		if(user != null) {
			new AddRoleToUserFrame(userRolesFrame, roles, user, container);
		} else {
			JOptionPane.showMessageDialog(userRolesFrame, "В таблице не выбрана ни одна роль", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
