package view.user;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.User;

public class UsersListTableModel implements TableModel {
	private List<User> users = new ArrayList<>();

	public void setUsers(List<User> users) {
		this.users = users;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public User getUser(int index) {
		return users.get(index);
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return UserFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return UserFiled.values()[columnIndex].getColumnName();
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
		User user = users.get(rowIndex);
		return UserFiled.values()[columnIndex].getValue(user);
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

	private static enum UserFiled {
		ID("ID") {
			@Override
			public String getValue(User user) {
				return String.format("%03d", user.getId());
			}
		},
		LOGIN("Логин") {
			@Override
			public String getValue(User user) {
				return user.getLogin();
			}
		},
		PASSWORD("Пароль") {
			@Override
			public String getValue(User user) {
				return user.getPassword();
			}
		};

		private final String columnName;

		private UserFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(User user);
	}
}
