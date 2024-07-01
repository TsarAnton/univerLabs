package controller.role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Role;
import ioc.IocContainer;
import view.role.RoleEditFrame;
import view.role.RolesListFrame;

public class EditRoleButtonClick implements ActionListener {
	private RolesListFrame rolesListFrame;
	private IocContainer container;

	public EditRoleButtonClick(RolesListFrame rolesListFrame, IocContainer container) {
		this.rolesListFrame = rolesListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Role role = rolesListFrame.getSelectedRole();
		if(role != null) {
			new RoleEditFrame(rolesListFrame, role, container);
		} else {
			JOptionPane.showMessageDialog(rolesListFrame, "В таблице не выбрана ни одна роль", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
