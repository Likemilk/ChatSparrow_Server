package client.chat.room;

import java.util.Vector; 

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6028052251505634886L;

	
	public CustomTableModel(String[][] row,String[] col){
		super(row,col);
	}
	
	
	@Override
	public void addColumn(Object columnName, Object[] columnData) {
		// TODO Auto-generated method stub
		super.addColumn(columnName, columnData);
	}

	@Override
	public void addColumn(Object columnName, Vector columnData) {
		// TODO Auto-generated method stub
		super.addColumn(columnName, columnData);
	}

	@Override
	public void addColumn(Object columnName) {
		// TODO Auto-generated method stub
		super.addColumn(columnName);
	}

	@Override
	public void addRow(Object[] rowData) {
		// TODO Auto-generated method stub
		super.addRow(rowData);
	}

	@Override
	public void addRow(Vector rowData) {
		// TODO Auto-generated method stub
		super.addRow(rowData);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return super.getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return super.getColumnName(column);
	}

	@Override
	public Vector getDataVector() {
		// TODO Auto-generated method stub
		return super.getDataVector();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return super.getRowCount();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return super.getValueAt(row, column);
	}

	@Override
	public void insertRow(int row, Object[] rowData) {
		// TODO Auto-generated method stub
		super.insertRow(row, rowData);
	}

	@Override
	public void insertRow(int row, Vector rowData) {
		// TODO Auto-generated method stub
		super.insertRow(row, rowData);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveRow(int start, int end, int to) {
		// TODO Auto-generated method stub
		super.moveRow(start, end, to);
	}

	@Override
	public void newDataAvailable(TableModelEvent event) {
		// TODO Auto-generated method stub
		super.newDataAvailable(event);
	}

	@Override
	public void newRowsAdded(TableModelEvent e) {
		// TODO Auto-generated method stub
		super.newRowsAdded(e);
	}

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		super.removeRow(row);
	}

	@Override
	public void rowsRemoved(TableModelEvent event) {
		// TODO Auto-generated method stub
		super.rowsRemoved(event);
	}

	@Override
	public void setColumnCount(int columnCount) {
		// TODO Auto-generated method stub
		super.setColumnCount(columnCount);
	}

	@Override
	public void setColumnIdentifiers(Object[] newIdentifiers) {
		// TODO Auto-generated method stub
		super.setColumnIdentifiers(newIdentifiers);
	}

	@Override
	public void setColumnIdentifiers(Vector columnIdentifiers) {
		// TODO Auto-generated method stub
		super.setColumnIdentifiers(columnIdentifiers);
	}

	@Override
	public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers) {
		// TODO Auto-generated method stub
		super.setDataVector(dataVector, columnIdentifiers);
	}

	@Override
	public void setDataVector(Vector dataVector, Vector columnIdentifiers) {
		// TODO Auto-generated method stub
		super.setDataVector(dataVector, columnIdentifiers);
	}

	@Override
	public void setNumRows(int rowCount) {
		// TODO Auto-generated method stub
		super.setNumRows(rowCount);
	}

	@Override
	public void setRowCount(int rowCount) {
		// TODO Auto-generated method stub
		super.setRowCount(rowCount);
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	}

	/**
	 * 
	 */
	

}
