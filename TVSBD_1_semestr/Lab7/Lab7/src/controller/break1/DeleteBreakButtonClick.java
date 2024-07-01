package controller.break1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.break1.BreakService;
import service.ServiceException;
import view.break1.BreakEditFrame;

public class DeleteBreakButtonClick implements ActionListener {
	private BreakEditFrame breakEditFrame;
	private IocContainer container;

	public DeleteBreakButtonClick(BreakEditFrame breakEditFrame, IocContainer container) {
		this.breakEditFrame = breakEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = breakEditFrame.getBreakId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(breakEditFrame, "Вы действительно хотите удалить запись", "Подтверждение действия", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					BreakService service = container.getBreakService();
					service.delete(id);
					JOptionPane.showMessageDialog(breakEditFrame, "Данные успешно удалены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
					breakEditFrame.update(service.findAll());
					breakEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(breakEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
