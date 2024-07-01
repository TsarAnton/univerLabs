package controller.user_vs_role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.User;
import ioc.ContainerException;
import ioc.IocContainer;
import service.role.RoleService;
import service.ServiceException;
import view.user_vs_role.AddRoleToUserFrame;

public class SaveRoleToUserButtonClick implements ActionListener {
	private AddRoleToUserFrame addRoleToUserFrame;
	private IocContainer container;

	public SaveRoleToUserButtonClick(AddRoleToUserFrame addRoleToUserFrame, IocContainer container) {
		this.addRoleToUserFrame = addRoleToUserFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long role_id = addRoleToUserFrame.getSelectedRoleId();
		User user = addRoleToUserFrame.getUser();
		if(role_id != null) {
			try {
				RoleService roleService = container.getRoleService();
				roleService.saveUserRole(user.getId(), role_id);
				JOptionPane.showMessageDialog(addRoleToUserFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				addRoleToUserFrame.update(roleService.findByUser(user.getId()));
				addRoleToUserFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(addRoleToUserFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
