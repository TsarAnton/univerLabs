package controller.user_vs_role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import service.role.RoleService;
import view.user_vs_role.UserRolesFrame;

public class DeleteRoleFromUserButtonClick implements ActionListener {
	private UserRolesFrame userRolesFrame;
	private IocContainer container;

	public DeleteRoleFromUserButtonClick(UserRolesFrame userRolesFrame, IocContainer container) {
		this.userRolesFrame = userRolesFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long role_id = userRolesFrame.getSelectedRole().getId();
        Long user_id = userRolesFrame.getUser().getId();
		if(user_id != null && role_id != null) {
			if(JOptionPane.showConfirmDialog(userRolesFrame, "Вы действительно хотите удалить запись", "Подтверждение действия", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					RoleService service = container.getRoleService();
					service.deleteUserRole(user_id, role_id);
					JOptionPane.showMessageDialog(userRolesFrame, "Данные успешно удалены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
					userRolesFrame.setRoles(container.getRoleService().findByUser(user_id));
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(userRolesFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
