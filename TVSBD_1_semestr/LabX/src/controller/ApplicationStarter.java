package controller;

import java.util.List;

import javax.swing.JOptionPane;

import domain.Author;
import ioc.ContainerException;
import ioc.IocContainer;
import service.AuthorService;
import service.ServiceException;
import view.AuthorsListFrame;

public class ApplicationStarter {
	private IocContainer container;

	public ApplicationStarter(IocContainer container) {
		this.container = container;
	}

	public void start() {
		try {
			AuthorService service = container.getAuthorService();
			List<Author> authors = service.findAll();
			AuthorsListFrame authorsListFrame = new AuthorsListFrame(container);
			authorsListFrame.setAuthors(authors);
		} catch (ContainerException | ServiceException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ошибка запуска приложения", "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}
}
