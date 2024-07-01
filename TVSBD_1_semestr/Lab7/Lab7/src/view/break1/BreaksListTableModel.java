package view.break1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Break;

public class BreaksListTableModel implements TableModel {
	private List<Break> breaks = new ArrayList<>();

	public void setBreaks(List<Break> breaks) {
		this.breaks = breaks;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Break getBreak(int index) {
		return breaks.get(index);
	}

	@Override
	public int getRowCount() {
		return breaks.size();
	}

	@Override
	public int getColumnCount() {
		return BreakFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return BreakFiled.values()[columnIndex].getColumnName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Break break1 = breaks.get(rowIndex);
		return BreakFiled.values()[columnIndex].getValue(break1);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

	private List<TableModelListener> listeners = new ArrayList<>();

	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	private static enum BreakFiled {
		ID("ID") {
			@Override
			public String getValue(Break break1) {
				return String.format("%03d", break1.getId());
			}
		},
		BREAK_NOTES("Описание поломки") {
			@Override
			public String getValue(Break break1) {
				return break1.getBreak_notes();
			}
		},
		REPAIR_NOTES("Описание ремонта") {
			@Override
			public String getValue(Break break1) {
				return break1.getRepair_notes();
			}
		};

		private final String columnName;

		private BreakFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Break break1);
	}
}
