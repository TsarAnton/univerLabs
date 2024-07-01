package view;

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

import controller.AddAuthorButtonClick;
import controller.EditAuthorButtonClick;
import domain.Author;
import ioc.IocContainer;

public class AuthorsListFrame extends JFrame {
	private AuthorsListTableModel model;
	private JTable authorsListTable;

	public AuthorsListFrame(IocContainer container) throws HeadlessException {
		super("Список авторов");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		model = new AuthorsListTableModel();
		authorsListTable = new JTable(model);
		authorsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(authorsListTable);
		add(jScrollPane);
		FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
		JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
		JButton addAuthorButton = new JButton("Добавить");
		addAuthorButton.setPreferredSize(new Dimension(100, 30));
		addAuthorButton.addActionListener(new AddAuthorButtonClick(this, container));
		buttonsPanel.add(addAuthorButton);
		JButton editAuthorButton = new JButton("Изменить");
		editAuthorButton.setPreferredSize(new Dimension(100, 30));
		editAuthorButton.addActionListener(new EditAuthorButtonClick(this, container));
		buttonsPanel.add(editAuthorButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setAuthors(List<Author> authors) {
		model.setAuthors(authors);
	}

	public Author getSelectedAuthor() {
		int index = authorsListTable.getSelectedRow();
		if(index != -1) {
			return model.getAuthor(index);
		}
		return null;
	}
}
