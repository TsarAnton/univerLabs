package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Author;

public class AuthorsListTableModel implements TableModel {
	private List<Author> authors = new ArrayList<>();

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Author getAuthor(int index) {
		return authors.get(index);
	}

	@Override
	public int getRowCount() {
		return authors.size();
	}

	@Override
	public int getColumnCount() {
		return AuthorFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return AuthorFiled.values()[columnIndex].getColumnName();
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
		Author author = authors.get(rowIndex);
		return AuthorFiled.values()[columnIndex].getValue(author);
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

	private static enum AuthorFiled {
		ID("ID") {
			@Override
			public String getValue(Author author) {
				return String.format("%03d", author.getId());
			}
		},
		NAME("Имя") {
			@Override
			public String getValue(Author author) {
				return author.getName();
			}
		},
		SURNAME("Фамилия") {
			@Override
			public String getValue(Author author) {
				return author.getSurname();
			}
		};

		private final String columnName;

		private AuthorFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Author author);
	}
}
