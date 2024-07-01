package controller.role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.role.RoleEditFrame;
import view.role.RolesListFrame;

public class AddRoleButtonClick implements ActionListener {
	private RolesListFrame rolesListFrame;
	private IocContainer container;

	public AddRoleButtonClick(RolesListFrame rolesListFrame, IocContainer container) {
		this.rolesListFrame = rolesListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new RoleEditFrame(rolesListFrame, container);
	}
}
