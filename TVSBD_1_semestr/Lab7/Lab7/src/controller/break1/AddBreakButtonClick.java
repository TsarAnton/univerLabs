package controller.break1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.break1.BreakEditFrame;
import view.break1.BreaksListFrame;

public class AddBreakButtonClick implements ActionListener {
	private BreaksListFrame breaksListFrame;
	private IocContainer container;

	public AddBreakButtonClick(BreaksListFrame breaksListFrame, IocContainer container) {
		this.breaksListFrame = breaksListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new BreakEditFrame(breaksListFrame, container);
	}
}
