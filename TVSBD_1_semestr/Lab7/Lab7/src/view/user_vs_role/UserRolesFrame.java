package view.user_vs_role;

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

import controller.user_vs_role.AddRoleToUserButtonClick;
import controller.user_vs_role.DeleteRoleFromUserButtonClick;
import domain.Role;
import domain.User;
import ioc.IocContainer;
import view.role.RolesListTableModel;

public class UserRolesFrame extends JFrame {
	private RolesListTableModel model;
	private JTable roleslistTable;
	private User user;

	public UserRolesFrame(IocContainer container) throws HeadlessException {
		super("Список ролей");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new RolesListTableModel();
		roleslistTable = new JTable(model);
		roleslistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(roleslistTable);
		add(jScrollPane);
		FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
		JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
		JButton adduserToRoleButton = new JButton("Добавить");
		adduserToRoleButton.setPreferredSize(new Dimension(100, 30));
		adduserToRoleButton.addActionListener(new AddRoleToUserButtonClick(this, container));
		buttonsPanel.add(adduserToRoleButton);
		JButton deleteUserRoleButton = new JButton("Удалить");
		deleteUserRoleButton.setPreferredSize(new Dimension(100, 30));
		deleteUserRoleButton.addActionListener(new DeleteRoleFromUserButtonClick(this, container));
		buttonsPanel.add(deleteUserRoleButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setRoles(List<Role> roles) {
		model.setRoles(roles);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getSelectedRole() {
		int index = roleslistTable.getSelectedRow();
		if(index != -1) {
			return model.getRole(index);
		}
		return null;
	}

	public User getUser() {
		return user;
	}
}
