package view.user_vs_role;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.user_vs_role.SaveUserToRoleButtonClick;
import domain.Role;
import domain.User;
import ioc.IocContainer;

public class AddUserToRoleFrame extends JDialog {
	private Role role;
	private List<User> users;
	private JComboBox<String> userNameBox;

	private AddUserToRoleFrame(JFrame owner, List<User> users, Role role, String title, IocContainer container) {
		super(owner, title);
		this.users = users;
		this.role = role;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 275);
		setResizable(false);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		JLabel userLabel = new JLabel("Пользователь:");
		userLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(userLabel, constraints);
		add(userLabel);

		//User usersArray[] = (User[]) users.toArray();
		String usersArray[] = new String[users.size()];
		for(int i = 0; i < users.size(); i++) {
			usersArray[i] = users.get(i).getId() + "; " + users.get(i).toString();
		}
		userNameBox = new JComboBox<>(usersArray);
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(userNameBox, constraints);
		add(userNameBox);

		JButton saveButton = new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveUserToRoleButtonClick(this, container));
		add(saveButton);

		setVisible(true);
	}

	public AddUserToRoleFrame(JFrame owner, List<User> users, Role role, IocContainer container) {
		this(owner, users, role, "Добавление пользователя", container);
	}

	public Long getSelectedUserId() {
		String selectedUser = (String) userNameBox.getSelectedItem();
		if(selectedUser.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Пользователь» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		String buff[] = selectedUser.split(";");
		Long user_id = Long.parseLong(buff[0]);
		return user_id;
	}

	public Role getRole() {
		return role;
	}

	public void update(List<User> users) {
		((RoleUsersFrame)getOwner()).setUsers(users);
	}
}

