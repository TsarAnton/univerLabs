package controller.request2_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import domain.Request2_1;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import view.request2_1.Request2_1ListFrame;
import view.request2_1.Request2_1InitFrame;

public class FindButtonClick implements ActionListener {
	private Request2_1InitFrame initFrame;
	private IocContainer container;

	public FindButtonClick(Request2_1InitFrame initFrame, IocContainer container) {
		this.initFrame = initFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Long driver_id = initFrame.getSelectedDriverId();
		List<Request2_1> numbers = new ArrayList<>();
		try {
			numbers = container.getRequest2_1Service().findByDriver(driver_id);
			Request2_1ListFrame numberFrame = new Request2_1ListFrame(container);
			numberFrame.setRequest2_1(numbers);
			initFrame.dispose();
		} catch (ServiceException e1) {
			System.out.println("oshibka1");
			e1.printStackTrace();
		} catch (ContainerException e1) {
			e1.printStackTrace();
		}
	}
}
