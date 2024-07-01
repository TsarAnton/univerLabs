package controller.user_vs_role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Role;
import ioc.ContainerException;
import ioc.IocContainer;
import service.user.UserService;
import service.role.RoleService;
import service.ServiceException;
import view.user_vs_role.AddUserToRoleFrame;

public class SaveUserToRoleButtonClick implements ActionListener {
	private AddUserToRoleFrame addUserToRoleFrame;
	private IocContainer container;

	public SaveUserToRoleButtonClick(AddUserToRoleFrame addUserToRoleFrame, IocContainer container) {
		this.addUserToRoleFrame = addUserToRoleFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long user_id = addUserToRoleFrame.getSelectedUserId();
		Role role = addUserToRoleFrame.getRole();
		if(user_id != null) {
			try {
				UserService userService = container.getUserService();
				RoleService roleService = container.getRoleService();
				roleService.saveUserRole(user_id, role.getId());
				JOptionPane.showMessageDialog(addUserToRoleFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				addUserToRoleFrame.update(userService.findByRole(role.getId()));
				addUserToRoleFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(addUserToRoleFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
