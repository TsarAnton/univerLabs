package view.user;

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

import controller.user.AddUserButtonClick;
import controller.user.EditUserButtonClick;
import controller.user_vs_role.StartUserRolesButtonClick;
import domain.User;
import ioc.IocContainer;

public class UsersListFrame extends JFrame {
	private UsersListTableModel model;
	private JTable usersListTable;

	public UsersListFrame(IocContainer container) throws HeadlessException {
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
		JButton addUserButton = new JButton("Добавить");
		addUserButton.setPreferredSize(new Dimension(100, 30));
		addUserButton.addActionListener(new AddUserButtonClick(this, container));
		buttonsPanel.add(addUserButton);
		JButton editUserButton = new JButton("Изменить");
		editUserButton.setPreferredSize(new Dimension(100, 30));
		editUserButton.addActionListener(new EditUserButtonClick(this, container));
		buttonsPanel.add(editUserButton);
		JButton rolesRoleButton = new JButton("Показать роли");
		rolesRoleButton.setPreferredSize(new Dimension(200, 30));
		rolesRoleButton.addActionListener(new StartUserRolesButtonClick(this, container));//сюда кнопочку правильную
		buttonsPanel.add(rolesRoleButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setUsers(List<User> users) {
		model.setUsers(users);
	}

	public User getSelectedUser() {
		int index = usersListTable.getSelectedRow();
		if(index != -1) {
			return model.getUser(index);
		}
		return null;
	}
}
