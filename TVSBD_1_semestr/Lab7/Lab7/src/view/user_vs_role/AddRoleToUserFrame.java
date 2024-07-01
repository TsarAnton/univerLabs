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

import controller.user_vs_role.SaveRoleToUserButtonClick;
import domain.Role;
import domain.User;
import ioc.IocContainer;

public class AddRoleToUserFrame extends JDialog {
	private User user;
	private List<Role> roles;
	private JComboBox<String> roleNameBox;

	private AddRoleToUserFrame(JFrame owner, List<Role> roles, User user, String title, IocContainer container) {
		super(owner, title);
		this.user = user;
		this.roles = roles;
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

		JLabel roleLabel = new JLabel("Роли:");
		roleLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(roleLabel, constraints);
		add(roleLabel);

		//User usersArray[] = (User[]) users.toArray();
		String rolesArray[] = new String[roles.size()];
		for(int i = 0; i < roles.size(); i++) {
			rolesArray[i] = roles.get(i).getId() + "; " + roles.get(i).getRole_name();
		}
		roleNameBox = new JComboBox<>(rolesArray);
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(roleNameBox, constraints);
		add(roleNameBox);

		JButton saveButton = new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveRoleToUserButtonClick(this, container));
		add(saveButton);

		setVisible(true);
	}

	public AddRoleToUserFrame(JFrame owner, List<Role> roles, User user, IocContainer container) {
		this(owner, roles, user, "Добавление роли", container);
	}

	public Long getSelectedRoleId() {
		String selectedRole = (String) roleNameBox.getSelectedItem();
		if(selectedRole.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Роль» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		String buff[] = selectedRole.split(";");
		Long role_id = Long.parseLong(buff[0]);
		return role_id;
	}

	public User getUser() {
		return user;
	}

	public void update(List<Role> roles) {
		((UserRolesFrame)getOwner()).setRoles(roles);
	}
}
