package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.AuthorEditFrame;
import view.AuthorsListFrame;

public class AddAuthorButtonClick implements ActionListener {
	private AuthorsListFrame authorsListFrame;
	private IocContainer container;

	public AddAuthorButtonClick(AuthorsListFrame authorsListFrame, IocContainer container) {
		this.authorsListFrame = authorsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new AuthorEditFrame(authorsListFrame, container);
	}
}
