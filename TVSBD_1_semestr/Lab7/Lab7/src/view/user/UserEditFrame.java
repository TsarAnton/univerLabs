package view.user;

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

import controller.user.DeleteUserButtonClick;
import controller.user.SaveUserButtonClick;
import domain.User;
import ioc.IocContainer;

public class UserEditFrame extends JDialog {
	private User user;
	private JTextField loginTextField;
	private JTextField passwordTextField;

	private UserEditFrame(JFrame owner, User user, String title, IocContainer container) {
		super(owner, title);
		this.user = user;
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

		JLabel passwordLabel = new JLabel("Пароль:");
		passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(passwordLabel, constraints);
		add(passwordLabel);

		passwordTextField = new JTextField(user.getPassword());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(passwordTextField, constraints);
		add(passwordTextField);

		JLabel loginLabel = new JLabel("Логин:");
		loginLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(loginLabel, constraints);
		add(loginLabel);

		loginTextField = new JTextField(user.getLogin());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(loginTextField, constraints);
		add(loginTextField);

		JButton saveButton = new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveUserButtonClick(this, container));
		add(saveButton);

		if(user.getId() != null) {
			JButton deleteButton = new JButton("Удалить");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteUserButtonClick(this, container));
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

	public UserEditFrame(JFrame owner, User user, IocContainer container) {
		this(owner, user, String.format("Редактирование пользователя %s", user.getLogin()), container);
	}

	public UserEditFrame(JFrame owner, IocContainer container) {
		this(owner, new User(), "Добавление пользователя", container);
	}

	public User getUser() {
		String login = loginTextField.getText();
		if(login.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Логин» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		user.setLogin(login);
		String password = passwordTextField.getText();
		if(password.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Пароль» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		user.setPassword(password);
		return user;
	}

	public Long getUserId() {
		return user != null ? user.getId() : null;
	}

	public void update(List<User> users) {
		((UsersListFrame)getOwner()).setUsers(users);
	}
}
