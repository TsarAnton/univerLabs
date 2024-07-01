package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Author;
import ioc.IocContainer;
import view.AuthorEditFrame;
import view.AuthorsListFrame;

public class EditAuthorButtonClick implements ActionListener {
	private AuthorsListFrame authorsListFrame;
	private IocContainer container;

	public EditAuthorButtonClick(AuthorsListFrame authorsListFrame, IocContainer container) {
		this.authorsListFrame = authorsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Author author = authorsListFrame.getSelectedAuthor();
		if(author != null) {
			new AuthorEditFrame(authorsListFrame, author, container);
		} else {
			JOptionPane.showMessageDialog(authorsListFrame, "В таблице не выбран ни один автор", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
