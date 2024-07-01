package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Author;
import ioc.ContainerException;
import ioc.IocContainer;
import service.AuthorService;
import service.ServiceException;
import view.AuthorEditFrame;

public class SaveAuthorButtonClick implements ActionListener {
	private AuthorEditFrame authorEditFrame;
	private IocContainer container;

	public SaveAuthorButtonClick(AuthorEditFrame authorEditFrame, IocContainer container) {
		this.authorEditFrame = authorEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Author author = authorEditFrame.getAuthor();
		if(author != null) {
			try {
				AuthorService service = container.getAuthorService();
				service.save(author);
				JOptionPane.showMessageDialog(authorEditFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				authorEditFrame.update(service.findAll());
				authorEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(authorEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
