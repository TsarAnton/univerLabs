package controller.break1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Break;
import ioc.IocContainer;
import view.break1.BreakEditFrame;
import view.break1.BreaksListFrame;

public class EditBreakButtonClick implements ActionListener {
	private BreaksListFrame breaksListFrame;
	private IocContainer container;

	public EditBreakButtonClick(BreaksListFrame breaksListFrame, IocContainer container) {
		this.breaksListFrame = breaksListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Break break1 = breaksListFrame.getSelectedBreak();
		if(break1 != null) {
			new BreakEditFrame(breaksListFrame, break1, container);
		} else {
			JOptionPane.showMessageDialog(breaksListFrame, "В таблице не выбрана ни одна поломка", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}
}
