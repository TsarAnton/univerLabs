package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.AuthorService;
import service.ServiceException;
import view.AuthorEditFrame;

public class DeleteAuthorButtonClick implements ActionListener {
	private AuthorEditFrame authorEditFrame;
	private IocContainer container;

	public DeleteAuthorButtonClick(AuthorEditFrame authorEditFrame, IocContainer container) {
		this.authorEditFrame = authorEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = authorEditFrame.getAuthorId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(authorEditFrame, "Вы действительно хотите удалить запись", "Подтверждение действия", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					AuthorService service = container.getAuthorService();
					service.delete(id);
					JOptionPane.showMessageDialog(authorEditFrame, "Данные успешно удалены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
					authorEditFrame.update(service.findAll());
					authorEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(authorEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
