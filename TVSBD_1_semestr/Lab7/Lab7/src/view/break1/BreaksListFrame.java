package view.break1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.break1.AddBreakButtonClick;
import controller.break1.EditBreakButtonClick;
import domain.Break;
import ioc.IocContainer;

public class BreaksListFrame extends JFrame {
	private BreaksListTableModel model;
	private JTable breaksListTable;

	public BreaksListFrame(IocContainer container) throws HeadlessException {
		super("Список поломок");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new BreaksListTableModel();
		breaksListTable = new JTable(model);
		breaksListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(breaksListTable);
		add(jScrollPane);
		FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
		JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
		JButton addBreakButton = new JButton("Добавить");
		addBreakButton.setPreferredSize(new Dimension(100, 30));
		addBreakButton.addActionListener(new AddBreakButtonClick(this, container));
		buttonsPanel.add(addBreakButton);
		JButton editBreakButton = new JButton("Изменить");
		editBreakButton.setPreferredSize(new Dimension(100, 30));
		editBreakButton.addActionListener(new EditBreakButtonClick(this, container));
		buttonsPanel.add(editBreakButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setBreaks(List<Break> breaks) {
		model.setBreaks(breaks);
	}

	public Break getSelectedBreak() {
		int index = breaksListTable.getSelectedRow();
		if(index != -1) {
			return model.getBreak(index);
		}
		return null;
	}
}
