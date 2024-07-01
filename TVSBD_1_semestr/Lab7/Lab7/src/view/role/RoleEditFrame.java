package view.role;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.role.DeleteRoleButtonClick;
import controller.role.SaveRoleButtonClick;
import domain.Role;
import ioc.IocContainer;

public class RoleEditFrame extends JDialog {
	private Role role;
	private JTextField role_nameTextField;

	private RoleEditFrame(JFrame owner, Role role, String title, IocContainer container) {
		super(owner, title);
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

		JLabel role_nameLabel = new JLabel("Название роли:");
		role_nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(role_nameLabel, constraints);
		add(role_nameLabel);

		role_nameTextField = new JTextField(role.getRole_name());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(role_nameTextField, constraints);
		add(role_nameTextField);

		JButton saveButton = new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveRoleButtonClick(this, container));
		add(saveButton);

		if(role.getId() != null) {
			JButton deleteButton = new JButton("Удалить");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteRoleButtonClick(this, container));
			add(deleteButton);
		} else {
			JLabel emptyLabel = new JLabel();
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(emptyLabel, constraints);
			add(emptyLabel);
		}

		setVisible(true);
	}

	public RoleEditFrame(JFrame owner, Role role, IocContainer container) {
		this(owner, role, String.format("Редактирование роли %s", role.getRole_name()), container);
	}

	public RoleEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Role(), "Добавление роли", container);
	}

	public Role getRole() {
		String role_name = role_nameTextField.getText();
		if(role_name.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Название роли» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		role.setRole_name(role_name);
		return role;
	}

	public Long getRoleId() {
		return role != null ? role.getId() : null;
	}

	public void update(List<Role> roles) {
		((RolesListFrame)getOwner()).setRoles(roles);
	}
}
