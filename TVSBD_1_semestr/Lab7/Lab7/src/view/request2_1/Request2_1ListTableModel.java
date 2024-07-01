package view.request2_1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Request2_1;

public class Request2_1ListTableModel implements TableModel {
	private List<Request2_1> numbers = new ArrayList<>();

	public void setRequest2_1(List<Request2_1> numbers) {
		this.numbers = numbers;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Request2_1 getRequest2_1(int index) {
		return numbers.get(index);
	}

	@Override
	public int getRowCount() {
		return numbers.size();
	}

	@Override
	public int getColumnCount() {
		return Request2_1Filed.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return Request2_1Filed.values()[columnIndex].getColumnName();
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
		Request2_1 request2_1 = numbers.get(rowIndex);
		return Request2_1Filed.values()[columnIndex].getValue(request2_1);
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

	private static enum Request2_1Filed {
		NUMBER("Номер телефона") {
			@Override
			public String getValue(Request2_1 request2_1) {
				return request2_1.getNumber();
			}
		};

		private final String columnName;

		private Request2_1Filed(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Request2_1 request2_1);
	}
}
