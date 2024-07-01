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

import controller.user_vs_role.AddUserToRoleButtonClick;
import controller.user_vs_role.DeleteUserFromRoleButtonClick;
import domain.Role;
import domain.User;
import ioc.IocContainer;
import view.user.UsersListTableModel;

public class RoleUsersFrame extends JFrame {
	private UsersListTableModel model;
	private JTable usersListTable;
	private Role role;

	public RoleUsersFrame(IocContainer container) throws HeadlessException {
		super("Список пользователей");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new UsersListTableModel();
		usersListTable = new JTable(model);
		usersListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(usersListTable);
		add(jScrollPane);
		FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
		JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
		JButton adduserToRoleButton = new JButton("Добавить");
		adduserToRoleButton.setPreferredSize(new Dimension(100, 30));
		adduserToRoleButton.addActionListener(new AddUserToRoleButtonClick(this, container));
		buttonsPanel.add(adduserToRoleButton);
		JButton deleteUserRoleButton = new JButton("Удалить");
		deleteUserRoleButton.setPreferredSize(new Dimension(100, 30));
		deleteUserRoleButton.addActionListener(new DeleteUserFromRoleButtonClick(this, container));
		buttonsPanel.add(deleteUserRoleButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setUsers(List<User> users) {
		model.setUsers(users);
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getSelectedUser() {
		int index = usersListTable.getSelectedRow();
		if(index != -1) {
			return model.getUser(index);
		}
		return null;
	}

	public Role getRole() {
		return role;
	}
}
