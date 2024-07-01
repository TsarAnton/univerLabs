package view.role;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Role;

public class RolesListTableModel implements TableModel {
	private List<Role> roles = new ArrayList<>();

	public void setRoles(List<Role> roles) {
		this.roles = roles;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Role getRole(int index) {
		return roles.get(index);
	}

	@Override
	public int getRowCount() {
		return roles.size();
	}

	@Override
	public int getColumnCount() {
		return RoleFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return RoleFiled.values()[columnIndex].getColumnName();
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
		Role role = roles.get(rowIndex);
		return RoleFiled.values()[columnIndex].getValue(role);
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

	private static enum RoleFiled {
		ID("ID") {
			@Override
			public String getValue(Role role) {
				return String.format("%03d", role.getId());
			}
		},
		ROLE_NAME("Название роли") {
			@Override
			public String getValue(Role role) {
				return role.getRole_name();
			}
		};

		private final String columnName;

		private RoleFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Role role);
	}
}
