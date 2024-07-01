package view.break1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.break1.DeleteBreakButtonClick;
import controller.break1.SaveBreakButtonClick;
import domain.Break;
import ioc.IocContainer;

public class BreakEditFrame extends JDialog {
	private Break break1;
	private JTextField repair_notesTextField;
	private JTextField break_notesTextField;

	private BreakEditFrame(JFrame owner, Break break1, String title, IocContainer container) {
		super(owner, title);
		this.break1 = break1;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 275);
		setResizable(false);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		JLabel break_notesLabel = new JLabel("Описание поломки:");
		break_notesLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(break_notesLabel, constraints);
		add(break_notesLabel);

		break_notesTextField = new JTextField(break1.getBreak_notes());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(break_notesTextField, constraints);
		add(break_notesTextField);

		JLabel repair_notesLabel = new JLabel("Описание ремонта:");
		repair_notesLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(repair_notesLabel, constraints);
		add(repair_notesLabel);

		repair_notesTextField = new JTextField(break1.getRepair_notes());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(repair_notesTextField, constraints);
		add(repair_notesTextField);

		JButton saveButton = new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveBreakButtonClick(this, container));
		add(saveButton);

		if(break1.getId() != null) {
			JButton deleteButton = new JButton("Удалить");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteBreakButtonClick(this, container));
			add(deleteButton);
		} else {
			JLabel emptyLabel = new JLabel();
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(emptyLabel, constraints);
			add(emptyLabel);
		}

		setVisible(true);
	}

	public BreakEditFrame(JFrame owner, Break break1, IocContainer container) {
		this(owner, break1, String.format("Редактирование поломки %s", break1.getBreak_notes()), container);
	}

	public BreakEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Break(), "Добавление поломки", container);
	}

	public Break getBreak() {
		String repair_notes = repair_notesTextField.getText();
		if(repair_notes.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Описание ремонта» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		break1.setRepair_notes(repair_notes);
		String break_notes = break_notesTextField.getText();
		if(break_notes.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Описание поломки» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		break1.setBreak_notes(break_notes);
		return break1;
	}

	public Long getBreakId() {
		return break1 != null ? break1.getId() : null;
	}

	public void update(List<Break> breaks) {
		((BreaksListFrame)getOwner()).setBreaks(breaks);
	}
}
