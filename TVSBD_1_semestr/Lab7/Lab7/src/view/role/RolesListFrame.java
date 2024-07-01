package view.role;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.role.AddRoleButtonClick;
import controller.role.EditRoleButtonClick;
import controller.user_vs_role.StartRoleUsersButtonClick;
import domain.Role;
import ioc.IocContainer;

public class RolesListFrame extends JFrame {
	private RolesListTableModel model;
	private JTable rolesListTable;

	public RolesListFrame(IocContainer container) throws HeadlessException {
		super("Список ролей");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new RolesListTableModel();
		rolesListTable = new JTable(model);
		rolesListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(rolesListTable);
		add(jScrollPane);
		FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
		JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
		JButton addRoleButton = new JButton("Добавить");
		addRoleButton.setPreferredSize(new Dimension(100, 30));
		addRoleButton.addActionListener(new AddRoleButtonClick(this, container));
		buttonsPanel.add(addRoleButton);
		JButton editRoleButton = new JButton("Изменить");
		editRoleButton.setPreferredSize(new Dimension(100, 30));
		editRoleButton.addActionListener(new EditRoleButtonClick(this, container));
		buttonsPanel.add(editRoleButton);
		JButton usersRoleButton = new JButton("Показать пользователей");
		usersRoleButton.setPreferredSize(new Dimension(200, 30));
		usersRoleButton.addActionListener(new StartRoleUsersButtonClick(this, container));//сюда кнопочку правильную
		buttonsPanel.add(usersRoleButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setRoles(List<Role> roles) {
		model.setRoles(roles);
	}

	public Role getSelectedRole() {
		int index = rolesListTable.getSelectedRow();
		if(index != -1) {
			return model.getRole(index);
		}
		return null;
	}
}
