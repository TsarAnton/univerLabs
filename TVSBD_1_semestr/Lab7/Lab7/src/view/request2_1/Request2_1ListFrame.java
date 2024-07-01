package view.request2_1;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Request2_1;
import ioc.IocContainer;

public class Request2_1ListFrame extends JFrame {
	private Request2_1ListTableModel model;
	private JTable request2_1ListTable;

	public Request2_1ListFrame(IocContainer container) throws HeadlessException {
		super("Список номеров телефонов");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Request2_1ListTableModel();
		request2_1ListTable = new JTable(model);
		request2_1ListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(request2_1ListTable);
		add(jScrollPane);
		setVisible(true);
	}

	public void setRequest2_1(List<Request2_1> request2_1) {
		model.setRequest2_1(request2_1);
	}
}
