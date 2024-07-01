package controller.role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Role;
import ioc.ContainerException;
import ioc.IocContainer;
import service.role.RoleService;
import service.ServiceException;
import view.role.RoleEditFrame;

public class SaveRoleButtonClick implements ActionListener {
	private RoleEditFrame roleEditFrame;
	private IocContainer container;

	public SaveRoleButtonClick(RoleEditFrame roleEditFrame, IocContainer container) {
		this.roleEditFrame = roleEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Role role = roleEditFrame.getRole();
		if(role != null) {
			try {
				RoleService service = container.getRoleService();
				service.save(role);
				JOptionPane.showMessageDialog(roleEditFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				roleEditFrame.update(service.findAll());
				roleEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(roleEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
