package controller.user_vs_role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import service.role.RoleService;
import view.user_vs_role.RoleUsersFrame;

public class DeleteUserFromRoleButtonClick implements ActionListener {
	private RoleUsersFrame roleUsersFrame;
	private IocContainer container;

	public DeleteUserFromRoleButtonClick(RoleUsersFrame roleUsersFrame, IocContainer container) {
		this.roleUsersFrame = roleUsersFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long user_id = roleUsersFrame.getSelectedUser().getId();
        Long role_id = roleUsersFrame.getRole().getId();
		if(user_id != null && role_id != null) {
			if(JOptionPane.showConfirmDialog(roleUsersFrame, "Вы действительно хотите удалить запись", "Подтверждение действия", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					RoleService service = container.getRoleService();
					service.deleteUserRole(user_id, role_id);
					JOptionPane.showMessageDialog(roleUsersFrame, "Данные успешно удалены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
					roleUsersFrame.setUsers(container.getUserService().findByRole(role_id));
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(roleUsersFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
