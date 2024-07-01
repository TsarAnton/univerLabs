package view.car;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Car;

public class CarsListTableModel implements TableModel {
	private List<Car> cars = new ArrayList<>();

	public void setCars(List<Car> cars) {
		this.cars = cars;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Car getCar(int index) {
		return cars.get(index);
	}

	@Override
	public int getRowCount() {
		return cars.size();
	}

	@Override
	public int getColumnCount() {
		return CarFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return CarFiled.values()[columnIndex].getColumnName();
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
		Car car = cars.get(rowIndex);
		return CarFiled.values()[columnIndex].getValue(car);
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

	private static enum CarFiled {
		ID("ID") {
			@Override
			public String getValue(Car car) {
				return String.format("%03d", car.getId());
			}
		},
		MARK("Марка") {
			@Override
			public String getValue(Car car) {
				return car.getMark();
			}
		},
		MODEL("Модель") {
			@Override
			public String getValue(Car car) {
				return car.getModel();
			}
		};

		private final String columnName;

		private CarFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Car car);
	}
}
