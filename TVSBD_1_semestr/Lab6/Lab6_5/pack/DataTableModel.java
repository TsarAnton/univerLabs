package pack;

import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DataTableModel implements TableModel {
    private List<Record> data;
    public DataTableModel(List<Record> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int index) {
        switch(index) {
            case 0: return "ID";
            case 1: return "Номер компьютера";
            case 2: return "Ф.И.О.";
            case 3: return "Дата";
            case 4: return "Время начала";
            case 5: return "Время завершения";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int index) {
        switch(index) {
            case 0: return Integer.class;
            case 1: return Integer.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            case 5: return String.class;
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0: return data.get(rowIndex).id;
            case 1: return data.get(rowIndex).computerNumber;
            case 2: return data.get(rowIndex).name;
            case 3: return Functions.dateFormat.format(data.get(rowIndex).date);
            case 4: return Functions.timeFormat.format(data.get(rowIndex).startTime); 
            case 5: return Functions.timeFormat.format(data.get(rowIndex).endTime);
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0:
                data.get(rowIndex).id = (int) value;
                break;
            case 1: 
                data.get(rowIndex).computerNumber = (int) value;
                break;
            case 2:
                data.get(rowIndex).name = (String) value;
                break;
            case 3: 
                data.get(rowIndex).date = (Date) value;
                break;
            case 4:
                data.get(rowIndex).startTime = (Date) value;
                break;
            case 5:
                data.get(rowIndex).endTime = (Date) value;
                break;
        }
    }

    @Override
    public boolean isCellEditable (int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0: return false;
            case 1: return true;
            case 2: return true;
            case 3: return true;
            case 4: return true;
            case 5: return true;
        }
        return false;
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {}

    @Override
    public void removeTableModelListener(TableModelListener listener) {}

    public List<Record> getData() {
        return data;
    }

    public boolean containsId(int id1) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).id == id1) {
                return true;
            }
        }
        return false;
    }

    public int getById(int id1) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).id == id1) {
                return i;
            }
        }
        return -1;
    }
}
