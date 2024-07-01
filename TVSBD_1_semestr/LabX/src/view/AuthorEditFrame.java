package view;

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

import controller.DeleteAuthorButtonClick;
import controller.SaveAuthorButtonClick;
import domain.Author;
import ioc.IocContainer;

public class AuthorEditFrame extends JDialog {
	private Author author;
	private JTextField nameTextField;
	private JTextField surnameTextField;

	private AuthorEditFrame(JFrame owner, Author author, String title, IocContainer container) {
		super(owner, title);
		this.author = author;
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

		JLabel nameLabel = new JLabel("Имя:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(nameLabel, constraints);
		add(nameLabel);

		nameTextField = new JTextField(author.getName());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(nameTextField, constraints);
		add(nameTextField);

		JLabel surnameLabel = new JLabel("Фамилия:");
		surnameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(surnameLabel, constraints);
		add(surnameLabel);

		surnameTextField = new JTextField(author.getSurname());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(surnameTextField, constraints);
		add(surnameTextField);

		JButton saveButton = new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveAuthorButtonClick(this, container));
		add(saveButton);

		if(author.getId() != null) {
			JButton deleteButton = new JButton("Удалить");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteAuthorButtonClick(this, container));
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

	public AuthorEditFrame(JFrame owner, Author author, IocContainer container) {
		this(owner, author, String.format("Редактирование автора %s %s", author.getName(), author.getSurname()), container);
	}

	public AuthorEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Author(), "Добавление автора", container);
	}

	public Author getAuthor() {
		String name = nameTextField.getText();
		if(name.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Имя» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		author.setName(name);
		String surname = surnameTextField.getText();
		if(surname.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Фамилия» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		author.setSurname(surname);
		return author;
	}

	public Long getAuthorId() {
		return author != null ? author.getId() : null;
	}

	public void update(List<Author> authors) {
		((AuthorsListFrame)getOwner()).setAuthors(authors);
	}
}
