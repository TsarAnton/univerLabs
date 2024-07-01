package controller.role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.role.RoleService;
import service.ServiceException;
import view.role.RoleEditFrame;

public class DeleteRoleButtonClick implements ActionListener {
	private RoleEditFrame roleEditFrame;
	private IocContainer container;

	public DeleteRoleButtonClick(RoleEditFrame roleEditFrame, IocContainer container) {
		this.roleEditFrame = roleEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = roleEditFrame.getRoleId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(roleEditFrame, "Вы действительно хотите удалить запись", "Подтверждение действия", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					RoleService service = container.getRoleService();
					service.delete(id);
					JOptionPane.showMessageDialog(roleEditFrame, "Данные успешно удалены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
					roleEditFrame.update(service.findAll());
					roleEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(roleEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
