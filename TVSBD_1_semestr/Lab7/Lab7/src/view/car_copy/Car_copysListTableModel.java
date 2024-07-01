package view.car_copy;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Car_copyGetAll;

public class Car_copysListTableModel implements TableModel {
	private List<Car_copyGetAll> car_copys = new ArrayList<>();

	public void setCar_copys(List<Car_copyGetAll> car_copys) {
		this.car_copys = car_copys;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Car_copyGetAll getCar_copy(int index) {
		return car_copys.get(index);
	}

	@Override
	public int getRowCount() {
		return car_copys.size();
	}

	@Override
	public int getColumnCount() {
		return Car_copyFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return Car_copyFiled.values()[columnIndex].getColumnName();
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
		Car_copyGetAll car_copy = car_copys.get(rowIndex);
		return Car_copyFiled.values()[columnIndex].getValue(car_copy);
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

	private static enum Car_copyFiled {
		ID("ID") {
			@Override
			public String getValue(Car_copyGetAll car_copy) {
				return String.format("%03d", car_copy.getId());
			}
		},
		MARK("Марка") {
			@Override
			public String getValue(Car_copyGetAll car_copy) {
				return car_copy.getMark();
			}
		},
		MODEL("Модель") {
			@Override
			public String getValue(Car_copyGetAll car_copy) {
				return car_copy.getModel();
			}
		},
		STATE_NUM("Госномер") {
			@Override
			public String getValue(Car_copyGetAll car_copy) {
				return car_copy.getState_num();
			}
		};

		private final String columnName;

		private Car_copyFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Car_copyGetAll car_copy);
	}
}
