package controller.break1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Break;
import ioc.ContainerException;
import ioc.IocContainer;
import service.break1.BreakService;
import service.ServiceException;
import view.break1.BreakEditFrame;

public class SaveBreakButtonClick implements ActionListener {
	private BreakEditFrame breakEditFrame;
	private IocContainer container;

	public SaveBreakButtonClick(BreakEditFrame breakEditFrame, IocContainer container) {
		this.breakEditFrame = breakEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Break break1 = breakEditFrame.getBreak();
		if(break1 != null) {
			try {
				BreakService service = container.getBreakService();
				service.save(break1);
				JOptionPane.showMessageDialog(breakEditFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				breakEditFrame.update(service.findAll());
				breakEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(breakEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
